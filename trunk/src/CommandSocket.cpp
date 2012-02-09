/** 
 * This code is written by Roozbeh Zabihollahi and with Marc Manthey 
 * partnership.
 *
 * Copyright (c) 2008 iPronto, All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of iPronto. ("Confidential Information").  You shall
 * not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into.
 *
 * IPRONTO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. IPRONTO SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

#include "Stdafx.h"
#include "OpenContinuePacket.h"
#include "VideoPacket.h"
#include "AudioPacket.h"
#include "CommandSocket.h"
#include "MainFrame.h"
#include "VideoFrame.h"
#include "AudioFrame.h"
#include "RefPacket.h"
#include "RateControlPacket.h"
#include "VideoCodec.h"
#include "AudioCodec.h"
#include "TextPacket.h"

CommandSocket::CommandSocket(MainFrame *mf) : m_seq(1), m_mainFrame(mf), 
	m_isWelcomeShowed(false), m_stopMutex(NULL), m_stopCondition(NULL)
{
	m_vcodec = new XVIDVideoCodec;
	m_acodec = new SpeexAudioCodec;
}

CommandSocket::~CommandSocket()
{
}

bool CommandSocket::Connect(std::string host_name, int port_number, std::string username,
#ifdef BIND_TO_PORT_FOR_LISTEN
							char *src_address, int bindPort
#else
							char *, int 
#endif
							)
{
	bool ret = false;
	m_totalReceived = 0;

	m_reflectorSocket.OpenConnection(host_name, port_number);
#ifdef BIND_TO_PORT_FOR_LISTEN
	m_listenSocket.Bind(src_address, bindPort);
	m_srcPort = bindPort;
#else
	m_srcAddr = UdpSocket::getLocalAddress();
	m_srcPort = m_reflectorSocket.getLocalPort();
#endif

	m_destport = port_number;

	m_isWelcomeShowed = false;

	m_openPacket = new OpenContinuePacket(0, FamilyClientOriginated, 
		(int) m_srcPort, m_srcAddr, m_seq, OpenConnection, 
		OpenContinue, 
		username, Small_Video, With_Video);
	ret = m_reflectorSocket.SendData(m_openPacket->getBuffer(), m_openPacket->getSize());

	if (ret) {
		m_isRunning = true;
		Create();
		Run();

		m_mainFrame->setReflectorName(/*host_name*/);
	}

	return ret;
}


void CommandSocket::Disconnect()
{
	wxMutex *stopMutex = new wxMutex;
    wxCondition *stopCondition = new wxCondition(*stopMutex);

	m_stopMutex = stopMutex;
	m_stopCondition = stopCondition;

	stopMutex->Lock();
	m_mainFrame = NULL;
	m_isRunning = false;
	m_reflectorSocket.CloseConnection();

	stopCondition->WaitTimeout(1000);
	stopMutex->Unlock();

	delete stopCondition;
	delete stopMutex;
}

void CommandSocket::keepAlive()
{
	/* we need to update send expected - receive expected */
	//m_openPacket->updateReceiveCount(m_clientRcvCountMap);
	m_reflectorSocket.SendData(m_openPacket->getBuffer(), m_openPacket->getSize());
}

void CommandSocket::SendVideo(unsigned char *cbuf, int cbuf_len)
{
	VideoPacket vPacket(m_destport, 0, 
		FamilyClientOriginated, m_srcPort, m_srcAddr, cbuf, cbuf_len);

	for (int i=0;i<vPacket.getNumPackets();i++) {
		int packetSize;
		char *packetData;
		packetData = vPacket.getPacketData(i, packetSize);
		m_reflectorSocket.SendData(packetData, packetSize);
	}

	//NOP
}

void CommandSocket::askForClient(ClientInfo *cf)
{
	cf->EnableReceive();
	m_openPacket->addClientToList(cf);

	m_reflectorSocket.SendData(m_openPacket->getBuffer(), m_openPacket->getSize());
}

void CommandSocket::AudioReceived(AudioFrame *af)
{
	printf("%s:%d\n", __FILE__, __LINE__);
	int cbuf_len;
	unsigned char *cbuf = m_acodec->compressFrame(af, &cbuf_len);

	AudioPacket audioPacket(m_destport, 0, 
		FamilyClientOriginated, m_srcPort, m_srcAddr, cbuf, cbuf_len);

	m_reflectorSocket.SendData(audioPacket.getBuffer(), audioPacket.getSize());

	delete[] cbuf;
}

void CommandSocket::VideoReceived(VideoFrame *vf)
{
	int cbuf_len;
	unsigned char *cbuf = m_vcodec->compressFrame(vf, &cbuf_len);
	SendVideo(cbuf, cbuf_len);
	delete[] cbuf;
}

bool CommandSocket::SendText(std::string text)
{
	TextPacket textPacket(m_destport, m_srcPort, m_srcAddr, 1, 1, text.c_str());
	m_reflectorSocket.SendData(textPacket.getBuffer(), textPacket.getSize());
	
	return true;
}

wxThread::ExitCode CommandSocket::Entry()
{
	while(true)
	{
		char buffer[2048];

		if (m_stopMutex) m_stopMutex->Lock();
		if (!m_isRunning) {
			break;
		}
		if (m_stopMutex) m_stopMutex->Unlock();

#ifdef BIND_TO_PORT_FOR_LISTEN
		int ret = m_listenSocket.RecvData(buffer, 2048);
#else
		int ret = m_reflectorSocket.RecvData(buffer, 2048);
#endif
		if (ret < 0) {
			if (m_stopMutex) m_stopMutex->Lock(); //stop running
			m_isRunning = false;
			break;
		}
		printf("Received: %d bytes\n", ret);
		m_totalReceived += ret + 28;

		Packet *p = Packet::parseBlob(buffer, ret);
		if (!p) {
			//msg could not be parsed!
			continue;
		}

		/* Update client statistics */
		if (m_clientMap.find(p->getSourceAddress()) != m_clientMap.end()) {
			m_clientMap[p->getSourceAddress()]->incPacketReceived();
		}

		/* handle received packet */
		if (dynamic_cast<OpenContinuePacket *> (p)) {
			onOpenContinue(dynamic_cast<OpenContinuePacket *> (p));
		} else if (dynamic_cast<AudioPacket *> (p)) {
			onAudioPacket(dynamic_cast<AudioPacket *> (p));
		} else if (dynamic_cast<VideoPacket *> (p)) {
			onVideoPacket(dynamic_cast<VideoPacket *> (p));
		} else if (dynamic_cast<ReflectorMessagePacket *> (p)) {
			onReflectorMessage(dynamic_cast<ReflectorMessagePacket *> (p));
		} else if (dynamic_cast<RateControlReq *> (p)) {
			onRateControlPacket(dynamic_cast<RateControlReq *> (p));
		} else if (dynamic_cast<AuxDataPacket *> (p)) {
			if (dynamic_cast<AuxDataPacket *> (p)->getType().compare("TEXT") == 0) {
				onTextPacket(reinterpret_cast<TextPacket *> (p));
			}
		}
		

		/* free memory */
		delete p;

	}

	if (m_stopCondition) m_stopCondition->Signal();
	if (m_stopMutex) m_stopMutex->Unlock();

	fprintf(stderr, "1\n");
	if (m_mainFrame) {
	    wxCommandEvent eventCustom(wxEVT_REFLECTOR_DISCONNECTED_EVENT);
    	wxPostEvent(m_mainFrame, eventCustom);
	}

	return NULL;
}

void CommandSocket::onOpenContinue(OpenContinuePacket *p)
{
	ClientInfo* ci;

	if (m_clientMap.find(p->getSourceAddress()) == m_clientMap.end()) {
		ci = m_clientMap[p->getSourceAddress()] = new ClientInfo(p->getSourceAddress(), p->getUserName());
		m_mainFrame->updateClientInfo(ci);
	} else {
		ci = m_clientMap[p->getSourceAddress()];
	}

	/** checking if the user seeing us or not */
	for (ClientInfoList::iterator i=p->getClientList().begin();i!=p->getClientList().end();i++) {
		ClientInfo *usersCI = *i;
		if (usersCI->getIPAddress() == m_srcAddr) {
			if (usersCI->isReceivingVideo()) {
				if (!ci->isSeeingUs()) {
					ci->hasSeeingUs(true);
					m_mainFrame->updateClientInfo(ci);
				}
				continue;
			}
		}

		/* we are not listed in client info - so he is not watching us */
		if (ci->isSeeingUs()) {
			ci->hasSeeingUs(false);
			m_mainFrame->updateClientInfo(ci);
		}
	}

	ci->resetPacketReceived();
}

void CommandSocket::onReflectorMessage(ReflectorMessagePacket *p)
{
	fprintf(stderr, "here\n");
	if (!m_isWelcomeShowed) {
		std::ostringstream stat_str;
		stat_str << "Message received from reflector: \n";
		stat_str <<  p->getMessage();

		if (m_mainFrame) {
		    wxCommandEvent welcomeEvent(wxEVT_REFMSG_EVENT);
			welcomeEvent.SetString(stat_str.str());
	    	wxPostEvent(m_mainFrame, welcomeEvent);
		}

		m_isWelcomeShowed = true;
	}
}

void CommandSocket::onRateControlPacket(RateControlReq *p)
{
	std::ostringstream stat_str;
	stat_str << "Rate cap: " << p->getSendCapacity();
	stat_str << ", send: " <<  p->getTotalSent();
	stat_str << ", rcv: " << m_totalReceived;
	stat_str << ", diff: " << (p->getTotalSent() - m_totalReceived);

	m_reflectorCapacity = p->getSendCapacity();

	//if (m_mainFrame) {
	//    wxCommandEvent setStatusEvent(wxEVT_SET_STATUS_EVENT);
	//	setStatusEvent.SetString(stat_str.str());
 //   	wxPostEvent(m_mainFrame, setStatusEvent);
	//}

	RateControlResponse resp(m_destport, 0, m_srcPort, 
		m_srcAddr, p, m_totalReceived);

	m_reflectorSocket.SendData(resp.getBuffer(), resp.getSize());
}

void CommandSocket::onAudioPacket(AudioPacket *p)
{
	AudioFrame *audioFrame = m_acodec->decompressFrame(p->getPlainBuffer(), p->getBufferSize());
	m_mainFrame->remoteAudioReceived(p->getSourceAddress(), audioFrame);
	//delete audioFrame; => the packet would be freed after playback
}

void CommandSocket::onVideoPacket(VideoPacket *p)
{
	VideoFrame *uncompressed = m_vcodec->decompressFrame(p->getVideoBuffer(), 
		p->getVideoBufferLen());
	if (!uncompressed) {
		//could not uncompress video
		return;
	}

	m_mainFrame->showClientVideo(p->getSourceAddress(), uncompressed);
	IplImage *img = uncompressed->getImage();
	cvReleaseImage(&img);
	delete uncompressed;
}

void CommandSocket::onTextPacket(TextPacket *p)
{
	ClientInfo* ci;

	if (m_clientMap.find(p->getSourceAddress()) != m_clientMap.end()) {
		ci = m_clientMap[p->getSourceAddress()];

		m_mainFrame->textMessageReceived(ci->getUsername(), p->getMessage());
	}
}

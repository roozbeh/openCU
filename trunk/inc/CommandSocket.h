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

#ifndef __OPENCU_COMMAND_SOCKET_H__
#define __OPENCU_COMMAND_SOCKET_H__

#include "UdpSocket.h"
#include "Capture.h"

class MainFrame;
class OpenContinuePacket;
class VideoPacket;
class AudioPacket;
class ReflectorMessagePacket;
class RateControlReq;
class VideoCodec;
class AudioCodec;
class TextPacket;

//#define BIND_TO_PORT_FOR_LISTEN

//map<long, long> Long2LongMap;
typedef std::map<long, ClientInfo *> ClientInfoMap;

class CommandSocket : public wxThread, public CaptureListener
{
	UdpSocket m_reflectorSocket;
#ifdef BIND_TO_PORT_FOR_LISTEN
	UdpSocket m_listenSocket;
#endif
	int m_seq;
	long m_srcAddr;
	short m_destport;
	short m_srcPort;

	bool m_isRunning;

	/** All clients which we have aware of */
	ClientInfoMap m_clientMap; ///< map of <client-address>  => <client info>

	/** reference to MainFrame - needed for updating TREE */
	MainFrame *m_mainFrame;

	/** 
	 * cached opencountinue packet - it required because we need to 
	 * send it time to time 
	 */
	OpenContinuePacket *m_openPacket;

	/* determines if welcome dialog shows to user or not */
	bool m_isWelcomeShowed;

	/* condition and lock for stoping commander */
	wxMutex *m_stopMutex;
    wxCondition *m_stopCondition;

	/** number of bytes received from connection time */
	unsigned long m_totalReceived;

	/** total capacity reflector dedicated to us */
	unsigned long m_reflectorCapacity;

	/** pointer to VideoCodec structure for COmpression and DECompression */
	VideoCodec *m_vcodec;

	/** pointer to VideoCodec structure for COmpression and DECompression */
	AudioCodec *m_acodec;

public:
	CommandSocket(MainFrame *mf);
	~CommandSocket();
	
	/**
	 * host_name: reflector IP Address
	 * src_address: client address (TODO: should be extracted automatically)
	 */
	bool Connect(std::string host_name, int port_number, std::string username,
		char *src_address = NULL, int rcv_port = -1);

	/** send keep alive packet */
	void keepAlive();

	/** Disconnect from reflector */
	void Disconnect();

	void SendVideo(unsigned char *cbuf, int cbuf_len);

	/** independent thread function which analyzes packets from reflector */
	ExitCode Entry();

	/** Calls when an OpenContinue packet arrives */
	void onOpenContinue(OpenContinuePacket *p);

	/** Calls when a VideoPacket packet arrives */
	void onVideoPacket(VideoPacket *p);
	
	/** cals when an AudioPacket arrives from reflector */
	void onAudioPacket(AudioPacket *p);

	/** Reflector sent a message */
	void onReflectorMessage(ReflectorMessagePacket *p);

	/** Reflector wants to measure network parameters */
	void onRateControlPacket(RateControlReq *p);

	/** Reflector send a text message sent by another client */
	void onTextPacket(TextPacket *p);
	
	void askForClient(ClientInfo *cf);

	/** CaptureListener callbacks */

	/** calls when a video frame available from video device */
	void VideoReceived(VideoFrame *vf);

	/** calls when a audio frame available*/
	void AudioReceived(AudioFrame *af);

	unsigned long getTotalReceived() { return m_totalReceived;} ;

	/** total capacity reflector dedicated to us */
	unsigned long getReflectorCapacity() {return m_reflectorCapacity;} ;

	unsigned long getTotalSent() {return m_reflectorSocket.getTotalSent();};

	bool SendText(std::string text);
};

#endif //__OPENCU_COMMAND_SOCKET_H__


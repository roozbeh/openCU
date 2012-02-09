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
#include "string.h"
#ifdef HAVE_ARPA_INET_H
#include <arpa/inet.h>
#endif

OpenContinuePacket::OpenContinuePacket(short conferenceID,
		SrcFamily sf, short sp, long sa, long seq, MessageType mt, DataType dt,
		std::string un, SendMode sm, ReceiveMode rm) : 
Packet (ExamineAndForward, conferenceID, 0, sf, sp, sa, seq, mt, dt), 
client_count(0), opencontinue_seq(0),
send_mode(sm), rcv_mode(rm), flags((Flags)(FlagsReceiveAudio | TransmitAudio | WindowsMachine)), version(0),
m_rateReport(1, 32, 0, 1, 256, 0)
{
	memset(username, 0x00, 19);
	if (un.size() > 19) {
		username_len = 19;
		memcpy(username, un.c_str(), 19);
	} else {
		username_len = un.size();
		strcpy(username, un.c_str());
	}
}

OpenContinuePacket::OpenContinuePacket(char *buffer, int len) : Packet(buffer, len)
{
	client_count = ntohs(*((short *) (buffer + 26)));
	opencontinue_seq = *((long *) (buffer + 28));
	username_len = *(buffer + 32);
	memcpy(username, buffer + 33, 19);
	send_mode = (SendMode) *(buffer + 52);
	rcv_mode = (ReceiveMode) *(buffer + 53);
	flags = (Flags) *(buffer + 54);
	version = *(buffer + 55);

	if (len == 56) {
		return;
	}

	/* parse client info */
	for (int i=0;i<client_count;i++) {
		if (56 + i * ClientInfo::getSize() > len) {
			//avoid memory problem
			break;
		}
		ClientInfo *ci = new ClientInfo(buffer + 56 + i * ClientInfo::getSize());
		m_activeClientInfos.push_back(ci);
	}

	/* parse extra headers */
	int cursor = 56 + ClientInfo::getSize() * client_count;
	while ((cursor + 4) <= len) {
		ExtraHeader *header = (ExtraHeader *) (buffer + cursor);
		cursor += 4;
		if ((cursor + header->getLen()) > len) {
			//not enough available
			break;
		}

		ExtraHeaderType t = header->getType();
		if (RateReportExtraType == t) {
			m_rateReport.init(buffer + cursor);
		}

		cursor += header->getLen();
	};

}

OpenContinuePacket::~OpenContinuePacket()
{
}

char *OpenContinuePacket::getBuffer()
{
	if (!m_buffer) {
		allocBuffer(getSize());
		char *target = m_buffer;

		FlushHeader2Buffer(target);
		Add2Buffer(target, client_count);
		Add2Buffer(target, opencontinue_seq);
		Add2Buffer(target, username_len);

		memcpy(target, &username, sizeof(username));
		target += sizeof(username);

		Add2Buffer(target, (char) send_mode);
		Add2Buffer(target, (char) rcv_mode);
		Add2Buffer(target, (char) flags);
		Add2Buffer(target, (char) version);

		if (m_activeClientInfos.size() > 0 ) {
			for (ClientInfoList::iterator i=m_activeClientInfos.begin();i!=m_activeClientInfos.end();i++) {
				ClientInfo *cf = *i;
				
				Add2BufferNoConv(target, (long) cf->ipAddress);
				Add2Buffer(target, (char) cf->flags);
				Add2Buffer(target, (char) cf->auxPrune);
				Add2Buffer(target, (char) cf->receive);
				Add2Buffer(target, (char) cf->send);
				Add2Buffer(target, (short) cf->packetSent);
				Add2Buffer(target, (short) cf->packetReceived);
			}
		}

		Add2Buffer(target, (char *)&m_rateReport, sizeof(RateReportHeader));
	} else {
		Packet::applySeqNumber(m_buffer);
	}

	incSeqNumber();
	opencontinue_seq++;
	return m_buffer;
}

int OpenContinuePacket::getSize()
{
	if (!m_size) {
		m_size = 56 + 
			(int) m_activeClientInfos.size() * ClientInfo::getSize() + 
			sizeof(RateReportHeader);
	}

	return m_size;
}

void OpenContinuePacket::addClientToList(ClientInfo *cf)
{
	bool exists = false;
	for (ClientInfoList::iterator i=m_activeClientInfos.begin();i!=m_activeClientInfos.end();i++) {
		ClientInfo *c = *i;
		if (c->getIPAddress() == cf->getIPAddress()) {
			//client aleady existed
			exists = true;
		}
	}

	if (!exists) {
		m_activeClientInfos.push_back(cf);
	}


	/* reset packet */
	m_size = 0;
	if (m_buffer) {
		delete m_buffer;
		m_buffer = NULL;
	}
	/* update number of clients */
	client_count = m_activeClientInfos.size();
}


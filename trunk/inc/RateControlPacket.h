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

#ifndef _OPENCU_RATE_CONTROL_PACKET_H_
#define _OPENCU_RATE_CONTROL_PACKET_H_

class RateControlResponse;

class RateControlReq : public Packet {
	friend class RateControlResponse;

	unsigned short       m_curSendCap;
	unsigned long        m_totalBytesSent;
	unsigned long        m_timeSent;

public:
	RateControlReq(char *buffer, int len);
	~RateControlReq();

	int getSize() { return -1; };
	char *getBuffer() { return 0;}; 

	unsigned short getSendCapacity() {return m_curSendCap;};
	unsigned long getTotalSent() {return m_totalBytesSent;};
};

class RateControlResponse : public Packet {
	unsigned short m_maxRecvKbps;
	unsigned long m_recvSeqNum;
	unsigned long m_totalBytesSent;
	unsigned long m_totalBytesRecv;
	unsigned long m_timeSentToMe;
	unsigned long m_timeRecvByMe;
public:
	RateControlResponse(short destPort, long destAddr, short srcPort, long srcAddr,
		RateControlReq *req, int total_received) : Packet(
								ExamineAndForward, destPort, destAddr, 
								FamilyClientOriginated, srcPort, srcAddr, 
								0, MorePacketToCome, DataRateControlResp)
	{
		m_maxRecvKbps = 256/*req->m_curSendCap*/;
		m_recvSeqNum = req->seq_number;
		m_totalBytesSent = req->m_totalBytesSent;
		m_totalBytesRecv = total_received;
		m_timeSentToMe = req->m_timeSent;
		m_timeRecvByMe = req->m_timeSent; /* TODO: should calculate time */
	}

	int getSize();
	char *getBuffer(); 
};
#endif //_OPENCU_RATE_CONTROL_PACKET_H_


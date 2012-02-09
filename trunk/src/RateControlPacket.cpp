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
#include "Packet.h"
#include "RateControlPacket.h"
#ifdef HAVE_ARPA_INET_H
#include <arpa/inet.h>
#endif

RateControlReq::RateControlReq(char *buffer, int len)  : Packet(buffer, len)
{
	assert(len == 36);

	m_curSendCap = ntohs(*((short *) (buffer +  26))) ;
	m_totalBytesSent = ntohl(*((long *) (buffer +  28)));
	m_timeSent = ntohl(*((long *) (buffer +  32)));
}

RateControlReq::~RateControlReq()
{
}

int RateControlResponse::getSize()
{
	return Packet::getSize() + 22;
}

char *RateControlResponse::getBuffer()
{
	if (!m_buffer) {
		allocBuffer(getSize());

		char *target = m_buffer;

		FlushHeader2Buffer(target);

		Add2Buffer(target, (short) m_maxRecvKbps);
		Add2Buffer(target, (long) m_recvSeqNum);
		Add2Buffer(target, (long) m_totalBytesSent);
		Add2Buffer(target, (long) m_totalBytesRecv);
		Add2Buffer(target, (long) m_timeSentToMe);
		Add2Buffer(target, (long) m_timeRecvByMe);
	}

	return m_buffer;
}

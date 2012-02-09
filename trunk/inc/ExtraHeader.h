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

#ifndef __OPENCU_EXTRA_HEADER_H__
#define __OPENCU_EXTRA_HEADER_H__

#ifdef HAVE_ARPA_INET_H
#include <arpa/inet.h>
#endif

typedef enum {
	AuxTMAdvExtraType = 1,       // Advertise AUX Data bit mapping
	oldRateReportExtraType = 2,       // Report Rate Parameters
	RateReportExtraType = 3,       // Report Rate Parameters
	VersionReportExtraType = 4       // New Version information scheme
} ExtraHeaderType;


class ExtraHeader {
	short m_type;
	unsigned short m_length;
public:
	ExtraHeader(ExtraHeaderType t, unsigned short len) : m_type(htons(t)), m_length(htons(len)) {};

	ExtraHeaderType getType() {return (ExtraHeaderType) ntohs(m_type);};
	short getLen() {return ntohs(m_length);};
};

class RateReportHeader : public ExtraHeader
{
	short        m_minSendKbps;
	short        m_maxSendKbps;
	short        m_curSendKbps;
	short        m_minRecvKbps;
	short        m_maxRecvKbps;
	short        m_curRecvKbps;
public:
	RateReportHeader(short minSend, short maxSend, short curSend, 
		short minRecv, short maxRecv, 
		short curRecv) : ExtraHeader(RateReportExtraType, 12), 
		m_minSendKbps(htons(minSend)), m_maxSendKbps(htons(maxSend)), m_curSendKbps(htons(curSend)),
		m_minRecvKbps(htons(minRecv)), m_maxRecvKbps(htons(maxRecv)), m_curRecvKbps(htons(curRecv))
	{
	};

	/* empty constructor */
	RateReportHeader() : ExtraHeader(RateReportExtraType, 12), 
		m_minSendKbps(-1), m_maxSendKbps(-1), m_curSendKbps(-1),
		m_minRecvKbps(-1), m_maxRecvKbps(-1), m_curRecvKbps(-1)
	{
	};


	void init(char *buffer)
	{
		m_minSendKbps = ((short *) buffer)[0];
		m_maxSendKbps = ((short *) buffer)[1];
		m_curSendKbps = ((short *) buffer)[2];
		m_minRecvKbps = ((short *) buffer)[3];
		m_maxRecvKbps = ((short *) buffer)[4];
		m_curRecvKbps = ((short *) buffer)[5];
	};
};

#endif


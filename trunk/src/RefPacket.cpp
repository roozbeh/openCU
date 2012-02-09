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
#include "RefPacket.h"
#ifdef HAVE_ARPA_INET_H
#include <arpa/inet.h>
#endif


ReflectorMessagePacket::ReflectorMessagePacket(char *buffer, int len, 
				ReflectorMessageType t) : Packet(buffer, len), m_type(t)
{
	int stringPlace;
	if (ReflectorMessageMOTD == t) {
		m_messageLen = ntohs(*((short *) (buffer + 28)));
		stringPlace = 30;
	} else {
		m_messageLen = *((unsigned char *) (buffer + 28));
		stringPlace = 29;
	}

	if (m_messageLen > len) {
		//could not proceed
		m_messageLen = 0;
		m_message = "";
	}

	char *str = new char [m_messageLen];
	memcpy(str, buffer + stringPlace, m_messageLen);

	m_message = str;

	delete str;
}

std::string ReflectorMessagePacket::getMessage()
{
	return m_message;
}


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

#ifndef __OPENCU_REFLECTOR_MESSAGE_PACKET_H__
#define __OPENCU_REFLECTOR_MESSAGE_PACKET_H__

#include "Packet.h"

typedef enum {
	ReflectorMessageMOTD = 1,
	ReflectorMessageKickOff = 2,
} ReflectorMessageType;

class ReflectorMessagePacket : public Packet {
	int m_messageLen;
	std::string m_message;

	ReflectorMessageType m_type;
public:
	ReflectorMessagePacket(char *buffer, int len, ReflectorMessageType t);

	std::string getMessage();
	/** This packet only receives from reflector - so no need to serialize */
	char *getBuffer() { return NULL;};
};

#endif //__OPENCU_REFLECTOR_MESSAGE_PACKET_H__


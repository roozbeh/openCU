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

#ifndef __OPENCU_TEXT_PACKET_H_
#define __OPENCU_TEXT_PACKET_H_

#include "Packet.h"

typedef enum {
	ADS_OP_SEND = 0,
	ADS_OP_SEND_WITH_ACK = 1,
	ADS_OP_SEND_ONCE = 2,
} AuxOpCond;

typedef enum {
	ADS_PRIORITY_LOW = -128,
	ADS_PRIORITY_NORMAL = 0,
	ADS_PRIORITY_HIGH = 127,
} AuxPriority;

typedef enum {
	ADR_STATUS_DATA = 0, //  A normal data segment.
	ADR_STATUS_RETRY = 1, //A retry data segment.
	ADR_STATUS_INCOMPLETE = 2, //The item has been cancelled.
} AuxStatus;


class AuxDataPacket : public Packet {
	unsigned char m_version;
	unsigned char m_headerLen;
	AuxOpCond m_opCode;
	AuxPriority m_priority;
	unsigned long m_prune;
	AuxStatus m_status;
	short m_segmentLength;  //This 16-bit field contains the actual length of the data portion of the packet
	char m_type[5]; /* 4 bytes are usable and the fifth should be \0 */
	long m_itemNumber;
	long m_itemID;
	long m_itemLength;
	long m_segmentOffset;
protected:
	unsigned char *m_data;

public:
	AuxDataPacket(int destPort, int srcPort, long sa, long seq,
		AuxOpCond code, AuxPriority priority, 
		AuxStatus status, char *type, long itemNum, 
		long itemId, unsigned char *data, long item_length);

	AuxDataPacket(char *audioData, int audioLen);

	int getSize() { return Packet::getSize() + 32 + m_itemLength; };
	char *getBuffer();

	std::string getType() {return m_type;};
};

class TextPacket : public AuxDataPacket {
	static long sCurrentSequence;
public:
	TextPacket(int destPort, int srcPort, long srcaddress, long itemNum, 
		long itemId, const char *txtMessage);

	TextPacket(char *pData, int pLen) : AuxDataPacket(pData, pLen) {};

	std::string getMessage() {return std::string((char *)m_data);};
};

#endif
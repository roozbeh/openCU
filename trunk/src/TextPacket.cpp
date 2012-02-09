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
#include "TextPacket.h"

long TextPacket::sCurrentSequence = 0;

AuxDataPacket::AuxDataPacket(int destPort, 
							 int srcPort, 
							 long sa, 
							 long seq, 
							 AuxOpCond code, 
							 AuxPriority priority, 
							 AuxStatus status, 
							 char *type, 
							 long itemNum, 
							 long itemId, 
							 unsigned char *data, 
							 long item_length) : 
		Packet(ForwardToAll, 
			destPort, 
			0, 
			FamilyClientOriginated, 
			srcPort, 
			sa, 
			seq, 
			FrameEndMessage, 
			AuxDataPacketType),
		m_version(0), 
		m_headerLen(32), 
		m_opCode(code), 
		m_priority(priority), 
		m_prune(0x1), 
		m_status(status), 
		m_segmentLength(item_length),
		m_itemNumber(itemNum), 
		m_itemID(itemId), 
		m_itemLength(item_length), 
		m_segmentOffset(0), 
		m_data(data)
{
	if (type && strlen(type) >=4) {
		memcpy(m_type, type, 4);
		m_type[4] = '\0';
	} else {
		strcpy(m_type, "CUCU");
	}
}

char *AuxDataPacket::getBuffer()
{
	if (!m_buffer) {
		allocBuffer(getSize());

		char *target = m_buffer;

		FlushHeader2Buffer(target);

		Add2Buffer(target, (char) m_version);
		Add2Buffer(target, (char) m_headerLen);
		Add2Buffer(target, (char) m_opCode);
		Add2Buffer(target, (char) m_priority);
		Add2Buffer(target, (long) m_prune);
		Add2Buffer(target, (char) m_status);
		Add2Buffer(target, (char) 0);
		Add2Buffer(target, m_segmentLength);
		Add2Buffer(target, (char) m_type[0]);
		Add2Buffer(target, (char) m_type[1]);
		Add2Buffer(target, (char) m_type[2]);
		Add2Buffer(target, (char) m_type[3]);
		Add2Buffer(target, m_itemNumber);
		Add2Buffer(target, m_itemID);
		Add2Buffer(target, m_itemLength);
		Add2Buffer(target, m_segmentOffset);
		Add2Buffer(target, (char *) m_data, m_itemLength);
	}

	return m_buffer;
}

AuxDataPacket::AuxDataPacket(char *buffer, int bufferLen)  : Packet(buffer, bufferLen)
{
	int base = 26;

	m_version = *((unsigned char *) (buffer + base + 0));
	m_headerLen = *((unsigned char *) (buffer + base + 1));
	m_opCode = (AuxOpCond) *((unsigned char *) (buffer + base + 2));
	m_priority = (AuxPriority) *((unsigned char *) (buffer + base + 3));
	m_prune = *((unsigned long *) (buffer + base + 4));
	m_status = (AuxStatus) *((unsigned char *) (buffer + base + 8));
	m_segmentLength = *((unsigned short *) (buffer + base + 10));
	m_type[0] = *((unsigned char *) (buffer + base + 12));
	m_type[1] = *((unsigned char *) (buffer + base + 13));
	m_type[2] = *((unsigned char *) (buffer + base + 14));
	m_type[3] = *((unsigned char *) (buffer + base + 15));
	m_type[4] = '\0';
	m_itemNumber = *((unsigned long *) (buffer + base + 16));
	m_itemID = *((unsigned long *) (buffer + base + 20));
	m_itemLength = *((unsigned long *) (buffer + base + 24));
	m_segmentOffset = *((unsigned long *) (buffer + base + 28));
	m_data = (unsigned char *) (buffer + base + 32);

}

TextPacket::TextPacket(int destPort, 
					   int srcPort, 
					   long srcaddress, 
					   long itemNum, 
					   long itemId, 
					   const char *txtMessage) :
		AuxDataPacket(destPort, 
			srcPort, 
			srcaddress, 
			++sCurrentSequence,
			ADS_OP_SEND_WITH_ACK, 
			ADS_PRIORITY_NORMAL, 
			ADR_STATUS_DATA, 
			"TEXT", 
			itemNum, 
			itemId, 
			(unsigned char *) txtMessage, 
			strlen(txtMessage) + 1)
{
};

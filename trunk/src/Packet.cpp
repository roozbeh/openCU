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
#include "RefPacket.h"
#include "RateControlPacket.h"
#include "TextPacket.h"

Packet::Packet(DestFamily df, short dp, long da, 
			   SrcFamily sf, short sp, long sa, long seq, MessageType mt, DataType dt) : 
					dest_family(df), dest_port(dp), dest_addr(da), 
					src_family(sf), src_port(sp), src_addr(sa), seq_number(seq), 
					msg_type(mt), data_type(dt), m_buffer(NULL), m_size(0)
{
}

Packet::Packet(char *buffer, int ) : m_buffer(NULL), m_size(0)
{
	dest_family = (DestFamily) ntohs(*((short *) buffer));
	dest_port = *((short *) (buffer +  2)) ;
	dest_addr = *((long *) (buffer +  4)) ;
	src_family = (SrcFamily) ntohs(*((short *) buffer + 8));
	src_port = *((short *) (buffer +  10)) ;
	src_addr = *((long *) (buffer +  12)) ;
	seq_number = ntohl(*((long *) (buffer +  16)));
	msg_type = (MessageType) ntohs(*((short *) (buffer + 20)));
	data_type = (DataType) ntohs(*((short *) (buffer + 22)));
	packet_length = ntohs(*((short *) (buffer + 24)));
}

Packet::~Packet()
{
	if (m_buffer) {
		delete m_buffer;
	}
}

void Packet::Add2Buffer(char*& target, char x)
{
	memcpy(target, &x, sizeof(x));
	target += sizeof(x);
}

void Packet::Add2Buffer(char*& target, short x)
{
	short nx = htons(x);
	memcpy(target, &nx, sizeof(nx));
	target += sizeof(nx);
}

void Packet::Add2BufferNoConv(char*& target, short x)
{
	memcpy(target, &x, sizeof(x));
	target += sizeof(x);
}

void Packet::Add2Buffer(char*& target, long x)
{
	long nx = htonl(x);
	memcpy(target, &nx, sizeof(nx));
	target += sizeof(nx);
}

void Packet::Add2Buffer(char*& target, char *buf, int len)
{
	memcpy(target, buf, len);
	target += len;
}

void Packet::Add2BufferNoConv(char*& target, long nx)
{
	memcpy(target, &nx, sizeof(nx));
	target += sizeof(nx);
}

void Packet::allocBuffer(int size)
{
	if (m_buffer) {
		delete m_buffer;
	}
	m_buffer = new char [size];
	setPacketSize(size);
}

void Packet::FlushHeader2Buffer(char*& target)
{
	Add2Buffer(target, (short) dest_family);
	Add2BufferNoConv(target, dest_port);
	Add2BufferNoConv(target, dest_addr);

	Add2Buffer(target, (short) src_family);
	Add2BufferNoConv(target, src_port);
	Add2BufferNoConv(target, src_addr);

	Add2Buffer(target, seq_number);

	Add2Buffer(target, (short) msg_type);
	Add2Buffer(target, (short) data_type);
	Add2Buffer(target, (short) packet_length);
}

void Packet::setPacketSize(int size)
{
	packet_length = size;
}

Packet *Packet::parseBlob(char *buffer, int len)
{
	MessageType msgType = (MessageType) ntohs(*((short *) (buffer + 20)));
	DataType data_type = (DataType) ntohs(*((short *) (buffer + 22)));

	switch(msgType) {
		case OpenConnection:
			return new OpenContinuePacket(buffer, len);
		case MorePacketToCome:
		case FrameEndMessage:
			if (data_type == SmallVideo) {
				return VideoPacket::concatBlobs(buffer, len, msgType);
			} else if (data_type == Audio) {
				return new AudioPacket(buffer, len);
			} else if (data_type == DataRateControlReq) {
				return new RateControlReq(buffer, len);
			} else if (data_type == Acknowledge) {
				/* Ignore it */
				return NULL;
			} else if (data_type == KickOffClient) {
				return new ReflectorMessagePacket(buffer, len, ReflectorMessageKickOff);
			} else if (data_type == WelcomeClient) {
				return new ReflectorMessagePacket(buffer, len, ReflectorMessageMOTD);
			} else if (data_type == AuxDataPacketType) {
				AuxDataPacket *aux = new AuxDataPacket(buffer, len);
				return aux;
			} else {
				std::ostringstream stat_str;
				stat_str << "Underestmined message received, type: " << msgType << ", data: " << data_type;

				wxMessageBox(stat_str.str(), 
					_T("Error"),
					wxICON_ERROR | wxOK);
				return NULL;
			}
		case AcknowledgeConnection:
			if (data_type == Acknowledge) {
				/* Acknowledge packet not understandable */
				return NULL;
			}

		default:
			{
				std::ostringstream stat_str;
				stat_str << "Message could not be parsed, Message Type: ";
				stat_str <<  msgType;
				stat_str <<  ", Data Type: ";
				stat_str <<  data_type;

				wxMessageBox(stat_str.str(), 
					_T("Error"),
					wxICON_ERROR | wxOK);
			}
			return NULL;

	}
}

void Packet::applySeqNumber(char *m_buffer)
{
	*((long *) (m_buffer +  16)) = htonl(seq_number);
}


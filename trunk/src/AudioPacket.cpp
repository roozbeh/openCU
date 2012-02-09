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
#include "AudioPacket.h"

long AudioPacket::sCurrentSequence = 0;

AudioPacket::AudioPacket(short destPort, long destAddress, 
		 SrcFamily sourceFamily, short sourcePort, 
		 long sourceAddress, unsigned char *cbuf, int buflen) : Packet(
				ForwardToAll, destPort, destAddress, sourceFamily, 
				sourcePort, sourceAddress, ++sCurrentSequence, 
				FrameEndMessage, Audio), 
		m_plain_buffer(cbuf), m_buffer_len(buflen)
{
}

AudioPacket::AudioPacket(char *buffer, int bufferLen)  : Packet(buffer, bufferLen)
{
	m_speaker_count = *((unsigned char *) (buffer + 26));
	m_flags = *((AudioFlags *) (buffer + 27));
	m_conf_ID = *((short *) (buffer + 28));
	/* m_timestamp = *((long *) (buffer + 30)); */

	m_plain_buffer = (unsigned char *) (buffer + 34);
	m_buffer_len = packet_length - 34;
}

AudioPacket::~AudioPacket()
{
}


char *AudioPacket::getBuffer()
{
	if (!m_buffer) {
		allocBuffer(getSize());

		char *target = m_buffer;

		FlushHeader2Buffer(target);

		long now_time = time(NULL);

		Add2Buffer(target, (char) m_speaker_count);
		Add2Buffer(target, (char) m_flags);
		Add2Buffer(target, (short) m_conf_ID);
		Add2Buffer(target, (long) now_time);
		Add2Buffer(target, (char *) m_plain_buffer, m_buffer_len);
	}

	return m_buffer;

}

int AudioPacket::getSize()
{
	if (!m_size) {
		m_size = Packet::getSize() + 8 + m_buffer_len;
	}

	return m_size;
}


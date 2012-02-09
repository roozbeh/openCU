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

#ifndef __OPENCU_AUDIO_PACKET_H_
#define __OPENCU_AUDIO_PACKET_H_

#include "Packet.h"
#include "AudioFrame.h"

enum AudioFlags {
	AUDF_MULAW8 = 0,   /* 64 kb/s 8KHz mu-law encoded PCM */
	AUDF_CELP = 1 ,  /* 4.8 kb/s FED-STD-1016 CELP */
	AUDF_G721 = 2 ,  /* 32 kb/s CCITT ADPCM */
	AUDF_GSM = 3 ,  /* 13 kb/s Groupe Special Mobile */
	AUDF_DELTAMOD = 26,   /* 16 kb/s 2 bit delta mod [cvk] */
	AUDF_LINEAR8 = 27,   /* 64 kb/s 8KHz linear PCM  [macintosh] */
	AUDF_LPC4 = 28,   /* 4.8 kb/s LPC, 4 frames */
	AUDF_LPC1 = 29,   /* 4.8 kb/s LPC, 1 frame */
	AUDF_IDVI = 30,   /* 32 kb/s Intel DVI ADPCM */
	AUDF_UNDEF = 31,   /* undefined */
	AUDF_NEWTS = 127,
};

class AudioPacket : public Packet {
	unsigned char m_speaker_count;
	AudioFlags m_flags; //char
	unsigned short m_conf_ID;
	//unsigned long m_timestamp; => should be gathered from AudioFrame

	unsigned char *m_plain_buffer;
	int m_buffer_len;

	static long sCurrentSequence;
public:
	AudioPacket(short destport, long destAddress, 
				 SrcFamily sourceFamily, short sourcePort, long sourceAddress, 
				 unsigned char *cbuf, int buflen);
	AudioPacket(char *audioData, int audioLen);
	virtual ~AudioPacket();

	char *getBuffer();
	int getSize();

	unsigned char *getPlainBuffer() {return m_plain_buffer;};
	int getBufferSize() {return m_buffer_len;};

};

#endif //__OPENCU_AUDIO_PACKET_H_


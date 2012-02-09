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
#include "AudioCodec.h"
#include "AudioFrame.h"
#include "CommonDefinitions.h"

SpeexAudioCodec::SpeexAudioCodec()
{
   m_encoder = speex_encoder_init(speex_lib_get_mode(SPEEX_MODEID_NB));
   m_decoder = speex_decoder_init(speex_lib_get_mode(SPEEX_MODEID_NB));

   spx_int32_t tmp;

   tmp=1;
   speex_decoder_ctl(m_decoder, SPEEX_SET_ENH, &tmp);
   tmp=0;
   speex_encoder_ctl(m_encoder, SPEEX_SET_VBR, &tmp);
   tmp=8;
   speex_encoder_ctl(m_encoder, SPEEX_SET_QUALITY, &tmp);
   tmp=1;
   speex_encoder_ctl(m_encoder, SPEEX_SET_COMPLEXITY, &tmp);
   
   speex_encoder_ctl(m_encoder, SPEEX_GET_FRAME_SIZE, &m_frameSize);

   speex_bits_init(&m_enc_bits);
   speex_bits_init(&m_dec_bits);
}

SpeexAudioCodec::~SpeexAudioCodec()
{
	speex_encoder_destroy(m_encoder);
	speex_decoder_destroy(m_decoder);
	speex_bits_destroy(&m_enc_bits);
	speex_bits_destroy(&m_dec_bits);
}

unsigned char *SpeexAudioCodec::compressFrame(AudioFrame *input, int *len)
{
	//reset the operation
	speex_bits_reset(&m_enc_bits);

	short *datap = (short *) input->getData();

	int iterCount = FRAMES_PER_BUFFER / m_frameSize;
	for (int i=0;i<iterCount;i++ ) {
		speex_encode_int(m_encoder, datap + i * m_frameSize, &m_enc_bits);
	}
	
	char *encoded = new char[m_enc_bits.charPtr];
	*len = speex_bits_write(&m_enc_bits, encoded, m_enc_bits.charPtr);

	return (unsigned char *) encoded;
}

AudioFrame *SpeexAudioCodec::decompressFrame(unsigned char *abuf, int buflen)
{
	//reset the operation
	short *uncompressed = new short [FRAMES_PER_BUFFER];
	//short *compressed = (short *) abuf;

	speex_bits_reset(&m_dec_bits);
	speex_bits_read_from(&m_dec_bits, (char *) abuf, buflen);

	int iterCount = FRAMES_PER_BUFFER / m_frameSize;
	for (int i=0;i<iterCount;i++ ) {
		int ret = speex_decode_int(m_decoder, &m_dec_bits, uncompressed + i * m_frameSize);
		if (ret < 0) { 
			wxMessageBox("Could not decode audio stream", 
				_T("Error"),
				wxICON_ERROR | wxOK);
		}
	}

	return new AudioFrame((unsigned char *) uncompressed, FRAMES_PER_BUFFER * sizeof(short), true);
}

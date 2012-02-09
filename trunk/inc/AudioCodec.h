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

class AudioFrame;

class AudioCodec 
{
public:
	virtual unsigned char *compressFrame(AudioFrame *input, int *len) = 0;
	virtual AudioFrame *decompressFrame(unsigned char *vbuf, int buflen) = 0;
};

class RawAudioCodec : public AudioCodec
{
public:
	unsigned char *compressFrame(AudioFrame *input, int *len);
	AudioFrame *decompressFrame(unsigned char *vbuf, int buflen);
};

class SpeexAudioCodec : public AudioCodec
{
	void *m_encoder;
	void *m_decoder;
	SpeexBits m_enc_bits;
	SpeexBits m_dec_bits;

	spx_int32_t m_frameSize;
public:
	SpeexAudioCodec();
	~SpeexAudioCodec();

	unsigned char *compressFrame(AudioFrame *input, int *len);
	AudioFrame *decompressFrame(unsigned char *vbuf, int buflen);
};


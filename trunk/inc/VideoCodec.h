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

class VideoFrame;

class VideoCodec {
public:
	virtual unsigned char *compressFrame(VideoFrame *input, int *len) = 0;
	virtual VideoFrame *decompressFrame(unsigned char *vbuf, int buflen) = 0;
};

class EmptyVideoCodec : public VideoCodec {
public:
	unsigned char *compressFrame(VideoFrame *input, int *len);
	VideoFrame *decompressFrame(unsigned char *vbuf, int buflen);
};

class CUVideoCodec : public VideoCodec {
public:
	virtual unsigned char *compressFrame(VideoFrame *input, int *len);
	VideoFrame *decompressFrame(unsigned char *vbuf, int buflen);
};

class XVIDVideoCodec : public VideoCodec 
{
	int m_width;
	int m_height;

	/* encoder stuff */
	unsigned char *mp4_buffer;
	unsigned char *in_buffer;
	unsigned char *out_buffer;
	float ARG_FRAMERATE;

	void *enc_handle;
#define MAX_ZONES   64
	xvid_enc_zone_t ZONES[MAX_ZONES];

	int ARG_QUALITY;

	/* decoder stuff */
	void *dec_handle;

	int enc_init();
	int enc_main(unsigned char *image,
		 unsigned char *bitstream,
		 int *key,
		 int *stats_type,
		 int *stats_quant,
		 int *stats_length,
		 int sse[3]);

public:
	XVIDVideoCodec();
	~XVIDVideoCodec();
	virtual unsigned char *compressFrame(VideoFrame *input, int *len);
	VideoFrame *decompressFrame(unsigned char *vbuf, int buflen);
};


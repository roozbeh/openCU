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
#include "VideoCodec.h"
#include "VideoFrame.h"
#include <zlib.h>


unsigned char *EmptyVideoCodec::compressFrame(VideoFrame *input, int *len)
{
	IplImage *original = input->getImage();
	IplImage *gray_image = cvCreateImage(cvSize(original->width, original->height), IPL_DEPTH_8U, 1);
	IplImage *resized = cvCreateImage(cvSize(160, 120), IPL_DEPTH_8U, 1);

	cvCvtColor(original, gray_image, CV_RGB2GRAY);
	cvResize(gray_image, resized);


	cvReleaseImage(&gray_image);

	unsigned char *resizedBuffer = new unsigned char[160 * 120];
	memcpy(resizedBuffer, resized->imageData, 160 * 120);
	*len = 160 * 120;

	cvReleaseImage(&resized);
	return resizedBuffer;
}

VideoFrame *EmptyVideoCodec::decompressFrame(unsigned char *vbuf, int buflen)
{
	IplImage *original = cvCreateImage(cvSize(160, 120), IPL_DEPTH_8U, 1);
	memcpy(original->imageData, vbuf, buflen);
 
	IplImage *color_image = cvCreateImage(cvSize(160, 120), IPL_DEPTH_8U, 3);

	cvCvtColor(original, color_image, CV_GRAY2BGR);

	VideoFrame *ret = new VideoFrame(color_image);

	cvReleaseImage(&original);

	return ret;
}

unsigned char *CUVideoCodec::compressFrame(VideoFrame *input, int *len)
{
	IplImage *original = input->getImage();
	IplImage *gray_image = cvCreateImage(cvSize(original->width, original->height), IPL_DEPTH_8U, 1);
	IplImage *resized = cvCreateImage(cvSize(160, 120), IPL_DEPTH_8U, 1);
	unsigned char *finalBuffer = new unsigned char[80 * 120];

	cvCvtColor(original, gray_image, CV_RGB2GRAY);
	cvResize(gray_image, resized);

	for (int i=0;i<120;i++) {
		for (int j=0;j<80;j++) {
			unsigned char double_point;
			unsigned char p1, p2;

			p1 = resized->imageData[i*resized->widthStep + 2*j];
			p2 = resized->imageData[i*resized->widthStep + 2*j + 1];
			p1 >>= 4;
			p2 >>= 4;
			double_point = (p1 << 4) + p2;

			finalBuffer[i*80 + j] = double_point;
		}
	}

	cvReleaseImage(&gray_image);
	cvReleaseImage(&resized);

#ifdef WITHOUT_ZLIB
	*len = 80 * 120;
	return finalBuffer;
#else //WITH_ZLIB
	{
		unsigned long buflen = 80 * 120;
		unsigned long destBufLen = compressBound(buflen);
		unsigned char *dest_buf = new unsigned char[destBufLen];

		int ret = compress2(dest_buf, &destBufLen, 
				finalBuffer, buflen, Z_BEST_COMPRESSION);

		if (ret == Z_OK) {
			printf("Ratio: %4.2g%%\n", destBufLen * 100.0 / buflen);
		} else {
			fprintf(stderr, "ERROR in zlib compresson!\n");
			exit(0);
		}

		delete[] finalBuffer;

		*len = destBufLen;
		return dest_buf;
	}
#endif
}

VideoFrame *CUVideoCodec::decompressFrame(unsigned char *comp_buf, int comp_len)
{
	unsigned char *vbuf;
	int buflen;
#ifdef WITHOUT_ZLIB
	vbuf = comp_buf;
	buflen = comp_len;
#else
	{
		unsigned long destBufLen = 80 * 120;
		unsigned char *dest_buf = new unsigned char[destBufLen];
		int ret = uncompress(dest_buf, &destBufLen, 
				comp_buf, (unsigned long) comp_len);

		if (ret != Z_OK) {
			fprintf(stderr, "ERROR in zlib decompresson!\n");
			return NULL;
		}

		vbuf = dest_buf;
		buflen = destBufLen;	
	}
#endif

	IplImage *original = cvCreateImage(cvSize(80, 120), IPL_DEPTH_8U, 1);
	IplImage *gray_image = cvCreateImage(cvSize(160, 120), IPL_DEPTH_8U, 1);
	IplImage *color_image = cvCreateImage(cvSize(160, 120), IPL_DEPTH_8U, 3);

	memcpy(original->imageData, vbuf, buflen);

#ifndef WITHOUT_ZLIB
	delete[] vbuf;
#endif

	{
		int count[16];
		for (int i=0;i<16;i++) {
			count[i] = 0;
		}

		for (int i=0;i<120;i++) {
			for (int j=0;j<80;j++) {
				unsigned char p1, p2;
				unsigned char double_point;

				double_point = original->imageData[i*original->widthStep + j];

				p1 = double_point & 0x0F;
				p2 = (double_point & 0xF0) >> 4;

				count[p1]++;
				count[p2]++;
			}
		}


		int maxCount = 0;
		for (int i=0;i<16;i++) {
			if (count[i] > maxCount) {
				maxCount = count[i];
			}
		}
	}

	for (int i=0;i<120;i++) {
		for (int j=0;j<80;j++) {
			unsigned char double_point;
			unsigned char p1, p2;

			double_point = original->imageData[i*original->widthStep + j];

			p1 = double_point >> 4;
			p2 = (double_point & 0xF0) >> 4;

			p1 = p1 << 4;
			p2 = p2 << 4;

			gray_image->imageData[i*gray_image->widthStep + 2*j] = p1;
			gray_image->imageData[i*gray_image->widthStep + 2*j + 1] = p2;
		}
	}

	cvCvtColor(gray_image, color_image, CV_GRAY2BGR);

	VideoFrame *ret = new VideoFrame(color_image);

	cvReleaseImage(&gray_image);
	cvReleaseImage(&original);

	return ret;
}

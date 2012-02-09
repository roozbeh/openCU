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

#ifndef __OPEN_CU_VIDEO_FRAME_H__
#define __OPEN_CU_VIDEO_FRAME_H__

class VideoFrame
{
	IplImage *m_image;
	bool m_ownImage;
public:
	VideoFrame(IplImage *image, bool ownImage = false) : m_image(image), m_ownImage(ownImage) {};

	///* copy constructor */
	//VideoFrame(const VideoFrame &vf) : m_image(vf.m_image), m_ownImage(false) {
	//};

	VideoFrame() : m_ownImage(false) {};

	~VideoFrame() {
			if (m_ownImage) {
				cvReleaseImage(&m_image);
			}
	};
	IplImage *getImage() {return m_image;};

	int getSize();
	char *getRawDataPtr();

	int getWidth() {return m_image->width;};
	int getHeight() {return m_image->height;};

	void setImage(IplImage *img, bool ownImage) {m_image = img; m_ownImage = ownImage;};
};

#endif //__OPEN_CU_VIDEO_FRAME_H__


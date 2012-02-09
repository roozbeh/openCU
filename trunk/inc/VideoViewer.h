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

/*abstract*/ class VideoViewer
{
public:
	VideoViewer() {};
	virtual void initWindow(std::string title) = 0;
	virtual void showFrame(VideoFrame &vf) = 0;
	virtual void showFrame(IplImage *f) = 0;
};

class CVVideoViewer : public VideoViewer
{
	std::string m_title;
public:
	CVVideoViewer();
	~CVVideoViewer();
	void initWindow(std::string title);
	void showFrame(VideoFrame &vf);
	void showFrame(IplImage *f);
};


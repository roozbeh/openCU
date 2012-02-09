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
#include <highgui.h>
#include "VideoViewer.h"

CVVideoViewer::CVVideoViewer()
{
}

CVVideoViewer::~CVVideoViewer()
{
	cvDestroyWindow(m_title.c_str());
}

void CVVideoViewer::initWindow(std::string title)
{
	m_title = title;
	cvNamedWindow(m_title.c_str(), 1);
}

void CVVideoViewer::showFrame(VideoFrame &vf)
{
	cvShowImage(m_title.c_str(), vf.getImage());
}

void CVVideoViewer::showFrame(IplImage *f)
{
	cvShowImage(m_title.c_str(), f);
}

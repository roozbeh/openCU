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
#include "Capture.h"
#include "VideoFrame.h"

CaptureThread::CaptureThread() : 
	m_running(true), m_capture(0), m_constantImage(0), m_stopMutex(NULL), m_stopCondition(NULL)
{

};

void CaptureThread::stop() {

	wxMutex *stopMutex = new wxMutex;
    wxCondition *stopCondition = new wxCondition(*stopMutex);

	m_stopMutex = stopMutex;
	m_stopCondition = stopCondition;

	stopMutex->Lock();
	m_running = false;

	stopCondition->Wait();
	stopMutex->Unlock();

	delete stopCondition;
	delete stopMutex;
}

void CaptureThread::start() {
	m_running = true;
}

void CaptureThread::loadFile(char *filename)
{
	m_constantImage = cvLoadImage(filename);
	if (!m_constantImage) {
		printf("Could not open file ...\n");
		exit(0);
	}

}

wxThread::ExitCode CaptureThread::Entry()
{
	while (true) 
	{
		if (m_stopMutex) m_stopMutex->Lock();
		if (!m_running) {
			break;
		}
		if (m_stopMutex) m_stopMutex->Unlock();

		IplImage *image;
		if (m_constantImage) {
			image = m_constantImage;
		} else {
			image = cvQueryFrame(m_capture);
			if (!image) {
				printf("Error: failed to query from camera!\n");
				if (m_stopMutex) m_stopMutex->Lock();
				break;
			}
		}

		VideoFrame *vf = new VideoFrame(image);
		for (CaptureListenerList::iterator i=m_listenerList.begin();i!=m_listenerList.end();i++) {
			CaptureListener *l = *i;
			l->VideoReceived(vf);
		}
		delete vf;

		/* wait a little */
		Sleep(500);
	}

	if (m_capture) {
		cvReleaseCapture(&m_capture);
		m_capture = NULL;
	}

	m_stopCondition->Signal();
	m_stopMutex->Unlock();

	return NULL;
}

Capture::Capture()
{
	m_captureThread = new CaptureThread();
}

Capture::~Capture() 
{
}

bool Capture::init()
{
	CvCapture *capture = cvCreateCameraCapture(0);
	if (!capture) {
		printf("Error: Could not capture from camera!\n");
		return false;
	}

	m_captureThread->setCapture(capture);

	return true;
}

void Capture::useFile(char *filename)
{
	m_captureThread->loadFile(filename);
}

bool Capture::startVideoCapture()
{
	m_captureThread->Create();
	m_captureThread->Run();

	return true;
}

void Capture::stopVideoCapture()
{
	m_captureThread->stop();
}

void Capture::addListener(CaptureListener *l)
{
	 m_captureThread->addListener(l);
}

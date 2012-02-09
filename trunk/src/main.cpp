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

//#include "Packet.h"
//#include "OpenContinuePacket.h"
//#include "CommandSocket.h"
//
//#include "Capture.h"
//#include "Frame.h"
//#include "VideoViewer.h"
//
//#include <stdio.h>
//


//class CaptureShow : public CaptureListener
//{
//	Capture *m_capture;
//	VideoViewer *m_viewer;
//	CommandSocket &m_commandSocket;
//public:
//	CaptureShow(Capture *c, CommandSocket &cs) : m_capture(c), m_commandSocket(cs) {
//	}
//
//	~CaptureShow()
//	{
//		m_capture->stopVideoCapture();
//		delete m_viewer;
//	}
//
//	void start() {
//		m_viewer = new CVVideoViewer();
//		m_capture->setListener(this);
//		m_viewer->initWindow("USB Video");
//		m_capture->startVideoCapture();
//	};
//
//	void VideoReceived(VideoFrame *vf)
//	{
//		IplImage *original = vf->getImage();
//		IplImage *gray_image = cvCreateImage(cvSize(original->width, original->height), IPL_DEPTH_8U, 1);
//		IplImage *resized = cvCreateImage(cvSize(160, 120), IPL_DEPTH_8U, 1);
//
//		cvCvtColor(original, gray_image, CV_RGB2GRAY);
//		cvResize(gray_image, resized);
//
//		for (int i=0;i<resized->height;i++) {
//			for (int j=0;j<resized->width;j++) {
//				CvScalar val;
//				val = cvGet2D(resized, i, j);
//				val.val[0] = (int) val.val[0] & 0xF0;
//				cvSet2D(resized, i, j, val);
//			}
//		}
//
//		VideoFrame new_vf(resized);
//		m_viewer->showFrame(new_vf);
//
//		m_commandSocket.SendVideo(new_vf);
//		delete vf;
//		cvReleaseImage(&gray_image);
//		cvReleaseImage(&resized);
//
//		/** wait 2 seconds */
//		Sleep(500);
//	}
//
//	void AudioReceived(AudioFrame *af)
//	{
//	}
//};
//
// ----------------------------------------------------------------------------
// event tables and other macros for wxWidgets
// ----------------------------------------------------------------------------


// ----------------------------------------------------------------------------
// main frame
// ----------------------------------------------------------------------------



#if 0
int main(int argv, char **argc)
{
	char *hostname;
	char *src_address;
	if (argv < 3) {
		hostname = "192.168.1.108";
		src_address = "192.168.1.108";
		printf("no hostname specified, using hostname = %s\n", hostname);
	} else {
		hostname = argc[1];
		src_address = argc[2];
		printf("Hostname: %s\n", hostname);
	}

	CommandSocket command;

	int receive_port=7658;
	int ref_port=7648;

	command.Connect(hostname, ref_port, src_address, receive_port);


	Capture *c = new Capture;
	if (!c->init()) {
		printf("Failed to capture from camera, using simple file ...\n");
		c->useFile("test.jpg");
	}
	CaptureShow *cs = new CaptureShow(c, command);
	cs->start();

	cvWaitKey(0);

	printf("Key pressed!\n");
	



	printf("delete Capture Show ....\n");
	delete cs;
	printf("delete Capture ....\n");
	delete c;

	printf("End!\n");
	return 0;
}
#endif

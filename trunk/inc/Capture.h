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

#ifndef _OPENCU_CAPTURE_H_
#define _OPENCU_CAPTURE_H_

class VideoFrame;
class AudioFrame;

/* abstract */ class CaptureListener
{
public:
	virtual void VideoReceived(VideoFrame *vf) = 0;
	virtual void AudioReceived(AudioFrame *af) = 0;
};

typedef std::list<CaptureListener *> CaptureListenerList;
class CaptureThread : public wxThread
{
	bool m_running;
	CvCapture *m_capture;
	CaptureListenerList m_listenerList;

	IplImage *m_constantImage;

	wxMutex *m_stopMutex;
    wxCondition *m_stopCondition;
public:
	CaptureThread();
	void stop();
	void start();
	ExitCode Entry();
	void loadFile(char *filename);

	void addListener(CaptureListener *l)
	{
		m_listenerList.push_back(l);
	}

	void setCapture(CvCapture *cvc)
	{
		m_capture = cvc;
	}
};


class Capture 
{
	CaptureThread *m_captureThread;
public:
	Capture();
	~Capture();

	bool init();
	void useFile(char *filename);

	bool startVideoCapture();
	void stopVideoCapture();

	void addListener(CaptureListener *l);
};

#endif


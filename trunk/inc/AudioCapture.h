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

#ifndef _OPENCU_AUDIO_CAPTURE_H_
#define _OPENCU_AUDIO_CAPTURE_H_

#include "Capture.h"


class AudioCapture 
{
	CaptureListenerList m_listeners;
	PaStream*           m_stream;

	static int recordCallback( const void *inputBuffer, void *outputBuffer,
		unsigned long framesPerBuffer, const PaStreamCallbackTimeInfo* timeInfo,
		PaStreamCallbackFlags statusFlags, void *userData );
public:
	AudioCapture();
	~AudioCapture();

	bool startAudioCapture();
	void stopAudioCapture();

	void addListener(CaptureListener *l);
};


#endif //_OPENCU_AUDIO_CAPTURE_H_


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

#ifndef __OPEN_CU_AUDIO_PLAYER_H__
#define __OPEN_CU_AUDIO_PLAYER_H__

typedef std::queue<AudioFrame *> AudioFrameQueue;

class AudioPlayer {
	PaStream *m_stream;

	SAMPLE *audioSamples;
	bool m_bufferFilled;

	wxMutex m_samplesMutex;
	AudioFrameQueue m_samplesQueue;

	static int playCallback( const void *inputBuffer, void *outputBuffer,
                         unsigned long framesPerBuffer,
                         const PaStreamCallbackTimeInfo* timeInfo,
                         PaStreamCallbackFlags statusFlags,
                         void *userData );

public:
	AudioPlayer();
	~AudioPlayer();

	void addBufferForPlayback(AudioFrame *af);
	
	/* return false if it could not start playback */
	bool startPlayback();
	void stopPlayback();
};

#endif


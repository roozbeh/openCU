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
#include "CommonDefinitions.h"
#include "AudioFrame.h"
#include "AudioPlayer.h"

AudioPlayer::AudioPlayer()
{
	audioSamples = new SAMPLE[FRAMES_PER_BUFFER];
	m_bufferFilled = false;
	memset(audioSamples, 0x00, FRAMES_PER_BUFFER * sizeof(SAMPLE));
}

AudioPlayer::~AudioPlayer()
{
	delete audioSamples;
}

void AudioPlayer::addBufferForPlayback(AudioFrame *af)
{
	m_samplesMutex.Lock();
	if (m_samplesQueue.size() > 10) {
		//queue full
		while(!m_samplesQueue.empty()) {
			delete m_samplesQueue.front();
			m_samplesQueue.pop();
		}
	}

	m_samplesQueue.push(af);
	m_samplesMutex.Unlock();
}

/* This routine will be called by the PortAudio engine when audio is needed.
** It may be called at interrupt level on some machines so don't do anything
** that could mess up the system like calling malloc() or free().
*/
int AudioPlayer::playCallback( const void *, void *outputBuffer,
                         unsigned long framesPerBuffer,
                         const PaStreamCallbackTimeInfo* ,
                         PaStreamCallbackFlags ,
                         void *userData )
{
    AudioPlayer *thisp = (AudioPlayer*) userData;

	assert(framesPerBuffer == FRAMES_PER_BUFFER);

	thisp->m_samplesMutex.Lock();
	if (!thisp->m_samplesQueue.empty()) {
		AudioFrame *fp = thisp->m_samplesQueue.front();
		memcpy(outputBuffer, fp->getData(), FRAMES_PER_BUFFER * sizeof(SAMPLE));
		delete fp;
		thisp->m_samplesQueue.pop();
	} else {
		memset(outputBuffer, 0x00, FRAMES_PER_BUFFER * sizeof(SAMPLE));
	}
	thisp->m_samplesMutex.Unlock();

    return paContinue;
}


bool AudioPlayer::startPlayback()
{
	PaStreamParameters  outputParameters;
	PaError err;

	err = Pa_Initialize();

    outputParameters.device = Pa_GetDefaultOutputDevice(); /* default output device */
    outputParameters.channelCount = NUM_CHANNELS;
    outputParameters.sampleFormat =  PA_SAMPLE_TYPE;
    outputParameters.suggestedLatency = Pa_GetDeviceInfo( outputParameters.device )->defaultLowOutputLatency;
    outputParameters.hostApiSpecificStreamInfo = NULL;

	err = Pa_OpenStream(
              &m_stream,
              NULL, /* no input */
              &outputParameters,
              SAMPLE_RATE,
              FRAMES_PER_BUFFER,
              paClipOff,      /* we won't output out of range samples so don't bother clipping them */
              playCallback,
              this );
	if( err != paNoError ) return false;

    err = Pa_StartStream( m_stream );
    if( err != paNoError ) return false;

	return true;
}

void AudioPlayer::stopPlayback()
{
	Pa_CloseStream( m_stream );
}


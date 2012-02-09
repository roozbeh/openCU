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
#include "AudioCapture.h"
#include "AudioFrame.h"
#include "CommonDefinitions.h"

AudioCapture::AudioCapture()
{
}

AudioCapture::~AudioCapture()
{
}

/* This routine will be called by the PortAudio engine when audio is needed.
** It may be called at interrupt level on some machines so don't do anything
** that could mess up the system like calling malloc() or free().
*/
int AudioCapture::recordCallback( const void *inputBuffer, void * /*outputBuffer*/,
                           unsigned long framesPerBuffer,
                           const PaStreamCallbackTimeInfo* /*timeInfo*/,
                           PaStreamCallbackFlags /*statusFlags*/,
                           void *userData )
{
    AudioCapture *thisp = (AudioCapture *) userData;
	short *audio_data = new short[framesPerBuffer];

    if( inputBuffer == NULL )
    {
		memset(audio_data, SAMPLE_SILENCE, framesPerBuffer * sizeof(SAMPLE));
    }
    else
    {
		memcpy(audio_data, inputBuffer, framesPerBuffer * sizeof(SAMPLE));
    }

	AudioFrame af((unsigned char *) audio_data, framesPerBuffer * sizeof(SAMPLE));

	for (CaptureListenerList::iterator i=thisp->m_listeners.begin();
		i!=thisp->m_listeners.end();
		i++) 
	{
		CaptureListener *cl = *i;
		cl->AudioReceived(&af);
	}

	delete[] audio_data;

    return paContinue;
}

bool AudioCapture::startAudioCapture()
{
	PaStreamParameters  inputParameters;
    PaError             err = paNoError;

	err = Pa_Initialize();
	if( err != paNoError ) {
		printf("Could not Initialize Port Audio\n");
		return false;
	}

    inputParameters.device = Pa_GetDefaultInputDevice(); /* default input device */
    inputParameters.channelCount = NUM_CHANNELS;                    /* stereo input */
    inputParameters.sampleFormat = PA_SAMPLE_TYPE;
    inputParameters.suggestedLatency = Pa_GetDeviceInfo( inputParameters.device )->defaultLowInputLatency;
    inputParameters.hostApiSpecificStreamInfo = NULL;

    /* Record some audio. -------------------------------------------- */
    err = Pa_OpenStream(
              &m_stream,
              &inputParameters,
              NULL,                  /* &outputParameters, */
              SAMPLE_RATE,
              FRAMES_PER_BUFFER,
              paClipOff,      /* we won't output out of range samples so don't bother clipping them */
              recordCallback,
              this );

    if( err != paNoError ) 
    {
        printf("Could not Open Stream (Port Audio)\n");
		return false;
    }

    err = Pa_StartStream( m_stream );
    if( err != paNoError ) {
        printf("Could not Start Stream (Port Audio)\n");
		return false;
	}

    printf("Stream started\n");
	return true;
}

void AudioCapture::stopAudioCapture()
{
    Pa_CloseStream( m_stream );
}

void AudioCapture::addListener(CaptureListener *l)
{
	m_listeners.push_back(l);
}




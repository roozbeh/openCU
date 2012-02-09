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

#ifndef __OPEN_CU_AUDIO_FRAME_H__
#define __OPEN_CU_AUDIO_FRAME_H__

class AudioFrame 
{
	unsigned char *m_audio_data;
	unsigned long m_timestamp; ///< Length of audio
	int m_data_len;
	bool m_own_buffer;
public:
	AudioFrame(unsigned char *data, int len, bool ownBuffer = false) : 
	  m_audio_data(data), m_data_len(len), m_own_buffer(ownBuffer) {
		  m_timestamp = m_data_len * 1000 / 8000;
	};

	~AudioFrame() {
		if (m_own_buffer) 
			delete m_audio_data;
	};

	unsigned char *getData() {return m_audio_data;};
	unsigned long getTimestamp() {return m_timestamp;};
	int getSize() {return m_data_len;};
};

#endif //__OPEN_CU_AUDIO_FRAME_H__


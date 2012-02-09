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
#include "AudioCodec.h"
#include "AudioFrame.h"

unsigned char *RawAudioCodec::compressFrame(AudioFrame *input, int *len)
{
	unsigned char *retbuf = new unsigned char[input->getSize()];
	memcpy(retbuf, input->getData(), input->getSize());
	*len = input->getSize();

	return retbuf;
}

AudioFrame *RawAudioCodec::decompressFrame(unsigned char *vbuf, int buflen)
{
	return new AudioFrame(vbuf, buflen);
}

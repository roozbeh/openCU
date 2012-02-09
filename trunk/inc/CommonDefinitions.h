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

#ifndef __OPENCU_COMMON_DEFINITIONS_H__
#define __OPENCU_COMMON_DEFINITIONS_H__

// IDs for the controls and the menu commands
enum
{
    Minimal_Run = wxID_HIGHEST + 1,
	menuReflectorConnectID,
	menuReflectorDisconnectID,
	menuReflectorSendVideo,
	menuReflectorSendAudio,
	menuLocalCaptureVideo,
	menuLocalCaptureAudio,
	MainFrameTreeControl, 
	MainFrameKeepAliveTimer,
	MainFrameVoiceGauge,
	MainFrameStatusBarTimer,
	MainFrameTextChatBox, 
	MainFrameSendTextBox, 
	MainFrameSendTextButton, 
	ConnectDlgHostIP,
	ConnectDlgHostPort,
	ConnectDlgLocalIP,
	ConnectDlgLocalPort,
	ConnectDlgConnectButton,
	ConnectDlgCancelButton,
	ConnectDlgStaticText,
};

/* Audio Definitions */
#define SAMPLE_RATE  (8000)
//#define FRAMES_PER_BUFFER (1024)
#define FRAMES_PER_BUFFER (800)
#define NUM_CHANNELS    (1)
#define DITHER_FLAG     (0) /**/

#define PA_SAMPLE_TYPE  paInt16
typedef short SAMPLE;
#define SAMPLE_SILENCE  (0)
#define PRINTF_S_FORMAT "%d"


#endif //__OPENCU_COMMON_DEFINITIONS_H__


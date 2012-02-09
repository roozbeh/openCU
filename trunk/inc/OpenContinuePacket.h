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

#ifndef __OPENCU_OPEN_CONTINUE_PACKET_H__
#define __OPENCU_OPEN_CONTINUE_PACKET_H__

#include "Packet.h"
#include "Client.h"
#include "ExtraHeader.h"

typedef enum {
	No_SendVideo = 0,
	Small_Video = 1, /*(160x120)*/
	Big_Video = 2, /*(240x320)*/
	WhitePine_ColorVideo = 3,
} SendMode;

typedef enum {
	No_RcvVideo = 0,
	With_Video = 1,
} ReceiveMode;

typedef enum {
	NoFlags = 0x00,
	FlagsReceiveAudio = 0x01,
	TransmitAudio = 0x02,
	AudioFromLurker = 0x04,
	WindowsMachine = 0x08,
	PrivateAudio = 0x10,
	SendVersionInformation = 0x20,
	AccepCloseMyVideo = 0x80,
} Flags;

class OpenContinuePacket : public Packet {
	short client_count;

	long opencontinue_seq;
	char username_len;
	char username[19];
	SendMode send_mode; /* char */
	ReceiveMode rcv_mode;  /* char */
	Flags flags; /* char */
	char version;

	/** Activated clients which we intend to receive data from */
	ClientInfoList m_activeClientInfos;

	/** Extra header info */
	RateReportHeader m_rateReport;
public:
	OpenContinuePacket(short dp,
		SrcFamily sf, short sp, long sa, long seq, MessageType mt, DataType dt,
		std::string username, SendMode sm, ReceiveMode rm);

	OpenContinuePacket(char *buffer, int len);
	~OpenContinuePacket();

	char *getBuffer();
	int getSize();

	void addClientToList(ClientInfo *cf);

	std::string getUserName() {return username;};
	ClientInfoList& getClientList() {return m_activeClientInfos;};
};

#endif


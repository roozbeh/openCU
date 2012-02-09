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

#ifndef __OPENCU_CLIENT_H__
#define __OPENCU_CLIENT_H__

typedef enum {
	NoCIFlags = 0x00,
	UpdateVideo = 0x01,
	UpdateAudio = 0x02,
	CIReceiveAudio = 0x04,
	UpdateAuxData = 0x08,
	CloseVideo = 0x10,
} CIFlags;

typedef enum {
	DontWantReceiveVideo = 0,
	WantReceiveVideo = 1,
} CIReceive;

typedef enum {
	DontWantSendVideo = 0,
	WantSendVideo = 1,
} CISend;

class ClientInfo {
	friend class OpenContinuePacket;

	long ipAddress;
	CIFlags flags;  //char
	char auxPrune;
	CIReceive receive; //char
	CISend send; //char
	short packetSent;
	short packetReceived;

	//not networking fields
	std::string m_username;
	bool m_isSeeingUs;
public:
	ClientInfo(long ip, std::string username);
	ClientInfo(char *buffer);
	static int getSize() { return 12; };

	long getIPAddress() {return ipAddress;};
	void EnableReceive();

	void resetPacketReceived() {packetReceived = 1;};
	void incPacketReceived() {packetReceived++;};

	std::string getUsername() {return m_username;};
	void hasSeeingUs(bool val) {m_isSeeingUs = val;};
	bool isSeeingUs() {return m_isSeeingUs;};
	bool isReceivingVideo() {return receive == WantReceiveVideo;};
};

typedef std::list<ClientInfo *> ClientInfoList;

#endif //__OPENCU_CLIENT_H__


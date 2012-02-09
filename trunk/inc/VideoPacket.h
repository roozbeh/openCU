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

#ifndef __OPENCU_VIDEO_PACKET_H_
#define __OPENCU_VIDEO_PACKET_H_

#include "Packet.h"
#include "VideoFrame.h"

class VideoDataBlob {
	long seq;
	char *data;
	short data_size;
public:
	VideoDataBlob(char *d, short size, long s) : data_size(size), seq(s) {
		data = new char[size];
		memcpy(data, d, size);
	};
	~VideoDataBlob() {
		delete data;
	}
	int getSize() {return data_size;};
	char *getData() {return data;};
	long getSequence() {return seq;};
};

typedef std::list<VideoDataBlob *> VideoDataBlobList;
typedef std::map<long, VideoDataBlobList *> VideoDataBlobListMap;

class VideoPacket : public Packet {
	unsigned char *m_videoBuffer;
	int m_videoBufferLen;
	bool m_shouldFreed;

	static VideoDataBlobListMap m_dataBlobMap;
	static long sCurrentSequence;
public:
	VideoPacket(short dp, long da, 
				 SrcFamily sf, short sp, long sa, 
				unsigned char *buf, int buflen);
	VideoPacket(char *samplePacketBytes, int packetLen, 
				char *imageData, int imageLen);
	virtual ~VideoPacket();

	char *getBuffer();
	int getSize();

	/** get number of packets which this packet should transfer with - for example
	 *  if size of this packet is 10,000 bytes, it should transfer by 5 different packets*/
	int getNumPackets();

	/** get binary data of i-th packet */
	char *getPacketData(int index, int &packetSize);

	static VideoPacket *concatBlobs(char *buffer, int len, MessageType msgType);

	unsigned char *getVideoBuffer() { return m_videoBuffer; };
	int getVideoBufferLen() { return m_videoBufferLen; };

};

#endif //__OPENCU_VIDEO_PACKET_H_


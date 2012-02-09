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
#include "VideoFrame.h"
#include "VideoPacket.h"
#ifdef HAVE_ARPA_INET_H
#include <arpa/inet.h>
#endif

VideoDataBlobListMap VideoPacket::m_dataBlobMap;
long VideoPacket::sCurrentSequence = 0;

VideoPacket::VideoPacket(short dp, long da, SrcFamily sf, short sp, long sa, 
						 unsigned char *buf, int buflen) : 
							Packet (ForwardToAll, 
							dp, da, sf, sp, sa, ++sCurrentSequence, 
							FrameEndMessage, SmallVideo), m_videoBuffer(buf),
							m_videoBufferLen(buflen), m_shouldFreed(false)
{
}

VideoPacket::VideoPacket(char *samplePacketBytes, int packetLen, char *imageData, int imageLen) : Packet(samplePacketBytes, packetLen)
{
	m_videoBuffer = new unsigned char[imageLen];
	m_videoBufferLen = imageLen;
	m_shouldFreed = true;

	memcpy(m_videoBuffer, imageData, imageLen);
}

VideoPacket::~VideoPacket()
{
	//NOP
	if (m_shouldFreed) {
		delete [] m_videoBuffer;
	}
}

char *VideoPacket::getBuffer()
{
	assert(FALSE);
	return m_buffer;

}

int VideoPacket::getSize()
{
	assert(FALSE);

	return m_size;
}

/** get number of packets which this packet should transfer with - for example
 *  if size of this packet is 10,000 bytes, it should transfer by 5 different packets*/
int VideoPacket::getNumPackets()
{
	int availableSizeInPacket = Packet::getMaxSize() - Packet::getSize() - 2 /*video size - short */;
	return (m_videoBufferLen / availableSizeInPacket) + 
		((m_videoBufferLen % availableSizeInPacket) ? 1 : 0);
}

/** get binary data of i-th packet */
char *VideoPacket::getPacketData(int index, int &packetSize)
{
	short availableSizeInPacket = Packet::getMaxSize() - Packet::getSize() - 2;
	int offset = availableSizeInPacket * index;
	int end = (availableSizeInPacket) * (index + 1);
	if ( (end > m_videoBufferLen) && (offset < m_videoBufferLen) ) {
		int vSize = m_videoBufferLen - offset;
		allocBuffer(Packet::getSize() + vSize + 2);
		char *target = m_buffer;

		msg_type = FrameEndMessage;
		FlushHeader2Buffer(target);
		Add2Buffer(target, (short) vSize);
		Add2Buffer(target, (char *) m_videoBuffer + offset, vSize);

		packetSize = Packet::getSize() + vSize + 2;

		return m_buffer;
	}

	allocBuffer(Packet::getMaxSize());
	char *target = m_buffer;
	msg_type = MorePacketToCome;

	FlushHeader2Buffer(target);
	Add2Buffer(target, availableSizeInPacket);
	Add2Buffer(target, (char *) m_videoBuffer + offset, availableSizeInPacket);

	packetSize = Packet::getMaxSize();

	return m_buffer;
}



VideoPacket *VideoPacket::concatBlobs(char *buffer, int len, MessageType msgType)
{

	/* parse basic data */
	long srcAddress = *((long *) (buffer +  12)) ;
	long sq = ntohl(*((long *) (buffer +  16)));

	short video_size = ntohs(*((long *) (buffer +  26)));
	char *video_data = buffer + 28;

	if (m_dataBlobMap.find(srcAddress) == m_dataBlobMap.end()) {
		//we have not any record for this client 
		VideoDataBlobList *dataList = new VideoDataBlobList;
		m_dataBlobMap[srcAddress] = dataList;
	}

	VideoDataBlob *blob = new VideoDataBlob(video_data, video_size, sq);
	VideoDataBlobList *dataList = m_dataBlobMap[srcAddress];

	/** checking for current blob sequences - drop old sequences */
	for (VideoDataBlobList::iterator i=dataList->begin();i!=dataList->end();) {
		VideoDataBlob *blob = *i;
		if (sq != blob->getSequence()) {
			VideoDataBlobList::iterator temp = i;
			delete blob;
			i++;
			dataList->erase(temp);
		} else {
			i++;
		}
	}
	dataList->push_back(blob);

	if (msgType == FrameEndMessage) {
		int sumSize = 0;
		char *totalBuffer = NULL;
		for (VideoDataBlobList::iterator i=dataList->begin();i!=dataList->end();i++) {
			VideoDataBlob *blob = *i;
			sumSize += blob->getSize();
		}

		/* 160 x 120 = 19200 */
		/* 80 x 120 = 9600 */
		/* if (sumSize == 9600) */ { 
			totalBuffer = new char[sumSize];
			char *cursor = totalBuffer;
			for (VideoDataBlobList::iterator i=dataList->begin();i!=dataList->end();i++) {
				VideoDataBlob *blob = *i;
				memcpy(cursor, blob->getData(), blob->getSize());
				cursor+=blob->getSize();
			}
		}

		for (VideoDataBlobList::iterator i=dataList->begin();i!=dataList->end();) {
			VideoDataBlobList::iterator temp = i;
			VideoDataBlob *blob = *i;
			delete blob;
			i++;
			dataList->erase(temp);
		}

		if (totalBuffer) {
			VideoPacket *ret = new VideoPacket(buffer, len, totalBuffer, sumSize);
			delete [] totalBuffer;
			return ret;
		}
	}
	
	return NULL;
}


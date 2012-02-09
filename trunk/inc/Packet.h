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
#ifndef _OPENCU_PACKET_H_
#define _OPENCU_PACKET_H_

typedef enum {
	ForwardToAll = 0x00,
	ForwardToSpecific = 0x01,
	ExamineAndForward = 0x02,
} DestFamily;

typedef enum {
	FamilyReserved = 0x00,
	FamilyClientOriginated = 0x01,
	ReflectorOriginated = 0x02,
} SrcFamily;

typedef enum {
	OpenConnection = 1,
	AcknowledgeConnection = 2,
	CloseConnection = 6,
	MorePacketToCome = 0,
	FrameEndMessage = 20,
	WaitAcknowledge = 0xffff,
} MessageType;

typedef enum {
	SmallVideo = 1, /*160x120*/
	BigVideo = 2, /*320x240*/
	Audio = 3,
	VersionReportExtra = 4,
	Acknowledge = 100,
	OpenContinue = 101,
	KickOffClient = 104,
	WelcomeClient = 105,
	ReflectorExchange = 106,
	AuxStreamData = 107,
	OldRateControlType = 108,
	OldRateReplyType = 109,
	DataRateControlReq = 110,
	DataRateControlResp = 111,
    CUWPVIDEO1 = 200,
	CU204 = 204,
	AuxDataControlMessage = 256,
	AuxDataPacketType = 257,
    CUWPVIDEO2 = -32568,
    CUPMREFOC = 21760,
    CUPMREFDATA = 21761,
    CUPMREFDENY = 21762,
    CUPMREFMSG = 21763,
} DataType;


/* abstract */ class Packet {
	DestFamily dest_family;
	short dest_port;
	long dest_addr;

	SrcFamily src_family;
	short src_port;
	long src_addr;
protected:
	long seq_number;

	MessageType msg_type; /* MessageType - short*/
	DataType data_type; /* Data Type - short */
	short packet_length; 

	char *m_buffer;
	int m_size;

	void Add2Buffer(char*& target, char x);
	void Add2Buffer(char*& target, short x);
	void Add2Buffer(char*& target, long x);
	void Add2Buffer(char*& target, char *buf, int len);

	/* don't call htonl */
	void Add2BufferNoConv(char*& target, long x);
	void Add2BufferNoConv(char*& target, short x);

	void allocBuffer(int size);

	/** Flush packet to a buffer */
	void FlushHeader2Buffer(char*& target);

	void incSeqNumber() {seq_number++;};
	void applySeqNumber(char *m_buffer);
public:
	Packet(DestFamily df, short dp, long da, 
		SrcFamily sf, short sp, long sa, long seq, MessageType mt, DataType dt);
	Packet(char *buffer, int len);
	virtual ~Packet();

	int getSize() { return 26; };
	virtual char *getBuffer() = 0; /* need to be implemented by child */

	void setPacketSize(int size);

	int getMaxSize() { return 1800; };

	static Packet *parseBlob(char *buffer, int len);

	long getSourceAddress() {return src_addr;};
};

#endif //_OPENCU_PACKET_H_


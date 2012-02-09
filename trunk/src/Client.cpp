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
#include "Client.h"


ClientInfo::ClientInfo(long ip, std::string username) : ipAddress(ip), flags(NoCIFlags), auxPrune(0), 
	receive(DontWantReceiveVideo), send(DontWantSendVideo), 
	packetSent(1), packetReceived(0), m_username(username), m_isSeeingUs(false)  {};

ClientInfo::ClientInfo(char *buffer)
{
	ipAddress = *((long *) buffer);
	flags = (CIFlags) *((char *) (buffer + 4));;
	auxPrune = *((char *) (buffer + 5));;
	receive = (CIReceive) *((char *) (buffer + 6));;
	send = (CISend) *((char *) (buffer + 7));;
	packetSent = *((short *) (buffer + 8));;
	packetReceived = *((short *) (buffer + 10));;
}

void ClientInfo::EnableReceive() {
	receive = WantReceiveVideo; 
	flags = (CIFlags) (UpdateVideo | CIReceiveAudio | UpdateAudio);
	packetSent = 11 * 6; /* fps * seconds * num packets */
};

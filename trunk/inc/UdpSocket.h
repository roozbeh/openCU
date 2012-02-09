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

#ifndef __OPENCU_UDP_SOCKET_H_
#define __OPENCU_UDP_SOCKET_H_

#ifndef WIN32
typedef int SOCKET;
#endif

#ifdef HAVE_NETINET_IN_H
#include <netinet/in.h>
#endif

class UdpSocket {
	static bool is_inited;
#ifdef WIN32
	static WSADATA wsaData;
#endif

	/** boolan value specifies this socket is connected to target socket or not */
	bool m_isConnected;

	/** object socket entity */
	SOCKET m_Socket;;

	/** local address */
	sockaddr_in m_localAddr;

	/** target machine address */
	sockaddr_in m_targetAddr;

	short m_local_port;

	/** 
	 * global initalization
	 */
	static void globalInit();

	unsigned long m_totalSent;
public:
	/** constructor */
	UdpSocket();

	/**
	 * Open connection to target computer at specified port
	 */
	bool OpenConnection(std::string target_addr, short port);

	/**
	 * Open connection to target computer at specified port
	 */
	bool OpenConnection(long target_addr, short port);

	/**
	 * Send data
	 */
	bool SendData(char * data, int len);

	/**
	 * Bind to specified address and port
	 */
	bool Bind(std::string local_addr, short port);
	bool Bind(long local_addr, short port);

	/**
	 * Recv data (when binded to a port)
	 *
	 * @return number of bytes received
	 */
	int RecvData(char *buffer, int buffer_len);

	/* resolve address */
	static long getLongAddress(std::string address);

	/** get local port of this address */
	short getLocalPort() {return m_local_port;};

	void CloseConnection();

	static long getLocalAddress();

	unsigned long getTotalSent() {return m_totalSent;};
};

#endif //__OPENCU_UDP_SOCKET_H_


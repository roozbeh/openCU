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
#include "UdpSocket.h"
#ifdef HAVE_ARPA_INET_H
#include <arpa/inet.h>
#endif
#ifdef HAVE_SYS_SOCKET_H
#include <sys/socket.h>
#endif
#ifdef HAVE_NETDB_H
#include <netdb.h>
#endif

#ifndef SOCKADDR
typedef struct sockaddr SOCKADDR;
#endif

bool UdpSocket::is_inited = false;
#ifdef WIN32
WSADATA UdpSocket::wsaData;
#endif

UdpSocket::UdpSocket() : m_isConnected(0) , m_totalSent(0)
{
	if (!is_inited)  {
		globalInit();
	}
};


void UdpSocket::globalInit()
{
#ifdef WIN32
	WSAStartup(MAKEWORD(2,2), &wsaData);
#endif
}

bool UdpSocket::OpenConnection(std::string target_addr, short port)
{
	return OpenConnection(inet_addr(target_addr.c_str()), port);
}

bool UdpSocket::OpenConnection(long target_addr, short port)
{
	m_Socket = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);

	m_targetAddr.sin_family = AF_INET;
	m_targetAddr.sin_addr.s_addr = target_addr;
	m_targetAddr.sin_port = htons(port);

	int ret = connect(m_Socket, (SOCKADDR*) &m_targetAddr, sizeof(m_targetAddr));

	{
		sockaddr_in server;
		int len = sizeof ( server );
		if ( getsockname ( m_Socket, (SOCKADDR*) &server, 
#ifndef WIN32
			(socklen_t *) 
#endif
			&len ) >= 0 )
			m_local_port = ntohs ( ((struct sockaddr_in *)&server)->sin_port );

	}

	return ret ? true : false;
}

void UdpSocket::CloseConnection()
{
#ifdef WIN32
	closesocket(m_Socket);
#else
	close(m_Socket);
#endif
}

bool UdpSocket::SendData(char *data, int len)
{
	int ret = sendto(m_Socket, 
		data, 
		len, 
		0, 
		(SOCKADDR *) &m_targetAddr, 
		sizeof(m_targetAddr));

	m_totalSent += len;

	return (ret == len) ? true : false;
}


bool UdpSocket::Bind(std::string local_addr, short port)
{
	return Bind(inet_addr(local_addr.c_str()), port);
}

bool UdpSocket::Bind(long local_addr, short port)
{
	m_localAddr.sin_family = AF_INET;
	m_localAddr.sin_addr.s_addr = local_addr;
	m_localAddr.sin_port = htons(port);

	int ret = bind(m_Socket, (SOCKADDR*) &m_localAddr, sizeof(m_localAddr));
	return (ret == 0) ? true : false;
}

int UdpSocket::RecvData(char *buffer, int buffer_len)
{
	int targetAddrSize = sizeof(m_targetAddr);
	int ret = recvfrom(m_Socket, 
		buffer, 
		buffer_len, 
		0, 
		(SOCKADDR *)&m_targetAddr, 
#ifndef WIN32
		(socklen_t *) 
#endif
		&targetAddrSize);

	return ret;
}

long UdpSocket::getLongAddress(std::string address)
{
	return inet_addr(address.c_str());
}

long UdpSocket::getLocalAddress()
{
/*	hostent* thisHost;

	thisHost = gethostbyname("localhost.localdomain");
	printf("this host: %p\n", thisHost);
	return inet_addr(inet_ntoa(*(struct in_addr *)*thisHost->h_addr_list));
*/

	char szHostName[255];
	struct hostent *host_entry;
	struct in_addr addr;

	gethostname(szHostName, 255);
	host_entry = (struct hostent *) 
		gethostbyname((const char *) szHostName);

	addr = *(struct in_addr *)*host_entry->h_addr_list;	
	return addr.s_addr;
}

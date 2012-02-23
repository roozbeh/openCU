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
#include "ConnectDlg.h"
#include "CommonDefinitions.h"

using namespace std;

BEGIN_EVENT_TABLE(ConnectDialog, wxDialog)
    EVT_BUTTON(ConnectDlgConnectButton, ConnectDialog::OnConnect)
    EVT_BUTTON(ConnectDlgCancelButton, ConnectDialog::OnCancel)
END_EVENT_TABLE()

ConnectDialog::ConnectDialog(wxWindow *parent) : wxDialog(parent, 
														  wxID_ANY, 
														  wxString(_T("Connect")))
{
    //wxBoxSizer *topSizer = new wxBoxSizer( wxVERTICAL );
    wxBoxSizer *item0 = new wxBoxSizer( wxVERTICAL );

    //// Host IP
    wxBoxSizer* itemSizer3 = new wxBoxSizer( wxHORIZONTAL );
	m_hostIP = new wxTextCtrl( this, ConnectDlgHostIP, "opencu://ipsix.org", wxDefaultPosition ); //91.184.33.25
	m_hostIP->Enable(false);
	itemSizer3->Add(new wxStaticText(this, ConnectDlgStaticText, _("Reflector:")), 0, wxALL|wxALIGN_CENTER_VERTICAL, 5);
    itemSizer3->Add(m_hostIP, 0, wxALL|wxALIGN_CENTER_VERTICAL, 5);
    item0->Add(itemSizer3, 0, wxGROW|wxALL, 0);

    wxBoxSizer* itemSizer2 = new wxBoxSizer( wxHORIZONTAL );
	m_userName = new wxTextCtrl( this, ConnectDlgHostIP, "username", wxDefaultPosition );
	itemSizer2->Add(new wxStaticText(this, ConnectDlgStaticText, _("Username:")), 0, wxALL|wxALIGN_CENTER_VERTICAL, 5);
    itemSizer2->Add(m_userName, 0, wxALL|wxALIGN_CENTER_VERTICAL, 5);
    item0->Add(itemSizer2, 0, wxGROW|wxALL, 0);

#if 0
    //// Host Port
    wxBoxSizer* itemSizer2 = new wxBoxSizer( wxHORIZONTAL );
	m_hostPort = new wxTextCtrl( this, ConnectDlgHostPort, "7648", wxDefaultPosition );
	itemSizer2->Add(new wxStaticText(this, ConnectDlgStaticText, _("Host Port:")), 0, wxALL|wxALIGN_CENTER_VERTICAL, 5);
    itemSizer2->Add(m_hostPort, 0, wxALL|wxALIGN_CENTER_VERTICAL, 5);
    item0->Add(itemSizer2, 0, wxGROW|wxALL, 0);

    //// Local IP
    wxBoxSizer* itemSizer1 = new wxBoxSizer( wxHORIZONTAL );
	m_localIP = new wxTextCtrl( this, ConnectDlgLocalIP, "192.168.1.108", wxDefaultPosition );
	itemSizer1->Add(new wxStaticText(this, ConnectDlgStaticText, _("Local IP:")), 0, wxALL|wxALIGN_CENTER_VERTICAL, 5);
    itemSizer1->Add(m_localIP, 0, wxALL|wxALIGN_CENTER_VERTICAL, 5);
    item0->Add(itemSizer1, 0, wxGROW|wxALL, 0);

    //// Local port
    wxBoxSizer* itemSizer0 = new wxBoxSizer( wxHORIZONTAL );
	m_localPort = new wxTextCtrl( this, ConnectDlgLocalPort, "7658", wxDefaultPosition );
	itemSizer0->Add(new wxStaticText(this, ConnectDlgStaticText, _("Local Port:")), 0, wxALL|wxALIGN_CENTER_VERTICAL, 5);
    itemSizer0->Add(m_localPort, 0, wxALL|wxALIGN_CENTER_VERTICAL, 5);
    item0->Add(itemSizer0, 0, wxGROW|wxALL, 0);
#endif

    wxBoxSizer* itemSizer4 = new wxBoxSizer( wxHORIZONTAL );
	wxButton *connectButton = new wxButton(this, ConnectDlgConnectButton, _T("&Connect"));
	wxButton *cancelButton = new wxButton(this, ConnectDlgCancelButton, _T("C&ancel"));
	itemSizer4->Add(connectButton, 0, wxALL|wxALIGN_LEFT, 5);
    itemSizer4->Add(cancelButton, 0, wxALL|wxALIGN_RIGHT, 5);
    item0->Add(itemSizer4, 0, wxGROW|wxALL, 0);

	SetSizer(item0);

	item0->Fit(this);

	SetAffirmativeId(ConnectDlgConnectButton);
	SetEscapeId(ConnectDlgCancelButton);
}

void ConnectDialog::OnConnect(wxCommandEvent& WXUNUSED(event))
{
	EndModal(1);
}

void ConnectDialog::OnCancel(wxCommandEvent& WXUNUSED(event))
{
	//Close(true);
	EndModal(0);
}


string ConnectDialog::getTargetIP()
{
	//return m_hostIP->GetValue().c_str();
	return "91.184.33.25";
}

string ConnectDialog::getReflectorName()
{
	string ret(m_hostIP->GetValue().c_str());
	return ret;
}


int ConnectDialog::getTargetPort()
{
#if 0
	return atoi(m_hostPort->GetValue().c_str());
#else
	//hard coded Reflector port
	return 7648;
#endif
}

string ConnectDialog::getUsername()
{
	string ret(m_userName->GetValue().c_str());
	return ret;
}


#if 0
	std::string getLocalIP();
	int getLocalPort();
string ConnectDialog::getLocalIP()
{
	return m_localIP->GetValue();
}

int ConnectDialog::getLocalPort()
{
	return atoi(m_localPort->GetValue().c_str());
}
#endif


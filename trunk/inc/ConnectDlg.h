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

#ifndef __OPENCU_CONNECT_DIALOG_H__
#define __OPENCU_CONNECT_DIALOG_H__

class ConnectDialog : public wxDialog
{
	wxTextCtrl *m_hostIP;
	wxTextCtrl *m_userName;
	//wxTextCtrl *m_hostPort;
	//wxTextCtrl *m_localIP;
	//wxTextCtrl *m_localPort;

public:
	ConnectDialog(wxWindow *parent);

    void OnConnect(wxCommandEvent& event);
    void OnCancel(wxCommandEvent& event);

	/* resolve IP and return */
	std::string getTargetIP();

	/* return user friendly name */
	std::string getReflectorName();

	int getTargetPort();
	std::string getUsername();
private:
    DECLARE_EVENT_TABLE()
};

#endif //__OPENCU_CONNECT_DIALOG_H__


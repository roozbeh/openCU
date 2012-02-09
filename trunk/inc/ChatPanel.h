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

#ifndef __OPENCU_CHAT_PANEL_H__
#define __OPENCU_CHAT_PANEL_H__

class MainFrame;

class ChatPanel : public wxPanel
{
	wxRichTextCtrl *m_richText;
	wxTextCtrl *m_sendTextBox;
	wxButton *m_sendTextButton;
	bool m_richTextChanged;

	MainFrame *m_parent;
public:
    ChatPanel(MainFrame *frame, int x, int y, int w, int h);
	virtual ~ChatPanel() {};

	void OnSize(wxSizeEvent&);
	void initTextChatItems();

	/**
	  * calls by network manager in case of text message 
	  * received from clients 
	  */
	void textMessageReceived(std::string username, std::string msg);

	/* calls when user wants to send a text message to conference */
	void OnSendButtonClicked(wxCommandEvent& event);

	/** set in online state is val is true and in offline state otherwise */
	void setOnline(bool val);

private:
	DECLARE_EVENT_TABLE()
};

#endif //__OPENCU_CHAT_PANEL_H__
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
#include "ChatPanel.h"
#include "CommonDefinitions.h"
#include "MainFrame.h"

BEGIN_EVENT_TABLE(ChatPanel, wxPanel)
	EVT_SIZE(ChatPanel::OnSize)

	EVT_BUTTON(MainFrameSendTextButton, ChatPanel::OnSendButtonClicked)
END_EVENT_TABLE()

ChatPanel::ChatPanel(MainFrame *frame, int x, int y, int w, int h) : wxPanel( (wxFrame *) frame, wxID_ANY, wxPoint(x, y), wxSize(w, h) )
{
	m_parent = frame;
#if 0	
	m_richText = new wxRichTextCtrl(this, MainFrameTextChatBox);
	m_sendTextBox = new wxTextCtrl(this, MainFrameSendTextBox);
	m_sendTextButton = new wxButton(this, MainFrameSendTextButton, _T("&Send"));
#endif
	initTextChatItems();
}


void ChatPanel::OnSize(wxSizeEvent&
#ifdef __WXUNIVERSAL__
					   event
#else
					   WXUNUSED(event)
#endif
					   )
{
	int w, h;
#if 0	
	GetClientSize(&w, &h);
	m_richText->SetSize(0, 0, w, 4*h/5);
	m_sendTextBox->SetSize(0, 4*h/5, 4*w/5, h/5);
	m_sendTextButton->SetSize(4*w/5, 4*h/5, w/5, h/5);
#endif
}


void ChatPanel::initTextChatItems()
{
#if 0	
    wxRichTextAttr backgroundColourAttr;
    backgroundColourAttr.SetTextColour(*wxGREEN);
    m_richText->BeginStyle(backgroundColourAttr);
    m_richText->WriteText(wxT("You could see text messages of your buddies here!"));
    m_richText->EndStyle();
	m_richText->SetEditable(false);

	m_sendTextButton->Enable(false);
	m_richTextChanged = false;
#endif
}

void ChatPanel::OnSendButtonClicked(wxCommandEvent& )
{
#if 0	
		wxString text = m_sendTextBox->GetValue();
		std::string textStr(text.mb_str());
		
		if (m_parent->SendText(textStr)) {
			if (!m_richTextChanged) {
				m_richText->SetValue("");
				m_richTextChanged = true;
			}

			wxRichTextAttr backgroundColourAttr;
			wxString username = m_parent->getUsername().c_str();
			
			backgroundColourAttr.SetTextColour(*wxGREEN);
			m_richText->BeginStyle(backgroundColourAttr);
			m_richText->WriteText(username);
			m_richText->WriteText(": ");
			m_richText->EndStyle();

			backgroundColourAttr.SetTextColour(*wxBLACK);
			m_richText->BeginStyle(backgroundColourAttr);
			m_richText->WriteText(text);
			m_richText->WriteText("\n");
			m_richText->EndStyle();

			m_richText->ShowPosition(m_richText->FindNextWordPosition() + 1);

			text = "";
			m_sendTextBox->SetValue(text);

			m_sendTextBox->SetFocus();
		} else {
			wxMessageBox(_T("Could not send text to conference!"), 
				_T("Error"),
				wxICON_ERROR | wxOK);
		}
#endif
}

void ChatPanel::textMessageReceived(std::string username, std::string msg)
{
#if 0	
	if (!m_richTextChanged) {
		m_richText->SetValue("");
		m_richTextChanged = true;
	}

	wxRichTextAttr backgroundColourAttr;
	wxString wxUsername = username.c_str();
	wxString wxMsg = msg.c_str();
	
	backgroundColourAttr.SetTextColour(*wxBLUE);
	m_richText->BeginStyle(backgroundColourAttr);
	m_richText->WriteText(wxUsername);
	m_richText->WriteText(": ");
	m_richText->EndStyle();

	backgroundColourAttr.SetTextColour(*wxBLACK);
	m_richText->BeginStyle(backgroundColourAttr);
	m_richText->WriteText(wxMsg);
	m_richText->WriteText("\n");
	m_richText->EndStyle();

	m_richText->ShowPosition(m_richText->FindNextWordPosition() + 1);
#endif
}

void ChatPanel::setOnline(bool val)
{
#if 0	
	m_sendTextButton->Enable(val);
#endif
}

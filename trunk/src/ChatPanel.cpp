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
	m_richText = new wxRichTextCtrl(this, MainFrameTextChatBox);
	m_sendTextBox = new wxTextCtrl(this, MainFrameSendTextBox);
	m_sendTextButton = new wxButton(this, MainFrameSendTextButton, _T("&Send"));

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
	GetClientSize(&w, &h);
	m_richText->SetSize(0, 0, w, 4*h/5);
	m_sendTextBox->SetSize(0, 4*h/5, 4*w/5, h/5);
	m_sendTextButton->SetSize(4*w/5, 4*h/5, w/5, h/5);
}


void ChatPanel::initTextChatItems()
{
    wxRichTextAttr backgroundColourAttr;
    backgroundColourAttr.SetTextColour(*wxGREEN);
    m_richText->BeginStyle(backgroundColourAttr);
    m_richText->WriteText(wxT("You could see text messages of your buddies here!"));
    m_richText->EndStyle();
	m_richText->SetEditable(false);

	m_sendTextButton->Enable(false);
	m_richTextChanged = false;
}

void ChatPanel::OnSendButtonClicked(wxCommandEvent& )
{
		wxString text = m_sendTextBox->GetValue();
		std::string textStr = text.mb_str();
		if (m_parent->SendText(textStr)) {
			if (!m_richTextChanged) {
				m_richText->SetValue("");
				m_richTextChanged = true;
			}

			wxRichTextAttr backgroundColourAttr;
			backgroundColourAttr.SetTextColour(*wxGREEN);
			m_richText->BeginStyle(backgroundColourAttr);
			m_richText->WriteText(m_parent->getUsername());
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
}

void ChatPanel::textMessageReceived(std::string username, std::string msg)
{
	if (!m_richTextChanged) {
		m_richText->SetValue("");
		m_richTextChanged = true;
	}

	wxRichTextAttr backgroundColourAttr;
	backgroundColourAttr.SetTextColour(*wxBLUE);
	m_richText->BeginStyle(backgroundColourAttr);
	m_richText->WriteText(username);
	m_richText->WriteText(": ");
	m_richText->EndStyle();

	backgroundColourAttr.SetTextColour(*wxBLACK);
	m_richText->BeginStyle(backgroundColourAttr);
	m_richText->WriteText(msg);
	m_richText->WriteText("\n");
	m_richText->EndStyle();

	m_richText->ShowPosition(m_richText->FindNextWordPosition() + 1);

}

void ChatPanel::setOnline(bool val)
{
	m_sendTextButton->Enable(val);
}

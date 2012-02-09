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
#include "MainFrame.h"
#include "CommonDefinitions.h"

BEGIN_EVENT_TABLE(MainFrameStatusBar, wxStatusBar)
    EVT_SIZE(MainFrameStatusBar::OnSize)
END_EVENT_TABLE()


MainFrameStatusBar::MainFrameStatusBar(wxWindow *parent)  : wxStatusBar(parent, wxID_ANY)
{
    static const int widths[Field_Max] = { 250, 150};

    SetFieldsCount(Field_Max);
    SetStatusWidths(Field_Max, widths);

	m_gauge = new wxGauge(this, MainFrameVoiceGauge, 100);

	SetMinHeight(15);
}

void MainFrameStatusBar::setGaugeValue(int val)
{
	m_gauge->SetValue(val);
}

MainFrameStatusBar::~MainFrameStatusBar()
{
}

// event handlers
void MainFrameStatusBar::OnSize(wxSizeEvent& )
{
    wxRect rect;
    GetFieldRect(Field_Gauge, rect);

    m_gauge->SetSize(rect.x + 2, rect.y + 2, rect.width - 4, rect.height - 4);
}

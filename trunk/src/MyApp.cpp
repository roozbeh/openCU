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
#include "MyApp.h"
#include "MainFrame.h"
#include "CommonDefinitions.h"

// Create a new application object: this macro will allow wxWidgets to create
// the application object during program execution (it's better than using a
// static object for many reasons) and also implements the accessor function
// wxGetApp() which will return the reference of the right type (i.e. MyApp and
// not wxApp)
IMPLEMENT_APP(MyApp)

// ============================================================================
// implementation
// ============================================================================

// ----------------------------------------------------------------------------
// the application class
// ----------------------------------------------------------------------------

// 'Main program' equivalent: the program execution "starts" here
bool MyApp::OnInit()
{
    // create the main application window
    MainFrame *frame = new MainFrame((wxFrame *)NULL, wxID_ANY, _T("openCU client"),
                        wxDefaultPosition, wxSize(500, 400),
                        wxDEFAULT_FRAME_STYLE | wxHSCROLL | wxVSCROLL);
/* TODO: 
    // set the frame icon
    frame->SetIcon(wxICON(sample));
*/

#if wxUSE_STATUSBAR
    // create a status bar just for fun (by default with 1 pane only)
	//MainFrameStatusBar *m_statbarCustom = new MainFrameStatusBar(frame);
 //   frame->SetStatusBar(m_statbarCustom);
	/* TODO: need to delete m_statbarCustom */
	frame->CreateStatusBar(1);
    frame->SetStatusText(_T("Welcome to openCU client!"));
#endif // wxUSE_STATUSBAR

    // and show it (the frames, unlike simple controls, are not shown when
    // created initially)
    frame->Show(true);

    frame->StartCapture();

    // success: wxApp::OnRun() will be called which will enter the main message
    // loop and the application will run. If we returned false here, the
    // application would exit immediately.
    return true;
}


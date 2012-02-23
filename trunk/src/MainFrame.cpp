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
#include "ConnectDlg.h"
#include "CommonDefinitions.h"
#include "CommandSocket.h"
#include "VideoWindow.h"
#include "AudioCapture.h"
#include "VideoFrame.h"
#include "AudioFrame.h"
#include "AudioPlayer.h"
#include "ChatPanel.h"

using namespace std;

#ifdef HAVE_ARPA_INET_H
#include <arpa/inet.h>
#endif

DEFINE_EVENT_TYPE(wxEVT_REFLECTOR_DISCONNECTED_EVENT)
DEFINE_EVENT_TYPE(wxEVT_SET_STATUS_EVENT)
DEFINE_EVENT_TYPE(wxEVT_REFMSG_EVENT)

// the event tables connect the wxWidgets events with the functions (event
// handlers) which process them. It can be also done at run-time, but for the
// simple menu events like this the static method is much simpler.
BEGIN_EVENT_TABLE(MainFrame, wxMDIParentFrame)
    EVT_MENU(wxID_EXIT,  MainFrame::OnQuit)
    EVT_MENU(menuReflectorConnectID,  MainFrame::OnConnect)
    EVT_MENU(menuReflectorDisconnectID,  MainFrame::OnDisconnect)
    EVT_MENU(menuReflectorSendVideo,  MainFrame::OnSendVideo)
    EVT_MENU(menuReflectorSendAudio,  MainFrame::OnSendAudio)

    EVT_MENU(menuLocalCaptureVideo,  MainFrame::OnLocalCaptureVideo)
    EVT_MENU(menuLocalCaptureAudio,  MainFrame::OnLocalCaptureAudio) 
    EVT_MENU(wxID_ABOUT, MainFrame::OnAbout)

    EVT_CLOSE(MainFrame::OnClose)
    EVT_SIZE(MainFrame::OnSize)

	EVT_TREE_ITEM_ACTIVATED(MainFrameTreeControl, MainFrame::OnTreeClick)

	EVT_TIMER(MainFrameKeepAliveTimer, MainFrame::OnKeepAliveTimer)
	EVT_TIMER(MainFrameStatusBarTimer, MainFrame::OnStatusbarTimer)
	

    EVT_RFLECTOR_DISCONNECTED_EVENT(wxID_ANY, 
		MainFrame::OnReflectorDisconnected)
	EVT_SET_STATUS_EVENT(wxID_ANY, MainFrame::OnSetStatus)
	EVT_REFMSG_EVENT(wxID_ANY, MainFrame::OnRefMSG)
END_EVENT_TABLE()

// frame constructor
MainFrame::MainFrame(wxWindow *parent,
                 const wxWindowID id,
                 const wxString& title,
                 const wxPoint& pos,
                 const wxSize& size,
                 const long style)
       : wxMDIParentFrame(parent, id, title, pos, size, style), 
	   m_capture(NULL), m_connected(false), m_localVideoFrame(NULL), 
	   m_keepAliveTimer(this, MainFrameKeepAliveTimer), m_command(NULL), 
	   m_statbarCustom(NULL), 
	   m_audioCapture(NULL), m_audioPlayer(NULL), 
	   m_statusBarTimer(this, MainFrameStatusBarTimer), m_treeWindow(this, MainFrameTreeControl)
{
	printf("create main frame ...\n");
#if wxUSE_MENUS
	printf("create Menus ...\n");
    // the "About" item should be in the help menu
    wxMenu *helpMenu = new wxMenu;
    helpMenu->Append(wxID_ABOUT, _T("&About...\tF1"), _T("Show about frame"));

    // create a menu bar
    wxMenu *menuReflector = new wxMenu;
    menuReflector->Append(menuReflectorConnectID, _T("&Connect\tF2"), _T("Connect to Reflector"));
    (menuReflector->Append(menuReflectorDisconnectID, _T("&Disconnet\tF3"), _T("Disconnect from Reflector")))->Enable(false);
    menuReflector->Append(menuReflectorSendVideo, _T("Send &Video\tF7"), _T("Send Video to Reflector"));
    menuReflector->Append(menuReflectorSendAudio, _T("Send &Audio\tF8"), _T("Send Audio to Reflector"));
    menuReflector->Append(wxID_EXIT, _T("E&xit\tAlt-X"), _T("Quit this program"));

#if 1
	printf("create captures menu ...\n");
    // create a menu bar
    wxMenu *menuLocal = new wxMenu;
    menuLocal->Append(menuLocalCaptureVideo, _T("&Capture Video\tF5"), _T("Search for local USB cameras"));
    menuLocal->Append(menuLocalCaptureAudio, _T("&Record Audio\tF6"), _T("Test local audio capturing devices"));
#endif

    // now append the freshly created menu to the menu bar...
    wxMenuBar *menuBar = new wxMenuBar();
    menuBar->Append(menuReflector, _T("&Reflector"));
    menuBar->Append(menuLocal, _T("&Local"));
    menuBar->Append(helpMenu, _T("&Help"));

#endif

    int w, h;
    GetClientSize(&w, &h);

	m_panel = new ChatPanel( this, 10, 10, 300, 100 );

	wxImageList *images = new wxImageList(14, 14, true);
	printf("Loading image: %s\n", IMAGESDIR "eyeopen.bmp");
	// m_openEyeImage = images->Add(wxImage( IMAGESDIR "eyeopen.bmp"));
	printf("%s : %d\n", __FILE__, __LINE__);
	m_closeEyeImage = images->Add(wxImage( IMAGESDIR "eyeclose.bmp"));
	printf("%s : %d\n", __FILE__, __LINE__);
	m_treeWindow.SetImageList(images);

	m_last_receive = 0;
	m_last_sent = 0;
	m_totalSeconds = 0;

    // ... and attach this menu bar to the frame
    SetMenuBar(menuBar);

	printf("menu bar set\n");

	//initTextChatItems();
}

void MainFrame::OnSize(wxSizeEvent&
                                  #ifdef __WXUNIVERSAL__
                                  event
                                  #else
                                  WXUNUSED(event)
                                  #endif
                                  )
{
	printf("%s : %d\n", __FILE__, __LINE__);
    int w, h;
    GetClientSize(&w, &h);
	printf("%s : %d\n", __FILE__, __LINE__);

	m_treeWindow.SetSize(0, 0, w/3, h);
	printf("%s : %d\n", __FILE__, __LINE__);
	printf("w : %d\n", w);
	printf("h : %d\n", h);
	printf("m_panel : %p\n", m_panel);
	m_panel->SetSize(w/3, 2*h/3, 2*w/3, h/3);
	printf("%s : %d\n", __FILE__, __LINE__);

	GetClientWindow()->SetSize(w/3, 0, w, 2*h/3);

	printf("%s : %d\n", __FILE__, __LINE__);
    // FIXME: On wxX11, we need the MDI frame to process this
    // event, but on other platforms this should not
    // be done.
#ifdef __WXUNIVERSAL__
    event.Skip();
#endif

	printf("%s : %d\n", __FILE__, __LINE__);
	
}

MainFrame::~MainFrame()
{
	setStatus("dispose video capture ...");
	/* stop video capture */

	wxMutex *stopMutex = new wxMutex;
    wxCondition *stopCondition = new wxCondition(*stopMutex);

	if (m_capture) {
		m_capture->stopVideoCapture();
		delete m_capture;
	}

	setStatus("dispose audio capture ...");
	/* stop audio capture */
	if (m_audioCapture) {
		m_audioCapture->stopAudioCapture();
		delete m_audioCapture;
	}

	setStatus("dispose audio player ...");
	if (m_audioPlayer) {
		m_audioPlayer->stopPlayback();
		delete m_audioPlayer;
	}

	setStatus("dispose reflector socket manager ...");
	if (m_command) {
		m_command->Disconnect();
		m_command = NULL;
	}

	delete stopCondition;
	delete stopMutex;
}


void MainFrame::StartCapture()
{
	printf("Start capture\n");
	wxCommandEvent e;
	OnLocalCaptureVideo(e);
	OnLocalCaptureAudio(e);
	SendSizeEvent();
}

// event handlers

void MainFrame::OnQuit(wxCommandEvent& WXUNUSED(event))
{
    // true is to force the frame to close
    Close(true);
}

void MainFrame::OnAbout(wxCommandEvent& WXUNUSED(event))
{
	wxStaticText *text = new wxStaticText( this,
		wxID_EXIT,
		_T("click somewhere\non image"),
		wxPoint(m_isPda ? 0 : 13,
		m_isPda ? 0 : 11)
		);
	text->SetBackgroundColour(*wxWHITE);
	text->SetForegroundColour(*wxBLACK);
	wxFont font = text->GetFont();
	font.SetPointSize(2*font.GetPointSize()/3);
	text->SetFont(font);
}

void MainFrame::OnConnect(wxCommandEvent& WXUNUSED(ev))
{
	ConnectDialog connectDlg(this);
	int ret = connectDlg.ShowModal();

	//Connect to server if user clicked connect button
	if (ret == 1) { 
		ostringstream stat_str;
		stat_str << "Connecting to ";
		stat_str <<  connectDlg.getTargetIP();
		stat_str << " : ";
		stat_str << connectDlg.getTargetPort();
		stat_str << " ...";
		wxString wxStatStr = stat_str.str().c_str();
		SetStatusText(wxStatStr);

		assert(m_command == NULL);
		m_command = new CommandSocket(this);

		m_reflectorName = connectDlg.getReflectorName();

		bool conn_ret = m_command->Connect(connectDlg.getTargetIP().c_str(), 
			connectDlg.getTargetPort(), connectDlg.getUsername()/*, connectDlg.getLocalIP().c_str(), 
			connectDlg.getLocalPort()*/);

		if (conn_ret) {
			SetStatusText("Connected.");
			m_connected = true;

			/* starting timer */
			m_keepAliveTimer.Start(3000);   //3 second timer
			m_statusBarTimer.Start(1000);

			GetMenuBar()->FindItem(menuReflectorConnectID, NULL)->Enable(false);
			GetMenuBar()->FindItem(menuReflectorDisconnectID, NULL)->Enable(true);
			m_panel->setOnline(true);
			m_username = connectDlg.getUsername();
			
		} else {
			SetStatusText("Connection error!");

			delete m_command;
			m_command = NULL;
		}

		//ClientInfo *cf = new ClientInfo(inet_addr("192.168.146.128"));
		//requestForClient(cf);
	}
}

void MainFrame::OnDisconnect(wxCommandEvent& WXUNUSED(event))
{
	if (m_connected) {
		m_keepAliveTimer.Stop();
		m_statusBarTimer.Stop();
		m_totalSeconds = 0;

		m_command->Disconnect();

		SetStatusText("Disconnected!");
		GetMenuBar()->FindItem(menuReflectorConnectID, NULL)->Enable(true);
		GetMenuBar()->FindItem(menuReflectorDisconnectID, NULL)->Enable(false);
		m_connected = false;

		m_treeWindow.DeleteAllItems();
		//m_command will destroy itself - no need to delete
		m_command = NULL;

		m_panel->setOnline(false);
	}
}

/** timer: for keep alive packets */
void MainFrame::OnKeepAliveTimer(wxTimerEvent& WXUNUSED(event))
{
	if (m_connected) {
		m_command->keepAlive();
	}
}

#if wxUSE_TOOLBAR
void MainFrame::InitToolBar(wxToolBar* WXUNUSED(toolBar))
{
    //wxBitmap bitmaps[8];

    //bitmaps[0] = wxBitmap( new_xpm );
    //bitmaps[1] = wxBitmap( open_xpm );
    //bitmaps[2] = wxBitmap( save_xpm );
    //bitmaps[3] = wxBitmap( copy_xpm );
    //bitmaps[4] = wxBitmap( cut_xpm );
    //bitmaps[5] = wxBitmap( paste_xpm );
    //bitmaps[6] = wxBitmap( print_xpm );
    //bitmaps[7] = wxBitmap( help_xpm );

    //toolBar->AddTool(MDI_NEW_WINDOW, _T("New"), bitmaps[0], _T("New file"));
    //toolBar->AddTool(1, _T("Open"), bitmaps[1], _T("Open file"));
    //toolBar->AddTool(2, _T("Save"), bitmaps[2], _T("Save file"));
    //toolBar->AddSeparator();
    //toolBar->AddTool(3, _T("Copy"), bitmaps[3], _T("Copy"));
    //toolBar->AddTool(4, _T("Cut"), bitmaps[4], _T("Cut"));
    //toolBar->AddTool(5, _T("Paste"), bitmaps[5], _T("Paste"));
    //toolBar->AddSeparator();
    //toolBar->AddTool(6, _T("Print"), bitmaps[6], _T("Print"));
    //toolBar->AddSeparator();
    //toolBar->AddTool(MDI_ABOUT, _T("About"), bitmaps[7], _T("Help"));

    //toolBar->Realize();
}
#endif // wxUSE_TOOLBAR

void MainFrame::OnClose(wxCloseEvent& event)
{
    event.Skip();
}

void MainFrame::OnSendVideo(wxCommandEvent& WXUNUSED(event))
{
	if (!m_connected) {
		wxMessageBox(_T("You should connect first."), 
			_T("Error"),
			wxICON_ERROR | wxOK);
		return;
	}

	if (!m_capture) {
		wxMessageBox(_T("You should capture video first."), 
			_T("Error"),
			wxICON_ERROR | wxOK);
		return;
	}

	m_capture->addListener(m_command);
	SetStatusText("Connected, Sending video ...");
}


void MainFrame::setReflectorName(/*std::string name*/)
{
	wxString wxReflectorName = m_reflectorName.c_str();
	m_rootItem = m_treeWindow.AddRoot(wxReflectorName);
	m_treeClients[m_rootItem.m_pItem] = 0;
}

void MainFrame::updateClientInfo(ClientInfo *cf)
{
	if (m_clientTreeMap.find(cf) != m_clientTreeMap.end()) {
		wxTreeItemId clientItem = m_clientTreeMap[cf];
		m_treeWindow.SetItemImage(clientItem, cf->isSeeingUs() ? m_openEyeImage : m_closeEyeImage);
		return;
	}

	wxString wxUsername = cf->getUsername().c_str();
	wxTreeItemId clientItem = m_treeWindow.AppendItem(m_rootItem, 
		wxUsername, 
		cf->isSeeingUs() ? m_openEyeImage : m_closeEyeImage);
	m_treeClients[clientItem.m_pItem] = cf;  //saving cf in a map to handle tree events
	m_clientTreeMap[cf] = clientItem; // saving iteeId in a map to handle reflector events
}

void MainFrame::OnTreeClick(wxTreeEvent& event)
{
	wxTreeItemId itemId = event.GetItem();
	ClientInfo *cf = m_treeClients[itemId.m_pItem];
	if (!cf) {
		//Root item clicked
		return;
	}
	
	requestForClient(cf);
}

void MainFrame::requestForClient(ClientInfo *cf)
{
	long clientIP = cf->getIPAddress();
	struct in_addr client_in;
	client_in.s_addr = clientIP;
	string client_address = inet_ntoa(client_in);
	wxString wxClientAddress = inet_ntoa(client_in);

	ostringstream stat_str;
	stat_str << "Asking for ";
	stat_str <<  client_address;
	wxString wxStatStr = stat_str.str().c_str();

	SetStatusText(wxStatStr);

	ClientVideoWindow *window = new ClientVideoWindow(this, wxClientAddress, getNextWindowPosition());
	m_clientWindows[clientIP] = window;

/* TODO: Not worked in Linux
	// Give it an icon
	window->SetIcon(wxICON(chart));
*/
	int width, height;
	window->GetClientSize(&width, &height);
	ImageCanvas *canvas = new ImageCanvas(window, wxPoint(0, 0), wxSize(width, height));
	canvas->SetCursor(wxCursor(wxCURSOR_HAND));
	canvas->setTitle(client_address);
	window->canvas = canvas;

	window->Show(true);

	m_command->askForClient(cf);
}

wxPoint MainFrame::getNextWindowPosition()
{
	int availWindows = 0;
	if (m_localVideoFrame) {
		availWindows++;
	}
	availWindows += m_clientWindows.size();

	int row = availWindows / 2;
	int col = availWindows % 2;

	// return wxPoint(VideoWindow::default_width * col, VideoWindow::default_height * row);
	return wxPoint(-1, -1);
}




void MainFrame::setStatus(std::string stat)
{
	wxString wx_stat = stat.c_str();
	SetStatusText(wx_stat);
}

void MainFrame::OnLocalCaptureVideo(wxCommandEvent& WXUNUSED(event))
{
	printf("local catpture video\n");
	m_capture = new Capture;
	if (!m_capture->init()) {
		wxMessageBox(_T("Failed to capture from camera, using simple file ..."), 
			_T("Warning"),
            wxICON_WARNING | wxOK);
		std::string testFile(IMAGESDIR "test.jpg");
		m_capture->useFile( (char *) testFile.c_str() );
	}

	m_localVideoFrame = new LocalVideoWindow(this, _T("Local Video"), getNextWindowPosition());

	m_capture->addListener(m_localVideoFrame);
	m_capture->startVideoCapture();

    m_localVideoFrame->SetTitle("Local Video");

/* TODO: Not worked in Linux
    // Give it an icon
    m_localVideoFrame->SetIcon(wxICON(chart));
*/
    int width, height;
    m_localVideoFrame->GetClientSize(&width, &height);
    ImageCanvas *canvas = new ImageCanvas(m_localVideoFrame, wxPoint(0, 0), wxSize(width, height));
    canvas->SetCursor(wxCursor(wxCURSOR_PENCIL));
    m_localVideoFrame->canvas = canvas;

	m_localVideoFrame->Show(true);
}

void MainFrame::showClientVideo(long address, VideoFrame *vf)
{
	printf("show client video\n");
	ClientVideoWindow *window;

	if (m_clientWindows.find(address) == m_clientWindows.end()) {
		/* WHY THAT HAPPENS !!!! */
		exit(0);
	} else {
		window = m_clientWindows[address];
	}

	window->VideoReceived(vf);
}

void MainFrame::remoteAudioReceived(long address, AudioFrame *af)
{
	ClientVideoWindow *window;

	if (m_clientWindows.find(address) == m_clientWindows.end()) {
		/* WHY THAT HAPPENS !!!! */
		exit(0);
	} else {
		window = m_clientWindows[address];
	}

	window->showAudioReceivedIcon();

	if (!m_audioPlayer) {
		m_audioPlayer = new AudioPlayer();
		if (!m_audioPlayer->startPlayback()) {
			wxMessageBox(_T("Could not start audio playback."), 
				_T("Error"),
				wxICON_ERROR | wxOK);
			return;
		}
	}

	m_audioPlayer->addBufferForPlayback(af);

}

void MainFrame::OnLocalCaptureAudio(wxCommandEvent& WXUNUSED(event))
{
	printf("on local capture audio\n");
 	if (!m_audioCapture) {
		m_audioCapture = new AudioCapture();
	}

	m_audioCapture->addListener(this);

	if (!m_audioCapture->startAudioCapture()) {
		wxMessageBox(_T("Could not record from audio device."), 
			_T("Error"),
			wxICON_ERROR | wxOK);
		return;
	}

	if (!m_statbarCustom) {
		m_origStatbar = GetStatusBar();
		if ( m_origStatbar )
		{
			m_origStatbar->Hide();
		}

		m_statbarCustom = new MainFrameStatusBar(this);
		SetStatusBar(m_statbarCustom);
	}
}

void MainFrame::AudioReceived(AudioFrame *af)
{
	printf("audio received\n");
	short *audio_buf = (short *) af->getData();
	SAMPLE max_d = 0;
	int frameCount = af->getSize() / sizeof(SAMPLE);
	for (int i=0;i< frameCount;i++) {
		SAMPLE d = audio_buf[i];
		if (d > max_d) {
			max_d = d;
		}
	}

	int percent = (max_d * 100) >> 16;
	if (m_statbarCustom) {
		m_statbarCustom->setGaugeValue(percent);
	}
}

void MainFrame::OnSendAudio(wxCommandEvent& )
{
	if (!m_connected) {
		wxMessageBox(_T("You should connect first."), 
			_T("Error"),
			wxICON_ERROR | wxOK);
		return;
	}

	if (!m_audioCapture) {
		wxMessageBox(_T("You should record audio first."), 
			_T("Error"),
			wxICON_ERROR | wxOK);
		return;
	}

	m_audioCapture->addListener(m_command);
	SetStatusText("Connected, Sending audio ...");
}


void MainFrame::OnReflectorDisconnected(wxCommandEvent&)
{
	if (m_connected) {
		m_keepAliveTimer.Stop();
		m_statusBarTimer.Stop();

		SetStatusText("Disconnected!");
		GetMenuBar()->FindItem(menuReflectorConnectID, NULL)->Enable(true);
		GetMenuBar()->FindItem(menuReflectorDisconnectID, NULL)->Enable(false);
		m_connected = false;

		m_treeWindow.DeleteAllItems();
		//m_command will destroy itself - no need to delete
		m_command = NULL;
	}
}

void MainFrame::OnSetStatus(wxCommandEvent& event)
{
	string s(event.GetString().c_str());
	setStatus(s);
}

void MainFrame::OnRefMSG(wxCommandEvent& event)
{
	wxMessageBox(event.GetString(), 
			_T("Info"),
			wxICON_INFORMATION | wxOK);
}

//TODO: add hidden list
//TODO: change default reflector address

void MainFrame::OnStatusbarTimer(wxTimerEvent& )
{
	m_totalSeconds++;

	int thisSecondReceive = m_command->getTotalReceived() - m_last_receive;
	int thisSecondSent = m_command->getTotalSent() - m_last_sent;

	ostringstream stat_str;
	stat_str << "Connected, Max: " << m_command->getReflectorCapacity();
	stat_str << ", Dl: " << m_command->getTotalReceived()/1000 << "KB ";
	stat_str << " (" << thisSecondReceive / 1000 << "KB/s)";
	stat_str << ", Up: " << m_command->getTotalSent() / 1000 << "KB ";
	stat_str << " (" << thisSecondSent / 1000 << "KB/s)";
	setStatus(stat_str.str());

	m_last_receive = m_command->getTotalReceived();
	m_last_sent = m_command->getTotalSent();
}

void MainFrame::textMessageReceived(std::string username, std::string msg)
{
	m_panel->textMessageReceived(username, msg);
}

bool MainFrame::SendText(std::string txt)
{
	return m_command->SendText(txt);
}

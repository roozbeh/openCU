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

#ifndef __OPENCU_MAIN_FRAME_H__
#define __OPENCU_MAIN_FRAME_H__

#include "Client.h"
#include "Capture.h"

class CommandSocket;
class ClientVideoWindow;
class VideoFrame;
class LocalVideoWindow;
class AudioCapture;
class AudioPlayer;
class ChatPanel;

typedef std::map<wxTreeItemIdValue, ClientInfo *> TreeItemClientMap;
typedef std::map<ClientInfo *, wxTreeItemId> ClientTreeItemMap; /* map ClientInfo pointer to tree item ID */

typedef std::map<long, ClientVideoWindow *> ClientWindowMap; /* map from IP address to video window */

/**
 * Customized events 
 */
BEGIN_DECLARE_EVENT_TYPES()
    DECLARE_EVENT_TYPE(wxEVT_REFLECTOR_DISCONNECTED_EVENT, 7777)
    DECLARE_EVENT_TYPE(wxEVT_SET_STATUS_EVENT, 7778)
    DECLARE_EVENT_TYPE(wxEVT_REFMSG_EVENT, 7779)
END_DECLARE_EVENT_TYPES()

// it may also be convenient to define an event table macro for this event type
#define EVT_RFLECTOR_DISCONNECTED_EVENT(id, fn) \
    DECLARE_EVENT_TABLE_ENTRY( \
        wxEVT_REFLECTOR_DISCONNECTED_EVENT, id, wxID_ANY, \
        (wxObjectEventFunction)(wxEventFunction) wxStaticCastEvent( wxCommandEventFunction, &fn ), \
        (wxObject *) NULL \
    ),
#define EVT_SET_STATUS_EVENT(id, fn) \
    DECLARE_EVENT_TABLE_ENTRY( \
        wxEVT_SET_STATUS_EVENT, id, wxID_ANY, \
        (wxObjectEventFunction)(wxEventFunction) wxStaticCastEvent( wxCommandEventFunction, &fn ), \
        (wxObject *) NULL \
    ),
#define EVT_REFMSG_EVENT(id, fn) \
    DECLARE_EVENT_TABLE_ENTRY( \
        wxEVT_REFMSG_EVENT, id, wxID_ANY, \
        (wxObjectEventFunction)(wxEventFunction) wxStaticCastEvent( wxCommandEventFunction, &fn ), \
        (wxObject *) NULL \
    ),

class MainFrameStatusBar : public wxStatusBar
{
public:
    MainFrameStatusBar(wxWindow *parent);
    virtual ~MainFrameStatusBar();

    // event handlers
    void OnSize(wxSizeEvent& event);

    void setGaugeValue(int val);
private:
    enum
    {
        Field_Text,
        Field_Gauge,
		Field_Max
    };

    wxGauge *m_gauge;

    DECLARE_EVENT_TABLE()
};

// Define a new frame type: this is going to be our main frame
class MainFrame : public wxMDIParentFrame, public CaptureListener
{
public:
    // ctor(s)
    MainFrame(wxWindow *parent,
                 const wxWindowID id,
                 const wxString& title,
                 const wxPoint& pos,
                 const wxSize& size,
                 const long style);
	~MainFrame();

    void InitToolBar(wxToolBar* toolBar);

    // event handlers (these functions should _not_ be virtual)
    void OnSize(wxSizeEvent& event);
    void OnQuit(wxCommandEvent& event);
    void OnClose(wxCloseEvent& event);
    void OnAbout(wxCommandEvent& event);

	//wxStatusBar* OnCreateStatusBar(int number, long style, wxWindowID id, const wxString& name);

    bool m_isPda;

	void OnConnect(wxCommandEvent& event);
	void OnDisconnect(wxCommandEvent& event);
	void OnSendVideo(wxCommandEvent& event);
	void OnSendAudio(wxCommandEvent& event);
	void OnLocalCaptureVideo(wxCommandEvent& event);
	void OnLocalCaptureAudio(wxCommandEvent& WXUNUSED(event));

	/** Tree double click callback */
	void OnTreeClick(wxTreeEvent& event);

	/** Timer call back - used for keepalive packets */
	void OnKeepAliveTimer(wxTimerEvent& event);

	/** Timer call back - used for updating status bar */
	void OnStatusbarTimer(wxTimerEvent& event);
	
	/* -- custom events -- */
	/* calls when reflector socket disconnected */
    void OnReflectorDisconnected(wxCommandEvent& event);
	/* calls when somebody wants to change status */
    void OnSetStatus(wxCommandEvent& event);
	/* calls when somebody wants to show a dialog containing Reflector msg */
	void OnRefMSG(wxCommandEvent& event);

	void setReflectorName(/*std::string name*/);
	void updateClientInfo(ClientInfo *cf);
	void setStatus(std::string stat);

	/** show video received from client (specified address) */
	void showClientVideo(long address, VideoFrame *vf);

	/** show an audio packet received from client (specified address)*/
	void remoteAudioReceived(long address, AudioFrame *af);
	
	/** CaptureListener callbacks */
	void VideoReceived(VideoFrame *) {};
	void AudioReceived(AudioFrame *af);

	/**
	  * calls by network manager in case of text message 
	  * received from clients 
	  */
	void textMessageReceived(std::string username, std::string msg);

	/**
	 * dispatch user inputed text to command manager
	 */
	bool SendText(std::string txt);

	std::string getUsername() { return m_username;};

	/** Start Audio and Video local capture -
 	    capture camera and recorder audio */
    	void StartCapture();
private:
    // any class wishing to process wxWidgets events must use this macro
    DECLARE_EVENT_TABLE()

	CommandSocket *m_command;

	std::string m_reflectorName;

	/** Tree root Item */
	wxTreeItemId m_rootItem;

	/** Tree control member variable*/
	wxTreeCtrl m_treeWindow;

	/** Child window which shows local capture */
	LocalVideoWindow *m_localVideoFrame;

	/** Map of tree-item => Client Info */
	TreeItemClientMap m_treeClients;

	/** Map of Client Info => tree-item */
	ClientTreeItemMap m_clientTreeMap;

	/** map of IP => Client Child Window */
	ClientWindowMap m_clientWindows;

	/** Capture member variable */
	Capture *m_capture;

	/** boolean value shows if we are connected or now */
	bool m_connected;
	/** username of connected user - the current local user*/
	std::string m_username;

	/** send a request for specified client and create a window for it */
	void requestForClient(ClientInfo *cf);

	/** proposes a position for next child window */
	wxPoint getNextWindowPosition();

	/** time object - we need this for keep alive sessions */
	wxTimer m_keepAliveTimer;

	/** a custom status bar containing a guage for showing audio activity */
	MainFrameStatusBar *m_statbarCustom;
	wxStatusBar *m_origStatbar;

	/** object for recording audio from local device */
	AudioCapture *m_audioCapture;

	/** object for playing remote audio */
	AudioPlayer *m_audioPlayer;

	/** image list IDs */
	int m_openEyeImage; ///< open eye ID
	int m_closeEyeImage; ///< close eye ID

	/** time object - this timer is responsible for updating statusbar 
		for informing user about send/receive rates */
	wxTimer m_statusBarTimer;

	/** bandwidth show stuff */
	int m_last_receive;
	int m_last_sent;
	int m_totalSeconds;
	double m_avg_receive;
	double m_avg_sent;

	/* text chat stuff */
	ChatPanel *m_panel;
};

#endif


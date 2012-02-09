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

#ifndef __OPENCU_VIDEO_WINDOW_H_
#define __OPENCU_VIDEO_WINDOW_H_

#include "Capture.h"

/**
 * Customized events 
 */
BEGIN_DECLARE_EVENT_TYPES()
    DECLARE_EVENT_TYPE(wxEVT_IMAGE_RECEIVE_EVENT, 7787)
END_DECLARE_EVENT_TYPES()

// it may also be convenient to define an event table macro for this event type
#define EVT_IMAGE_RECEIVE_EVENT(id, fn) \
    DECLARE_EVENT_TABLE_ENTRY( \
        wxEVT_IMAGE_RECEIVE_EVENT, id, wxID_ANY, \
        (wxObjectEventFunction)(wxEventFunction) wxStaticCastEvent( wxCommandEventFunction, &fn ), \
        (wxObject *) NULL \
    ),

class ImageCanvas : public wxScrolledWindow
{
public:
    ImageCanvas(wxWindow *parent, const wxPoint& pos, const wxSize& size);
    virtual void OnDraw(wxDC& dc);

	void showImage(VideoFrame *vf);
	void setTitle(std::string s) { m_title = s;};

	void audioReceived();

	void onImageReceived(wxCommandEvent& event);
private:
	wxBitmap *m_bitmap;
	wxMutex m_frameMutex;
	std::string m_title;

	bool m_audioReceived;
	wxBitmap m_speakerIcon;

    DECLARE_EVENT_TABLE()
};

class VideoWindow : public wxMDIChildFrame
{
public:
    ImageCanvas *canvas;
    VideoWindow(wxMDIParentFrame *parent, const wxString& title, wxPoint pos);

	void OnClose(wxCloseEvent& event);

	static const int default_width = 170;
	static const int default_height = 160;

    DECLARE_EVENT_TABLE()
};


class LocalVideoWindow : public VideoWindow, public CaptureListener
{
public:
	LocalVideoWindow(wxMDIParentFrame *parent, const wxString& title, wxPoint pos);
	~LocalVideoWindow();

	void VideoReceived(VideoFrame *vf);
	void AudioReceived(AudioFrame *WXUNUSED(af)) {};
};

class ClientVideoWindow : public VideoWindow
{
public:
	ClientVideoWindow(wxMDIParentFrame *parent, const wxString& title, wxPoint pos);

	void VideoReceived(VideoFrame *vf);
	void showAudioReceivedIcon();
};

#endif //__OPENCU_VIDEO_WINDOW_H_


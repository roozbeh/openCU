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
#include "VideoWindow.h"
#include "VideoFrame.h"

DEFINE_EVENT_TYPE(wxEVT_IMAGE_RECEIVE_EVENT)

BEGIN_EVENT_TABLE(ImageCanvas, wxScrolledWindow)
	EVT_IMAGE_RECEIVE_EVENT(wxID_ANY, ImageCanvas::onImageReceived)
END_EVENT_TABLE()

BEGIN_EVENT_TABLE(VideoWindow, wxMDIChildFrame)
    EVT_CLOSE(VideoWindow::OnClose)
END_EVENT_TABLE()

// Define a constructor for my canvas
ImageCanvas::ImageCanvas(wxWindow *parent, const wxPoint& pos, const wxSize& size)
        : wxScrolledWindow(parent, wxID_ANY, pos, size/*,
                           wxSUNKEN_BORDER |
                           wxNO_FULL_REPAINT_ON_RESIZE |
                           wxVSCROLL | wxHSCROLL*/), m_bitmap(NULL), m_audioReceived(false),
						   m_speakerIcon( IMAGESDIR "speaker.bmp", wxBITMAP_TYPE_BMP)
{
    //SetBackgroundColour(wxColour(_T("WHITE")));
}

VideoWindow::VideoWindow(wxMDIParentFrame *parent, const wxString& title, wxPoint pos)
: wxMDIChildFrame(parent, wxID_ANY, title, pos, wxSize(VideoWindow::default_width, VideoWindow::default_height),
                         wxNO_FULL_REPAINT_ON_RESIZE)
{
    canvas = (ImageCanvas *) NULL;

	
    // this should work for MDI frames as well as for normal ones
}

void VideoWindow::OnClose(wxCloseEvent& event)
{
    event.Skip();
}

LocalVideoWindow::LocalVideoWindow(wxMDIParentFrame *parent, const wxString& title, wxPoint pos) : 
	VideoWindow(parent, title, pos)
{
}

LocalVideoWindow::~LocalVideoWindow()
{
}

void LocalVideoWindow::VideoReceived(VideoFrame *vf)
{
	if (canvas) {
		canvas->showImage(vf);
	}
}

ClientVideoWindow::ClientVideoWindow(wxMDIParentFrame *parent, const wxString& title, wxPoint pos) : 
	VideoWindow(parent, title, pos)
{
}

void ClientVideoWindow::VideoReceived(VideoFrame *vf)
{
	if (canvas) {
		canvas->showImage(vf);
	}
}

void ClientVideoWindow::showAudioReceivedIcon()
{
	if (canvas) {
		canvas->audioReceived();
	}
}

void ImageCanvas::showImage(VideoFrame *vf)
{

	wxImage *image = new wxImage(vf->getWidth(), vf->getHeight(), false);
	unsigned char *imageData = new unsigned char[vf->getSize()];
	memcpy(imageData, vf->getRawDataPtr(), vf->getSize());
	image->SetData(imageData, false);
	image->Rescale(160, 120);

	wxCommandEvent imageEvent(wxEVT_IMAGE_RECEIVE_EVENT);
	imageEvent.SetClientData(image);
	wxPostEvent(this, imageEvent);
}

void ImageCanvas::audioReceived() 
{ 
	//make it flashing
	m_audioReceived = !m_audioReceived; 

	Refresh();
}

void ImageCanvas::onImageReceived(wxCommandEvent& event)
{
	wxImage *image = (wxImage *) event.GetClientData();
	wxBitmap *bitmap = new wxBitmap(*image);
	//delete image->GetData();
	delete image;

	m_frameMutex.Lock();
	if (m_bitmap) {
		delete m_bitmap;
	}
	m_bitmap = bitmap; 
	m_frameMutex.Unlock();

	Refresh();
}

// Define the repainting behaviour
void ImageCanvas::OnDraw(wxDC& dc)
{

	dc.SetPen(*wxGREEN_PEN);
	dc.DrawRectangle(0, 0, 169, 159);
	dc.DrawRectangle(1, 1, 167, 157);
	dc.DrawRectangle(2, 2, 165, 155);
	dc.DrawRectangle(3, 3, 163, 153);
	dc.DrawRectangle(4, 4, 161, 151);

	dc.SetTextForeground(*wxBLACK);
	dc.SetFont(*wxSWISS_FONT);
	dc.DrawText(m_title, 5, 5);

	m_frameMutex.Lock();
	if (m_bitmap) {
		dc.DrawBitmap(*m_bitmap, 5, 35);
	}
	m_frameMutex.Unlock();

	if (m_audioReceived) {
		dc.DrawBitmap(m_speakerIcon, VideoWindow::default_width - 20, 5);
	}
}

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

/* It was for before which we inherited from wxMDIChildFrame */
BEGIN_EVENT_TABLE(VideoWindow, wxPanel)
//     EVT_CLOSE(VideoWindow::OnClose)
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
: wxPanel(parent, wxID_ANY, pos, wxSize(VideoWindow::default_width, VideoWindow::default_height),
                         wxNO_FULL_REPAINT_ON_RESIZE)
{
    canvas = (ImageCanvas *) NULL;
	
	printf("pos: %dx%d\n", pos.x, pos.y);
    // this should work for MDI frames as well as for normal ones
}

VideoWindow::VideoWindow(wxMDIParentFrame *parent, const wxString& title, wxPoint pos, wxSize siz) 
	: wxPanel(parent, wxID_ANY, pos, siz, wxNO_FULL_REPAINT_ON_RESIZE)
{
    canvas = (ImageCanvas *) NULL;
	
	printf("pos: %dx%d - %dx%d\n", pos.x, pos.y, siz.x, siz.y);
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

LocalVideoWindow::LocalVideoWindow(wxMDIParentFrame *parent, const wxString& title, wxPoint pos, wxSize size) : 
	VideoWindow(parent, title, pos, size)
{
	printf("Create local video window: at %dx%d, in %dx%d pixels\n", pos.x, pos.y, size.x, size.y);
}

LocalVideoWindow::~LocalVideoWindow()
{
}

void LocalVideoWindow::VideoReceived(VideoFrame *vf)
{
	// printf("frame received: %dx%d\n", vf->getWidth(), vf->getHeight());
	if (canvas) {
		// vf->resizeImage(int targetWidth, int targetHeight)
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
    int width, height;
    GetClientSize(&width, &height);
	
	// image should be converted to w*h*3 instead of w*h*4
	
	// printf("alpha channels: %d, dep: %d, widthStep: %d\n", vf->getImage()->alphaChannel, vf->getImage()->depth, vf->getImage()->widthStep);
	// printf("should be resized to %dx%d\n", width, height);
	width -= 10;
	height -= 40;
	vf->resizeImage(width, height);
	// vf->

	printf("image original size: %dx%d, size: %d, channels: %d\n", 
		vf->getWidth(), vf->getHeight(), vf->getSize(), vf->getImage()->nChannels);
	
	wxImage *image = new wxImage(vf->getWidth(), vf->getHeight(), false);
	unsigned char *imageData = new unsigned char[vf->getWidth() * vf->getHeight() * 3];
	for (int i=0;i<vf->getHeight();i++) {
		for (int j=0;j<vf->getWidth();j++) {
			CvScalar s; 
			s = cvGet2D(vf->getImage(), i, j);
			imageData[(i*vf->getWidth() + j)*3 + 0] = s.val[0];
			imageData[(i*vf->getWidth() + j)*3 + 1] = s.val[1]; 
			imageData[(i*vf->getWidth() + j)*3 + 2] = s.val[2];
		}
	}
	
	// memcpy(imageData, vf->getRawDataPtr(), vf->getSize());
	image->SetData(imageData, false);
	image->Rescale(width, height);

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
    int width, height;
    GetClientSize(&width, &height);

	printf("on draw, title: %s\n", m_title.c_str());

	dc.SetPen(*wxGREEN_PEN);
	dc.DrawRectangle(0, 0, width - 1, height - 1);
	dc.SetPen(*wxRED_PEN);
	dc.DrawRectangle(1, 1, width - 3, height - 3);
	dc.SetPen(*wxGREEN_PEN);
	dc.DrawRectangle(2, 2, width - 5, height - 5);
	dc.SetPen(*wxRED_PEN);
	dc.DrawRectangle(3, 3, width - 7, height - 7);
	dc.SetPen(*wxGREEN_PEN);
	dc.DrawRectangle(4, 4, width - 9, height - 9);

	dc.SetTextForeground(*wxBLACK);
	dc.SetFont(*wxSWISS_FONT);
	wxString wxTitle = m_title.c_str();
	dc.DrawText(wxTitle, 5, 5);

	m_frameMutex.Lock();
	if (m_bitmap) {
		dc.DrawBitmap(*m_bitmap, 5, 35);
	}
	m_frameMutex.Unlock();

	if (m_audioReceived) {
		dc.DrawBitmap(m_speakerIcon, VideoWindow::default_width - 20, 5);
	}
}

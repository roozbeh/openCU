bin_PROGRAMS = h264client

AM_CPPFLAGS = \
	`../extlib/wxGTK-2.8.10/wx-config --cppflags`	\
	-I../extlib/opencv-1.1pre1/cxcore/include	\
	-I../extlib/opencv-1.1pre1/cv/include	\
	-I../extlib/opencv-1.1pre1/otherlibs/highgui	\
	-I../extlib/pa_stable_v19_20071207/include/	\
	-I../extlib/xvidcore-1.2.2/src		\
	-I../extlib/speex-1.2rc1/include/	\
	-DIMAGESDIR='"$(pkgdatadir)/images/"'

h264client_LDFLAGS = \
	`../extlib/wxGTK-2.8.10/wx-config --libs`	\
	-L../extlib/opencv-1.1pre1/cxcore/src/.libs/	\
	-L../extlib/opencv-1.1pre1/cv/src/.libs/		\
	-L../extlib/opencv-1.1pre1/otherlibs/highgui/.libs/ 	\
	-L../extlib/speex-1.2rc1/libspeex/.libs \
	-L../extlib/pa_stable_v19_20071207/lib/.libs \
	-L../extlib/xvidcore-1.2.2/build/generic/=build 

h264client_LDADD = -lcxcore -lcv -lhighgui -lspeex -lportaudio -lxvidcore

h264client_SOURCES = \
	AudioCapture.cpp	\
	AudioPacket.cpp		\
	AudioPlayer.cpp		\
	Capture.cpp		\
	ChatPanel.cpp		\
	Client.cpp		\
	CommandSocket.cpp	\
	ConnectDialog.cpp	\
	MainFrame.cpp		\
	MainStatusBar.cpp	\
	MyApp.cpp		\
	OpenContinuePacket.cpp	\
	Packet.cpp		\
	RateControlPacket.cpp	\
	RefPacket.cpp		\
	SpeexAudioCodec.cpp	\
	Stdafx.cpp		\
	TextPacket.cpp	\
	UdpSocket.cpp		\
	VideoCodec.cpp		\
	VideoFrame.cpp		\
	VideoPacket.cpp		\
	VideoWindow.cpp		\
	XVIDVideoCodec.cpp	\
	main.cpp

 

bin_PROGRAMS = openCU

AM_CPPFLAGS = \
	-DIMAGESDIR='"./"'
#-DIMAGESDIR='"$(pkgdatadir)/images/"'

# openCU_LDFLAGS = \
# 	`../extlib/wxWidgets-2.9.3/wx-config --libs` -lz

# openCU_LDADD = -lcxcore -lcv -lhighgui -lspeex -lportaudio -lxvidcore

# PRECOMPILED_HEADER = ../inc/StdAfx.h.gch
# all-am: PRECOMPILED_HEADER

# PRECOMPILED_HEADER : ../inc/StdAfx.h
# 	g++ -c ../inc/StdAfx.h $(CPPFLAGS) -o $(PRECOMPILED_HEADER)
	
%.o: %.cpp
	@echo "  COMPILE: $(@D)/$(<F) "
	@$(CXXCOMPILE) -c $< $(CPPFLAGS) $(CFLAGS)  -o $@


openCU_SOURCES = \
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

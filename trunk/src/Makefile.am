bin_PROGRAMS = openCU

AM_CPPFLAGS = \
	-DIMAGESDIR='"./"'
	
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

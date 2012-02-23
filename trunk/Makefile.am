SUBDIRS = \
 inc \
 src

AM_CPPFLAGS = \
	`$(srcdir)/extlib/wxMac-2.8.12/wx-config --cppflags`	\
	-I$(srcdir)/extlib/opencv-1.1pre1/cxcore/include	\
	-I$(srcdir)/extlib/opencv-1.1pre1/cv/include	\
	-I$(srcdir)/extlib/opencv-1.1pre1/otherlibs/highgui	\
	-I$(srcdir)/extlib/pa_stable_v19_20071207/include/	\
	-I$(srcdir)/extlib/xvidcore-1.2.2/src		\
	-I$(srcdir)/extlib/speex-1.2rc1/include/	

dist_doc_DATA = README
images_DATA = eyeopen.bmp eyeclose.bmp test.jpg speaker.bmp
imagesdir = $(pkgdatadir)/images

#                                               -*- Autoconf -*-
# Process this file with autoconf to produce a configure script.

AC_PREREQ([2.63])
AC_INIT([openCU Client], [0.04], [roozbeh@cuseeme.sourceforge.net], [openCU-Client])

AC_CONFIG_SRCDIR([src/])

AC_CONFIG_HEADERS([inc/config.h])
AC_CONFIG_FILES([Makefile inc/Makefile src/Makefile])

AM_INIT_AUTOMAKE([-Wall -Werror foreign])

# Checks for programs.
AC_PROG_CXX
AC_PROG_CC

# Checks for libraries.

# Checks for header files.
AC_CHECK_HEADERS([arpa/inet.h netdb.h netinet/in.h sys/socket.h])

# Checks for typedefs, structures, and compiler characteristics.
AC_HEADER_STDBOOL


# Checks for library functions.
AC_CHECK_FUNCS([gethostbyname inet_ntoa memset socket])

#check package config
AC_CHECK_PROG([TEST_PKG_CONFIG], [pkg-config], [yes], [no])
AS_IF([test "$TEST_PKG_CONFIG" == no],[echo 'ERROR: pkg-config not found. Please add it to PATH.'; exit])

#preparing portaudio

OPENCV_PREFIX=/usr
AC_ARG_WITH(opencv,
    [  --with-opencv=<path>       prefix of opencv2.0 root directory for cv, cvaux, cxcore, etc, default /usr],
    [OPENCV_PREFIX=$with_opencv])

AC_SUBST(OPENCV_PREFIX)
OPENCV_LDFLAGS="-L${OPENCV_PREFIX}/lib -lopencv_core -lopencv_highgui -lopencv_imgproc"
OPENCV_CPPFLAGS="-I${OPENCV_PREFIX}/include"
AC_SUBST(OPENCV_LIBS)
AC_SUBST(OPENCV_CFLAGS)

PORTAUDIO_PREFIX=/usr
AC_ARG_WITH(portaudio,
    [  --with-portaudio=<path>       prefix of PortAudio directory /usr/local/portaudio],
    [PORTAUDIO_PREFIX=$with_portaudio])

AC_SUBST(PORTAUDIO_PREFIX)
PORTAUDIO_LDFLAGS="-L${PORTAUDIO_PREFIX}/lib -lportaudio "
PORTAUDIO_CPPFLAGS="-I${PORTAUDIO_PREFIX}/include"
AC_SUBST(PORTAUDIO_LDFLAGS)
AC_SUBST(PORTAUDIO_CPPFLAGS)


SPEEX_PREFIX=/usr
AC_ARG_WITH(speex,
    [  --with-speex=<path>       prefix of Speex directory /usr/local/speex],
    [SPEEX_PREFIX=$with_speex])

AC_SUBST(SPEEX_PREFIX)
SPEEX_LDFLAGS="-L${SPEEX_PREFIX}/lib -lportaudio "
SPEEX_CPPFLAGS="-I${SPEEX_PREFIX}/include"
AC_SUBST(SPEEX_LDFLAGS)
AC_SUBST(SPEEX_CPPFLAGS)


XVID_PREFIX=/usr
AC_ARG_WITH(xvid,
    [  --with-xvid=<path>       prefix of Xvid directory /usr/local/xvid],
    [XVID_PREFIX=$with_xvid])

AC_SUBST(XVID_PREFIX)
XVID_LDFLAGS="-L${XVID_PREFIX}/lib -lxvidcore "
XVID_CPPFLAGS="-I${XVID_PREFIX}/include"
AC_SUBST(SPEEX_LDFLAGS)
AC_SUBST(SPEEX_CPPFLAGS)


WXWIDGETS_PREFIX=/usr
AC_ARG_WITH(wxwidgets,
    [  --with-wxwidgets=<path>       prefix of WxWidgets 2.9.3 directory],
    [WXWIDGETS_PREFIX=$with_wxwidgets])

AC_SUBST(WXWIDGETS_PREFIX)
# WXWIDGETS_LDFLAGS="`WXWIDGETS_PREFIX/wx-config --libs` "
# WXWIDGETS_CPPFLAGS="`WXWIDGETS_PREFIX/wx-config --cppflags`"
# AC_SUBST(WXWIDGETS_LDFLAGS)
# AC_SUBST(WXWIDGETS_CPPFLAGS)


#AC_CONFIG_SUBDIRS([extlib/speex-1.2rc1]) 

LDFLAGS="${OPENCV_LDFLAGS} $LDFLAGS"
CPPFLAGS="${OPENCV_CPPFLAGS} $CPPFLAGS"
AC_CHECK_HEADERS(opencv/cxcore.h,,AC_MSG_ERROR(openCU requires OpenCV 2.0 installed),)
AC_CHECK_LIB(opencv_core,cvCreateImage,,AC_MSG_ERROR(openCU requires OpenCV 2.0))
AC_CHECK_LIB(opencv_highgui,cvQueryFrame,,AC_MSG_ERROR(openCU requires OpenCV 2.0))

LDFLAGS="${PORTAUDIO_LDFLAGS} $LDFLAGS"
CPPFLAGS="${PORTAUDIO_CPPFLAGS} $CPPFLAGS"
AC_CHECK_HEADERS(portaudio.h,,AC_MSG_ERROR(openCU requires PortAudio installed),)
AC_CHECK_LIB(portaudio,Pa_Initialize,,AC_MSG_ERROR(openCU requires PortAudio installed))

LDFLAGS="${SPEEX_LDFLAGS} $LDFLAGS"
CPPFLAGS="${SPEEX_CPPFLAGS} $CPPFLAGS"
AC_CHECK_HEADERS(speex/speex.h,,AC_MSG_ERROR(openCU requires Speex library installed),)
AC_CHECK_LIB(speex,speex_encoder_init,,AC_MSG_ERROR(openCU requires Speex library installed))

LDFLAGS="${XVID_LDFLAGS} $LDFLAGS"
CPPFLAGS="${XVID_CPPFLAGS} $CPPFLAGS"
AC_CHECK_HEADERS(xvid.h,,AC_MSG_ERROR(openCU requires Xvid library installed),)
AC_CHECK_LIB(xvidcore,xvid_global,,AC_MSG_ERROR(openCU requires Xvid library installed))

# AC_CHECK_PROG (wxwidgets_param, 'wx-config',,, WXWIDGETS_PREFIX, AC_MSG_ERROR(openCU requires wxWidgets library installed))
# AC_CHECK_PROG ([wxparam], [find], 1, 0)
AC_CHECK_PROG(HAVE_WXWIDGERS, wx-config, true, false, $WXWIDGETS_PREFIX/bin)
if test "x$HAVE_WXWIDGERS" = "xfalse" ; then
	echo $WXWIDGETS_PREFIX/bin
	AC_MSG_ERROR(openCU requires wxWidgets library installed)
fi
LDFLAGS="`$WXWIDGETS_PREFIX/bin/wx-config --libs` $LDFLAGS"
CPPFLAGS="`$WXWIDGETS_PREFIX/bin/wx-config --cppflags` $CPPFLAGS"


AC_CHECK_LIB(z,compress2,,AC_MSG_ERROR(openCU requires zlib library installed))
LDFLAGS="-lz $LDFLAGS"

#make default to be C++
AC_LANG([C++])
	

AC_OUTPUT

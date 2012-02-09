#                                               -*- Autoconf -*-
# Process this file with autoconf to produce a configure script.

AC_PREREQ([2.63])
AC_INIT([openCU Client], [0.04], [roozbeh@cuseeme.sourceforge.net], [openCU-Client])

AC_CONFIG_SRCDIR([src/])

AC_CONFIG_HEADERS([inc/config.h])
AC_CONFIG_FILES([Makefile src/Makefile])

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
#AC_CONFIG_SUBDIRS([extlib/opencv-1.1pre1])
AC_CONFIG_SUBDIRS([extlib/pa_stable_v19_20071207])
AC_CONFIG_SUBDIRS([extlib/speex-1.2rc1]) 
AC_CONFIG_SUBDIRS([extlib/xvidcore-1.2.2/build/generic])

#make default to be C++
AC_LANG([C++])

AC_CONFIG_SUBDIRS([extlib/wxGTK-2.8.10])

AC_OUTPUT

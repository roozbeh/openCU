#ifndef _STD_AFX_H_
#define _WINSOCKAPI_
// For compilers that support precompilation, includes "wx/wx.h".
#include "wx/wxprec.h"

#ifdef __BORLANDC__
    #pragma hdrstop
#endif

/* wxWidgets */
#ifndef WX_PRECOMP
    #include "wx/wx.h"
#endif

#include <wx/treectrl.h>
#include <wx/thread.h>
#include <wx/gauge.h>
#include <wx/statusbr.h>
#include <wx/imaglist.h>
#include <wx/richtext/richtextctrl.h>

/* STL */
#include <string>
#include <list>
#include <map>
#include <queue>
#include <sstream>

/** Sockets */
#ifdef WIN32
#include <winsock2.h>
#endif

/* openCV */
#include <cxcore.h>
#include <highgui.h>
#include <cv.h>

/* port audio */
#include <portaudio.h>

/* for supporting autoconf */
#include "config.h"

/* for xvid */
#include <xvid.h>

/* for speex */
#include <speex/speex.h>
#include <speex/speex_callbacks.h>

#endif


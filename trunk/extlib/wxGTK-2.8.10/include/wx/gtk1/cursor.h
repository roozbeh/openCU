/////////////////////////////////////////////////////////////////////////////
// Name:        wx/gtk1/cursor.h
// Purpose:
// Author:      Robert Roebling
// Id:          $Id: cursor.h,v 1.1 2009/06/02 18:24:55 yakhmak Exp $
// Copyright:   (c) 1998 Robert Roebling
// Licence:     wxWindows licence
/////////////////////////////////////////////////////////////////////////////

#ifndef __GTKCURSORH__
#define __GTKCURSORH__

#include "wx/defs.h"
#include "wx/object.h"
#include "wx/gdicmn.h"

#if wxUSE_IMAGE
#include "wx/image.h"
#endif

//-----------------------------------------------------------------------------
// wxCursor
//-----------------------------------------------------------------------------

class WXDLLIMPEXP_CORE wxCursor: public wxObject
{
public:

    wxCursor();
    wxCursor( int cursorId );
#if wxUSE_IMAGE
    wxCursor( const wxImage & image );
#endif
    wxCursor( const char bits[], int width, int  height,
              int hotSpotX=-1, int hotSpotY=-1,
              const char maskBits[] = NULL, const wxColour *fg = NULL, const wxColour *bg = NULL );
    virtual ~wxCursor();
    bool Ok() const { return IsOk(); }
    bool IsOk() const;

    // implementation

    GdkCursor *GetCursor() const;

private:
    DECLARE_DYNAMIC_CLASS(wxCursor)
};

#endif // __GTKCURSORH__

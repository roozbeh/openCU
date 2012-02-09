# $Id: Makefile.in,v 1.1 2009/06/02 18:24:50 yakhmak Exp $

CONTRIB_SUBDIRS=fl gizmos mmedia ogl plot stc svg #deprecated #applet

all:
	@for d in $(CONTRIB_SUBDIRS); do (cd $$d && $(MAKE)); done

clean:
	@for d in $(CONTRIB_SUBDIRS); do (cd $$d && $(MAKE) clean); done

install:
	@for d in $(CONTRIB_SUBDIRS); do (cd $$d && $(MAKE) install); done

uninstall:
	@for d in $(CONTRIB_SUBDIRS); do (cd $$d && $(MAKE) uninstall); done

.PHONY: all clean install uninstall


# $Id: Makefile.in,v 1.1 2009/06/02 18:24:49 yakhmak Exp $

CONTRIB_SAMPLES=editlbox dynsash dynsash_switch multicell splittree led

all:
	@for d in $(CONTRIB_SAMPLES); do (cd $$d && $(MAKE)); done

clean:
	@for d in $(CONTRIB_SAMPLES); do (cd $$d && $(MAKE) clean); done


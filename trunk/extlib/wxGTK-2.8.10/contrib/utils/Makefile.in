# $Id: Makefile.in,v 1.1 2009/06/02 18:24:52 yakhmak Exp $

CONTRIB_UTILS=convertrc

all:
	@for d in $(CONTRIB_UTILS); do (cd $$d && $(MAKE)); done

clean:
	@for d in $(CONTRIB_UTILS); do (cd $$d && $(MAKE) clean); done


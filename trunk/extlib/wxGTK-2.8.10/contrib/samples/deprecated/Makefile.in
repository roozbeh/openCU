# $Id: Makefile.in,v 1.1 2009/06/02 18:24:48 yakhmak Exp $

CONTRIB_SAMPLES=proplist resource treelay

all:
	@for d in $(CONTRIB_SAMPLES); do (cd $$d && $(MAKE)); done

clean:
	@for d in $(CONTRIB_SAMPLES); do (cd $$d && $(MAKE) clean); done


#############################################################################
#
# XviD examples Makefile
#
# $Id: Makefile,v 1.1 2009/06/02 18:25:20 yakhmak Exp $
#
#############################################################################

include ../build/generic/platform.inc

HDIR    = -I../src
CFLAGS  = -g $(ARCHITECTURE) $(BUS) $(ENDIANNESS) $(FEATURES) $(SPECIFIC_CFLAGS)
LDFLAGS = -lc -lm ../build/generic/=build/$(STATIC_LIB) -lpthread

SOURCES= xvid_encraw.c xvid_decraw.c xvid_bench.c
OBJECTS=$(SOURCES:.c=.o)
TESTS=$(SOURCES:.c=)

all: $(TESTS)

xvid_encraw: xvid_encraw.o
	$(CC) -o $@ $< $(LDFLAGS)

xvid_encraw.o: xvid_encraw.c
	$(CC) $(CFLAGS) $(HDIR) -c $<

xvid_decraw: xvid_decraw.o
	$(CC) -o $@ $< $(LDFLAGS)

xvid_decraw.o: xvid_decraw.c
	$(CC) $(CFLAGS) $(HDIR) -c $<

xvid_bench: xvid_bench.o
	$(CC) -o $@ $< $(LDFLAGS)

xvid_bench.o: xvid_bench.c
	$(CC) $(CFLAGS) $(HDIR) -c $<

clean:
	rm -f $(OBJECTS) $(TESTS)

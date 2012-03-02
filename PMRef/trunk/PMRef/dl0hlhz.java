// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.net.UnknownHostException;
import java.util.Vector;

// Referenced classes of package PMRef:
//            dlkhlhz, d4321hz

class dl0hlhz extends dlkhlhz
{

    dl0hlhz()
    {
    }

    synchronized void bdhb(String s, String s1)
        throws UnknownHostException
    {
        super.ktdblhbdhb(s);
        bdHb = s1;
    }

    String Bdhb(String s)
    {
        return "Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + bdHb;
    }

    synchronized String ktdblhbdHb()
    {
        int i = super.bdhB.size();
        StringBuffer stringbuffer = new StringBuffer();
        for(int j = 0; j < i; j++)
        {
            d4321hz d4321hz1 = (d4321hz)super.bdhB.elementAt(j);
            stringbuffer.append(d4321hz1.Bdhb());
            stringbuffer.append(" : [" + bdHb + "]\n");
        }

        return stringbuffer.toString();
    }

    synchronized void ktdblhBDHb(String s)
    {
        super.ktdblhBDHb(s);
    }

    String bdHb;
}

// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package PMRef:
//            dlkhlhz, d4321hz

class d2k49hz extends dlkhlhz
{

    d2k49hz()
    {
        bdHb = new Hashtable();
    }

    synchronized void bdhb(String s, String s1)
        throws UnknownHostException
    {
        super.ktdblhbdhb(s);
        bdHb.put(s, s1);
    }

    String Bdhb(InetAddress inetaddress)
    {
        int i = super.bdhB.size();
        for(int j = 0; j < i; j++)
        {
            d4321hz d4321hz1 = (d4321hz)super.bdhB.elementAt(j);
            if(d4321hz1.bdhb(inetaddress))
                return "Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + (String)bdHb.get(d4321hz1.Bdhb());
        }

        return "error";
    }

    String ktdblhbdHb()
    {
        int i = super.bdhB.size();
        StringBuffer stringbuffer = new StringBuffer();
        for(int j = 0; j < i; j++)
        {
            d4321hz d4321hz1 = (d4321hz)super.bdhB.elementAt(j);
            stringbuffer.append(d4321hz1.Bdhb());
            stringbuffer.append(" : [" + bdHb.get(d4321hz1.Bdhb()) + "]\n");
        }

        return stringbuffer.toString();
    }

    synchronized void ktdblhBDHb(String s)
    {
        super.ktdblhBDHb(s);
        bdHb.remove(s);
    }

    Hashtable bdHb;
}

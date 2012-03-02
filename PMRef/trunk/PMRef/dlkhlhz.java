// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

// Referenced classes of package PMRef:
//            Constants, d4321hz

class dlkhlhz
    implements Constants
{

    dlkhlhz()
    {
        bdhB = new Vector();
    }

    synchronized void ktdblhbdhb(String s)
        throws UnknownHostException
    {
        d4321hz d4321hz1 = new d4321hz(s);
        ktdblhBDHb(s);
        bdhB.add(d4321hz1);
    }

    synchronized boolean ktdblhBdhb(InetAddress inetaddress)
    {
        int i = bdhB.size();
        for(int j = 0; j < i; j++)
        {
            d4321hz d4321hz1 = (d4321hz)bdhB.elementAt(j);
            if(d4321hz1.bdhb(inetaddress))
                return true;
        }

        return false;
    }

    synchronized String ktdblhbDhb(String s)
    {
        int i = bdhB.size();
        for(int j = 0; j < i; j++)
        {
            d4321hz d4321hz1 = (d4321hz)bdhB.elementAt(j);
            if(d4321hz1.equals(s))
                return d4321hz1.Bdhb();
        }

        return "No AddressMask";
    }

    synchronized int ktdblhBDhb(String s)
    {
        int i = bdhB.size();
        for(int j = 0; j < i; j++)
        {
            d4321hz d4321hz1 = (d4321hz)bdhB.elementAt(j);
            if(d4321hz1.Bdhb().equals(s))
                return j;
        }

        return -1;
    }

    synchronized String ktdblhbdHb()
    {
        int i = bdhB.size();
        StringBuffer stringbuffer = new StringBuffer();
        for(int j = 0; j < i; j++)
        {
            d4321hz d4321hz1 = (d4321hz)bdhB.elementAt(j);
            stringbuffer.append(d4321hz1.Bdhb() + "\n");
        }

        return stringbuffer.toString();
    }

    synchronized String ktdblhBdHb()
    {
        int i = bdhB.size();
        StringBuffer stringbuffer = new StringBuffer("");
        if(!bdhB.isEmpty())
        {
            d4321hz d4321hz1 = (d4321hz)bdhB.elementAt(0);
            stringbuffer.append(d4321hz1.Bdhb());
            for(int j = 1; j < i; j++)
            {
                stringbuffer.append(", ");
                d4321hz d4321hz2 = (d4321hz)bdhB.elementAt(j);
                stringbuffer.append(d4321hz2.Bdhb());
            }

        }
        return stringbuffer.toString();
    }

    boolean ktdblhbDHb()
    {
        return bdhB.isEmpty();
    }

    synchronized void ktdblhBDHb(String s)
    {
        int i = ktdblhBDhb(s);
        if(i != -1)
            bdhB.remove(i);
    }

    Vector bdhB;
}

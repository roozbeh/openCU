// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.net.InetAddress;

// Referenced classes of package PMRef:
//            d0khlhz, Constants

class dlkh89z
    implements Constants
{

    dlkh89z(d0khlhz d0khlhz1, long l, int i)
    {
        ltht = d0khlhz1;
        Ltht = l;
        lTht = i;
    }

    d0khlhz bdhb()
    {
        return ltht;
    }

    int Bdhb()
    {
        return lTht;
    }

    String bDhb()
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s = ltht.bDhb().getHostName();
        stringbuffer.append("Address : " + s + "  ");
        stringbuffer.append("Remain : " + BDhb(Ltht) + "  ");
        stringbuffer.append("Reason : " + Bdhb() + "\n");
        return stringbuffer.toString();
    }

    long BDhb(long l)
    {
        return (l * 1000L) / 1000L;
    }

    long bdHb()
    {
        return BDhb(Ltht);
    }

    synchronized void BdHb()
    {
        Ltht--;
    }

    private d0khlhz ltht;
    private long Ltht;
    private int lTht;
}

// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.util.Enumeration;
import java.util.Hashtable;

// Referenced classes of package PMRef:
//            Constants, dlkh89z, TimerObserver, d0khlhz

class d4kh98z
    implements Constants, TimerObserver
{

    d4kh98z()
    {
        ltht = new Hashtable();
    }

    synchronized void bdhb(dlkh89z dlkh89z1)
    {
        ltht.put(dlkh89z1.bdhb(), dlkh89z1);
    }

    synchronized dlkh89z Bdhb(d0khlhz d0khlhz)
    {
        return (dlkh89z)ltht.get(d0khlhz);
    }

    synchronized String bDhb()
    {
        Enumeration enumeration = ltht.elements();
        StringBuffer stringbuffer = new StringBuffer();
        dlkh89z dlkh89z1;
        for(; enumeration.hasMoreElements(); stringbuffer.append(dlkh89z1.bDhb()))
            dlkh89z1 = (dlkh89z)enumeration.nextElement();

        return stringbuffer.toString();
    }

    synchronized void BDhb(dlkh89z dlkh89z1)
    {
        ltht.remove(dlkh89z1.bdhb());
    }

    public synchronized void updateTimer()
    {
        for(Enumeration enumeration = ltht.elements(); enumeration.hasMoreElements();)
        {
            dlkh89z dlkh89z1 = (dlkh89z)enumeration.nextElement();
            dlkh89z1.BdHb();
            if(dlkh89z1.bdHb() <= 0L)
                BDhb(dlkh89z1);
        }

    }

    private Hashtable ltht;
}

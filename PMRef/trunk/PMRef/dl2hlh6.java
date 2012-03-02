// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.util.Enumeration;
import java.util.Vector;

// Referenced classes of package PMRef:
//            Constants, TimerObserver

class dl2hlh6 extends Thread
    implements Constants
{

    dl2hlh6(long l)
    {
        BDhb = l;
        bdHb = new Vector();
        setName("Timer");
    }

    synchronized void bdhb(Object obj)
    {
        bdHb.add(obj);
    }

    void Bdhb()
    {
        Enumeration enumeration;
        synchronized(this)
        {
            enumeration = bdHb.elements();
        }
        TimerObserver timerobserver;
        for(; enumeration.hasMoreElements(); timerobserver.updateTimer())
            timerobserver = (TimerObserver)enumeration.nextElement();

    }

    synchronized void bDhb(Object obj)
    {
        bdHb.remove(obj);
    }

    public void run()
    {
        do
        {
            try
            {
                Thread.sleep(BDhb);
            }
            catch(InterruptedException _ex)
            {
                return;
            }
            Bdhb();
        } while(true);
    }

    long BDhb;
    Vector bdHb;
}

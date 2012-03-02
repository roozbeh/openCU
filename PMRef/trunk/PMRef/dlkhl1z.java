// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

// Referenced classes of package PMRef:
//            Constants, d2khlh9, TimerObserver

class dlkhl1z
    implements TimerObserver, Constants
{

    dlkhl1z(ConfigLoader d2khlh9_1)
    {
        BdHb = d2khlh9_1;
        try
        {
            BDhb = Integer.parseInt(d2khlh9_1.bdHBLFz().getProperty("pmref.gc", "0"));
        }
        catch(NumberFormatException _ex)
        {
            bdHb = new StringBuffer();
            bdHb.append("Proparties file Error : pmref.gc , using the default value(0).");
            d2khlh9_1.bDHbLfZ(bdHb.toString());
            BDhb = Integer.parseInt("0");
        }
    }

    int bdhb()
    {
        return BDhb;
    }

    int Bdhb(int i)
    {
        return (int)(((long)i * 1000L) / 1000L);
    }

    public void updateTimer()
    {
        bDhb++;
        if(BDhb < Bdhb(bDhb))
        {
            System.gc();
            bDhb = 0;
        }
    }

    int bDhb;
    int BDhb;
    StringBuffer bdHb;
    ConfigLoader BdHb;
}

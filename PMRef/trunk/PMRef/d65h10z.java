// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.net.InetAddress;
import java.net.UnknownHostException;

// Referenced classes of package PMRef:
//            Constants, dl4h01z

class d65h10z
    implements Constants
{

    d65h10z(InetAddress inetaddress)
    {
        BDhBl = inetaddress;
        bDHBl = BDhBl.getAddress();
        try
        {
            bdHBl = InetAddress.getByName("0.0.0.0");
        }
        catch(UnknownHostException _ex) { }
        bdhbL = 0;
        BdhbL = 0;
        BDHBl = 0L;
    }

    byte[] bdhbl()
    {
        return bDHBl;
    }

    synchronized dl4h01z Bdhbl()
    {
        return BdHBl;
    }

    InetAddress bDhbl()
    {
        return bdHBl;
    }

    InetAddress BDhbl()
    {
        return BDhBl;
    }

    int bdHbl()
    {
        return bdhbL;
    }

    synchronized boolean BdHbl(dl4h01z dl4h01z1)
    {
        switch(bdhbL)
        {
        case 0: // '\0'
            bdhbL = 1;
            BdHBl = dl4h01z1;
            bdHBl = dl4h01z1.Bdhb();
            return true;

        case 1: // '\001'
            return dl4h01z1.Bdhb().equals(BdHBl.Bdhb());

        case 2: // '\002'
            return dl4h01z1.Bdhb().equals(BdHBl.Bdhb());

        case 3: // '\003'
            return false;
        }
        return false;
    }

    synchronized boolean bDHbl()
    {
        BdhbL--;
        return BdhbL != 0;
    }

    synchronized void BDHbl()
    {
        BdhbL++;
    }

    synchronized void bdhBl()
    {
        try
        {
            bdHBl = InetAddress.getByName("0.0.0.0");
        }
        catch(UnknownHostException _ex) { }
        BDHBl = 0L;
        BdHBl.BdHb();
        BdHBl = null;
        bdhbL = 0;
    }

    void BdhBl(int i)
    {
        bdhbL = i;
    }

    void bDhBl()
    {
        BDHBl = System.currentTimeMillis();
    }

    public void updateTimer()
    {
        if(BDHBl != 0L && System.currentTimeMillis() - BDHBl > 60000L)
            bdhBl();
    }

    InetAddress BDhBl;
    InetAddress bdHBl;
    dl4h01z BdHBl;
    byte bDHBl[];
    long BDHBl;
    int bdhbL;
    int BdhbL;
    int bDhbL;
}

// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.net.InetAddress;

// Referenced classes of package PMRef:
//            Constants, d65h10z

class dl4h01z
    implements Constants
{

    dl4h01z(InetAddress inetaddress, d65h10z d65h10z)
    {
        bdhB = inetaddress;
        BdhB = bdhB.getAddress();
        bDhB = d65h10z;
        bdHB = 0;
    }

    byte[] bdhb()
    {
        return BdhB;
    }

    InetAddress Bdhb()
    {
        return bdhB;
    }

    int bDhb()
    {
        return BDhB;
    }

    d65h10z BDhb()
    {
        return bDhB;
    }

    int bdHb()
    {
        return bdHB;
    }

    void BdHb()
    {
        bdHB = 0;
    }

    void bDHb(int i)
    {
        BDhB = i;
    }

    void BDHb(int i)
    {
        bdHB = i;
    }

    InetAddress bdhB;
    byte BdhB[];
    d65h10z bDhB;
    int BDhB;
    int bdHB;
}

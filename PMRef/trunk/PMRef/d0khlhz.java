// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.net.InetAddress;

// Referenced classes of package PMRef:
//            d21hlhz

class d0khlhz
{

    d0khlhz(InetAddress inetaddress, int i, InetAddress inetaddress1, int j)
    {
        bdHb = inetaddress;
        BdHb = i;
        bDHb = inetaddress1;
        BDHb = j;
    }

    public boolean equals(Object obj)
    {
        return BdHb == ((d0khlhz)obj).BDhb() && BDHb == ((d0khlhz)obj).Bdhb() && bdHb.equals(((d0khlhz)obj).bDhb()) && bDHb.equals(((d0khlhz)obj).bdhb());
    }

    InetAddress bdhb()
    {
        return bDHb;
    }

    int Bdhb()
    {
        return BDHb;
    }

    InetAddress bDhb()
    {
        return bdHb;
    }

    int BDhb()
    {
        return BdHb;
    }

    public int hashCode()
    {
        return Utils.readInt(bdHb.getAddress(), 0);
    }

    public String toString()
    {
        return bdHb.toString() + ":" + bDHb.toString();
    }

    InetAddress bdHb;
    int BdHb;
    InetAddress bDHb;
    int BDHb;
}

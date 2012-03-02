// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;

// Referenced classes of package PMRef:
//            d0khlhz, d21hlhz, Constants, d4321hz

class dlk9lh4
    implements Constants
{

    dlk9lh4(String s)
        throws UnknownHostException, NumberFormatException
    {
        bdhBl = new d4321hz(s);
        int i = bdhBl.BDhb();
        if(i < 9 || i > 24)
            throw new NumberFormatException();
        BdhBl = bdhBl.bDhb().getAddress();
        if(BdhBl[0] != 127)
            throw new NumberFormatException();
        bDhBl = new byte[4];
        System.arraycopy(BdhBl, 0, bDhBl, 0, 4);
        bDhBl[3] = 1;
        BDhBl = new Hashtable();
        bdHBl = new Hashtable();
        BdHBl = BdhBl[1] * 0x10000 + BdhBl[2] * 256 + BdhBl[3];
        BDHBl = 1;
        for(int j = 0; j < 32 - i; j++)
            BDHBl *= 2;

        BDHBl--;
        bdhbL = bDHBl + 1;
    }

    synchronized InetAddress bdhbl(d0khlhz d0khlhz1)
    {
        if(BDhBl.containsKey(d0khlhz1))
            return (InetAddress)BDhBl.get(d0khlhz1);
        for(int i = bdhbL; i < BDHBl; i++)
            try
            {
                InetAddress inetaddress = InetAddress.getByName(Utils.BDHb(Bdhbl(i + BdHBl)));
                if(!bdHBl.containsKey(inetaddress))
                {
                    BDhBl.put(d0khlhz1, inetaddress);
                    bdHBl.put(inetaddress, d0khlhz1);
                    bdhbL = i + 1;
                    if(bdhbL == BDHBl)
                        bdhbL = bDHBl + 1;
                    return inetaddress;
                }
            }
            catch(UnknownHostException _ex) { }

        for(int j = bDHBl + 1; j < bdhbL; j++)
            try
            {
                InetAddress inetaddress1 = InetAddress.getByName(Utils.BDHb(Bdhbl(j + BdHBl)));
                if(!bdHBl.containsKey(inetaddress1))
                {
                    BDhBl.put(d0khlhz1, inetaddress1);
                    bdHBl.put(inetaddress1, d0khlhz1);
                    bdhbL = j + 1;
                    if(bdhbL == BDHBl)
                        bdhbL = bDHBl + 1;
                    return inetaddress1;
                }
            }
            catch(UnknownHostException _ex) { }

        return null;
    }

    byte[] Bdhbl(int i)
    {
        byte abyte0[] = new byte[4];
        abyte0[3] = (byte)(i % 256);
        i /= 256;
        abyte0[2] = (byte)(i % 256);
        i /= 256;
        abyte0[1] = (byte)(i % 256);
        abyte0[0] = 127;
        return abyte0;
    }

    int bDhbl(InetAddress inetaddress)
    {
        byte abyte0[] = inetaddress.getAddress();
        abyte0[1] ^= BdhBl[1];
        abyte0[2] ^= BdhBl[2];
        return abyte0[1] * 0x10000 + abyte0[2] * 256 + abyte0[3];
    }

    synchronized boolean BDhbl(d0khlhz d0khlhz1)
    {
        InetAddress inetaddress = (InetAddress)BDhBl.remove(d0khlhz1);
        bdHBl.remove(inetaddress);
        if(inetaddress != null)
        {
            int i = bDhbl(inetaddress);
            return true;
        } else
        {
            return false;
        }
    }

    String bdHbl()
    {
        return bdhBl.Bdhb();
    }

    d0khlhz BdHbl(InetAddress inetaddress)
    {
        return (d0khlhz)bdHBl.get(inetaddress);
    }

    byte[] bDHbl()
    {
        return bDhBl;
    }

    int BDHbl()
    {
        return BDHBl;
    }

    d4321hz bdhBl;
    byte BdhBl[];
    byte bDhBl[];
    Hashtable BDhBl;
    Hashtable bdHBl;
    int BdHBl;
    int bDHBl;
    int BDHBl;
    int bdhbL;
}

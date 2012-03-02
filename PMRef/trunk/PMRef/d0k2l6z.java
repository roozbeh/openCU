// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.net.*;
import java.util.Vector;

// Referenced classes of package PMRef:
//            d21hlhz, dlk7lhz, d8kh3hz, Constants, 
//            d87hl2z, d210l8z

class d0k2l6z
    implements Constants
{

    d0k2l6z(d210l8z d210l8z1, Conference d8kh3hz1)
    {
        ltht = d210l8z1;
        Ltht = d8kh3hz1;
        lTht = d210l8z1.bDhb();
        ltHt = d210l8z1.bdHb();
        try
        {
            LTht = InetAddress.getByName(lTht);
            lTHt = InetAddress.getByName("0.0.0.0");
        }
        catch(UnknownHostException _ex) { }
        LTHt = d8kh3hz1.BDHbLFzv();
        LthT = new Vector();
        d210l8z1.bdhb(this);
    }

    synchronized void bdhbl(String s)
    {
        if(!LthT.contains(s))
            LthT.add(s);
    }

    String Bdhbl()
    {
        return lTht;
    }

    Conference bDhbl()
    {
        return Ltht;
    }

    DatagramSocket BDhbl()
    {
        return ltht.BDhb();
    }

    InetAddress bdHbl()
    {
        return LTht;
    }

    InetAddress BdHbl()
    {
        return ltHt;
    }

    InetAddress bDHbl()
    {
        MainReceiver d87hl2z1 = ltht.BdHb();
        if(d87hl2z1 != null)
            return d87hl2z1.BDHb();
        else
            return null;
    }

    d210l8z BDHbl()
    {
        return ltht;
    }

    int bdhBl(int i)
    {
        return (int)(((long)i * 1000L) / 1000L);
    }

    synchronized void BdhBl()
    {
        LthT = new Vector();
    }

    synchronized void bDhBl(String s)
    {
        if(LthT.contains(s))
            LthT.remove(s);
    }

    void BDhBl(byte abyte0[])
    {
        if(LthT.contains(Utils.convertToIPform(abyte0, 12)))
        {
            return;
        } else
        {
            bDHBl(abyte0, 2, ltHt, lTHt, 21761);
            return;
        }
    }

    void bdHBl(byte abyte0[], int i)
    {
        if(LthT.contains(Utils.convertToIPform(abyte0, 12)))
        {
            return;
        } else
        {
            BDHBl(abyte0, 2, ltHt, lTHt, 21761, i);
            return;
        }
    }

    void BdHBl(byte abyte0[], int i, String s, int j)
    {
        if(LthT.contains(Utils.convertToIPform(abyte0, 12)))
            return;
        try
        {
            InetAddress inetaddress = InetAddress.getByName(s);
            bDHBl(abyte0, i, inetaddress, lTHt, j);
        }
        catch(UnknownHostException _ex) { }
    }

    void bDHBl(byte abyte0[], int i, InetAddress inetaddress, InetAddress inetaddress1, int j)
    {
        if(ltht.BdhB())
        {
            int k = Utils.readShort(abyte0, 24);
            byte abyte1[] = new byte[26 + k];
            System.arraycopy(abyte0, 0, abyte1, 26, k);
            CuseemePacket dlk7lhz1 = new CuseemePacket(abyte1, true);
            dlk7lhz1.setSocket(BDhbl());
            dlk7lhz1.setDestFamily(i);
            dlk7lhz1.setDestport(LTHt);
            dlk7lhz1.setDestAddr2(inetaddress1);
            dlk7lhz1.setSourceAddress4(inetaddress);
            dlk7lhz1.setDataType(j);
            dlk7lhz1.setPacketLength(k + 26);
            try
            {
                dlk7lhz1.sendToAddress(LTht, 7648);
            }
            catch(Exception _ex)
            {
                StringBuffer stringbuffer = new StringBuffer();
                stringbuffer.append("DataSocket Send Error");
                Ltht.BdHblFZv(stringbuffer.toString());
            }
        }
    }

    void BDHBl(byte abyte0[], int i, InetAddress inetaddress, InetAddress inetaddress1, int j, int k)
    {
        if(ltht.BdhB())
        {
            int l = Utils.readShort(abyte0, 24);
            byte abyte1[] = new byte[26 + l];
            System.arraycopy(abyte0, 0, abyte1, 26, l);
            CuseemePacket dlk7lhz1 = new CuseemePacket(abyte1, true);
            dlk7lhz1.setSocket(BDhbl());
            dlk7lhz1.setDestFamily(i);
            dlk7lhz1.setDestport(k);
            dlk7lhz1.setDestAddr2(inetaddress1);
            dlk7lhz1.setSourceAddress4(inetaddress);
            dlk7lhz1.setDataType(j);
            dlk7lhz1.setPacketLength(l + 26);
            try
            {
                dlk7lhz1.sendToAddress(LTht, 7648);
            }
            catch(Exception _ex)
            {
                StringBuffer stringbuffer = new StringBuffer();
                stringbuffer.append("DataSocket Send Error");
                Ltht.BdHblFZv(stringbuffer.toString());
            }
        }
    }

    void bdhbL(byte abyte0[], int i, String s, int j)
    {
        if(bDHbl() != null)
            BdhbL(abyte0, i, ltht.BdHb().bdhb(), s, j);
    }

    void BdhbL(byte abyte0[], int i, String s, String s1, int j)
    {
        if(ltht.BdhB())
        {
            int k = abyte0.length;
            byte abyte1[] = new byte[26 + k];
            System.arraycopy(abyte0, 0, abyte1, 26, k);
            CuseemePacket dlk7lhz1 = new CuseemePacket(abyte1, true);
            dlk7lhz1.setSocket(BDhbl());
            dlk7lhz1.setDestFamily(i);
            dlk7lhz1.setDestport(LTHt);
            dlk7lhz1.setDestAddr(s1);
            dlk7lhz1.setSourceAddress3(s);
            dlk7lhz1.setDataType(j);
            dlk7lhz1.setPacketLength(k + 26);
            try
            {
                dlk7lhz1.sendToAddress(LTht, 7648);
            }
            catch(Exception _ex)
            {
                StringBuffer stringbuffer = new StringBuffer();
                stringbuffer.append("DataSocket Send Error");
                Ltht.BdHblFZv(stringbuffer.toString());
            }
        }
    }

    private d210l8z ltht;
    private Conference Ltht;
    private String lTht;
    private InetAddress LTht;
    private InetAddress ltHt;
    private InetAddress LtHt;
    private InetAddress lTHt;
    private int LTHt;
    private DatagramSocket lthT;
    private Vector LthT;
}

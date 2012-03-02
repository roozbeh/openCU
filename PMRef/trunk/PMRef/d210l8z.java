// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.net.*;
import java.util.Enumeration;
import java.util.Vector;

// Referenced classes of package PMRef:
//            dlk7lhz, Constants, d87hl2z, d0k2l6z, 
//            d2khlh9, TimerObserver

class d210l8z
    implements TimerObserver, Constants
{

    d210l8z(ConfigLoader d2khlh9_1, String s)
    {
        ltht = d2khlh9_1;
        Ltht = s;
        try
        {
            LTht = InetAddress.getByName(s);
        }
        catch(UnknownHostException _ex) { }
        ltHt = d2khlh9_1.bdHBlfZ();
        LtHt = ltHt.getAddress();
        LThT = new Vector();
        lThT = null;
        lTHt = false;
    }

    void bdhb(d0k2l6z d0k2l6z1)
    {
        LThT.add(d0k2l6z1);
    }

    void Bdhb(String s)
    {
        lTht = s;
        lthT = 0;
        if(!lTHt)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("Peer reflector is up, ");
            stringbuffer.append(Ltht + "(" + s + ")");
            ltht.bDHbLfZ(stringbuffer.toString());
            lTHt = true;
        }
    }

    String bDhb()
    {
        return Ltht;
    }

    DatagramSocket BDhb()
    {
        return LthT;
    }

    InetAddress bdHb()
    {
        return ltHt;
    }

    MainReceiver BdHb()
    {
        return lThT;
    }

    ConfigLoader bDHb()
    {
        return ltht;
    }

    int BDHb(int i)
    {
        return (int)(((long)i * 1000L) / 1000L);
    }

    String bdhB()
    {
        return lTht;
    }

    boolean BdhB()
    {
        return lTHt;
    }

    void bDhB(DatagramSocket datagramsocket, byte abyte0[], boolean flag)
    {
        byte abyte1[] = new byte[26];
        CuseemePacket dlk7lhz1 = new CuseemePacket(abyte1, true);
        dlk7lhz1.setSocket(datagramsocket);
        dlk7lhz1.setDestFamily(1);
        dlk7lhz1.setDestport(7648);
        dlk7lhz1.setDestAddr(Ltht);
        dlk7lhz1.setSourceFamily(2);
        dlk7lhz1.setSourcePort(7648);
        dlk7lhz1.setSourceAddr2(abyte0);
        dlk7lhz1.setMessageType(2);
        if(flag)
            dlk7lhz1.setDataType(21766);
        else
            dlk7lhz1.setDataType(21760);
        dlk7lhz1.setPacketLength(26);
        try
        {
            dlk7lhz1.sendToAddress(LTht, 7648);
        }
        catch(Exception _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("DataSocket Send Error");
            ltht.bDHbLfZ(stringbuffer.toString());
        }
    }

    void BDhB(DatagramSocket datagramsocket)
    {
        LthT = datagramsocket;
    }

    void bdHB(MainReceiver d87hl2z1)
    {
        BDhB(d87hl2z1.bDHb());
        lThT = d87hl2z1;
    }

    public void updateTimer()
    {
        lthT++;
        if(lTHt && BDHb(lthT) > 70)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("Peer reflector is down, ");
            stringbuffer.append(Ltht + "(" + lTht + ")");
            ltht.bDHbLfZ(stringbuffer.toString());
            lTHt = false;
            lThT = null;
            d0k2l6z d0k2l6z1;
            for(Enumeration enumeration = LThT.elements(); enumeration.hasMoreElements(); d0k2l6z1.BdhBl())
                d0k2l6z1 = (d0k2l6z)enumeration.nextElement();

        }
        LTHt++;
        if(BDHb(LTHt) > 15)
        {
            if(lThT != null)
            {
                bDhB(LthT, LtHt, false);
            } else
            {
                bDhB(ltht.bdHbLFz(0).bDHb(), LtHt, true);
                if(ltht.BdHbLFz() == 2)
                    bDhB(ltht.bdHbLFz(1).bDHb(), LtHt, true);
            }
            LTHt = 0;
        }
    }

    private ConfigLoader ltht;
    private String Ltht;
    private String lTht;
    private InetAddress LTht;
    private InetAddress ltHt;
    private byte LtHt[];
    private boolean lTHt;
    private int LTHt;
    private int lthT;
    private DatagramSocket LthT;
    private MainReceiver lThT;
    private Vector LThT;
    private static final int ltHT = 70;
    private static final int LtHT = 26;
    private static final int lTHT = 15;
}

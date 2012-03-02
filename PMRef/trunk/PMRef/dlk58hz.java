// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.io.IOException;
import java.net.*;

// Referenced classes of package PMRef:
//            d21hlhz, dlk7lhz, Constants, dl230hz, 
//            d6khl0z, d87hl2z, dl4h01z, d65h10z, 
//            d2khlh9

class dlk58hz extends Thread
    implements Constants
{

    dlk58hz(ConfigLoader d2khlh9_1, MainReceiver d87hl2z1, DatagramPacket datagrampacket, dl4h01z dl4h01z1, d65h10z d65h10z1, boolean flag)
    {
        ltht = d2khlh9_1;
        Ltht = d87hl2z1;
        lTht = datagrampacket;
        lTHt = dl4h01z1;
        LTHt = d65h10z1;
        LtHt = flag;
        setName("DatagramProxy");
    }

    void bdhb(String s, InetAddress inetaddress, InetAddress inetaddress1, int i)
    {
        byte abyte1[] = s.getBytes();
        int j = abyte1.length;
        byte abyte0[] = new byte[j + 3];
        System.arraycopy(abyte1, 0, abyte0, 3, j);
        abyte1 = Utils.ishort2Bytes(j);
        abyte0[2] = abyte1[1];
        DisconnectPacket dl230hz1 = new DisconnectPacket(abyte0);
        dl230hz1.setSocket(Ltht.bDHb());
        try
        {
            dl230hz1.bdhb(inetaddress, inetaddress1, Ltht.BDHb().getAddress(), i);
        }
        catch(Exception _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("DataSocket Send Error");
            bDhb(stringbuffer.toString());
        }
    }

    int Bdhb(byte abyte0[])
    {
        short word0 = Utils.BDhb(abyte0, 22);
        byte byte0;
        switch(word0)
        {
        case 101: // 'e'
            short word1 = Utils.BDhb(abyte0, 20);
            if(word1 == 1)
            {
                byte0 = 1;
                break;
            }
            if(word1 == 6)
                byte0 = 2;
            else
                byte0 = -1;
            break;

        case -32568: 
        case 1: // '\001'
        case 2: // '\002'
        case 200: 
            byte0 = 3;
            break;

        case 3: // '\003'
            byte0 = 4;
            break;

        case 110: // 'n'
            byte0 = 7;
            break;

        case 111: // 'o'
            byte0 = 8;
            break;

        case 256: 
            byte0 = 5;
            break;

        case 257: 
            long l = Utils.BdHb(lTht.getData(), 38);
            if(l == 0x4355746bL)
                byte0 = 9;
            else
                byte0 = 6;
            break;

        case 21760: 
            byte0 = 10;
            break;

        case 21761: 
            byte0 = 11;
            break;

        case 21762: 
            byte0 = 12;
            break;

        case 21763: 
            byte0 = 13;
            break;

        case 21764: 
            byte0 = 14;
            break;

        case 21765: 
            byte0 = 15;
            break;

        case 104: // 'h'
            byte0 = 17;
            break;

        case 105: // 'i'
            byte0 = 16;
            break;

        case 100: // 'd'
            byte0 = 18;
            break;

        case 204: 
            byte0 = 19;
            break;

        default:
            byte0 = -1;
            break;
        }
        return byte0;
    }

    void bDhb(String s)
    {
        ltht.getLogger().logStr(s);
    }

    public void run()
    {
        try
        {
            LTht = lTht.getData();
            ltHt = Bdhb(LTht);
            String s = Utils.convertToIPform(LTht, 12);
            InetAddress inetaddress1 = lTht.getAddress();
            byte abyte0[] = inetaddress1.getAddress();
            String s1 = Utils.BDHb(abyte0);
            byte abyte1[] = new byte[4];
            System.arraycopy(LTht, 12, abyte1, 0, 4);
            byte abyte2[] = new byte[4];
            System.arraycopy(LTht, 4, abyte2, 0, 4);
            switch(ltHt)
            {
            case 1: // '\001'
                if(LtHt)
                {
                    switch(lTHt.bdHb())
                    {
                    case 0: // '\0'
                        if(lTHt.BDhb().BdHbl(lTHt))
                        {
                            lTHt.BDhb().bDhBl();
                            if(Utils.compare(abyte1, lTHt.bdhb(), 4))
                                System.arraycopy(Ltht.bDhb().Bdhb(), 0, LTht, 12, 4);
                            if(Utils.compare(abyte2, Ltht.Bdhb(), 4))
                                System.arraycopy(lTHt.BDhb().bdhbl(), 0, LTht, 4, 4);
                            short word0 = Utils.BDhb(LTht, 26);
                            byte abyte3[] = new byte[4];
                            byte abyte6[] = lTHt.bdhb();
                            for(int i = 0; i < word0; i++)
                            {
                                System.arraycopy(LTht, 56 + i * 12, abyte3, 0, 4);
                                if(!Utils.compare(abyte3, abyte6, 4))
                                    continue;
                                System.arraycopy(Ltht.bDhb().Bdhb(), 0, LTht, 56 + i * 12, 4);
                                break;
                            }

                            CuseemePacket dlk7lhz1 = new CuseemePacket(LTht);
                            dlk7lhz1.setSocket(Ltht.bDhb().bDHb());
                            try
                            {
                                dlk7lhz1.sendToAddress(lTHt.BDhb().BDhbl(), 7648);
                            }
                            catch(SocketException _ex) { }
                            catch(IOException _ex)
                            {
                                bDhb("Proxy Packet Error");
                            }
                        } else
                        {
                            StringBuffer stringbuffer = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + ltht.BDHBLFz());
                            try
                            {
                                InetAddress inetaddress = InetAddress.getByName(s);
                                bdhb(stringbuffer.toString(), inetaddress1, inetaddress, lTHt.bDhb());
                            }
                            catch(UnknownHostException _ex) { }
                        }
                        break;

                    case 1: // '\001'
                        String s2 = Utils.readStr(LTht, 32);
                        lTHt.BDHb(2);
                        lTHt.BDhb().BdhBl(2);
                        StringBuffer stringbuffer1 = new StringBuffer();
                        stringbuffer1.append("Proxy starts for " + s2 + " from " + s1 + " to " + Utils.BDHb(lTHt.BDhb().bdhbl()));
                        bDhb(stringbuffer1.toString());
                        // fall through

                    case 2: // '\002'
                        lTHt.BDhb().bDhBl();
                        if(Utils.compare(abyte1, lTHt.bdhb(), 4))
                            System.arraycopy(Ltht.bDhb().Bdhb(), 0, LTht, 12, 4);
                        if(Utils.compare(abyte2, Ltht.Bdhb(), 4))
                            System.arraycopy(lTHt.BDhb().bdhbl(), 0, LTht, 4, 4);
                        short word1 = Utils.BDhb(LTht, 26);
                        byte abyte4[] = new byte[4];
                        byte abyte7[] = lTHt.bdhb();
                        for(int j = 0; j < word1; j++)
                        {
                            System.arraycopy(LTht, 56 + j * 12, abyte4, 0, 4);
                            if(!Utils.compare(abyte4, abyte7, 4))
                                continue;
                            System.arraycopy(Ltht.bDhb().Bdhb(), 0, LTht, 56 + j * 12, 4);
                            break;
                        }

                        CuseemePacket dlk7lhz2 = new CuseemePacket(LTht);
                        dlk7lhz2.setSocket(Ltht.bDhb().bDHb());
                        try
                        {
                            dlk7lhz2.sendToAddress(lTHt.BDhb().BDhbl(), 7648);
                        }
                        catch(SocketException _ex) { }
                        catch(IOException _ex)
                        {
                            bDhb("Proxy Packet Error");
                        }
                        break;
                    }
                } else
                {
                    if(Utils.compare(abyte1, LTHt.bdhbl(), 4))
                        System.arraycopy(Ltht.bDhb().Bdhb(), 0, LTht, 12, 4);
                    else
                    if(Utils.compare(abyte1, Ltht.Bdhb(), 4))
                        System.arraycopy(LTHt.bDhbl().getAddress(), 0, LTht, 12, 4);
                    if(Utils.compare(abyte2, Ltht.Bdhb(), 4))
                        System.arraycopy(LTHt.bDhbl().getAddress(), 0, LTht, 4, 4);
                    else
                    if(Utils.compare(abyte2, LTHt.bdhbl(), 4))
                        System.arraycopy(Ltht.bDhb().Bdhb(), 0, LTht, 4, 4);
                    short word2 = Utils.BDhb(LTht, 26);
                    byte abyte5[] = new byte[4];
                    byte abyte8[] = LTHt.bDhbl().getAddress();
                    for(int k = 0; k < word2; k++)
                    {
                        System.arraycopy(LTht, 56 + k * 12, abyte5, 0, 4);
                        if(!Utils.compare(abyte5, Ltht.Bdhb(), 4))
                            continue;
                        System.arraycopy(abyte8, 0, LTht, 56 + k * 12, 4);
                        break;
                    }

                    CuseemePacket dlk7lhz3 = new CuseemePacket(LTht);
                    dlk7lhz3.setSocket(Ltht.bDhb().bDHb());
                    try
                    {
                        dlk7lhz3.sendToAddress(LTHt.Bdhbl().Bdhb(), LTHt.Bdhbl().bDhb());
                    }
                    catch(SocketException _ex) { }
                    catch(IOException _ex)
                    {
                        bDhb("Proxy Packet Error");
                    }
                }
                break;

            case 2: // '\002'
                if(LtHt)
                {
                    switch(lTHt.bdHb())
                    {
                    case 0: // '\0'
                        if(Utils.compare(abyte1, lTHt.bdhb(), 4))
                            System.arraycopy(Ltht.bDhb().Bdhb(), 0, LTht, 12, 4);
                        if(Utils.compare(abyte2, Ltht.Bdhb(), 4))
                            System.arraycopy(lTHt.BDhb().bdhbl(), 0, LTht, 4, 4);
                        CuseemePacket dlk7lhz4 = new CuseemePacket(LTht);
                        dlk7lhz4.setSocket(Ltht.bDhb().bDHb());
                        try
                        {
                            dlk7lhz4.sendToAddress(lTHt.BDhb().BDhbl(), 7648);
                        }
                        catch(SocketException _ex) { }
                        catch(IOException _ex)
                        {
                            bDhb("Proxy Packet Error");
                        }
                        lTHt.BDhb().bdhBl();
                        break;

                    case 2: // '\002'
                        String s3 = Utils.readStr(LTht, 32);
                        lTHt.BDHb(3);
                        lTHt.BDhb().BdhBl(3);
                        StringBuffer stringbuffer2 = new StringBuffer();
                        stringbuffer2.append("Proxy ends for " + s3 + " from " + s1 + " to " + Utils.BDHb(lTHt.BDhb().bdhbl()));
                        bDhb(stringbuffer2.toString());
                        // fall through

                    case 1: // '\001'
                    case 3: // '\003'
                        if(Utils.compare(abyte1, lTHt.bdhb(), 4))
                            System.arraycopy(Ltht.bDhb().Bdhb(), 0, LTht, 12, 4);
                        if(Utils.compare(abyte2, Ltht.Bdhb(), 4))
                            System.arraycopy(lTHt.BDhb().bdhbl(), 0, LTht, 4, 4);
                        CuseemePacket dlk7lhz5 = new CuseemePacket(LTht);
                        dlk7lhz5.setSocket(Ltht.bDhb().bDHb());
                        try
                        {
                            dlk7lhz5.sendToAddress(lTHt.BDhb().BDhbl(), 7648);
                        }
                        catch(SocketException _ex) { }
                        catch(IOException _ex)
                        {
                            bDhb("Proxy Packet Error");
                        }
                        try
                        {
                            Thread.sleep(10000L);
                        }
                        catch(InterruptedException _ex) { }
                        lTHt.BDhb().bdhBl();
                        break;
                    }
                } else
                {
                    if(Utils.compare(abyte1, LTHt.bdhbl(), 4))
                        System.arraycopy(Ltht.bDhb().Bdhb(), 0, LTht, 12, 4);
                    else
                    if(Utils.compare(abyte1, Ltht.Bdhb(), 4))
                        System.arraycopy(LTHt.bDhbl().getAddress(), 0, LTht, 12, 4);
                    if(Utils.compare(abyte2, Ltht.Bdhb(), 4))
                        System.arraycopy(LTHt.bDhbl().getAddress(), 0, LTht, 4, 4);
                    else
                    if(Utils.compare(abyte2, LTHt.bdhbl(), 4))
                        System.arraycopy(Ltht.bDhb().Bdhb(), 0, LTht, 4, 4);
                    CuseemePacket dlk7lhz6 = new CuseemePacket(LTht);
                    dlk7lhz6.setSocket(Ltht.bDhb().bDHb());
                    try
                    {
                        dlk7lhz6.sendToAddress(LTHt.Bdhbl().Bdhb(), LTHt.Bdhbl().bDhb());
                    }
                    catch(SocketException _ex) { }
                    catch(IOException _ex)
                    {
                        bDhb("Proxy Packet Error");
                    }
                }
                break;

            case 16: // '\020'
                if(!LtHt && lTHt.bdHb() == 0)
                    lTHt.BDHb(1);
                // fall through

            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            case 8: // '\b'
            case 9: // '\t'
            case 10: // '\n'
            case 11: // '\013'
            case 12: // '\f'
            case 13: // '\r'
            case 14: // '\016'
            case 15: // '\017'
            case 17: // '\021'
            case 18: // '\022'
                if(LtHt)
                {
                    if(Utils.compare(abyte1, lTHt.bdhb(), 4))
                        System.arraycopy(Ltht.bDhb().Bdhb(), 0, LTht, 12, 4);
                    if(Utils.compare(abyte2, Ltht.Bdhb(), 4))
                        System.arraycopy(lTHt.BDhb().bdhbl(), 0, LTht, 4, 4);
                    CuseemePacket dlk7lhz7 = new CuseemePacket(LTht);
                    dlk7lhz7.setSocket(Ltht.bDhb().bDHb());
                    try
                    {
                        dlk7lhz7.sendToAddress(lTHt.BDhb().BDhbl(), 7648);
                    }
                    catch(SocketException _ex) { }
                    catch(IOException _ex)
                    {
                        bDhb("Proxy Packet Error");
                    }
                } else
                {
                    if(Utils.compare(abyte1, LTHt.bdhbl(), 4))
                        System.arraycopy(Ltht.bDhb().Bdhb(), 0, LTht, 12, 4);
                    if(Utils.compare(abyte2, Ltht.Bdhb(), 4))
                        System.arraycopy(LTHt.bDhbl().getAddress(), 0, LTht, 4, 4);
                    CuseemePacket dlk7lhz8 = new CuseemePacket(LTht);
                    dlk7lhz8.setSocket(Ltht.bDhb().bDHb());
                    try
                    {
                        dlk7lhz8.sendToAddress(LTHt.Bdhbl().Bdhb(), LTHt.Bdhbl().bDhb());
                    }
                    catch(SocketException _ex) { }
                    catch(IOException _ex)
                    {
                        bDhb("Proxy Packet Error");
                    }
                }
                break;

            case 19: // '\023'
                if(LtHt)
                {
                    byte abyte9[] = new byte[4];
                    System.arraycopy(LTht, 30, abyte9, 0, 4);
                    if(Utils.compare(abyte1, lTHt.bdhb(), 4))
                        System.arraycopy(Ltht.bDhb().Bdhb(), 0, LTht, 12, 4);
                    if(Utils.compare(abyte2, Ltht.Bdhb(), 4))
                        System.arraycopy(lTHt.BDhb().bdhbl(), 0, LTht, 4, 4);
                    if(Utils.compare(abyte9, lTHt.bdhb(), 4))
                        System.arraycopy(Ltht.bDhb().Bdhb(), 0, LTht, 30, 4);
                    CuseemePacket dlk7lhz9 = new CuseemePacket(LTht);
                    dlk7lhz9.setSocket(Ltht.bDhb().bDHb());
                    try
                    {
                        dlk7lhz9.sendToAddress(lTHt.BDhb().BDhbl(), 7648);
                    }
                    catch(SocketException _ex) { }
                    catch(IOException _ex)
                    {
                        bDhb("Proxy Packet Error");
                    }
                    break;
                }
                if(Utils.compare(abyte1, LTHt.bdhbl(), 4))
                    System.arraycopy(Ltht.bDhb().Bdhb(), 0, LTht, 12, 4);
                if(Utils.compare(abyte2, Ltht.Bdhb(), 4))
                    System.arraycopy(LTHt.bDhbl().getAddress(), 0, LTht, 4, 4);
                CuseemePacket dlk7lhz10 = new CuseemePacket(LTht);
                dlk7lhz10.setSocket(Ltht.bDhb().bDHb());
                try
                {
                    dlk7lhz10.sendToAddress(LTHt.Bdhbl().Bdhb(), LTHt.Bdhbl().bDhb());
                }
                catch(SocketException _ex) { }
                catch(IOException _ex)
                {
                    bDhb("Proxy Packet Error");
                }
                // fall through

            default:
                StringBuffer stringbuffer3 = new StringBuffer();
                stringbuffer3.append("Invalid data type arrived ");
                stringbuffer3.append("raw DataType  " + Utils.BDhb(LTht, 22));
                stringbuffer3.append(" ," + s1);
                bDhb(stringbuffer3.toString());
                break;
            }
        }
        catch(NullPointerException _ex) { }
    }

    private ConfigLoader ltht;
    private MainReceiver Ltht;
    private DatagramPacket lTht;
    private byte LTht[];
    private int ltHt;
    private boolean LtHt;
    private dl4h01z lTHt;
    private d65h10z LTHt;
}

// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;

// Referenced classes of package PMRef:
//            dlkhlhz, d0khlhz, d21hlhz, dlk7lhz, 
//            d8kh3hz, Constants, dl230hz, d87hl2z, 
//            dl01l9z, dlkh89z, d4kh98z, dlk145z, 
//            d8k054z, dlkhlh8, d2khlh9, TimerObserver, 
//            dlk9lh4

class ClientInfo
        implements Constants, TimerObserver
{

    ClientInfo(Conference d8kh3hz1, d0khlhz d0khlhz1, String s, byte abyte0[], MainReceiver mr)
    {
        sendCapKilo = 0;
        sendCapacity = 0L;
        lthtlFz = 0L;
        LthtlFz = 0L;
        lThtlFz = 0L;
        LThtlFz = 0L;
        ltHtlFz = 0L;
        LtHtlFz = 0L;
        lTHtlFz = 0L;
        LTHtlFz = 0L;
        Lthtlfz = d8kh3hz1;
        lthtlfz = d8kh3hz1.bDhblfZv();
        LtHTLFz = d0khlhz1;
        LThtlfz = d0khlhz1.bDhb();
        LTHtlfz = LThtlfz.getAddress();
        lThtlfz = LThtlfz.getHostAddress();
        ltHtlfz = d0khlhz1.bdhb();
        lthTlfz = ltHtlfz.getAddress();
        lTHtlfz = ltHtlfz.getHostAddress();
        LThTLFz = lthtlfz.BdHBlfZ();
        LthTLFz = LThTLFz.bdhbl(d0khlhz1);
        lThTLFz = LthTLFz.getAddress();
        LtHtlfz = d0khlhz1.BDhb();
        LThTlfz = s;
        LTHTlFz = false;
        mainReciver = mr;
        lthtLFz = true;
        lTHTlFz = false;
        lThtLFz = false;
        if(d8kh3hz1.BDHbLfZv())
            LthTlfz = BdhBLFz(lthTlfz);
        LThtLFz = new Hashtable();
        ltHtLFz = new Hashtable();
        LtHtLFz = new Hashtable();
        lTHtLFz = new Hashtable();
        LTHtLFz = new Hashtable();
        LtHTlFz = d8kh3hz1.bdhBlfZv();
        LTHTlfz = System.currentTimeMillis();
        sendCapKilo = d8kh3hz1.BDhblFzv();
        bdhBLFz(abyte0);
        sendCapacity = (long) sendCapKilo * 1000L;
        bdHblfZ();
        BDhblfZ();
    }

    void bdhblfz(ClientInfo dlk3l7z1)
    {
        ltHtLFz.put(dlk3l7z1.bdhBlFz(), dlk3l7z1);
    }

    void Bdhblfz(dl01l9z dl01l9z1)
    {
        lTHtLFz.put(dl01l9z1.BdhbLf(), dl01l9z1);
    }

    void bDhblfz(ClientInfo dlk3l7z1)
    {
        LThtLFz.put(dlk3l7z1.bdhBlFz(), dlk3l7z1);
    }

    void BDhblfz(dl01l9z dl01l9z1)
    {
        LtHtLFz.put(dl01l9z1.BdhbLf(), dl01l9z1);
    }

    synchronized void parseClientCount(int i)
    {
        LthtlFz += i + 28;
    }

    synchronized void BdHblfz(int i)
    {
        lthtlFz += i + 28;
    }

    synchronized void reduceSendCapacity(int i)
    {
        sendCapacity -= i;
    }

    void BDHblfz(String s)
    {
        bdhBlfz(s, LThtlfz, ltHtlfz);
    }

    void bdhBlfz(String s, InetAddress inetaddress, InetAddress inetaddress1)
    {
        byte abyte1[] = s.getBytes();
        int i = abyte1.length;
        byte abyte0[] = new byte[i + 3];
        System.arraycopy(abyte1, 0, abyte0, 3, i);
        abyte1 = Utils.ishort2Bytes(i);
        abyte0[2] = abyte1[1];
        DisconnectPacket dl230hz1 = new DisconnectPacket(abyte0);
        dl230hz1.setSocket(mainReciver.bDHb());
        try
        {
            dl230hz1.setDestport(LtHtlfz);
            dl230hz1.bdhb(inetaddress, inetaddress1, mainReciver.BDHb().getAddress(), LtHtlfz);
        }
        catch(Exception _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("DataSocket Send Error");
            BDHbLFz(stringbuffer.toString());
        }
    }

    boolean BdhBlfz()
    {
        return LThTLFz.BDhbl(LtHTLFz);
    }

    String bDhBlfz()
    {
        return lThtlfz;
    }

    byte[] BDhBlfz()
    {
        return LThtlfz.getAddress();
    }

    d0khlhz bdHBlfz()
    {
        return LtHTLFz;
    }

    Conference BdHBlfz()
    {
        return Lthtlfz;
    }

    String bDHBlfz()
    {
        return lTHtlfz;
    }

    byte[] BDHBlfz()
    {
        return lthTlfz;
    }

    InetAddress bdhbLfz()
    {
        return ltHtlfz;
    }

    int BdhbLfz()
    {
        return LThTLfz;
    }

    int bDhbLfz()
    {
        return lthTLfz;
    }

    int BDhbLfz()
    {
        return sendCapKilo;
    }

    Hashtable bdHbLfz()
    {
        return ltHtLFz;
    }

    Hashtable BdHbLfz()
    {
        return lTHtLFz;
    }

    Hashtable bDHbLfz()
    {
        return LThtLFz;
    }

    Hashtable BDHbLfz()
    {
        return LtHtLFz;
    }

    InetAddress bdhBLfz()
    {
        return LThtlfz;
    }

    int getSendCapKiloMax()
    {
        return sendCapKiloMax;
    }

    int bDhBLfz()
    {
        return LTHtLfz;
    }

    int getSendCapKiloMin()
    {
        return sendCapKiloMin;
    }

    int bdHBLfz()
    {
        return lTHtLfz;
    }

    String BdHBLfz()
    {
        return LThTlfz;
    }

    MainReceiver getMainReceiver()
    {
        return mainReciver;
    }

    boolean BDHBLfz()
    {
        return lthtLFz;
    }

    int bdhblFz()
    {
        return LtHtlfz;
    }

    long BdhblFz()
    {
        return LthtlFz;
    }

    double bDhblFz()
    {
        int i = (int)(LthTlFz * 100D);
        return (double)i / 100D;
    }

    double BDhblFz()
    {
        int i = (int)(LtHTLfz * 100D);
        return (double)i / 100D;
    }

    long bdHblFz(long l)
    {
        return (l * 1000L) / 1000L;
    }

    long BdHblFz()
    {
        return lthtlFz;
    }

    double bDHblFz()
    {
        int i = (int)(lthTlFz * 100D);
        return (double)i / 100D;
    }

    double BDHblFz()
    {
        int i = (int)(ltHTLfz * 100D);
        return (double)i / 100D;
    }

    InetAddress bdhBlFz()
    {
        return LthTLFz;
    }

    byte[] BdhBlFz()
    {
        return lThTLFz;
    }

    String bDhBlFz()
    {
        return ltHTLFz;
    }

    int BDhBlFz()
    {
        return ltHTlFz;
    }

    Hashtable bdHBlFz()
    {
        return LTHtLFz;
    }

    boolean BdHBlFz()
    {
        return LTHTlFz;
    }

    boolean bDHBlFz()
    {
        return (ltHtLfz & 0x10) != 0;
    }

    boolean BDHBlFz()
    {
        return (ltHtLfz & 1) != 0;
    }

    boolean bdhbLFz()
    {
        return (ltHtLfz & 4) != 0;
    }

    boolean BdhbLFz()
    {
        return LThtLfz != 0;
    }

    boolean bDhbLFz()
    {
        return (ltHtLfz & 2) != 0;
    }

    boolean BDhbLFz()
    {
        return (ltHtLfz & 0x20) != 0;
    }

    boolean bdHbLFz()
    {
        return lThtLfz != 0;
    }

    boolean BdHbLFz()
    {
        return lTHTlFz;
    }

    boolean bDHbLFz()
    {
        return LthtLFz;
    }

    void BDHbLFz(String s)
    {
        Lthtlfz.BdHblFZv(s);
    }

    synchronized void bdhBLFz(byte abyte0[])
    {
        lthtLfz = 0L;
        boolean flag = false;
        boolean flag1 = false;
        byte abyte2[] = new byte[16];
        short word1 = Utils.BDhb(abyte0, 26);
        short word0 = Utils.BDhb(abyte0, 24);
        lThtLfz = abyte0[52];
        switch(lThTlFz)
        {
        default:
            break;

        case 0: // '\0'
            if(bdHbLFz())
                lThTlFz = 1;
            else
                lThTlFz = 2;
            break;

        case 1: // '\001'
            if(!bdHbLFz())
            {
                Lthtlfz.bdHblfzv();
                lthtlfz.bdHblfz();
                lThTlFz = 2;
            }
            break;

        case 2: // '\002'
            if(bdHbLFz())
            {
                Lthtlfz.BdHblfzv();
                lthtlfz.BdHblfz();
                lThTlFz = 1;
            }
            break;
        }
        LThtLfz = abyte0[53];
        ltHtLfz = abyte0[54];
        LtHtLfz = abyte0[55];
        int k = 0;
        Hashtable hashtable = Lthtlfz.BdHBLFzv();
        Hashtable hashtable1 = Lthtlfz.BDHBLFzv();
        InetAddress inetaddress = null;
        for(; k < word1; k++)
        {
            int k1 = 56 + 12 * k;
            try
            {
                inetaddress = InetAddress.getByName(Utils.convertToIPform(abyte0, k1));
            }
            catch(UnknownHostException _ex) { }
            byte byte0 = abyte0[k1 + 4];
            byte byte1 = abyte0[k1 + 5];
            byte byte2 = abyte0[k1 + 6];
            byte byte3 = abyte0[k1 + 7];
            if(!Lthtlfz.bDhbLfZv())
            {
                abyte0[k1 + 4] &= 0xef;
                byte0 = abyte0[k1 + 4];
            }
            if(byte2 == 1)
            {
                ClientInfo dlk3l7z1 = (ClientInfo)hashtable.get(inetaddress);
                if(dlk3l7z1 != null)
                {
                    bDhblfz(dlk3l7z1);
                } else
                {
                    dl01l9z dl01l9z1 = (dl01l9z)hashtable1.get(inetaddress);
                    if(dl01l9z1 != null)
                        BDhblfz(dl01l9z1);
                }
            } else
            {
                ClientInfo dlk3l7z2 = (ClientInfo)hashtable.get(inetaddress);
                if(dlk3l7z2 != null)
                {
                    BDHBLFz(dlk3l7z2);
                } else
                {
                    dl01l9z dl01l9z2 = (dl01l9z)hashtable1.get(inetaddress);
                    if(dl01l9z2 != null)
                        bdhblfZ(dl01l9z2);
                }
            }
            if((byte0 & 4) != 0)
            {
                ClientInfo dlk3l7z3 = (ClientInfo)hashtable.get(inetaddress);
                if(dlk3l7z3 != null)
                {
                    bdhblfz(dlk3l7z3);
                } else
                {
                    dl01l9z dl01l9z3 = (dl01l9z)hashtable1.get(inetaddress);
                    if(dl01l9z3 != null)
                        Bdhblfz(dl01l9z3);
                }
            } else
            {
                ClientInfo dlk3l7z4 = (ClientInfo)hashtable.get(inetaddress);
                if(dlk3l7z4 != null)
                {
                    BdHBLFz(dlk3l7z4);
                } else
                {
                    dl01l9z dl01l9z4 = (dl01l9z)hashtable1.get(inetaddress);
                    if(dl01l9z4 != null)
                        bDHBLFz(dl01l9z4);
                }
            }
        }

        short word3;
        for(int j = 56 + 12 * word1; j + 4 < word0; j += word3)
        {
            short word2 = Utils.BDhb(abyte0, j);
            word3 = Utils.BDhb(abyte0, j + 2);
            if((j += 4) + word3 > word0)
                break;
label0:
            switch(word2)
            {
            case 1: // '\001'
            case 2: // '\002'
            default:
                break;

            case 3: // '\003'
                int i = Utils.readShort(abyte0, j);
                if(i <= Lthtlfz.bdHBlFzv())
                {
                    lTHtLfz = i;
                } else
                {
                    lthtLFz = false;
                    StringBuffer stringbuffer = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + Lthtlfz.BdHBlFzv() + " " + Lthtlfz.bdHBlFzv() + "Kbps.");
                    BDHblfz(stringbuffer.toString());
                    if(Lthtlfz.bDHBlFzv() != 0)
                    {
                        dlkh89z dlkh89z1 = new dlkh89z(LtHTLFz, Lthtlfz.bDHBlFzv(), 3);
                        Lthtlfz.bdhblfZv().bdhb(dlkh89z1);
                    }
                    stringbuffer = new StringBuffer();
                    stringbuffer.append("Client " + LThTlfz + " ");
                    stringbuffer.append("exceeded max.min.send:" + Lthtlfz.bdHBlFzv());
                    stringbuffer.append(" client:" + i);
                    stringbuffer.append(" " + bDhBlfz() + ", Confernce " + Lthtlfz.BDHbLFzv());
                    BDHbLFz(stringbuffer.toString());
                }
                i = Utils.readShort(abyte0, j + 2);
                if(i <= Lthtlfz.bDHblFzv())
                {
                    LTHtLfz = i;
                } else
                {
                    lthtLFz = false;
                    StringBuffer stringbuffer1 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + Lthtlfz.BDHblFzv() + " " + Lthtlfz.bDHblFzv() + "Kbps.");
                    BDHblfz(stringbuffer1.toString());
                    if(Lthtlfz.bdhBlFzv() != 0)
                    {
                        dlkh89z dlkh89z2 = new dlkh89z(LtHTLFz, Lthtlfz.bdhBlFzv(), 2);
                        Lthtlfz.bdhblfZv().bdhb(dlkh89z2);
                    }
                    stringbuffer1 = new StringBuffer();
                    stringbuffer1.append("Client " + LThTlfz + " ");
                    stringbuffer1.append("exceeded max.max.send:" + Lthtlfz.bDHblFzv());
                    stringbuffer1.append(" client:" + i);
                    stringbuffer1.append(" " + bDhBlfz() + ", Confernce " + Lthtlfz.BDHbLFzv());
                    BDHbLFz(stringbuffer1.toString());
                }
                lthTLfz = Utils.readShort(abyte0, j + 4);
                i = Utils.readShort(abyte0, j + 6);
                if(i <= Lthtlfz.BdhBlFzv())
                {
                    sendCapKiloMin = i;
                } else
                {
                    lthtLFz = false;
                    StringBuffer stringbuffer2 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + Lthtlfz.bDhBlFzv() + " " + Lthtlfz.BdhBlFzv() + "Kbps.");
                    BDHblfz(stringbuffer2.toString());
                    if(Lthtlfz.BDhBlFzv() != 0)
                    {
                        dlkh89z dlkh89z3 = new dlkh89z(LtHTLFz, Lthtlfz.BDhBlFzv(), 5);
                        Lthtlfz.bdhblfZv().bdhb(dlkh89z3);
                    }
                    stringbuffer2 = new StringBuffer();
                    stringbuffer2.append("Client " + LThTlfz + " ");
                    stringbuffer2.append("exceeded max.min.recv:" + Lthtlfz.BdhBlFzv());
                    stringbuffer2.append(" client:" + i);
                    stringbuffer2.append(" " + bDhBlfz() + ", Confernce " + Lthtlfz.BDHbLFzv());
                    BDHbLFz(stringbuffer2.toString());
                }
                i = Utils.readShort(abyte0, j + 8);
                if(i <= Lthtlfz.BDhblFzv())
                {
                    if(i != sendCapKiloMax)
                        sendCapKilo = i;
                    sendCapKiloMax = i;
                } else
                {
                    lthtLFz = false;
                    StringBuffer stringbuffer3 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + Lthtlfz.bdHblFzv() + " " + Lthtlfz.BDhblFzv() + "Kbps.");
                    BDHblfz(stringbuffer3.toString());
                    if(Lthtlfz.BdHblFzv() != 0)
                    {
                        dlkh89z dlkh89z4 = new dlkh89z(LtHTLFz, Lthtlfz.BdHblFzv(), 4);
                        Lthtlfz.bdhblfZv().bdhb(dlkh89z4);
                    }
                    stringbuffer3 = new StringBuffer();
                    stringbuffer3.append("Client " + LThTlfz + " ");
                    stringbuffer3.append("exceeded max.max.recv:" + Lthtlfz.BDhblFzv());
                    stringbuffer3.append(" client:" + i);
                    stringbuffer3.append(" " + bDhBlfz() + ", Confernce " + Lthtlfz.BDHbLFzv());
                    BDHbLFz(stringbuffer3.toString());
                }
                LThTLfz = Utils.readShort(abyte0, j + 10);
                byte abyte1[] = Utils.ishort2Bytes(sendCapKilo);
                System.arraycopy(abyte1, 0, abyte0, j + 10, 2);
                break;

            case 4: // '\004'
                if(ltHTLFz == null)
                {
                    lThTlfz = new byte[32];
                    byte abyte3[] = new byte[word3 - 8];
                    System.arraycopy(abyte0, j + 8, lThTlfz, 0, 32);
                    System.arraycopy(abyte0, j + 8, abyte3, 0, word3 - 8);
                    for(int l = 0; l < word3 - 8; l++)
                        if(abyte3[l] == 10 || abyte3[l] == 0)
                            abyte3[l] = 32;

                    ltHTLFz = new String(abyte3);
                    if(ltHTLFz.startsWith("VChat "))
                        lThtLFz = true;
                }
                if(abyte0[j] == 4)
                {
                    LthtLFz = true;
                    if(!Lthtlfz.BdHBLfzv())
                        break;
                    for(int i1 = j + 8; i1 < j + word3; i1++)
                    {
                        if(Utils.compare(abyte0, i1, Constants.WINVERSION1BYTES, 0, Constants.WINVERSION1BYTES.length))
                        {
                            System.arraycopy(Constants.MODOKI1BYTES, 0, abyte0, i1, Constants.MODOKI1BYTES.length);
                            break label0;
                        }
                        if(Utils.compare(abyte0, i1, Constants.WINVERSION2BYTES, 0, Constants.WINVERSION2BYTES.length))
                        {
                            System.arraycopy(Constants.MODOKI2BYTES, 0, abyte0, i1, Constants.MODOKI2BYTES.length);
                            break label0;
                        }
                        if(Utils.compare(abyte0, i1, Constants.WINVERSION3BYTES, 0, Constants.WINVERSION3BYTES.length))
                        {
                            System.arraycopy(Constants.MODOKI3BYTES, 0, abyte0, i1, Constants.MODOKI3BYTES.length);
                            break label0;
                        }
                    }

                    break;
                }
                LthtLFz = false;
                if(Lthtlfz.BdHBLfzv())
                    abyte0[j] = 4;
                break;

            case 200: 
                ltHTlFz = Utils.readInt(abyte0, j + 12);
                break;

            case 4297: 
                if(!lTHTlFz && lThTlfz != null)
                {
                    System.arraycopy(abyte0, j, abyte2, 0, word3);
                    for(int j1 = 0; j1 < 2; j1++)
                    {
                        for(int l1 = 0; l1 < 16; l1++)
                            abyte2[l1] ^= lThTlfz[j1 * 16 + l1];

                    }

                }
                flag = true;
                break;

            case 201: 
                flag1 = true;
                if(!Lthtlfz.BdHBlfZv())
                    break;
                abyte2 = new byte[word3];
                System.arraycopy(abyte0, j, abyte2, 0, word3);
                flag = true;
                if(Lthtlfz.bdhblFZv())
                    break;
                if(Lthtlfz.BdHBLfZv())
                {
                    Lthtlfz.BDhblfzV(abyte2, LthTlfz);
                    StringBuffer stringbuffer4 = new StringBuffer();
                    stringbuffer4.append("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\rThe \"WhitePine Encoded Password\" string of which you sent as password is\r\r\"" + Lthtlfz.BDHBlfZv() + "\"");
                    BDHblfz(stringbuffer4.toString());
                    stringbuffer4 = new StringBuffer();
                    stringbuffer4.append("Client " + LThTlfz + " ");
                    stringbuffer4.append("gets a passdiff string from " + bDhBlfz() + ", Conference " + Lthtlfz.BDHbLFzv());
                    BDHbLFz(stringbuffer4.toString());
                    Lthtlfz.bDHblfzv();
                    return;
                }
                if(lthtlfz.BdhblfZ().ktdblhBdhb(LThtlfz))
                {
                    Lthtlfz.BDhblfzV(abyte2, LthTlfz);
                    StringBuffer stringbuffer5 = new StringBuffer();
                    stringbuffer5.append("The whitepine.encoded.password.string is set temporarily from " + bDhBlfz() + ", Conference " + Lthtlfz.BDHbLFzv() + ", \"" + Lthtlfz.BDHBlfZv() + "\". " + "Set this to " + "\"conference.whitepine.encoded.password." + "string-" + Lthtlfz.BDHbLFzv() + "\" property for parmanent use");
                    BDHbLFz(stringbuffer5.toString());
                    lTHTlFz = true;
                } else
                {
                    StringBuffer stringbuffer6 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + Lthtlfz.BDhBLFzv());
                    BDHblfz(stringbuffer6.toString());
                    stringbuffer6 = new StringBuffer();
                    stringbuffer6.append("Client " + LThTlfz + " ");
                    stringbuffer6.append("doesn't allow to set a password from " + bDhBlfz() + ", Conference " + Lthtlfz.BDHbLFzv());
                    BDHbLFz(stringbuffer6.toString());
                    return;
                }
                break;
            }
        }

        if(Lthtlfz.BDHbLfZv() && !lTHTlFz)
        {
            if(!flag)
            {
                StringBuffer stringbuffer7 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + Lthtlfz.bDhBLFzv());
                BDHblfz(stringbuffer7.toString());
                stringbuffer7 = new StringBuffer();
                stringbuffer7.append("Client " + LThTlfz + " ");
                stringbuffer7.append("doesn't send a password from " + bDhBlfz() + ", Conference " + Lthtlfz.BDHbLFzv());
                BDHbLFz(stringbuffer7.toString());
                return;
            }
            if(flag1 && !Lthtlfz.BDhblFZv(abyte2, LthTlfz) || !flag1 && !Lthtlfz.BdhblFZv(abyte2))
            {
                StringBuffer stringbuffer8 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + Lthtlfz.bDHBLfzv());
                BDHblfz(stringbuffer8.toString());
                stringbuffer8 = new StringBuffer();
                stringbuffer8.append("Client " + LThTlfz + " ");
                stringbuffer8.append("sends an invalid password from " + bDhBlfz() + ", Conference " + Lthtlfz.BDHbLFzv());
                BDHbLFz(stringbuffer8.toString());
                return;
            }
        }
        lTHTlFz = true;
    }

    byte[] BdhBLFz(byte abyte0[])
    {
        byte abyte1[] = new byte[32];
        byte abyte2[] = new byte[8];
        byte byte0 = -120;
        for(int i = 0; i < abyte0.length;)
        {
            byte byte1 = abyte0[i];
            int i1 = 1;
            for(int k = 0; k < 8;)
            {
                if((byte1 & i1) == i1)
                    abyte2[k] += byte0;
                k++;
                i1 <<= 1;
            }

            i++;
            byte0 >>>= 1;
            byte0 &= 0x7f;
        }

        for(int j = 0; j < 8; j++)
        {
            for(int l = 0; l < 4; l++)
                abyte1[j * 4 + l] = abyte2[j];

        }

        return abyte1;
    }

    synchronized void applyRateControlResponse(byte abyte0[])
    {
        long totalSent = Utils.BdHb(abyte0, 32);
        long totalRcvd = Utils.BdHb(abyte0, 36);
        long pureSent = totalSent - LtHtlFz;
        long pureRcv = totalRcvd - lTHtlFz;
        long timeSent = Utils.BdHb(abyte0, 40);
        long l3 = timeSent - LTHtlFz;
        if(pureSent > 0L && pureRcv >= 0L && pureSent >= pureRcv)
        {
            int lastSendCapKilo = sendCapKilo;
            int rate;
            try
            {
                rate = (int)((pureRcv * 100L) / pureSent);
                if(rate < 75)
                    rate = 75;
            }
            catch(ArithmeticException _ex)
            {
                rate = -1;
            }
            if(rate > 0) {
                if(rate == 100)
                {
                    if(sendCapKilo < sendCapKiloMax)
                        if(lastSendCapKilo < sendCapKiloMax / 2)
                        {
                            if(lastSendCapKilo <= 2)
                                lastSendCapKilo += 2;
                            else
                                lastSendCapKilo += (lastSendCapKilo * 50) / 100;
                        } else
                        if(lastSendCapKilo <= 4)
                            lastSendCapKilo += 4;
                        else
                            lastSendCapKilo += (lastSendCapKilo * 25) / 100;
                } else if(rate < 90) {
                    lastSendCapKilo = (lastSendCapKilo * rate) / 100;
                }
            }
            sendCapKilo = lastSendCapKilo;
        }
        if(sendCapKilo > sendCapKiloMax)
            sendCapKilo = sendCapKiloMax;
        if(sendCapKilo < sendCapKiloMin)
            sendCapKilo = sendCapKiloMin;
        sendCapacity = ((long) sendCapKilo * 1000L * 6L) / 8L;
        if(pureSent > 0L)
        {
            LtHTLfz = 100D - ((double)pureRcv * 100D) / (double)pureSent;
            if(LtHTLfz < 0.0D)
                LtHTLfz = 0.0D;
            lthTlFz = ((double)pureSent * 8D) / ((double)l3 / 1000D) / 1000D;
            LTHtlFz = timeSent;
            LtHtlFz = totalSent;
            lTHtlFz = totalRcvd;
        }
    }

    void BDhBLFz(String s)
    {
        bdHBLFz(s, LThtlfz, ltHtlfz);
    }

    void bdHBLFz(String s, InetAddress inetaddress, InetAddress inetaddress1)
    {
        byte abyte1[] = s.getBytes();
        int i = abyte1.length;
        byte abyte0[] = new byte[i + 4];
        System.arraycopy(abyte1, 0, abyte0, 4, i);
        abyte1 = Utils.ishort2Bytes(i);
        System.arraycopy(abyte1, 0, abyte0, 2, 2);
        WelcomePacket welcomePacket = new WelcomePacket(abyte0);
        welcomePacket.setSocket(mainReciver.bDHb());
        try
        {
            welcomePacket.setDestport(LtHtlfz);
            welcomePacket.bdhb(inetaddress, inetaddress1, mainReciver.BDHb().getAddress(), LtHtlfz);
        }
        catch(NullPointerException _ex) { }
        catch(Exception _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("DataSocket Send Error");
            BDHbLFz(stringbuffer.toString());
        }
    }

    void BdHBLFz(ClientInfo dlk3l7z1)
    {
        ltHtLFz.remove(dlk3l7z1.bdhBlFz());
    }

    void bDHBLFz(dl01l9z dl01l9z1)
    {
        lTHtLFz.remove(dl01l9z1.BdhbLf());
    }

    void BDHBLFz(ClientInfo dlk3l7z1)
    {
        LThtLFz.remove(dlk3l7z1.bdhBlFz());
    }

    void bdhblfZ(dl01l9z dl01l9z1)
    {
        LtHtLFz.remove(dl01l9z1.BdhbLf());
    }

    void BdhblfZ(InetAddress inetaddress)
    {
        synchronized(LTHtLFz)
        {
            LTHtLFz.remove(inetaddress);
        }
    }

    void bDhblfZ(byte abyte0[], int i)
    {
        if(Utils.compare(abyte0, 4, lThTLFz, 0, 4))
            System.arraycopy(LTHtlfz, 0, abyte0, 4, 4);
        int j = Utils.readShort(abyte0, 24);
        CuseemePacket dlk7lhz1 = new CuseemePacket(abyte0, LtHTlFz);
        dlk7lhz1.setSocket(mainReciver.bDHb());
        dlk7lhz1.setPacketLength(j);
        switch(i)
        {
        case 1: // '\001'
            reduceSendCapacity(j);
            break;

        case 2: // '\002'
            break;

        case 3: // '\003'
            if(!BdhbLFz())
                return;
            if((long)j < sendCapacity)
                reduceSendCapacity(j);
            else
                return;
            break;

        case 23: // '\027'
            if(!BdhbLFz())
                return;
            String s = Utils.convertToIPform(abyte0, 12);
            InetAddress inetaddress;
            try
            {
                inetaddress = InetAddress.getByName(s);
            }
            catch(UnknownHostException _ex)
            {
                return;
            }
            int k = Utils.readShort(abyte0, 30);
            synchronized(LTHtLFz)
            {
                if((long)j <= sendCapacity)
                {
                    Integer integer = (Integer)LTHtLFz.get(inetaddress);
                    if(integer == null)
                    {
                        LTHtLFz.put(inetaddress, new Integer(-1));
                    } else
                    {
                        int l = integer.intValue();
                        ClientInfo dlk3l7z1 = (ClientInfo)LThtLFz.get(inetaddress);
                        if(dlk3l7z1 != null)
                        {
                            switch(dlk3l7z1.BDhBlFz())
                            {
                            case 1296715847: 
                                if(l != -1)
                                {
                                    if(l == k)
                                        return;
                                    LTHtLFz.put(inetaddress, new Integer(-1));
                                }
                                break;

                            case 1448620363: 
                                if(l != -1)
                                {
                                    if(l == k)
                                        return;
                                    LTHtLFz.put(inetaddress, new Integer(-1));
                                }
                                break;

                            default:
                                if(l != -1)
                                    LTHtLFz.put(inetaddress, new Integer(-1));
                                break;
                            }
                        } else
                        {
                            dl01l9z dl01l9z1 = (dl01l9z)LtHtLFz.get(inetaddress);
                            if(dl01l9z1 != null)
                                switch(dl01l9z1.bDHBLf())
                                {
                                case 1296715847: 
                                    if(l != -1)
                                    {
                                        if(l == k)
                                            return;
                                        LTHtLFz.put(inetaddress, new Integer(-1));
                                    }
                                    break;

                                case 1448620363: 
                                    if(l != -1)
                                    {
                                        if(l == k)
                                            return;
                                        LTHtLFz.put(inetaddress, new Integer(-1));
                                    }
                                    break;

                                default:
                                    LTHtLFz.put(inetaddress, new Integer(-1));
                                    break;
                                }
                        }
                    }
                } else
                {
                    ClientInfo dlk3l7z2 = (ClientInfo)LThtLFz.get(inetaddress);
                    if(dlk3l7z2 != null)
                    {
                        switch(dlk3l7z2.BDhBlFz())
                        {
                        case 1296715847: 
                            LTHtLFz.put(inetaddress, new Integer(k));
                            break;

                        case 1448620363: 
                            LTHtLFz.put(inetaddress, new Integer(k));
                            break;

                        default:
                            LTHtLFz.put(inetaddress, new Integer(k));
                            break;
                        }
                        return;
                    }
                    dl01l9z dl01l9z2 = (dl01l9z)LtHtLFz.get(inetaddress);
                    if(dl01l9z2 != null)
                        switch(dl01l9z2.bDHBLf())
                        {
                        case 1296715847: 
                            LTHtLFz.put(inetaddress, new Integer(k));
                            break;

                        case 1448620363: 
                            LTHtLFz.put(inetaddress, new Integer(k));
                            break;

                        default:
                            LTHtLFz.put(inetaddress, new Integer(k));
                            break;
                        }
                    return;
                }
                reduceSendCapacity(j);
            }
            break;

        case 4: // '\004'
            if(!BDHBlFz())
                return;
            reduceSendCapacity(j);
            break;

        case 9: // '\t'
            reduceSendCapacity(j);
            break;

        case 5: // '\005'
        case 6: // '\006'
            reduceSendCapacity(j);
            break;

        case 7: // '\007'
        case 8: // '\b'
            reduceSendCapacity(j);
            break;

        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 16: // '\020'
        case 17: // '\021'
        case 18: // '\022'
        case 19: // '\023'
        case 20: // '\024'
        case 21: // '\025'
        case 22: // '\026'
        default:
            reduceSendCapacity(j);
            break;
        }
        try
        {
            BdHblfz(j);
            dlk7lhz1.sendToAddress(LThtlfz, LtHtlfz);
        }
        catch(Exception _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("DataSocket Send Error");
            BDHbLFz(stringbuffer.toString());
        }
    }

    void BDhblfZ()
    {
        byte abyte0[] = new byte[26];
        CuseemePacket dlk7lhz1 = new CuseemePacket(abyte0, LtHTlFz);
        dlk7lhz1.setSocket(mainReciver.bDHb());
        dlk7lhz1.setDestFamily(1);
        dlk7lhz1.setDestAddr(lTHtlfz);
        dlk7lhz1.setSourceFamily(2);
        dlk7lhz1.setSourcePort(7648);
        dlk7lhz1.setSourceAddr2(mainReciver.BDHb().getAddress());
        dlk7lhz1.setMessageType(2);
        dlk7lhz1.setDataType(100);
        dlk7lhz1.setPacketLength(26);
        try
        {
            BdHblfz(26);
            dlk7lhz1.setDestport(LtHtlfz);
            dlk7lhz1.sendToAddress(LThtlfz, LtHtlfz);
        }
        catch(Exception _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("DataSocket Send Error");
            BDHbLFz(stringbuffer.toString());
        }
    }

    synchronized void bdHblfZ()
    {
        byte abyte0[] = new byte[10];
        byte abyte1[] = Utils.ishort2Bytes(sendCapKilo);
        System.arraycopy(abyte1, 0, abyte0, 0, 2);
        abyte1 = Utils.long2Bytes(lthtlFz + 28L + 26L + 10L);
        System.arraycopy(abyte1, 0, abyte0, 2, 4);
        abyte1 = Utils.long2Bytes(System.currentTimeMillis());
        System.arraycopy(abyte1, 0, abyte0, 6, 4);
        RateControlPacket dlk145z1 = new RateControlPacket(abyte0, LtHTlFz);
        dlk145z1.setSocket(mainReciver.bDHb());
        try
        {
            BdHblfz(26 + abyte0.length);
            dlk145z1.setDestport(LtHtlfz);
            dlk145z1.bdhb(LThtlfz, ltHtlfz, mainReciver.BDHb().getAddress(), LtHtlfz);
        }
        catch(Exception _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("DataSocket Send Error");
            BDHbLFz(stringbuffer.toString());
        }
    }

    synchronized void BdHblfZ(byte abyte0[])
    {
        long l = System.currentTimeMillis();
        byte abyte1[] = new byte[22];
        byte abyte2[] = Utils.ishort2Bytes(sendCapKiloMax);
        System.arraycopy(abyte2, 0, abyte1, 0, 2);
        System.arraycopy(abyte0, 28, abyte1, 6, 4);
        abyte2 = Utils.long2Bytes(LthtlFz);
        System.arraycopy(abyte2, 0, abyte1, 10, 4);
        System.arraycopy(abyte0, 32, abyte1, 14, 4);
        abyte2 = Utils.long2Bytes(l);
        System.arraycopy(abyte2, 0, abyte1, 18, 4);
        RateReplyPacket d8k054z1 = new RateReplyPacket(abyte1, LtHTlFz);
        d8k054z1.setSocket(mainReciver.bDHb());
        try
        {
            BdHblfz(26 + abyte1.length);
            d8k054z1.setDestport(LtHtlfz);
            d8k054z1.bdhb(LThtlfz, ltHtlfz, mainReciver.BDHb().getAddress(), LtHtlfz);
        }
        catch(Exception _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("DataSocket Send Error");
            BDHbLFz(stringbuffer.toString());
        }
        long l3 = Utils.BdHb(abyte0, 28);
        long l1 = l3 - lThtlFz;
        long l2 = LthtlFz - LThtlFz;
        if(l1 > 0L)
        {
            ltHTLfz = 100D - ((double)l2 * 100D) / (double)l1;
            if(ltHTLfz < 0.0D)
                ltHTLfz = 0.0D;
            LthTlFz = ((double)l2 * 8D) / (((double)l - (double)ltHtlFz) / 1000D) / 1000D;
            ltHtlFz = l;
            lThtlFz = l3;
            LThtlFz = LthtlFz;
        }
    }

    void bDHblfZ(String s)
    {
        lTHtlfz = s;
    }

    void BDHblfZ(boolean flag)
    {
        LTHTlFz = flag;
    }

    void bdhBlfZ(InetAddress inetaddress)
    {
        LthTLFz = inetaddress;
        lThTLFz = inetaddress.getAddress();
    }

    public void updateTimer()
    {
        lTHTlfz++;
        if(Lthtlfz.BdhBlfZv() != 0 && (System.currentTimeMillis() - LTHTlfz) / 1000L >= (long)Lthtlfz.BdhBlfZv())
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + Lthtlfz.bDhBlfZv());
            stringbuffer.append("\rYou are put in \"Penalty Box\".  Try again in " + Lthtlfz.BDhBlfZv() + " seconds.");
            BDHblfz(stringbuffer.toString());
            dlkh89z dlkh89z1 = new dlkh89z(LtHTLFz, Lthtlfz.BDhBlfZv(), 6);
            Lthtlfz.bdhblfZv().bdhb(dlkh89z1);
            stringbuffer = new StringBuffer();
            stringbuffer.append("Client " + LThTlfz + " ");
            stringbuffer.append("TIMELIMIT reached " + lThtlfz + ":" + lTHtlfz);
            stringbuffer.append("(" + LthTLFz.getHostAddress() + ")");
            stringbuffer.append(", Conference " + Lthtlfz.BDHbLFzv());
            BDHbLFz(stringbuffer.toString());
        }
        lthtLfz++;
        if(bdHblFz(lthtLfz) > 60L && Lthtlfz.bDHblFZv(this))
        {
            StringBuffer stringbuffer1 = new StringBuffer();
            stringbuffer1.append("Client " + LThTlfz + " ");
            stringbuffer1.append("OC PACKET TIMEOUT deleted " + lThtlfz + ":" + lTHtlfz);
            stringbuffer1.append("(" + LthTLFz.getHostAddress() + ")");
            stringbuffer1.append(", Conference " + Lthtlfz.BDHbLFzv());
            BDHbLFz(stringbuffer1.toString());
        }
        LthtLfz++;
        if(bdHblFz(LthtLfz) > 30L)
        {
            BDhblfZ();
            LthtLfz = 0L;
        }
        LThTlFz++;
        if(LThTlFz == 1)
            bdHblfZ();
        if(LThTlFz == 6)
            LThTlFz = 0;
    }

    private ConfigLoader lthtlfz;
    private Conference Lthtlfz;
    private String lThtlfz;
    private InetAddress LThtlfz;
    private InetAddress ltHtlfz;
    private int LtHtlfz;
    private String lTHtlfz;
    private byte LTHtlfz[];
    private byte lthTlfz[];
    private byte LthTlfz[];
    private byte lThTlfz[];
    private String LThTlfz;
    private long ltHTlfz;
    private long LtHTlfz;
    private long lTHTlfz;
    private long LTHTlfz;
    private long lthtLfz;
    private long LthtLfz;
    private byte lThtLfz;
    private byte LThtLfz;
    private byte ltHtLfz;
    private byte LtHtLfz;
    private int lTHtLfz;
    private int LTHtLfz;
    private int lthTLfz;
    private int sendCapKiloMin;
    private int sendCapKiloMax;
    private int LThTLfz;
    private double ltHTLfz;
    private double LtHTLfz;
    private int sendCapKilo;
    private long sendCapacity;
    private long lthtlFz;
    private long LthtlFz;
    private long lThtlFz;
    private long LThtlFz;
    private long ltHtlFz;
    private long LtHtlFz;
    private long lTHtlFz;
    private long LTHtlFz;
    private double lthTlFz;
    private double LthTlFz;
    private int lThTlFz;
    private int LThTlFz;
    private int ltHTlFz;
    private boolean LtHTlFz;
    private boolean lTHTlFz;
    private boolean LTHTlFz;
    private boolean lthtLFz;
    private boolean LthtLFz;
    private boolean lThtLFz;
    private Hashtable LThtLFz;
    private Hashtable ltHtLFz;
    private Hashtable LtHtLFz;
    private Hashtable lTHtLFz;
    private Hashtable LTHtLFz;
    private MainReceiver mainReciver;
    private InetAddress LthTLFz;
    private byte lThTLFz[];
    private dlk9lh4 LThTLFz;
    private String ltHTLFz;
    private d0khlhz LtHTLFz;
    private static final int lTHTLFz = 0;
    private static final int LTHTLFz = 1;
    private static final int lthtlfZ = 2;
    private static final int LthtlfZ = 28;
    private static final long lThtlfZ = 60L;
    private static final int LThtlfZ = 1;
    private static final int ltHtlfZ = 6;
    private static final long LtHtlfZ = 1000L;
    private static final int lTHtlfZ = 80;
    private static final int LTHtlfZ = 10;
    private static final int lthTlfZ = 10;
    private static final int LthTlfZ = 2;
    private static final int lThTlfZ = 6;
    private static final int LThTlfZ = 22;
    private static final int ltHTlfZ = 2;
    private static final int LtHTlfZ = 6;
    private static final int lTHTlfZ = 10;
    private static final int LTHTlfZ = 14;
    private static final int lthtLfZ = 18;
    private static final int LthtLfZ = 100;
    private static final int lThtLfZ = 90;
    private static final int LThtLfZ = 75;
    private static final int ltHtLfZ = 50;
    private static final int LtHtLfZ = 25;
    private static final short lTHtLfZ = 56;
    private static final short LTHtLfZ = 1;
    private static final short lthTLfZ = 2;
    private static final short LthTLfZ = 3;
    private static final short lThTLfZ = 4;
    private static final short LThTLfZ = 28;
    private static final short ltHTLfZ = 29;
    private static final int LtHTLfZ = 12;
    private static final long lTHTLfZ = 0xb030000L;
    private static final long LTHTLfZ = 30L;
    private static final int lthtlFZ = 26;
    private static final int LthtlFZ = 100;
    private static final int lThtlFZ = 5;
    private static final String LThtlFZ = "VChat ";
}

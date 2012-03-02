// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.net.*;
import java.util.Hashtable;

// Referenced classes of package PMRef:
//            d21hlhz, d8kh3hz, Constants, d87hl2z, 
//            dlk3l7z, d0k2l6z, dlkh89z, d4kh98z, 
//            d2khlh9, TimerObserver, d0khlhz, d09h5hz

class dl01l9z
    implements Constants, TimerObserver
{

    dl01l9z(Conference d8kh3hz1, String s, String s1, String s2, String s3, String s4, byte abyte0[],
            MainReceiver d87hl2z1, d0khlhz d0khlhz)
    {
        lthtlf = d8kh3hz1;
        Lthtlf = s;
        try
        {
            LtHtlf = InetAddress.getByName(s);
        }
        catch(UnknownHostException _ex)
        {
            LtHtlf = null;
        }
        try
        {
            LthtlF = InetAddress.getByName(s).getAddress();
        }
        catch(UnknownHostException _ex) { }
        lThtlf = s1;
        try
        {
            lTHtlf = InetAddress.getByName(s1);
        }
        catch(UnknownHostException _ex)
        {
            lTHtlf = null;
        }
        LtHtlF = s2;
        LThtlf = s3;
        try
        {
            LTHtlf = InetAddress.getByName(s3);
        }
        catch(UnknownHostException _ex)
        {
            LTHtlf = null;
        }
        ltHtlF = d0khlhz;
        ltHtlf = s4;
        lthtlF = d87hl2z1;
        lThTLf = false;
        LThTLf = new Hashtable();
        ltHTLf = new Hashtable();
        LtHTLf = new Hashtable();
        lTHTLf = new Hashtable();
        LthTLf = d8kh3hz1.bdhBlfZv();
        LThTlf = System.currentTimeMillis();
        BdhBlF(abyte0);
    }

    void bdhblf(ClientInfo dlk3l7z1)
    {
        ltHTLf.put(dlk3l7z1.bdhBlFz(), dlk3l7z1);
    }

    void Bdhblf(dl01l9z dl01l9z1)
    {
        lTHTLf.put(dl01l9z1.BdhbLf(), dl01l9z1);
    }

    void bDhblf(ClientInfo dlk3l7z1)
    {
        LThTLf.put(dlk3l7z1.bdhBlFz(), dlk3l7z1);
    }

    void BDhblf(dl01l9z dl01l9z1)
    {
        LtHTLf.put(dl01l9z1.BdhbLf(), dl01l9z1);
    }

    void bdHblf(String s)
    {
        BdHblf(s, (d0k2l6z)bdhBlf().BDhbLfzv().get(lTHtlf), bDHblf(), bDhBLf());
    }

    void BdHblf(String s, d0k2l6z d0k2l6z1, String s1, String s2)
    {
        int i = s.getBytes().length;
        byte abyte0[] = new byte[i + 6];
        System.arraycopy(s.getBytes(), 0, abyte0, 6, i);
        System.arraycopy(Utils.ishort2Bytes(i), 0, abyte0, 4, 2);
        try
        {
            System.arraycopy(InetAddress.getByName(s1).getAddress(), 0, abyte0, 0, 4);
        }
        catch(UnknownHostException _ex) { }
        d0k2l6z1.BdhbL(abyte0, 1, d0k2l6z1.BdHbl().getHostAddress(), s2, 21762);
    }

    String bDHblf()
    {
        return Lthtlf;
    }

    d0khlhz BDHblf()
    {
        return ltHtlF;
    }

    Conference bdhBlf()
    {
        return lthtlf;
    }

    int BdhBlf()
    {
        return lTHtLf;
    }

    int bDhBlf()
    {
        return LThtLf;
    }

    PacketParserThread BDhBlf()
    {
        return lthtlF.BdHb();
    }

    Hashtable bdHBlf()
    {
        return ltHTLf;
    }

    Hashtable BdHBlf()
    {
        return lTHTLf;
    }

    Hashtable bDHBlf()
    {
        return LThTLf;
    }

    Hashtable BDHBlf()
    {
        return LtHTLf;
    }

    String bdhbLf()
    {
        return lThtlf;
    }

    InetAddress BdhbLf()
    {
        return LtHtlf;
    }

    InetAddress bDhbLf()
    {
        return lTHtlf;
    }

    int BDhbLf()
    {
        return LtHtLf;
    }

    int bdHbLf()
    {
        return lThtLf;
    }

    int BdHbLf()
    {
        return ltHtLf;
    }

    int bDHbLf()
    {
        return LthtLf;
    }

    String BDHbLf()
    {
        return ltHtlf;
    }

    MainReceiver bdhBLf()
    {
        return lthtlF;
    }

    synchronized long BdhBLf()
    {
        LthTlf++;
        return LthTlf;
    }

    String bDhBLf()
    {
        return LThtlf;
    }

    long BDhBLf(long l)
    {
        return (l * 1000L) / 1000L;
    }

    synchronized long bdHBLf()
    {
        lthTlf++;
        return lthTlf;
    }

    String BdHBLf()
    {
        return LThtlF;
    }

    int bDHBLf()
    {
        return lthTLf;
    }

    boolean BDHBLf()
    {
        return (LTHTlf & 0x10) != 0;
    }

    boolean bdhblF()
    {
        return (LTHTlf & 1) != 0;
    }

    boolean BdhblF()
    {
        return (LTHTlf & 4) != 0;
    }

    boolean bDhblF()
    {
        return lTHTlf != 0;
    }

    boolean BDhblF()
    {
        return (LTHTlf & 2) != 0;
    }

    boolean bdHblF()
    {
        return (LTHTlf & 0x20) != 0;
    }

    boolean BdHblF()
    {
        return LtHTlf != 0;
    }

    boolean bDHblF()
    {
        return lThTLf;
    }

    boolean BDHblF()
    {
        return (LTHTlf & 8) != 0;
    }

    void bdhBlF(String s)
    {
        lthtlf.BdHblFZv(s);
    }

    synchronized void BdhBlF(byte abyte0[])
    {
        ltHTlf = 0L;
        byte abyte1[] = new byte[16];
        boolean flag = false;
        boolean flag2 = false;
        short word1 = Utils.BDhb(abyte0, 26);
        short word0 = Utils.BDhb(abyte0, 24);
        LtHTlf = abyte0[52];
        d0k2l6z d0k2l6z1 = (d0k2l6z)lthtlf.BDhbLfzv().get(lTHtlf);
        switch(LTHtLf)
        {
        default:
            break;

        case 0: // '\0'
            if(BdHblF())
                LTHtLf = 1;
            else
                LTHtLf = 2;
            break;

        case 1: // '\001'
            if(!BdHblF())
            {
                lthtlf.bdHblfzv();
                lthtlf.bDhblfZv().bdHblfz();
                LTHtLf = 2;
            }
            break;

        case 2: // '\002'
            if(BdHblF())
            {
                lthtlf.BdHblfzv();
                lthtlf.bDhblfZv().BdHblfz();
                LTHtLf = 1;
            }
            break;
        }
        lTHTlf = abyte0[53];
        LTHTlf = abyte0[54];
        lthtLf = abyte0[55];
        int k = 0;
        Hashtable hashtable = lthtlf.BdHBLFzv();
        Hashtable hashtable1 = lthtlf.BDHBLFzv();
        InetAddress inetaddress = null;
        for(; k < word1; k++)
        {
            int i1 = 56 + 12 * k;
            try
            {
                inetaddress = InetAddress.getByName(Utils.convertToIPform(abyte0, i1));
            }
            catch(UnknownHostException _ex) { }
            byte byte0 = abyte0[i1 + 4];
            byte byte1 = abyte0[i1 + 5];
            byte byte2 = abyte0[i1 + 6];
            byte byte3 = abyte0[i1 + 7];
            if(byte2 == 1)
            {
                ClientInfo dlk3l7z1 = (ClientInfo)hashtable.get(inetaddress);
                if(dlk3l7z1 != null)
                {
                    if(!LThTLf.containsKey(inetaddress))
                        bDhblf(dlk3l7z1);
                } else
                {
                    dl01l9z dl01l9z1 = (dl01l9z)hashtable1.get(inetaddress);
                    if(dl01l9z1 != null && !LtHTLf.containsKey(inetaddress))
                        BDhblf(dl01l9z1);
                }
            } else
            {
                ClientInfo dlk3l7z2 = (ClientInfo)hashtable.get(inetaddress);
                if(dlk3l7z2 != null)
                {
                    if(LThTLf.containsKey(inetaddress))
                        bDHBlF(dlk3l7z2);
                } else
                {
                    dl01l9z dl01l9z2 = (dl01l9z)hashtable1.get(inetaddress);
                    if(dl01l9z2 != null && LtHTLf.containsKey(inetaddress))
                        BDHBlF(dl01l9z2);
                }
            }
            if((byte0 & 4) != 0)
            {
                ClientInfo dlk3l7z3 = (ClientInfo)hashtable.get(inetaddress);
                if(dlk3l7z3 != null)
                {
                    if(!ltHTLf.containsKey(inetaddress))
                        bdhblf(dlk3l7z3);
                } else
                {
                    dl01l9z dl01l9z3 = (dl01l9z)hashtable1.get(inetaddress);
                    if(dl01l9z3 != null && !lTHTLf.containsKey(inetaddress))
                        Bdhblf(dl01l9z3);
                }
            } else
            {
                ClientInfo dlk3l7z4 = (ClientInfo)hashtable.get(inetaddress);
                if(dlk3l7z4 != null)
                {
                    if(ltHTLf.containsKey(inetaddress))
                        bdHBlF(dlk3l7z4);
                } else
                {
                    dl01l9z dl01l9z4 = (dl01l9z)hashtable1.get(inetaddress);
                    if(dl01l9z4 != null && lTHTLf.containsKey(inetaddress))
                        BdHBlF(dl01l9z4);
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
            boolean flag3;
            switch(word2)
            {
            case 1: // '\001'
            case 2: // '\002'
            default:
                break;

            case 3: // '\003'
                int i = Utils.readShort(abyte0, j);
                if(i <= lthtlf.bdHBlFzv())
                {
                    LthtLf = i;
                } else
                {
                    StringBuffer stringbuffer = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + lthtlf.BdHBlFzv() + " " + lthtlf.bdHBlFzv() + "Kbps.");
                    bdHblf(stringbuffer.toString());
                    if(lthtlf.bDHBlFzv() != 0)
                    {
                        dlkh89z dlkh89z1 = new dlkh89z(ltHtlF, lthtlf.bDHBlFzv(), 3);
                        lthtlf.bdhblfZv().bdhb(dlkh89z1);
                    }
                    stringbuffer = new StringBuffer();
                    stringbuffer.append("Client " + ltHtlf + " ");
                    stringbuffer.append("exceeded max.min.send:" + lthtlf.bdHBlFzv());
                    stringbuffer.append(" client:" + i);
                    stringbuffer.append(" " + Lthtlf);
                    stringbuffer.append("(" + lThtlf + ":" + LThtlf + ")");
                    stringbuffer.append(", Conference " + lthtlf.BDHbLFzv());
                    bdhBlF(stringbuffer.toString());
                }
                i = Utils.readShort(abyte0, j + 2);
                if(i <= lthtlf.bDHblFzv())
                {
                    lThtLf = i;
                } else
                {
                    StringBuffer stringbuffer1 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + lthtlf.BDHblFzv() + " " + lthtlf.bDHblFzv() + "Kbps.");
                    bdHblf(stringbuffer1.toString());
                    if(lthtlf.bdhBlFzv() != 0)
                    {
                        dlkh89z dlkh89z2 = new dlkh89z(ltHtlF, lthtlf.bdhBlFzv(), 2);
                        lthtlf.bdhblfZv().bdhb(dlkh89z2);
                    }
                    stringbuffer1 = new StringBuffer();
                    stringbuffer1.append("Client " + ltHtlf + " ");
                    stringbuffer1.append("exceeded max.max.send:" + lthtlf.bDHblFzv());
                    stringbuffer1.append(" client:" + i);
                    stringbuffer1.append(" " + Lthtlf);
                    stringbuffer1.append("(" + lThtlf + ":" + LThtlf + ")");
                    stringbuffer1.append(", Confernce " + lthtlf.BDHbLFzv());
                    bdhBlF(stringbuffer1.toString());
                }
                LThtLf = Utils.readShort(abyte0, j + 4);
                i = Utils.readShort(abyte0, j + 6);
                if(i <= lthtlf.BdhBlFzv())
                {
                    ltHtLf = i;
                } else
                {
                    StringBuffer stringbuffer2 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + lthtlf.bDhBlFzv() + " " + lthtlf.BdhBlFzv() + "Kbps.");
                    bdHblf(stringbuffer2.toString());
                    if(lthtlf.BDhBlFzv() != 0)
                    {
                        dlkh89z dlkh89z3 = new dlkh89z(ltHtlF, lthtlf.BDhBlFzv(), 5);
                        lthtlf.bdhblfZv().bdhb(dlkh89z3);
                    }
                    stringbuffer2 = new StringBuffer();
                    stringbuffer2.append("Client " + ltHtlf + " ");
                    stringbuffer2.append("exceeded max.min.recv:" + lthtlf.BdhBlFzv());
                    stringbuffer2.append(" client:" + i);
                    stringbuffer2.append(" " + Lthtlf);
                    stringbuffer2.append("(" + lThtlf + ":" + LThtlf + ")");
                    stringbuffer2.append(", Confernce " + lthtlf.BDHbLFzv());
                    bdhBlF(stringbuffer2.toString());
                }
                i = Utils.readShort(abyte0, j + 8);
                if(i <= lthtlf.BDhblFzv())
                {
                    LtHtLf = i;
                } else
                {
                    StringBuffer stringbuffer3 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + lthtlf.bdHblFzv() + " " + lthtlf.BDhblFzv() + "Kbps.");
                    bdHblf(stringbuffer3.toString());
                    if(lthtlf.BdHblFzv() != 0)
                    {
                        dlkh89z dlkh89z4 = new dlkh89z(ltHtlF, lthtlf.BdHblFzv(), 4);
                        lthtlf.bdhblfZv().bdhb(dlkh89z4);
                    }
                    stringbuffer3 = new StringBuffer();
                    stringbuffer3.append("Client " + ltHtlf + " ");
                    stringbuffer3.append("exceeded max.max.recv:" + lthtlf.BDhblFzv());
                    stringbuffer3.append(" client:" + i);
                    stringbuffer3.append(" " + Lthtlf);
                    stringbuffer3.append("(" + lThtlf + ":" + LThtlf + ")");
                    stringbuffer3.append(", Confernce " + lthtlf.BDHbLFzv());
                    bdhBlF(stringbuffer3.toString());
                }
                lTHtLf = Utils.readShort(abyte0, j + 10);
                break;

            case 4: // '\004'
                if(LThtlF != null)
                    break;
                byte abyte2[] = new byte[word3 - 8];
                System.arraycopy(abyte0, j + 8, abyte2, 0, word3 - 8);
                for(int l = 0; l < word3 - 8; l++)
                    if(abyte2[l] == 10 || abyte2[l] == 0)
                        abyte2[l] = 32;

                LThtlF = new String(abyte2);
                break;

            case 200: 
                lthTLf = Utils.readInt(abyte0, j + 12);
                break;

            case 4297: 
                System.arraycopy(abyte0, j, abyte1, 0, 16);
                boolean flag1 = true;
                break;

            case 201: 
                flag3 = true;
                break;
            }
        }

        lThTLf = true;
    }

    void bDhBlF(String s)
    {
        BDhBlF(s, (d0k2l6z)bdhBlf().BDhbLfzv().get(lTHtlf), bDHblf(), bDhBLf());
    }

    void BDhBlF(String s, d0k2l6z d0k2l6z1, String s1, String s2)
    {
        int i = s.getBytes().length;
        byte abyte0[] = new byte[i + 6];
        System.arraycopy(s.getBytes(), 0, abyte0, 6, i);
        System.arraycopy(Utils.ishort2Bytes(i), 0, abyte0, 4, 2);
        try
        {
            System.arraycopy(InetAddress.getByName(s1).getAddress(), 0, abyte0, 0, 4);
        }
        catch(UnknownHostException _ex) { }
        d0k2l6z1.BdhbL(abyte0, 1, d0k2l6z1.BdHbl().getHostAddress(), s2, 21763);
    }

    void bdHBlF(ClientInfo dlk3l7z1)
    {
        ltHTLf.remove(dlk3l7z1.bdhBlFz());
    }

    void BdHBlF(dl01l9z dl01l9z1)
    {
        lTHTLf.remove(dl01l9z1.BdhbLf());
    }

    void bDHBlF(ClientInfo dlk3l7z1)
    {
        LThTLf.remove(dlk3l7z1.bdhBlFz());
    }

    void BDHBlF(dl01l9z dl01l9z1)
    {
        LtHTLf.remove(dl01l9z1.BdhbLf());
    }

    public void updateTimer()
    {
        lThTlf++;
        ltHTlf++;
        if(BDhBLf(ltHTlf) > 60L)
        {
            lthtlf.BDHblFZv(this);
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("Client " + ltHtlf + " ");
            stringbuffer.append("OC PACKET TIMEOUT deleted " + Lthtlf);
            stringbuffer.append("(" + LtHtlF + ":" + LThtlf + ")");
            stringbuffer.append(", Conference " + lthtlf.BDHbLFzv());
            bdhBlF(stringbuffer.toString());
        }
    }

    private Conference lthtlf;
    private String Lthtlf;
    private String lThtlf;
    private String LThtlf;
    private String ltHtlf;
    private InetAddress LtHtlf;
    private InetAddress lTHtlf;
    private InetAddress LTHtlf;
    private long lthTlf;
    private long LthTlf;
    private long lThTlf;
    private long LThTlf;
    private long ltHTlf;
    private byte LtHTlf;
    private byte lTHTlf;
    private byte LTHTlf;
    private byte lthtLf;
    private int LthtLf;
    private int lThtLf;
    private int LThtLf;
    private int ltHtLf;
    private int LtHtLf;
    private int lTHtLf;
    private int LTHtLf;
    private int lthTLf;
    private boolean LthTLf;
    private boolean lThTLf;
    private Hashtable LThTLf;
    private Hashtable ltHTLf;
    private Hashtable LtHTLf;
    private Hashtable lTHTLf;
    private DatagramSocket LTHTLf;
    private MainReceiver lthtlF;
    private byte LthtlF[];
    private byte lThtlF[];
    private String LThtlF;
    private d0khlhz ltHtlF;
    private String LtHtlF;
}

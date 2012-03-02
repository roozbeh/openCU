// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.util.*;

// Referenced classes of package PMRef:
//            dlkhlhz, d21hlhz, dlk7lhz, d8kh3hz, 
//            Constants, d2k49hz, dl230hz, d6khl0z, 
//            d87hl2z, dlk3l7z, dl01l9z, d4kh98z, 
//            d2khlh9, dlk9lh4

class d09876z extends Thread
    implements Constants
{

    d09876z(Socket socket, ConfigLoader d2khlh9_1, MainReceiver d87hl2z1)
        throws IOException
    {
        ltht = socket;
        LTht = d2khlh9_1;
        LtHt = d87hl2z1;
        ltHt = d2khlh9_1.BdHbLfz();
        Ltht = new DataInputStream(socket.getInputStream());
        lTht = new DataOutputStream(socket.getOutputStream());
        setName("RefmonConnection");
    }

    void bdhb(String s, InetAddress inetaddress, InetAddress inetaddress1, int i, MainReceiver d87hl2z1)
    {
        byte abyte1[] = s.getBytes();
        int j = abyte1.length;
        byte abyte0[] = new byte[j + 3];
        System.arraycopy(abyte1, 0, abyte0, 3, j);
        abyte1 = Utils.ishort2Bytes(j);
        abyte0[2] = abyte1[1];
        DisconnectPacket dl230hz1 = new DisconnectPacket(abyte0, true);
        dl230hz1.setSocket(d87hl2z1.bDHb());
        try
        {
            dl230hz1.bdhb(inetaddress, inetaddress1, d87hl2z1.BDHb().getAddress(), i);
        }
        catch(Exception _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("DataSocket Send Error");
            BDhb(stringbuffer.toString());
        }
    }

    void Bdhb()
    {
        for(Enumeration enumeration = LTht.bDHbLFz().elements(); enumeration.hasMoreElements();)
        {
            ClientInfo dlk3l7z1 = (ClientInfo)enumeration.nextElement();
            if(dlk3l7z1 != null)
            {
                for(int i = 0; i < 3; i++)
                    dlk3l7z1.BDHblfz("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\rThis reflector is going to shutdown. Thank you.");

            }
        }

    }

    boolean bDhb(Socket socket, ConfigLoader d2khlh9_1)
    {
        return d2khlh9_1.BdhblfZ().ktdblhBdhb(socket.getInetAddress());
    }

    void BDhb(String s)
    {
        LTht.getLogger().logStr(s);
    }

    public void run()
    {
        boolean flag = true;
        PacketParserThread d09h5hz = LtHt.BdHb();
        try
        {
            while(flag) 
            {
                byte abyte0[] = new byte[4];
                int i11 = Ltht.read(abyte0, 0, 4);
                if(i11 == -1)
                {
                    flag = false;
                    break;
                }
                short word41 = Utils.BDhb(abyte0, 2);
                switch(Utils.BDhb(abyte0, 0))
                {
                case 1: // '\001'
                    BDhb("VERSION recieved from " + ltht.getInetAddress().getHostName());
                    short word0 = (short)(4 + "Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)".length());
                    byte abyte1[] = new byte[word0];
                    int i = 0;
                    byte abyte42[] = Utils.short2bytes((short)1);
                    abyte1[i++] = abyte42[0];
                    abyte1[i++] = abyte42[1];
                    abyte42 = Utils.short2bytes(word0);
                    abyte1[i++] = abyte42[0];
                    abyte1[i++] = abyte42[1];
                    abyte0 = "Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)".getBytes();
                    System.arraycopy(abyte0, 0, abyte1, i, abyte0.length);
                    lTht.write(abyte1, 0, word0);
                    lTht.flush();
                    break;

                case 2: // '\002'
                    if(bDhb(ltht, LTht))
                    {
                        BDhb("WHO recieved from " + ltht.getInetAddress().getHostName());
                        StringBuffer stringbuffer = new StringBuffer();
                        for(int i14 = 0; i14 < ltHt.length; i14++)
                            if(!ltHt[i14].bdHblfZv().equals(""))
                                stringbuffer.append(ltHt[i14].bdHblfZv() + "\n");

                        if(stringbuffer.toString().length() == 0)
                            stringbuffer.append("No Clients");
                        else
                            stringbuffer.append("Total # of clients " + LTht.BDHbLFz() + "  senders " + LTht.BDHblfZ() + "  lurkers " + LTht.BdHBLfz());
                        int j = 0;
                        String s = stringbuffer.toString();
                        short word1 = (short)(4 + s.length());
                        byte abyte2[] = new byte[word1];
                        byte abyte43[] = Utils.short2bytes((short)2);
                        abyte2[j++] = abyte43[0];
                        abyte2[j++] = abyte43[1];
                        abyte43 = Utils.short2bytes(word1);
                        abyte2[j++] = abyte43[0];
                        abyte2[j++] = abyte43[1];
                        abyte0 = s.getBytes();
                        System.arraycopy(abyte0, 0, abyte2, j, abyte0.length);
                        lTht.write(abyte2, 0, word1);
                        lTht.flush();
                    } else
                    {
                        BDhb("WHO recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer1 = new StringBuffer();
                        stringbuffer1.append("WHO command from your IP Address not allowed.\n");
                        int k = 0;
                        String s1 = stringbuffer1.toString();
                        short word2 = (short)(4 + s1.length());
                        byte abyte3[] = new byte[word2];
                        byte abyte44[] = Utils.short2bytes((short)2);
                        abyte3[k++] = abyte44[0];
                        abyte3[k++] = abyte44[1];
                        abyte44 = Utils.short2bytes(word2);
                        abyte3[k++] = abyte44[0];
                        abyte3[k++] = abyte44[1];
                        abyte0 = s1.getBytes();
                        System.arraycopy(abyte0, 0, abyte3, k, abyte0.length);
                        lTht.write(abyte3, 0, word2);
                        lTht.flush();
                    }
                    break;

                case 3: // '\003'
                    if(bDhb(ltht, LTht))
                    {
                        BDhb("TERM recieved from " + ltht.getInetAddress().getHostName());
                        Bdhb();
                        LTht.BdHBLfZ();
                        flag = false;
                    } else
                    {
                        BDhb("TERM recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer2 = new StringBuffer();
                        stringbuffer2.append("TERM command from your IP Address not allowed.\n");
                        int l = 0;
                        String s2 = stringbuffer2.toString();
                        short word3 = (short)(4 + s2.length());
                        byte abyte4[] = new byte[word3];
                        byte abyte45[] = Utils.short2bytes((short)3);
                        abyte4[l++] = abyte45[0];
                        abyte4[l++] = abyte45[1];
                        abyte45 = Utils.short2bytes(word3);
                        abyte4[l++] = abyte45[0];
                        abyte4[l++] = abyte45[1];
                        abyte0 = s2.getBytes();
                        System.arraycopy(abyte0, 0, abyte4, l, abyte0.length);
                        lTht.write(abyte4, 0, word3);
                        lTht.flush();
                    }
                    break;

                case 4: // '\004'
                    if(bDhb(ltht, LTht))
                    {
                        BDhb("PARAM recieved from " + ltht.getInetAddress().getHostName());
                        StringBuffer stringbuffer3 = new StringBuffer();
                        stringbuffer3.append(LTht.BDhblfZ());
                        int i1 = 0;
                        String s3 = stringbuffer3.toString();
                        short word4 = (short)(4 + s3.length());
                        byte abyte5[] = new byte[word4];
                        byte abyte46[] = Utils.short2bytes((short)4);
                        abyte5[i1++] = abyte46[0];
                        abyte5[i1++] = abyte46[1];
                        abyte46 = Utils.short2bytes(word4);
                        abyte5[i1++] = abyte46[0];
                        abyte5[i1++] = abyte46[1];
                        abyte0 = s3.getBytes();
                        System.arraycopy(abyte0, 0, abyte5, i1, abyte0.length);
                        lTht.write(abyte5, 0, word4);
                        lTht.flush();
                    } else
                    {
                        BDhb("NOT-SUPPORTED recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer4 = new StringBuffer();
                        stringbuffer4.append("NOT-SUPPORTED command from your IP Address not allowed.\n");
                        int j1 = 0;
                        String s4 = stringbuffer4.toString();
                        short word5 = (short)(4 + s4.length());
                        byte abyte6[] = new byte[word5];
                        byte abyte47[] = Utils.short2bytes((short)4);
                        abyte6[j1++] = abyte47[0];
                        abyte6[j1++] = abyte47[1];
                        abyte47 = Utils.short2bytes(word5);
                        abyte6[j1++] = abyte47[0];
                        abyte6[j1++] = abyte47[1];
                        abyte0 = s4.getBytes();
                        System.arraycopy(abyte0, 0, abyte6, j1, abyte0.length);
                        lTht.write(abyte6, 0, word5);
                        lTht.flush();
                    }
                    break;

                case 6: // '\006'
                    if(bDhb(ltht, LTht))
                    {
                        BDhb("UPTIME recieved from " + ltht.getInetAddress().getHostName());
                        StringBuffer stringbuffer5 = new StringBuffer();
                        stringbuffer5.append("Started: ");
                        stringbuffer5.append(DateFormat.getDateTimeInstance(1, 1).format(LTht.BDhBlfZ()));
                        Date date = new Date();
                        long l14 = date.getTime() - LTht.BDhBlfZ().getTime();
                        long l15 = l14 / 1000L;
                        stringbuffer5.append(" (");
                        stringbuffer5.append(l15 / 0x15180L + " days ");
                        String s5 = (new Long((l15 % 0x15180L) / 3600L)).toString();
                        if(s5.length() == 1)
                            s5 = "0" + s5;
                        stringbuffer5.append(s5 + ":");
                        s5 = (new Long((l15 % 0x15180L % 3600L) / 60L)).toString();
                        if(s5.length() == 1)
                            s5 = "0" + s5;
                        stringbuffer5.append(s5 + ":");
                        s5 = (new Long(l15 % 0x15180L % 3600L % 60L)).toString();
                        if(s5.length() == 1)
                            s5 = "0" + s5;
                        stringbuffer5.append(s5 + ")");
                        s5 = stringbuffer5.toString();
                        short word6 = (short)(4 + s5.length());
                        byte abyte7[] = new byte[word6];
                        int k1 = 0;
                        byte abyte48[] = Utils.short2bytes((short)6);
                        abyte7[k1++] = abyte48[0];
                        abyte7[k1++] = abyte48[1];
                        abyte48 = Utils.short2bytes(word6);
                        abyte7[k1++] = abyte48[0];
                        abyte7[k1++] = abyte48[1];
                        abyte0 = s5.getBytes();
                        System.arraycopy(abyte0, 0, abyte7, k1, abyte0.length);
                        lTht.write(abyte7, 0, word6);
                        lTht.flush();
                    } else
                    {
                        BDhb("UPTIME recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer6 = new StringBuffer();
                        stringbuffer6.append("UPTIME command from your IP Address not allowed.\n");
                        int l1 = 0;
                        String s6 = stringbuffer6.toString();
                        short word7 = (short)(4 + s6.length());
                        byte abyte8[] = new byte[word7];
                        byte abyte49[] = Utils.short2bytes((short)6);
                        abyte8[l1++] = abyte49[0];
                        abyte8[l1++] = abyte49[1];
                        abyte49 = Utils.short2bytes(word7);
                        abyte8[l1++] = abyte49[0];
                        abyte8[l1++] = abyte49[1];
                        abyte0 = s6.getBytes();
                        System.arraycopy(abyte0, 0, abyte8, l1, abyte0.length);
                        lTht.write(abyte8, 0, word7);
                        lTht.flush();
                    }
                    break;

                case 7: // '\007'
                    if(bDhb(ltht, LTht))
                    {
                        byte abyte81[] = new byte[word41 - 4];
                        i11 = Ltht.read(abyte81, 0, word41 - 4);
                        if(i11 == -1)
                        {
                            flag = false;
                            break;
                        }
                        StringTokenizer stringtokenizer = new StringTokenizer(new String(abyte81), "\0");
                        String s7;
                        try
                        {
                            StringBuffer stringbuffer7;
                            if(stringtokenizer.countTokens() != 2)
                            {
                                stringbuffer7 = new StringBuffer();
                                stringbuffer7.append("Parameters error.");
                            } else
                            {
                                String s41 = stringtokenizer.nextToken();
                                String s45 = stringtokenizer.nextToken();
                                byte abyte94[] = s45.getBytes();
                                for(int i2 = 0; i2 < abyte94.length; i2++)
                                    if(abyte94[i2] == 13)
                                        abyte94[i2] = 32;

                                InetAddress inetaddress;
                                try
                                {
                                    inetaddress = InetAddress.getByName(s41);
                                }
                                catch(UnknownHostException _ex)
                                {
                                    inetaddress = null;
                                }
                                ClientInfo dlk3l7z1 = null;
                                d0khlhz d0khlhz = LTht.BdHBlfZ().BdHbl(inetaddress);
                                if(d0khlhz != null)
                                    dlk3l7z1 = (ClientInfo)LTht.bDHbLFz().get(d0khlhz);
                                if(dlk3l7z1 != null)
                                {
                                    stringbuffer7 = new StringBuffer();
                                    stringbuffer7.append("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + s45);
                                    if(stringbuffer7.toString().length() > 255)
                                        stringbuffer7 = new StringBuffer(stringbuffer7.substring(0, 254));
                                    BDhb("KILL recieved from " + ltht.getInetAddress().getHostName() + ", \"" + new String(abyte94) + "\" to " + s41);
                                    if(dlk3l7z1.BdHBlFz())
                                        bdhb(stringbuffer7.toString(), dlk3l7z1.bdhBLfz(), dlk3l7z1.bdhbLfz(), dlk3l7z1.bdhblFz(), dlk3l7z1.getMainReceiver());
                                    else
                                        bdhb(stringbuffer7.toString(), dlk3l7z1.bdhBLfz(), dlk3l7z1.bdhBLfz(), dlk3l7z1.bdhblFz(), dlk3l7z1.getMainReceiver());
                                    stringbuffer7 = new StringBuffer();
                                    stringbuffer7.append("Killed " + s41);
                                } else
                                {
                                    dl01l9z dl01l9z1 = (dl01l9z)LTht.BDhBlfz().get(inetaddress);
                                    if(dl01l9z1 != null)
                                    {
                                        stringbuffer7 = new StringBuffer();
                                        stringbuffer7.append("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + s45);
                                        if(stringbuffer7.toString().length() > 255)
                                            stringbuffer7 = new StringBuffer(stringbuffer7.substring(0, 254));
                                        BDhb("KILL recieved from " + ltht.getInetAddress().getHostName() + ", \"" + new String(abyte94) + "\" to " + s41);
                                        dl01l9z1.bdHblf(stringbuffer7.toString());
                                        stringbuffer7 = new StringBuffer();
                                        stringbuffer7.append("Killed " + s41);
                                    } else
                                    {
                                        BDhb("KILL recieved from " + ltht.getInetAddress().getHostName() + ", \"" + new String(abyte94) + "\" to " + s41 + ", but Client not found");
                                        stringbuffer7 = new StringBuffer();
                                        stringbuffer7.append("Client not found.");
                                    }
                                }
                            }
                            s7 = stringbuffer7.toString();
                        }
                        catch(NullPointerException _ex)
                        {
                            s7 = new String("Client not found.");
                        }
                        short word8 = (short)(4 + s7.length());
                        byte abyte9[] = new byte[word8];
                        int j2 = 0;
                        byte abyte50[] = Utils.short2bytes((short)7);
                        abyte9[j2++] = abyte50[0];
                        abyte9[j2++] = abyte50[1];
                        abyte50 = Utils.short2bytes(word8);
                        abyte9[j2++] = abyte50[0];
                        abyte9[j2++] = abyte50[1];
                        abyte0 = s7.getBytes();
                        System.arraycopy(abyte0, 0, abyte9, j2, abyte0.length);
                        lTht.write(abyte9, 0, word8);
                        lTht.flush();
                    } else
                    {
                        BDhb("KILL recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer8 = new StringBuffer();
                        stringbuffer8.append("KILL command from your IP Address not allowed.\n");
                        int k2 = 0;
                        String s8 = stringbuffer8.toString();
                        short word9 = (short)(4 + s8.length());
                        byte abyte10[] = new byte[word9];
                        byte abyte51[] = Utils.short2bytes((short)7);
                        abyte10[k2++] = abyte51[0];
                        abyte10[k2++] = abyte51[1];
                        abyte51 = Utils.short2bytes(word9);
                        abyte10[k2++] = abyte51[0];
                        abyte10[k2++] = abyte51[1];
                        abyte0 = s8.getBytes();
                        System.arraycopy(abyte0, 0, abyte10, k2, abyte0.length);
                        lTht.write(abyte10, 0, word9);
                        lTht.flush();
                    }
                    break;

                case 8: // '\b'
                    if(bDhb(ltht, LTht))
                    {
                        StringBuffer stringbuffer9;
                        if(word41 == 4)
                        {
                            BDhb("DENY list recieved from " + ltht.getInetAddress().getHostName());
                            stringbuffer9 = new StringBuffer();
                            stringbuffer9.append(LTht.BDHbLfz().ktdblhbdHb());
                            if(stringbuffer9.toString().length() == 0)
                                stringbuffer9.append("No Addresses\n\n");
                        } else
                        {
                            byte abyte82[] = new byte[word41 - 4];
                            i11 = Ltht.read(abyte82, 0, word41 - 4);
                            if(i11 == -1)
                            {
                                flag = false;
                                break;
                            }
                            StringTokenizer stringtokenizer1 = new StringTokenizer(new String(abyte82), "\0");
                            String s9 = stringtokenizer1.nextToken();
                            if(s9.equals("-d"))
                                try
                                {
                                    s9 = stringtokenizer1.nextToken();
                                    LTht.BDHbLfz().ktdblhBDHb(s9);
                                    BDhb("DENY recieved from " + ltht.getInetAddress().getHostName() + " to delete " + s9);
                                    stringbuffer9 = new StringBuffer();
                                    stringbuffer9.append("Deny deleted " + s9);
                                }
                                catch(NoSuchElementException _ex)
                                {
                                    stringbuffer9 = new StringBuffer();
                                    stringbuffer9.append("Deny format error. ");
                                }
                            else
                                try
                                {
                                    String s42 = stringtokenizer1.nextToken();
                                    if(s42.equals("(REFLECTOR DEFAULT)"))
                                        s42 = LTht.BdhBLfz();
                                    LTht.BDHbLfz().bdhb(s9, s42);
                                    BDhb("DENY recieved from " + ltht.getInetAddress().getHostName() + " to add " + s9);
                                    stringbuffer9 = new StringBuffer();
                                    stringbuffer9.append("Deny added " + s9);
                                }
                                catch(NoSuchElementException _ex)
                                {
                                    stringbuffer9 = new StringBuffer();
                                    stringbuffer9.append("Deny format error. ");
                                }
                        }
                        String s10 = stringbuffer9.toString();
                        short word10 = (short)(4 + s10.length());
                        byte abyte11[] = new byte[word10];
                        int l2 = 0;
                        byte abyte52[] = Utils.short2bytes((short)8);
                        abyte11[l2++] = abyte52[0];
                        abyte11[l2++] = abyte52[1];
                        abyte52 = Utils.short2bytes(word10);
                        abyte11[l2++] = abyte52[0];
                        abyte11[l2++] = abyte52[1];
                        abyte0 = s10.getBytes();
                        System.arraycopy(abyte0, 0, abyte11, l2, abyte0.length);
                        lTht.write(abyte11, 0, word10);
                        lTht.flush();
                    } else
                    {
                        BDhb("DENY recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer10 = new StringBuffer();
                        stringbuffer10.append("DENY command from your IP Address not allowed.\n");
                        int i3 = 0;
                        String s11 = stringbuffer10.toString();
                        short word11 = (short)(4 + s11.length());
                        byte abyte12[] = new byte[word11];
                        byte abyte53[] = Utils.short2bytes((short)8);
                        abyte12[i3++] = abyte53[0];
                        abyte12[i3++] = abyte53[1];
                        abyte53 = Utils.short2bytes(word11);
                        abyte12[i3++] = abyte53[0];
                        abyte12[i3++] = abyte53[1];
                        abyte0 = s11.getBytes();
                        System.arraycopy(abyte0, 0, abyte12, i3, abyte0.length);
                        lTht.write(abyte12, 0, word11);
                        lTht.flush();
                    }
                    break;

                case 9: // '\t'
                    if(bDhb(ltht, LTht))
                    {
                        StringBuffer stringbuffer11;
                        if(word41 == 4)
                        {
                            BDhb("ALLOW list recieved from " + ltht.getInetAddress().getHostName());
                            stringbuffer11 = new StringBuffer();
                            stringbuffer11.append(LTht.bDHBlfz().ktdblhbdHb());
                            if(stringbuffer11.toString().length() == 0)
                                stringbuffer11.append("No Addresses\n");
                        } else
                        {
                            byte abyte83[] = new byte[word41 - 4];
                            i11 = Ltht.read(abyte83, 0, word41 - 4);
                            if(i11 == -1)
                            {
                                flag = false;
                                break;
                            }
                            StringTokenizer stringtokenizer2 = new StringTokenizer(new String(abyte83), "\0");
                            if(stringtokenizer2.countTokens() == 1)
                            {
                                String s12 = stringtokenizer2.nextToken();
                                BDhb("ALLOW recieved from " + ltht.getInetAddress().getHostName() + " for " + s12);
                                try
                                {
                                    LTht.bDHBlfz().ktdblhbdhb(s12);
                                    stringbuffer11 = new StringBuffer();
                                    stringbuffer11.append("Allow added for " + s12);
                                }
                                catch(UnknownHostException _ex)
                                {
                                    BDhb("ALLOW Address Error : " + s12);
                                    stringbuffer11 = new StringBuffer();
                                    stringbuffer11.append("Allow added error " + s12);
                                }
                            } else
                            {
                                String s13 = stringtokenizer2.nextToken();
                                s13 = stringtokenizer2.nextToken();
                                LTht.bDHBlfz().ktdblhBDHb(s13);
                                BDhb("ALLOW recieved from " + ltht.getInetAddress().getHostName() + " deleting " + s13);
                                stringbuffer11 = new StringBuffer();
                                stringbuffer11.append("Allow deleted " + s13);
                            }
                        }
                        String s14 = stringbuffer11.toString();
                        short word12 = (short)(4 + s14.length());
                        byte abyte13[] = new byte[word12];
                        int j3 = 0;
                        byte abyte54[] = Utils.short2bytes((short)9);
                        abyte13[j3++] = abyte54[0];
                        abyte13[j3++] = abyte54[1];
                        abyte54 = Utils.short2bytes(word12);
                        abyte13[j3++] = abyte54[0];
                        abyte13[j3++] = abyte54[1];
                        abyte0 = s14.getBytes();
                        System.arraycopy(abyte0, 0, abyte13, j3, abyte0.length);
                        lTht.write(abyte13, 0, word12);
                        lTht.flush();
                    } else
                    {
                        BDhb("ALLOW recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer12 = new StringBuffer();
                        stringbuffer12.append("ALLOW command from your IP Address not allowed.\n");
                        int k3 = 0;
                        String s15 = stringbuffer12.toString();
                        short word13 = (short)(4 + s15.length());
                        byte abyte14[] = new byte[word13];
                        byte abyte55[] = Utils.short2bytes((short)9);
                        abyte14[k3++] = abyte55[0];
                        abyte14[k3++] = abyte55[1];
                        abyte55 = Utils.short2bytes(word13);
                        abyte14[k3++] = abyte55[0];
                        abyte14[k3++] = abyte55[1];
                        abyte0 = s15.getBytes();
                        System.arraycopy(abyte0, 0, abyte14, k3, abyte0.length);
                        lTht.write(abyte14, 0, word13);
                        lTht.flush();
                    }
                    break;

                case 10: // '\n'
                    if(bDhb(ltht, LTht))
                    {
                        BDhb("DUMP recieved from " + ltht.getInetAddress().getHostName());
                        StringBuffer stringbuffer13 = new StringBuffer();
                        stringbuffer13.append(LTht.BDHBLfZ());
                        stringbuffer13.append(LTht.bDHBLfZ());
                        int l3 = 0;
                        String s16 = stringbuffer13.toString();
                        short word14 = (short)(4 + s16.length());
                        byte abyte15[] = new byte[word14];
                        byte abyte56[] = Utils.short2bytes((short)10);
                        abyte15[l3++] = abyte56[0];
                        abyte15[l3++] = abyte56[1];
                        abyte56 = Utils.short2bytes(word14);
                        abyte15[l3++] = abyte56[0];
                        abyte15[l3++] = abyte56[1];
                        abyte0 = s16.getBytes();
                        System.arraycopy(abyte0, 0, abyte15, l3, abyte0.length);
                        lTht.write(abyte15, 0, word14);
                        lTht.flush();
                    } else
                    {
                        BDhb("NOT-SUPPORTED recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer14 = new StringBuffer();
                        stringbuffer14.append("NOT-SUPPORTED command from your IP Address not allowed.\n");
                        int i4 = 0;
                        String s17 = stringbuffer14.toString();
                        short word15 = (short)(4 + s17.length());
                        byte abyte16[] = new byte[word15];
                        byte abyte57[] = Utils.short2bytes((short)10);
                        abyte16[i4++] = abyte57[0];
                        abyte16[i4++] = abyte57[1];
                        abyte57 = Utils.short2bytes(word15);
                        abyte16[i4++] = abyte57[0];
                        abyte16[i4++] = abyte57[1];
                        abyte0 = s17.getBytes();
                        System.arraycopy(abyte0, 0, abyte16, i4, abyte0.length);
                        lTht.write(abyte16, 0, word15);
                        lTht.flush();
                    }
                    break;

                case 256: 
                    if(bDhb(ltht, LTht))
                    {
                        byte abyte84[] = new byte[word41 - 4];
                        Ltht.read(abyte84, 0, word41 - 4);
                        if(i11 == -1)
                        {
                            flag = false;
                            break;
                        }
                        StringTokenizer stringtokenizer3 = new StringTokenizer(new String(abyte84), "\0");
                        String s18;
                        try
                        {
                            StringBuffer stringbuffer15;
                            if(stringtokenizer3.countTokens() != 2)
                            {
                                stringbuffer15 = new StringBuffer();
                                stringbuffer15.append("Parameters error.");
                            } else
                            {
                                String s43 = stringtokenizer3.nextToken();
                                String s46 = stringtokenizer3.nextToken();
                                byte abyte95[] = s46.getBytes();
                                for(int j4 = 0; j4 < abyte95.length; j4++)
                                    if(abyte95[j4] == 13)
                                        abyte95[j4] = 32;

                                InetAddress inetaddress1;
                                try
                                {
                                    inetaddress1 = InetAddress.getByName(s43);
                                }
                                catch(UnknownHostException _ex)
                                {
                                    inetaddress1 = null;
                                }
                                ClientInfo dlk3l7z2 = null;
                                d0khlhz d0khlhz1 = LTht.BdHBlfZ().BdHbl(inetaddress1);
                                if(d0khlhz1 != null)
                                    dlk3l7z2 = (ClientInfo)LTht.bDHbLFz().get(d0khlhz1);
                                if(dlk3l7z2 != null)
                                {
                                    stringbuffer15 = new StringBuffer();
                                    stringbuffer15.append("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + s46);
                                    dlk3l7z2.BdHblfz(stringbuffer15.toString().getBytes().length + 26 + 4);
                                    dlk3l7z2.BDhBLFz(stringbuffer15.toString());
                                    stringbuffer15 = new StringBuffer();
                                    stringbuffer15.append("Message : \"" + s46 + "\" to " + s43);
                                    BDhb("MSG recieved from " + ltht.getInetAddress().getHostName() + ", \"" + new String(abyte95) + "\" to " + s43);
                                } else
                                {
                                    dl01l9z dl01l9z2 = (dl01l9z)LTht.BDhBlfz().get(inetaddress1);
                                    if(dl01l9z2 != null)
                                    {
                                        stringbuffer15 = new StringBuffer();
                                        stringbuffer15.append("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + s46);
                                        dl01l9z2.bDhBlF(stringbuffer15.toString());
                                        stringbuffer15 = new StringBuffer();
                                        stringbuffer15.append("Message : \"" + s46 + "\" to " + s43);
                                        BDhb("MSG recieved from " + ltht.getInetAddress().getHostName() + ", \"" + new String(abyte95) + "\" to " + s43);
                                    } else
                                    {
                                        BDhb("MSG recieved from " + ltht.getInetAddress().getHostName() + ", \"" + new String(abyte95) + "\" to " + s43 + ", but Client not found");
                                        stringbuffer15 = new StringBuffer();
                                        stringbuffer15.append("Client not found.");
                                    }
                                }
                            }
                            s18 = stringbuffer15.toString();
                        }
                        catch(NullPointerException _ex)
                        {
                            s18 = new String("Client not found.");
                        }
                        short word16 = (short)(4 + s18.length());
                        byte abyte17[] = new byte[word16];
                        int k4 = 0;
                        byte abyte58[] = Utils.short2bytes((short)256);
                        abyte17[k4++] = abyte58[0];
                        abyte17[k4++] = abyte58[1];
                        abyte58 = Utils.short2bytes(word16);
                        abyte17[k4++] = abyte58[0];
                        abyte17[k4++] = abyte58[1];
                        abyte0 = s18.getBytes();
                        System.arraycopy(abyte0, 0, abyte17, k4, abyte0.length);
                        lTht.write(abyte17, 0, word16);
                        lTht.flush();
                    } else
                    {
                        BDhb("MSG recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer16 = new StringBuffer();
                        stringbuffer16.append("MSG command from your IP Address not allowed.\n");
                        int l4 = 0;
                        String s19 = stringbuffer16.toString();
                        short word17 = (short)(4 + s19.length());
                        byte abyte18[] = new byte[word17];
                        byte abyte59[] = Utils.short2bytes((short)256);
                        abyte18[l4++] = abyte59[0];
                        abyte18[l4++] = abyte59[1];
                        abyte59 = Utils.short2bytes(word17);
                        abyte18[l4++] = abyte59[0];
                        abyte18[l4++] = abyte59[1];
                        abyte0 = s19.getBytes();
                        System.arraycopy(abyte0, 0, abyte18, l4, abyte0.length);
                        lTht.write(abyte18, 0, word17);
                        lTht.flush();
                    }
                    break;

                case 257: 
                    if(bDhb(ltht, LTht))
                    {
                        byte abyte85[] = new byte[word41 - 4];
                        int j11 = Ltht.read(abyte85, 0, word41 - 4);
                        if(j11 == -1)
                        {
                            flag = false;
                            break;
                        }
                        String s44 = new String(abyte85);
                        for(int j14 = 0; j14 < abyte85.length; j14++)
                            if(abyte85[j14] == 13)
                                abyte85[j14] = 32;

                        BDhb("MSGALL recieved from " + ltht.getInetAddress().getHostName() + ", \"" + new String(abyte85) + "\"");
                        StringBuffer stringbuffer17 = new StringBuffer();
                        stringbuffer17.append("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + s44);
                        for(Enumeration enumeration = LTht.bDHbLFz().elements(); enumeration.hasMoreElements();)
                            try
                            {
                                ClientInfo dlk3l7z3 = (ClientInfo)enumeration.nextElement();
                                dlk3l7z3.BdHblfz(stringbuffer17.toString().getBytes().length + 26 + 4);
                                dlk3l7z3.BDhBLFz(stringbuffer17.toString());
                            }
                            catch(NullPointerException _ex) { }

                        for(Enumeration enumeration1 = LTht.BDhBlfz().elements(); enumeration1.hasMoreElements();)
                            try
                            {
                                dl01l9z dl01l9z3 = (dl01l9z)enumeration1.nextElement();
                                dl01l9z3.bDhBlF(stringbuffer17.toString());
                            }
                            catch(NullPointerException _ex) { }

                        stringbuffer17 = new StringBuffer();
                        stringbuffer17.append("Message : \"" + new String(abyte85) + "\" to all.");
                        String s20 = stringbuffer17.toString();
                        short word18 = (short)(4 + s20.length());
                        byte abyte19[] = new byte[word18];
                        int i5 = 0;
                        byte abyte60[] = Utils.short2bytes((short)257);
                        abyte19[i5++] = abyte60[0];
                        abyte19[i5++] = abyte60[1];
                        abyte60 = Utils.short2bytes(word18);
                        abyte19[i5++] = abyte60[0];
                        abyte19[i5++] = abyte60[1];
                        abyte0 = s20.getBytes();
                        System.arraycopy(abyte0, 0, abyte19, i5, abyte0.length);
                        lTht.write(abyte19, 0, word18);
                        lTht.flush();
                    } else
                    {
                        BDhb("MSGALL recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer18 = new StringBuffer();
                        stringbuffer18.append("MSGALL command from your IP Address not allowed.\n");
                        int j5 = 0;
                        String s21 = stringbuffer18.toString();
                        short word19 = (short)(4 + s21.length());
                        byte abyte20[] = new byte[word19];
                        byte abyte61[] = Utils.short2bytes((short)257);
                        abyte20[j5++] = abyte61[0];
                        abyte20[j5++] = abyte61[1];
                        abyte61 = Utils.short2bytes(word19);
                        abyte20[j5++] = abyte61[0];
                        abyte20[j5++] = abyte61[1];
                        abyte0 = s21.getBytes();
                        System.arraycopy(abyte0, 0, abyte20, j5, abyte0.length);
                        lTht.write(abyte20, 0, word19);
                        lTht.flush();
                    }
                    break;

                case 258: 
                    if(bDhb(ltht, LTht))
                    {
                        if(LTht.BDHbLFz() != 0)
                        {
                            BDhb("RESTART(TERM) recieved from " + ltht.getInetAddress().getHostName());
                            Bdhb();
                            LTht.BdHBLfZ();
                            flag = false;
                        } else
                        {
                            BDhb("RESTART recieved from " + ltht.getInetAddress().getHostName());
                            Bdhb();
                            LTht.BDHbLfZ();
                            flag = false;
                        }
                    } else
                    {
                        BDhb("RESTART recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer19 = new StringBuffer();
                        stringbuffer19.append("RESTART command from your IP Address not allowed.\n");
                        int k5 = 0;
                        String s22 = stringbuffer19.toString();
                        short word20 = (short)(4 + s22.length());
                        byte abyte21[] = new byte[word20];
                        byte abyte62[] = Utils.short2bytes((short)258);
                        abyte21[k5++] = abyte62[0];
                        abyte21[k5++] = abyte62[1];
                        abyte62 = Utils.short2bytes(word20);
                        abyte21[k5++] = abyte62[0];
                        abyte21[k5++] = abyte62[1];
                        abyte0 = s22.getBytes();
                        System.arraycopy(abyte0, 0, abyte21, k5, abyte0.length);
                        lTht.write(abyte21, 0, word20);
                        lTht.flush();
                    }
                    break;

                case 259: 
                    StringBuffer stringbuffer20 = new StringBuffer();
                    BDhb("PROXYLIST recieved from " + ltht.getInetAddress().getHostName());
                    if(bDhb(ltht, LTht))
                        stringbuffer20.append(LTht.bdHblfZ());
                    else
                        stringbuffer20.append(LTht.BdHblfZ(ltht.getInetAddress()));
                    int l5 = 0;
                    String s23 = stringbuffer20.toString();
                    short word21 = (short)(4 + s23.length());
                    byte abyte22[] = new byte[word21];
                    byte abyte63[] = Utils.short2bytes((short)259);
                    abyte22[l5++] = abyte63[0];
                    abyte22[l5++] = abyte63[1];
                    abyte63 = Utils.short2bytes(word21);
                    abyte22[l5++] = abyte63[0];
                    abyte22[l5++] = abyte63[1];
                    abyte0 = s23.getBytes();
                    System.arraycopy(abyte0, 0, abyte22, l5, abyte0.length);
                    lTht.write(abyte22, 0, word21);
                    lTht.flush();
                    break;

                case 260: 
                    if(bDhb(ltht, LTht))
                    {
                        byte abyte86[] = new byte[word41 - 4];
                        int k11 = Ltht.read(abyte86, 0, word41 - 4);
                        if(k11 == -1)
                        {
                            flag = false;
                            break;
                        }
                        StringBuffer stringbuffer21 = new StringBuffer();
                        BDhb("PROXYSET recieved from " + ltht.getInetAddress().getHostName());
                        try
                        {
                            if(LTht.bDhblfz(InetAddress.getByName(Utils.convertToIPform(abyte86, 0)), InetAddress.getByName(Utils.convertToIPform(abyte86, 4))))
                                stringbuffer21.append("OK.");
                            else
                                stringbuffer21.append("User and/or Ref in the Session, or the Ref is peer.");
                        }
                        catch(UnknownHostException _ex)
                        {
                            stringbuffer21.append("Host Address Error " + Utils.convertToIPform(abyte86, 0) + ":" + Utils.convertToIPform(abyte86, 4));
                        }
                        int i6 = 0;
                        String s24 = stringbuffer21.toString();
                        short word22 = (short)(4 + s24.length());
                        byte abyte23[] = new byte[word22];
                        byte abyte64[] = Utils.short2bytes((short)260);
                        abyte23[i6++] = abyte64[0];
                        abyte23[i6++] = abyte64[1];
                        abyte64 = Utils.short2bytes(word22);
                        abyte23[i6++] = abyte64[0];
                        abyte23[i6++] = abyte64[1];
                        abyte0 = s24.getBytes();
                        System.arraycopy(abyte0, 0, abyte23, i6, abyte0.length);
                        lTht.write(abyte23, 0, word22);
                        lTht.flush();
                    } else
                    {
                        BDhb("PROXYSET recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer22 = new StringBuffer();
                        stringbuffer22.append("PROXSET command from your IP Address not allowed.\n");
                        int j6 = 0;
                        String s25 = stringbuffer22.toString();
                        short word23 = (short)(4 + s25.length());
                        byte abyte24[] = new byte[word23];
                        byte abyte65[] = Utils.short2bytes((short)260);
                        abyte24[j6++] = abyte65[0];
                        abyte24[j6++] = abyte65[1];
                        abyte65 = Utils.short2bytes(word23);
                        abyte24[j6++] = abyte65[0];
                        abyte24[j6++] = abyte65[1];
                        abyte0 = s25.getBytes();
                        System.arraycopy(abyte0, 0, abyte24, j6, abyte0.length);
                        lTht.write(abyte24, 0, word23);
                        lTht.flush();
                    }
                    break;

                case 261: 
                    if(bDhb(ltht, LTht))
                    {
                        byte abyte87[] = new byte[word41 - 4];
                        int l11 = Ltht.read(abyte87, 0, word41 - 4);
                        if(l11 == -1)
                        {
                            flag = false;
                            break;
                        }
                        StringBuffer stringbuffer23 = new StringBuffer();
                        BDhb("PROXYDEL recieved from " + ltht.getInetAddress().getHostName());
                        try
                        {
                            if(LTht.bdhBlfz(InetAddress.getByName(Utils.convertToIPform(abyte87, 0))))
                                stringbuffer23.append("OK.");
                            else
                                stringbuffer23.append("User and/or Ref in the Session.");
                        }
                        catch(UnknownHostException _ex)
                        {
                            stringbuffer23.append("Host Address Error " + Utils.convertToIPform(abyte87, 0));
                        }
                        int k6 = 0;
                        String s26 = stringbuffer23.toString();
                        short word24 = (short)(4 + s26.length());
                        byte abyte25[] = new byte[word24];
                        byte abyte66[] = Utils.short2bytes((short)261);
                        abyte25[k6++] = abyte66[0];
                        abyte25[k6++] = abyte66[1];
                        abyte66 = Utils.short2bytes(word24);
                        abyte25[k6++] = abyte66[0];
                        abyte25[k6++] = abyte66[1];
                        abyte0 = s26.getBytes();
                        System.arraycopy(abyte0, 0, abyte25, k6, abyte0.length);
                        lTht.write(abyte25, 0, word24);
                        lTht.flush();
                    } else
                    {
                        BDhb("PROXYDEL recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer24 = new StringBuffer();
                        stringbuffer24.append("PROXYDEL command from your IP Address not allowed.\n");
                        int l6 = 0;
                        String s27 = stringbuffer24.toString();
                        short word25 = (short)(4 + s27.length());
                        byte abyte26[] = new byte[word25];
                        byte abyte67[] = Utils.short2bytes((short)261);
                        abyte26[l6++] = abyte67[0];
                        abyte26[l6++] = abyte67[1];
                        abyte67 = Utils.short2bytes(word25);
                        abyte26[l6++] = abyte67[0];
                        abyte26[l6++] = abyte67[1];
                        abyte0 = s27.getBytes();
                        System.arraycopy(abyte0, 0, abyte26, l6, abyte0.length);
                        lTht.write(abyte26, 0, word25);
                        lTht.flush();
                    }
                    break;

                case 262: 
                    byte abyte88[] = new byte[word41 - 4];
                    int i12 = Ltht.read(abyte88, 0, word41 - 4);
                    if(i12 == -1)
                    {
                        flag = false;
                        break;
                    }
                    StringBuffer stringbuffer25 = new StringBuffer();
                    BDhb("PROXYUSERSET recieved from " + ltht.getInetAddress().getHostName());
                    try
                    {
                        if(LTht.bDhblfz(ltht.getInetAddress(), InetAddress.getByName(Utils.convertToIPform(abyte88, 0))))
                            stringbuffer25.append("OK.");
                        else
                            stringbuffer25.append("User and/or Ref in the Session, or the Ref is peer.");
                    }
                    catch(UnknownHostException _ex)
                    {
                        stringbuffer25.append("Host Address Error " + Utils.convertToIPform(abyte88, 0) + ":" + Utils.convertToIPform(abyte88, 4));
                    }
                    int i7 = 0;
                    String s28 = stringbuffer25.toString();
                    short word26 = (short)(4 + s28.length());
                    byte abyte27[] = new byte[word26];
                    byte abyte68[] = Utils.short2bytes((short)262);
                    abyte27[i7++] = abyte68[0];
                    abyte27[i7++] = abyte68[1];
                    abyte68 = Utils.short2bytes(word26);
                    abyte27[i7++] = abyte68[0];
                    abyte27[i7++] = abyte68[1];
                    abyte0 = s28.getBytes();
                    System.arraycopy(abyte0, 0, abyte27, i7, abyte0.length);
                    lTht.write(abyte27, 0, word26);
                    lTht.flush();
                    break;

                case 263: 
                    StringBuffer stringbuffer26 = new StringBuffer();
                    BDhb("PROXYUSERDEL recieved from " + ltht.getInetAddress().getHostName());
                    if(LTht.bdhBlfz(ltht.getInetAddress()))
                        stringbuffer26.append("OK.");
                    else
                        stringbuffer26.append("User and/or Ref in the Session");
                    int j7 = 0;
                    String s29 = stringbuffer26.toString();
                    short word27 = (short)(4 + s29.length());
                    byte abyte28[] = new byte[word27];
                    byte abyte69[] = Utils.short2bytes((short)263);
                    abyte28[j7++] = abyte69[0];
                    abyte28[j7++] = abyte69[1];
                    abyte69 = Utils.short2bytes(word27);
                    abyte28[j7++] = abyte69[0];
                    abyte28[j7++] = abyte69[1];
                    abyte0 = s29.getBytes();
                    System.arraycopy(abyte0, 0, abyte28, j7, abyte0.length);
                    lTht.write(abyte28, 0, word27);
                    lTht.flush();
                    break;

                case 264: 
                    if(bDhb(ltht, LTht))
                    {
                        BDhb("PENALTYBOX recieved from " + ltht.getInetAddress().getHostName());
                        StringBuffer stringbuffer27 = new StringBuffer();
                        stringbuffer27.append("Penalty Box(Reflector) : \n");
                        stringbuffer27.append(LTht.bdhBLFz().bDhb());
                        stringbuffer27.append("\n");
                        stringbuffer27.append("Penalty Box(Conference) : \n");
                        for(int k7 = 0; k7 < ltHt.length; k7++)
                            if(!ltHt[k7].bdhblfZv().bDhb().equals(""))
                            {
                                stringbuffer27.append("Conference " + ltHt[k7].BDHbLFzv() + "\n");
                                stringbuffer27.append(ltHt[k7].bdhblfZv().bDhb());
                                stringbuffer27.append("\n");
                            }

                        int l7 = 0;
                        String s30 = stringbuffer27.toString();
                        short word28 = (short)(4 + s30.length());
                        byte abyte29[] = new byte[word28];
                        byte abyte70[] = Utils.short2bytes((short)264);
                        abyte29[l7++] = abyte70[0];
                        abyte29[l7++] = abyte70[1];
                        abyte70 = Utils.short2bytes(word28);
                        abyte29[l7++] = abyte70[0];
                        abyte29[l7++] = abyte70[1];
                        abyte0 = s30.getBytes();
                        System.arraycopy(abyte0, 0, abyte29, l7, abyte0.length);
                        lTht.write(abyte29, 0, word28);
                        lTht.flush();
                    } else
                    {
                        BDhb("NOT-SUPPORTED recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer28 = new StringBuffer();
                        stringbuffer28.append("NOT-SUPPORTED command from your IP Address not allowed.\n");
                        int i8 = 0;
                        String s31 = stringbuffer28.toString();
                        short word29 = (short)(4 + s31.length());
                        byte abyte30[] = new byte[word29];
                        byte abyte71[] = Utils.short2bytes((short)264);
                        abyte30[i8++] = abyte71[0];
                        abyte30[i8++] = abyte71[1];
                        abyte71 = Utils.short2bytes(word29);
                        abyte30[i8++] = abyte71[0];
                        abyte30[i8++] = abyte71[1];
                        abyte0 = s31.getBytes();
                        System.arraycopy(abyte0, 0, abyte30, i8, abyte0.length);
                        lTht.write(abyte30, 0, word29);
                        lTht.flush();
                    }
                    break;

                case 265: 
                    if(bDhb(ltht, LTht))
                    {
                        StringBuffer stringbuffer29 = new StringBuffer();
                        if(word41 < 10)
                        {
                            stringbuffer29.append("Parameters length error.");
                            break;
                        }
                        byte abyte89[] = new byte[word41 - 4];
                        int j12 = Ltht.read(abyte89, 0, word41 - 4);
                        if(j12 == -1)
                        {
                            flag = false;
                            break;
                        }
                        switch(Utils.BDhb(abyte89, 0))
                        {
                        case 1: // '\001'
                            stringbuffer29.append((new Short(LTht.bdHBlFz())).toString());
                            break;

                        case 2: // '\002'
                            stringbuffer29.append((new Short(LTht.bDHBlFz())).toString());
                            break;

                        case 3: // '\003'
                            stringbuffer29.append((new Short(LTht.bDHBLfz())).toString());
                            break;

                        default:
                            stringbuffer29.append("Parameter error.");
                            break;
                        }
                        int j8 = 0;
                        String s32 = stringbuffer29.toString();
                        short word30 = (short)(4 + s32.length());
                        byte abyte31[] = new byte[word30];
                        byte abyte72[] = Utils.short2bytes((short)265);
                        abyte31[j8++] = abyte72[0];
                        abyte31[j8++] = abyte72[1];
                        abyte72 = Utils.short2bytes(word30);
                        abyte31[j8++] = abyte72[0];
                        abyte31[j8++] = abyte72[1];
                        abyte0 = s32.getBytes();
                        System.arraycopy(abyte0, 0, abyte31, j8, abyte0.length);
                        lTht.write(abyte31, 0, word30);
                        lTht.flush();
                    } else
                    {
                        BDhb("NOT-SUPPORTED recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer30 = new StringBuffer();
                        stringbuffer30.append("NOT-SUPPORTED command from your IP Address not allowed.");
                        int k8 = 0;
                        String s33 = stringbuffer30.toString();
                        short word31 = (short)(4 + s33.length());
                        byte abyte32[] = new byte[word31];
                        byte abyte73[] = Utils.short2bytes((short)265);
                        abyte32[k8++] = abyte73[0];
                        abyte32[k8++] = abyte73[1];
                        abyte73 = Utils.short2bytes(word31);
                        abyte32[k8++] = abyte73[0];
                        abyte32[k8++] = abyte73[1];
                        abyte0 = s33.getBytes();
                        System.arraycopy(abyte0, 0, abyte32, k8, abyte0.length);
                        lTht.write(abyte32, 0, word31);
                        lTht.flush();
                    }
                    break;

                case 266: 
                    if(bDhb(ltht, LTht))
                    {
                        BDhb("REFATTBSET recieved from " + ltht.getInetAddress().getHostName());
                        StringBuffer stringbuffer31 = new StringBuffer();
                        if(word41 < 10)
                        {
                            stringbuffer31.append("Parameters length error.");
                        } else
                        {
                            byte abyte90[] = new byte[word41 - 4];
                            int k12 = Ltht.read(abyte90, 0, word41 - 4);
                            if(k12 == -1)
                            {
                                flag = false;
                                break;
                            }
                            short word42 = Utils.BDhb(abyte90, 4);
                            switch(Utils.BDhb(abyte90, 0))
                            {
                            case 1: // '\001'
                                if(word42 > LTht.BdHBlfZ().BDHbl() - 1)
                                {
                                    stringbuffer31.append("Invalid parameter value.It must be less than " + LTht.BdHBlfZ().BDHbl() + ".");
                                } else
                                {
                                    stringbuffer31.append("reflector.max.participants is set to " + word42);
                                    BDhb("reflector.max.participants is set to " + word42);
                                    LTht.BDhBLfZ(word42);
                                }
                                break;

                            case 2: // '\002'
                                if(word42 > LTht.bdHBlFz())
                                {
                                    stringbuffer31.append("Invalid parameter value.It must be equal to or less than reflector.max.participants(" + LTht.bdHBlFz() + ").");
                                } else
                                {
                                    stringbuffer31.append("reflector.max.senders is set to " + word42);
                                    BDhb("reflector.max.senders is set to " + word42);
                                    LTht.bdHBLfZ(word42);
                                }
                                break;

                            case 3: // '\003'
                                if(word42 > LTht.bdHBlFz())
                                {
                                    stringbuffer31.append("Invalid parameter value.It must be equal to or less than reflector.max.participants(" + LTht.bdHBlFz() + ").");
                                } else
                                {
                                    stringbuffer31.append("reflector.max.lurkers is set to " + word42);
                                    BDhb("reflector.max.lurkers is set to " + word42);
                                    LTht.bDhBLfZ(word42);
                                }
                                break;

                            default:
                                stringbuffer31.append("Invalid parameter code.");
                                break;
                            }
                        }
                        int l8 = 0;
                        String s34 = stringbuffer31.toString();
                        short word32 = (short)(4 + s34.length());
                        byte abyte33[] = new byte[word32];
                        byte abyte74[] = Utils.short2bytes((short)266);
                        abyte33[l8++] = abyte74[0];
                        abyte33[l8++] = abyte74[1];
                        abyte74 = Utils.short2bytes(word32);
                        abyte33[l8++] = abyte74[0];
                        abyte33[l8++] = abyte74[1];
                        abyte0 = s34.getBytes();
                        System.arraycopy(abyte0, 0, abyte33, l8, abyte0.length);
                        lTht.write(abyte33, 0, word32);
                        lTht.flush();
                    } else
                    {
                        BDhb("NOT-SUPPORTED recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer32 = new StringBuffer();
                        stringbuffer32.append("NOT-SUPPORTED command from your IP Address not allowed.");
                        int i9 = 0;
                        String s35 = stringbuffer32.toString();
                        short word33 = (short)(4 + s35.length());
                        byte abyte34[] = new byte[word33];
                        byte abyte75[] = Utils.short2bytes((short)266);
                        abyte34[i9++] = abyte75[0];
                        abyte34[i9++] = abyte75[1];
                        abyte75 = Utils.short2bytes(word33);
                        abyte34[i9++] = abyte75[0];
                        abyte34[i9++] = abyte75[1];
                        abyte0 = s35.getBytes();
                        System.arraycopy(abyte0, 0, abyte34, i9, abyte0.length);
                        lTht.write(abyte34, 0, word33);
                        lTht.flush();
                    }
                    break;

                case 267: 
                    if(bDhb(ltht, LTht))
                    {
                        StringBuffer stringbuffer33 = new StringBuffer();
                        if(word41 < 10)
                        {
                            stringbuffer33.append("Parameters length error.");
                            break;
                        }
                        byte abyte91[] = new byte[word41 - 4];
                        int l12 = Ltht.read(abyte91, 0, word41 - 4);
                        if(l12 == -1)
                        {
                            flag = false;
                            break;
                        }
                        int k13 = Utils.readShort(abyte91, 2);
                        Conference d8kh3hz1 = LTht.bdHbLfz(LTht.bdhBLfZ(k13));
                        switch(Utils.BDhb(abyte91, 0))
                        {
                        case 0: // '\0'
                            stringbuffer33.append(LTht.bDhblfZ());
                            break;

                        case 1: // '\001'
                            stringbuffer33.append((new Short(d8kh3hz1.BDHBlFzv())).toString());
                            break;

                        case 2: // '\002'
                            stringbuffer33.append((new Short(d8kh3hz1.BdhbLFzv())).toString());
                            break;

                        case 3: // '\003'
                            stringbuffer33.append((new Short(d8kh3hz1.BdhblFzv())).toString());
                            break;

                        case 4: // '\004'
                            stringbuffer33.append((new Integer(d8kh3hz1.bDHblFzv())).toString());
                            break;

                        case 5: // '\005'
                            stringbuffer33.append((new Integer(d8kh3hz1.bdHBlFzv())).toString());
                            break;

                        case 6: // '\006'
                            stringbuffer33.append((new Integer(d8kh3hz1.BDhblFzv())).toString());
                            break;

                        case 7: // '\007'
                            stringbuffer33.append((new Integer(d8kh3hz1.BdhBlFzv())).toString());
                            break;

                        case 8: // '\b'
                            stringbuffer33.append((new Short((short)d8kh3hz1.BdhBlfZv())).toString());
                            break;

                        case 9: // '\t'
                            if(d8kh3hz1.BdhblfZv())
                                stringbuffer33.append((new Short((short)1)).toString());
                            else
                                stringbuffer33.append((new Short((short)0)).toString());
                            break;

                        case 10: // '\n'
                            if(d8kh3hz1.bDhBLfZv())
                                stringbuffer33.append((new Short((short)1)).toString());
                            else
                                stringbuffer33.append((new Short((short)0)).toString());
                            break;

                        case 11: // '\013'
                            if(d8kh3hz1.bdhbLfZv())
                                stringbuffer33.append((new Short((short)1)).toString());
                            else
                                stringbuffer33.append((new Short((short)0)).toString());
                            break;

                        case 12: // '\f'
                            if(d8kh3hz1.BdhbLfZv())
                                stringbuffer33.append((new Short((short)1)).toString());
                            else
                                stringbuffer33.append((new Short((short)0)).toString());
                            break;

                        case 13: // '\r'
                            if(d8kh3hz1.bdhBLfZv())
                                stringbuffer33.append((new Short((short)1)).toString());
                            else
                                stringbuffer33.append((new Short((short)0)).toString());
                            break;

                        case 14: // '\016'
                            if(d8kh3hz1.BdHBLfzv())
                                stringbuffer33.append((new Short((short)1)).toString());
                            else
                                stringbuffer33.append((new Short((short)0)).toString());
                            break;

                        default:
                            stringbuffer33.append("Parameter error.");
                            break;
                        }
                        int j9 = 0;
                        String s36 = stringbuffer33.toString();
                        short word34 = (short)(4 + s36.length());
                        byte abyte35[] = new byte[word34];
                        byte abyte76[] = Utils.short2bytes((short)267);
                        abyte35[j9++] = abyte76[0];
                        abyte35[j9++] = abyte76[1];
                        abyte76 = Utils.short2bytes(word34);
                        abyte35[j9++] = abyte76[0];
                        abyte35[j9++] = abyte76[1];
                        abyte0 = s36.getBytes();
                        System.arraycopy(abyte0, 0, abyte35, j9, abyte0.length);
                        lTht.write(abyte35, 0, word34);
                        lTht.flush();
                    } else
                    {
                        BDhb("NOT-SUPPORTED recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer34 = new StringBuffer();
                        stringbuffer34.append("NOT-SUPPORTED command from your IP Address not allowed.");
                        int k9 = 0;
                        String s37 = stringbuffer34.toString();
                        short word35 = (short)(4 + s37.length());
                        byte abyte36[] = new byte[word35];
                        byte abyte77[] = Utils.short2bytes((short)267);
                        abyte36[k9++] = abyte77[0];
                        abyte36[k9++] = abyte77[1];
                        abyte77 = Utils.short2bytes(word35);
                        abyte36[k9++] = abyte77[0];
                        abyte36[k9++] = abyte77[1];
                        abyte0 = s37.getBytes();
                        System.arraycopy(abyte0, 0, abyte36, k9, abyte0.length);
                        lTht.write(abyte36, 0, word35);
                        lTht.flush();
                    }
                    break;

                case 268: 
                    if(bDhb(ltht, LTht))
                    {
                        BDhb("CONFATTBSET recieved from " + ltht.getInetAddress().getHostName());
                        StringBuffer stringbuffer35 = new StringBuffer();
                        if(word41 < 10)
                        {
                            stringbuffer35.append("Parameters length error.");
                        } else
                        {
                            byte abyte92[] = new byte[word41 - 4];
                            int i13 = Ltht.read(abyte92, 0, word41 - 4);
                            if(i13 == -1)
                            {
                                flag = false;
                                break;
                            }
                            short word43 = Utils.BDhb(abyte92, 4);
                            int l13 = Utils.readShort(abyte92, 2);
                            Conference d8kh3hz2 = LTht.bdHbLfz(LTht.bdhBLfZ(l13));
                            switch(Utils.BDhb(abyte92, 0))
                            {
                            case 1: // '\001'
                                if(word43 > LTht.bdHBlFz())
                                {
                                    stringbuffer35.append("Invalid parameter value.It must be equal to or less than reflector.max.participants(" + LTht.bdHBlFz() + ").");
                                } else
                                {
                                    stringbuffer35.append("conference.max.participants-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to " + word43);
                                    BDhb("conference.max.participants-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to " + word43);
                                    d8kh3hz2.BDHbLFZv(word43);
                                }
                                break;

                            case 2: // '\002'
                                short word44 = LTht.bDHBlFz() >= d8kh3hz2.BDHBlFzv() ? d8kh3hz2.BDHBlFzv() : LTht.bDHBlFz();
                                if(word43 > word44)
                                {
                                    stringbuffer35.append("Invalid parameter value.It must be equal to or less than ");
                                    if(LTht.bDHBlFz() < d8kh3hz2.BDHBlFzv())
                                    {
                                        stringbuffer35.append("reflector.max.senders");
                                    } else
                                    {
                                        stringbuffer35.append("conference.max.participants-");
                                        stringbuffer35.append(d8kh3hz2.BDHbLFzv());
                                    }
                                    stringbuffer35.append("(" + word44 + ").");
                                } else
                                {
                                    stringbuffer35.append("conference.max.senders-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to " + word43);
                                    BDhb("conference.max.senders-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to " + word43);
                                    d8kh3hz2.bdhBLFZv(word43);
                                }
                                break;

                            case 3: // '\003'
                                short word45 = LTht.bDHBLfz() >= d8kh3hz2.BDHBlFzv() ? d8kh3hz2.BDHBlFzv() : LTht.bDHBLfz();
                                if(word43 > word45)
                                {
                                    stringbuffer35.append("Invalid parameter value.It must be equal to or less than ");
                                    if(LTht.bDHBLfz() < d8kh3hz2.BDHBlFzv())
                                    {
                                        stringbuffer35.append("reflector.max.lurkers");
                                    } else
                                    {
                                        stringbuffer35.append("conference.max.participants-");
                                        stringbuffer35.append(d8kh3hz2.BDHbLFzv());
                                    }
                                    stringbuffer35.append("(" + word45 + ").");
                                } else
                                {
                                    stringbuffer35.append("conference.max.lurkers-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to " + word43);
                                    BDhb("conference.max.lurkers-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to " + word43);
                                    d8kh3hz2.bDhbLFZv(word43);
                                }
                                break;

                            case 4: // '\004'
                                if(word43 > 65535)
                                {
                                    stringbuffer35.append("Invalid parameter value.It must be equal to or less than 65535.");
                                    break;
                                }
                                if(word43 < d8kh3hz2.bdHBlFzv())
                                {
                                    stringbuffer35.append("Invalid parameter value.It must be equal to or more than conference.max.min.send-" + d8kh3hz2.BDHbLFzv() + "(" + d8kh3hz2.bdHBlFzv() + ").");
                                } else
                                {
                                    stringbuffer35.append("conference.max.max.send-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to " + word43);
                                    BDhb("conference.max.max.send-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to " + word43);
                                    d8kh3hz2.bdHbLFZv(word43);
                                }
                                break;

                            case 5: // '\005'
                                if(word43 > d8kh3hz2.bDHblFzv())
                                {
                                    stringbuffer35.append("Invalid parameter value.It must be equal to or less than conference.max.max.send-" + d8kh3hz2.BDHbLFzv() + "(" + d8kh3hz2.bDHblFzv() + ").");
                                } else
                                {
                                    stringbuffer35.append("conference.max.min.send-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to " + word43);
                                    BDhb("conference.max.min.send-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to " + word43);
                                    d8kh3hz2.bDHbLFZv(word43);
                                }
                                break;

                            case 6: // '\006'
                                if(word43 > 65535)
                                {
                                    stringbuffer35.append("Invalid parameter value.It must be equal to or less than 65535.");
                                    break;
                                }
                                if(word43 < d8kh3hz2.BdhBlFzv())
                                {
                                    stringbuffer35.append("Invalid parameter value.It must be equal to or more than conference.max.min.recv-" + d8kh3hz2.BDHbLFzv() + "(" + d8kh3hz2.BdhBlFzv() + ").");
                                } else
                                {
                                    stringbuffer35.append("conference.max.max.recv-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to " + word43);
                                    BDhb("conference.max.max.recv-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to " + word43);
                                    d8kh3hz2.BDhbLFZv(word43);
                                }
                                break;

                            case 7: // '\007'
                                if(word43 > d8kh3hz2.BDhblFzv())
                                {
                                    stringbuffer35.append("Invalid parameter value.It must be equal to or less than conference.max.max.recv-" + d8kh3hz2.BDHbLFzv() + "(" + d8kh3hz2.BDhblFzv() + ").");
                                } else
                                {
                                    stringbuffer35.append("conference.max.min.recv-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to " + word43);
                                    BDhb("conference.max.min.recv-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to " + word43);
                                    d8kh3hz2.BdHbLFZv(word43);
                                }
                                break;

                            case 8: // '\b'
                                if(word43 > 32767)
                                {
                                    stringbuffer35.append("Invalid parameter value.It must be equal to or less than 32767.");
                                } else
                                {
                                    stringbuffer35.append("conference.timelimit-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to " + word43);
                                    BDhb("conference.timelimit-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to " + word43);
                                    d8kh3hz2.bdhblfzV(word43);
                                }
                                break;

                            case 9: // '\t'
                                if(word43 == 0)
                                {
                                    stringbuffer35.append("conference.private-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to off.");
                                    BDhb("conference.private-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to off");
                                    d8kh3hz2.bdHBLFZv(false);
                                } else
                                {
                                    stringbuffer35.append("conference.private-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to on.");
                                    BDhb("conference.private-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to on");
                                    d8kh3hz2.bdHBLFZv(true);
                                }
                                break;

                            case 10: // '\n'
                                if(word43 == 0)
                                {
                                    stringbuffer35.append("conference.recvonly-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to off.");
                                    BDhb("conference.recvonly-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to off");
                                    d8kh3hz2.BdHBLFZv(false);
                                } else
                                {
                                    stringbuffer35.append("conference.recvonly-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to on.");
                                    BDhb("conference.recvonly-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to on");
                                    d8kh3hz2.BdHBLFZv(true);
                                }
                                break;

                            case 11: // '\013'
                                if(word43 == 0)
                                {
                                    stringbuffer35.append("conference.allowaudio-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to off.");
                                    BDhb("conference.allowaudio-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to off");
                                    d8kh3hz2.bdHBlFZv(false);
                                } else
                                {
                                    stringbuffer35.append("conference.allowaudio-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to on.");
                                    BDhb("conference.allowaudio-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to on");
                                    d8kh3hz2.bdHBlFZv(true);
                                }
                                break;

                            case 12: // '\f'
                                if(word43 == 0)
                                {
                                    stringbuffer35.append("conference.allowchat-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to off.");
                                    BDhb("conference.allowchat-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to off");
                                    d8kh3hz2.BdHBlFZv(false);
                                } else
                                {
                                    stringbuffer35.append("conference.allowchat-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to on.");
                                    BDhb("conference.allowchat-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to on");
                                    d8kh3hz2.BdHBlFZv(true);
                                }
                                break;

                            case 13: // '\r'
                                if(word43 == 0)
                                {
                                    stringbuffer35.append("conference.nogeek-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to off.");
                                    BDhb("conference.nogeek-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to off");
                                    d8kh3hz2.bDhBLFZv(false);
                                } else
                                {
                                    stringbuffer35.append("conference.nogeek-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to on.");
                                    BDhb("conference.nogeek-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to on");
                                    d8kh3hz2.bDhBLFZv(true);
                                }
                                break;

                            case 14: // '\016'
                                if(word43 == 0)
                                {
                                    stringbuffer35.append("conference.whitepine.kanji-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to off.");
                                    BDhb("conference.whitepine.kanji-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to off");
                                    d8kh3hz2.bdhbLFZv(false);
                                } else
                                {
                                    stringbuffer35.append("conference.whitepine.kanji-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to on.");
                                    BDhb("conference.whitepine.kanji-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is set to on");
                                    d8kh3hz2.bdhbLFZv(true);
                                }
                                break;

                            case 15: // '\017'
                                if(d8kh3hz2.bdhblFZv())
                                {
                                    stringbuffer35.append("conference.whitepine.encoded.password.string-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is cleared");
                                    BDhb("conference.whitepine.encoded.password.string-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is cleared");
                                    d8kh3hz2.bDHblfzv();
                                } else
                                {
                                    stringbuffer35.append("conference.whitepine.encoded.password.string-" + (new Integer(d8kh3hz2.BDHbLFzv())).toString() + " is not set");
                                }
                                break;

                            default:
                                stringbuffer35.append("Invalid parameter code.");
                                break;
                            }
                        }
                        int l9 = 0;
                        String s38 = stringbuffer35.toString();
                        short word36 = (short)(4 + s38.length());
                        byte abyte37[] = new byte[word36];
                        byte abyte78[] = Utils.short2bytes((short)268);
                        abyte37[l9++] = abyte78[0];
                        abyte37[l9++] = abyte78[1];
                        abyte78 = Utils.short2bytes(word36);
                        abyte37[l9++] = abyte78[0];
                        abyte37[l9++] = abyte78[1];
                        abyte0 = s38.getBytes();
                        System.arraycopy(abyte0, 0, abyte37, l9, abyte0.length);
                        lTht.write(abyte37, 0, word36);
                        lTht.flush();
                    } else
                    {
                        BDhb("NOT-SUPPORTED recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer36 = new StringBuffer();
                        stringbuffer36.append("NOT-SUPPORTED command from your IP Address not allowed.");
                        int i10 = 0;
                        String s39 = stringbuffer36.toString();
                        short word37 = (short)(4 + s39.length());
                        byte abyte38[] = new byte[word37];
                        byte abyte79[] = Utils.short2bytes((short)268);
                        abyte38[i10++] = abyte79[0];
                        abyte38[i10++] = abyte79[1];
                        abyte79 = Utils.short2bytes(word37);
                        abyte38[i10++] = abyte79[0];
                        abyte38[i10++] = abyte79[1];
                        abyte0 = s39.getBytes();
                        System.arraycopy(abyte0, 0, abyte38, i10, abyte0.length);
                        lTht.write(abyte38, 0, word37);
                        lTht.flush();
                    }
                    break;

                case 5: // '\005'
                    if(bDhb(ltht, LTht))
                    {
                        short word38 = (short)(4 + "Not Yet Supported.".length());
                        byte abyte39[] = new byte[word38];
                        int j10 = 0;
                        abyte39[j10++] = 0;
                        abyte39[j10++] = abyte0[1];
                        abyte39[j10++] = 0;
                        abyte39[j10++] = (byte)word38;
                        abyte0 = "Not Yet Supported.".getBytes();
                        System.arraycopy(abyte0, 0, abyte39, j10, abyte0.length);
                        lTht.write(abyte39, 0, word38);
                        lTht.flush();
                    } else
                    {
                        BDhb("NOT-SUPPORTED recieved from " + ltht.getInetAddress().getHostName() + " but denied");
                        StringBuffer stringbuffer37 = new StringBuffer();
                        stringbuffer37.append("NOT-SUPPORTED command from your IP Address not allowed.\n");
                        int k10 = 0;
                        String s40 = stringbuffer37.toString();
                        short word39 = (short)(4 + s40.length());
                        byte abyte40[] = new byte[word39];
                        byte abyte80[] = Utils.short2bytes((short)5);
                        abyte40[k10++] = abyte80[0];
                        abyte40[k10++] = abyte80[1];
                        abyte80 = Utils.short2bytes(word39);
                        abyte40[k10++] = abyte80[0];
                        abyte40[k10++] = abyte80[1];
                        abyte0 = s40.getBytes();
                        System.arraycopy(abyte0, 0, abyte40, k10, abyte0.length);
                        lTht.write(abyte40, 0, word39);
                        lTht.flush();
                    }
                    break;

                case 269: 
                    if(!bDhb(ltht, LTht))
                        break;
                    BDhb("LOGGER recieved from " + ltht.getInetAddress().getHostName());
                    StringBuffer stringbuffer38 = new StringBuffer();
                    if(word41 < 8)
                    {
                        stringbuffer38.append("Parameters length error.");
                        break;
                    }
                    byte abyte93[] = new byte[word41 - 4];
                    int j13 = Ltht.read(abyte93, 0, word41 - 4);
                    if(j13 == -1)
                        flag = false;
                    else
                        LTht.getLogger().BDHb(new String(ltht.getInetAddress().getHostName() + "+" + (Utils.readShort(abyte93, 0) * 0x10000 + Utils.readShort(abyte93, 2))), lTht);
                    break;

                default:
                    short word40 = (short)(4 + "Unknown Command.".length());
                    byte abyte41[] = new byte[word40];
                    int l10 = 0;
                    abyte41[l10++] = 0;
                    abyte41[l10++] = abyte0[1];
                    abyte41[l10++] = 0;
                    abyte41[l10++] = (byte)word40;
                    abyte0 = "Unknown Command.".getBytes();
                    System.arraycopy(abyte0, 0, abyte41, l10, abyte0.length);
                    lTht.write(abyte41, 0, word40);
                    lTht.flush();
                    break;
                }
            }
        }
        catch(IOException _ex) { }
        finally
        {
            BDhb("Refmon connection closed from " + ltht.getInetAddress().getHostName());
            LTht.getLogger().bDhb(new String(ltht.getInetAddress().getHostName() + "+" + ltht.getPort()));
            try
            {
                ltht.close();
            }
            catch(IOException ioexception)
            {
                BDhb(ioexception.toString());
            }
        }
    }

    private Socket ltht;
    private DataInputStream Ltht;
    private DataOutputStream lTht;
    private ConfigLoader LTht;
    private Conference ltHt[];
    private MainReceiver LtHt;
}

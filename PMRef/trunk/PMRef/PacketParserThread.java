// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.net.*;
import java.util.*;

// Referenced classes of package PMRef:
//            dlkhlhz, d0khlhz, dl0hlhz, d21hlhz, 
//            dlk7lhz, d8kh3hz, Constants, d2k49hz, 
//            dl230hz, d6khl0z, d87hl2z, dlk3l7z, 
//            d0k2l6z, dl01l9z, d210l8z, dlkh89z, 
//            d4kh98z, d2khlh9, dlk9lh4

class PacketParserThread extends Thread
    implements Constants
{

    PacketParserThread(ConfigLoader d2khlh9_1, MainReceiver d87hl2z1, DatagramPacket datagrampacket)
    {
        config = d2khlh9_1;
        lTHt = d87hl2z1;
        Ltht = d2khlh9_1.BdHbLfz();
        datagramPacket = datagrampacket;
        setName("Datagram");
    }

    void bdhb(String s, InetAddress inetaddress, InetAddress inetaddress1, int i)
    {
        byte abyte1[] = s.getBytes();
        int j = abyte1.length;
        byte abyte0[] = new byte[j + 3];
        System.arraycopy(abyte1, 0, abyte0, 3, j);
        abyte1 = Utils.ishort2Bytes(j);
        abyte0[2] = abyte1[1];
        String s1 = new String(abyte0);
        DisconnectPacket dl230hz1 = new DisconnectPacket(abyte0);
        dl230hz1.setSocket(lTHt.bDHb());
        try
        {
            dl230hz1.bdhb(inetaddress, inetaddress1, lTHt.BDHb().getAddress(), i);
        }
        catch(Exception _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("DataSocket Send Error");
            log(stringbuffer.toString());
        }
    }

    void Bdhb(String s, d0k2l6z d0k2l6z1, String s1, String s2)
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
        d0k2l6z1.bdhbL(abyte0, 1, s2, 21762);
    }

    void bDhb(int i, byte abyte0[], int j)
    {
        boolean flag = false;
        Object obj = null;
        InetAddress inetaddress = null;
        try
        {
            if(abyte0[1] == 1)
            {
                flag = true;
                String s = Utils.convertToIPform(abyte0, 4);
                inetaddress = InetAddress.getByName(s);
            }
        }
        catch(UnknownHostException _ex) { }
        short word0 = 0;
        byte abyte3[] = null;
        byte abyte4[] = null;
        byte abyte2[] = new byte[2];
        abyte2[0] = abyte0[2];
        abyte2[1] = abyte0[3];
        if(j == 1 || j == 2)
        {
            if(j == 1)
            {
                word0 = Utils.BDhb(abyte0, 26);
                if(word0 > 0)
                    abyte3 = Ltht[0].bDhblfzv(abyte0);
            }
            abyte4 = new byte[abyte0.length];
            System.arraycopy(abyte0, 0, abyte4, 0, abyte0.length);
            System.arraycopy(Utils.short2bytes((short)613), 0, abyte4, 22, 2);
        }
        for(int k = 0; k < Ltht.length; k++)
            if(Ltht[k].BDHbLFzv() != i)
            {
                if(j == 1 || j == 2)
                {
                    byte abyte1[] = Utils.short2bytes((short)Ltht[k].BDHbLFzv());
                    if(word0 > 0)
                    {
                        abyte3[2] = abyte1[0];
                        abyte3[3] = abyte1[1];
                    } else
                    {
                        abyte0[2] = abyte1[0];
                        abyte0[3] = abyte1[1];
                    }
                    abyte4[2] = abyte1[0];
                    abyte4[3] = abyte1[1];
                }
                for(Enumeration enumeration = Ltht[k].BdHBLFzv().elements(); enumeration.hasMoreElements();)
                    try
                    {
                        ClientInfo dlk3l7z1 = (ClientInfo)enumeration.nextElement();
                        if(j == 1 && word0 > 0)
                        {
                            Ltht[k].BDhblfzv(abyte0, abyte3, dlk3l7z1);
                            byte byte0 = abyte3[52];
                            if((abyte3[60] & 0x10) == 16)
                            {
                                abyte3[52] = 0;
                                abyte3[63] = 0;
                            }
                            dlk3l7z1.bDhblfZ(abyte3, j);
                            abyte3[52] = byte0;
                        } else
                        if(flag)
                        {
                            if(dlk3l7z1.bdhBlFz().equals(inetaddress))
                                dlk3l7z1.bDhblfZ(abyte0, j);
                        } else
                        {
                            dlk3l7z1.bDhblfZ(abyte0, j);
                        }
                    }
                    catch(NullPointerException _ex) { }

                switch(j)
                {
                case 1: // '\001'
                case 2: // '\002'
                    Ltht[k].BDhBlfzv(abyte4);
                    break;

                case 3: // '\003'
                case 23: // '\027'
                    Vector vector = new Vector();
                    for(Enumeration enumeration1 = Ltht[k].BDHBLFzv().elements(); enumeration1.hasMoreElements();)
                    {
                        dl01l9z dl01l9z1 = (dl01l9z)enumeration1.nextElement();
                        if(!vector.contains(dl01l9z1.bDhbLf()))
                            vector.add(dl01l9z1.bDhbLf());
                    }

                    Ltht[k].BdHBlfzv(abyte0, vector);
                    break;

                case 4: // '\004'
                    Vector vector1 = new Vector();
                    for(Enumeration enumeration2 = Ltht[k].BDHBLFzv().elements(); enumeration2.hasMoreElements();)
                    {
                        dl01l9z dl01l9z2 = (dl01l9z)enumeration2.nextElement();
                        if(!vector1.contains(dl01l9z2.bDhbLf()))
                            vector1.add(dl01l9z2.bDhbLf());
                    }

                    Ltht[k].BdHBlfzv(abyte0, vector1);
                    break;

                default:
                    Ltht[k].BDhBlfzv(abyte0);
                    break;
                }
            }

        abyte0[2] = abyte2[0];
        abyte0[3] = abyte2[1];
    }

    void BDhb(Conference d8kh3hz1, byte abyte0[], int i)
    {
        abyte0[22] = 0;
        i -= 19;
        byte abyte1[] = null;
        short word0 = 0;
        if(i == 1)
        {
            word0 = Utils.BDhb(abyte0, 26);
            if(word0 > 0)
                abyte1 = d8kh3hz1.bDhblfzv(abyte0);
        }
        for(Enumeration enumeration = d8kh3hz1.BdHBLFzv().elements(); enumeration.hasMoreElements();)
            try
            {
                ClientInfo dlk3l7z1 = (ClientInfo)enumeration.nextElement();
                switch(i)
                {
                case 1: // '\001'
                    if(word0 > 0)
                    {
                        d8kh3hz1.BDhblfzv(abyte0, abyte1, dlk3l7z1);
                        dlk3l7z1.bDhblfZ(abyte1, i);
                    } else
                    {
                        dlk3l7z1.bDhblfZ(abyte0, i);
                    }
                    break;

                default:
                    dlk3l7z1.bDhblfZ(abyte0, i);
                    break;
                }
            }
            catch(NullPointerException _ex) { }

    }

    void bdHb(int i, byte abyte0[], int j)
    {
        byte abyte2[] = abyte0;
        byte abyte3[] = new byte[2];
        abyte3[0] = abyte0[2];
        abyte3[1] = abyte0[3];
        short word0 = 0;
        for(Enumeration enumeration = config.bDhbLFz().elements(); enumeration.hasMoreElements();)
        {
            Conference d8kh3hz1 = (Conference)enumeration.nextElement();
            if(d8kh3hz1.BDHbLFzv() != i)
            {
                if(j == 1)
                {
                    byte abyte1[] = Utils.ishort2Bytes(d8kh3hz1.BDHbLFzv());
                    abyte0[2] = abyte1[0];
                    abyte0[3] = abyte1[1];
                    word0 = Utils.BDhb(abyte0, 26);
                    if(word0 > 1)
                    {
                        short word1 = Utils.BDhb(abyte0, 24);
                        int i1 = word1 - 12 * (word0 - 1);
                        abyte2 = new byte[i1];
                        System.arraycopy(abyte0, 0, abyte2, 0, 56);
                        System.arraycopy(Utils.short2bytes((short)1), 0, abyte2, 26, 2);
                        System.arraycopy(Utils.short2bytes((short)i1), 0, abyte2, 24, 2);
                        System.arraycopy(abyte0, 56 + 12 * word0, abyte2, 68, i1 - 68);
                    }
                }
                for(Enumeration enumeration1 = d8kh3hz1.BdHBLFzv().elements(); enumeration1.hasMoreElements();)
                    try
                    {
                        ClientInfo dlk3l7z1 = (ClientInfo)enumeration1.nextElement();
                        if(j == 1)
                        {
                            for(int k = 0; k < word0; k++)
                            {
                                int l = 56 + 12 * k;
                                if(!Utils.compare(dlk3l7z1.BdhBlFz(), 0, abyte0, l, 4))
                                    continue;
                                System.arraycopy(abyte0, l, abyte2, 56, 12);
                                System.arraycopy(dlk3l7z1.BDHBlfz(), 0, abyte2, 56, 4);
                                if((abyte2[60] & 0x10) == 16)
                                {
                                    abyte2[60] &= 0xef;
                                    abyte2[52] = -56;
                                    abyte2[63] = 1;
                                }
                                break;
                            }

                        }
                        dlk3l7z1.bDhblfZ(abyte2, j);
                    }
                    catch(NullPointerException _ex) { }

            }
        }

        abyte0[2] = abyte3[0];
        abyte0[3] = abyte3[1];
    }

    int determineDataType(byte abyte0[])
    {
        short dataType = Utils.BDhb(abyte0, 22);
        byte byte0;
        switch(dataType)
        {
        case CUOC: // 'e' -- opencontinue packet
            short msgType = Utils.BDhb(abyte0, 20);
            if(msgType == CUMSGOPEN)
            {
                byte0 = DATAOC;
                break;
            }
            if(msgType == CUMSGCLOSE)
                byte0 = DATAOCCLOSE;
            else
                byte0 = -1;
            break;

        case CUSVIDEO: // '\001' -- small video
        case CUBVIDEO: // '\002' -- big video
            byte0 = DATAVIDEO;
            break;

        case CUWPVIDEO2: 
        case CUWPVIDEO1: //
            byte0 = DATAWPVIDEO;
            break;

        case 3: // '\003' //audio
            byte0 = DATAAUDIO;
            break;

        case 110: // 'n'
            byte0 = DATARATECONTROL; //data for rate control
            break;

        case 111: // 'o'
            byte0 = DATARATEREPLY; //data for rate control
            break;

        case 256: 
            byte0 = DATAAUXCONTROL; //aux control
            break;

        case 257: 
            long l = Utils.BdHb(abyte0, 38); //aux data
            if(l == 0x4355746bL)
                byte0 = DATATALK;
            else
                byte0 = DATAAUXDATA;
            break;

        case 21760: 
            byte0 = DATAPMREFOC;
            break;

        case 21766: 
            byte0 = DATAPMREFOCSEARCH;
            break;

        case 21761: 
            byte0 = DATAPMREFDATA;
            break;

        case 21762: 
            byte0 = DATAPMREFDENY;
            break;

        case 21763: 
            byte0 = DATAPMREFMSG;
            break;

        case 21764: 
            byte0 = DATAPMREFCONTROLADD;
            break;

        case 21765: 
            byte0 = DATAPMREFCONTROLREMOVE;
            break;

        case 204: 
            byte0 = DATA204;
            break;

        case 613: 
            short word2 = Utils.BDhb(abyte0, 20);
            if(word2 == 1)
            {
                byte0 = DATAMONITOROC;
                break;
            }
            if(word2 == 6)
                byte0 = DATAMONITOROCCLOSE;
            else
                byte0 = DATAUNKNOWN;
            break;

        case 104: // 'h'
            byte0 = DATADISCONNECT;
            break;

        default:
            byte0 = DATAUNKNOWN;
            break;
        }
        return byte0;
    }

    void log(String s)
    {
        config.getLogger().logStr(s);
        System.out.println(s);
    }

    void BDHb(String s, InetAddress inetaddress)
    {
        try
        {
            d0khlhz d0khlhz1 = config.BdHBlfZ().BdHbl(inetaddress);
            if(d0khlhz1 != null)
            {
                ClientInfo dlk3l7z1 = (ClientInfo) config.bDHbLFz().get(d0khlhz1);
                if(dlk3l7z1 != null)
                {
                    for(int i = 0; i < 3; i++)
                        dlk3l7z1.BdHblfz(s.getBytes().length + 26 + 4);

                    dlk3l7z1.BDhBLFz(s);
                }
            }
        }
        catch(NullPointerException _ex) { }
        catch(Exception _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("DataSocket Send Error");
            log(stringbuffer.toString());
        }
    }

    public void run()
    {
        buffer = datagramPacket.getData();
        int cliPort = datagramPacket.getPort();
        packetLength = datagramPacket.getLength();
        InetAddress cliInetaddress = datagramPacket.getAddress();
        dataType = determineDataType(buffer);
        int refPort = Utils.readShort(buffer, 10);
        String sourceAddress = Utils.convertToIPform(buffer, 12);
        InetAddress refInetAddress;
        try
        {
            refInetAddress = InetAddress.getByName(sourceAddress);
        }
        catch(UnknownHostException _ex)
        {
            refInetAddress = null;
        }
        d0khlhz packetAddressInfo = new d0khlhz( cliInetaddress, cliPort, refInetAddress, refPort);
        byte abyte1[] = datagramPacket.getAddress().getAddress();
        String destIP = Utils.BDHb(abyte1);
        boolean isPrivateMessage = buffer[1] == 1;
        boolean isClientSeen = false;
        boolean flag1 = false;
        Object obj = null;
        ClientInfo clientInfo = (ClientInfo) config.bDHbLFz().get(packetAddressInfo);
        if(clientInfo != null)
        {
            isClientSeen = true;
            try
            {
                clientInfo.parseClientCount(packetLength);
            }
            catch(NullPointerException _ex)
            {
                config.getLogger().BdhB("Datagram : run : NullPointerException : 1", (short)3);
            }
        }
label0:
        switch(dataType)
        {
        case DATAOC: // '\001' 
            String username = Utils.readStr(buffer, 32);
            int i = config.bdhBLfZ(Utils.readShort(buffer, 2));
            dlkh89z dlkh89z1 = config.bdhBLFz().Bdhb(packetAddressInfo);
            if(dlkh89z1 != null)
            {
                StringBuffer stringbuffer = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + config.BdhBLFz());
                stringbuffer.append("   (" + dlkh89z1.bdHb() + " seconds)");
                bdhb(stringbuffer.toString(),  cliInetaddress,  cliInetaddress, datagramPacket.getPort());
                break;
            }
            if(i == -1)
            {
                StringBuffer stringbuffer1 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + config.bDhbLfz());
                stringbuffer1.append("  (No." + Utils.readShort(buffer, 2) + ")");
                bdhb(stringbuffer1.toString(),  cliInetaddress,  cliInetaddress, datagramPacket.getPort());
                dlkh89z dlkh89z2 = new dlkh89z(packetAddressInfo, config.BDhbLfz(), 1);
                config.bdhBLFz().bdhb(dlkh89z2);
                stringbuffer1 = new StringBuffer();
                stringbuffer1.append("Client " + username + " ");
                stringbuffer1.append("Request non-existant conference(" + Utils.readShort(buffer, 2) + ") from " + destIP + ":" + sourceAddress);
                log(stringbuffer1.toString());
                break;
            }
            Conference d8kh3hz1 = Ltht[i];
            if(!refInetAddress.equals( cliInetaddress))
            {
                if(d8kh3hz1.BDHBLfzv().equals("Never") || d8kh3hz1.BDHBLfzv().equals("PrivateOnly") && !config.bDhBLFz().ktdblhBdhb(refInetAddress))
                {
                    StringBuffer stringbuffer2 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\rThe IP Addresses of IP Header and CU-SeeMe Header are different. \r\r");
                    stringbuffer2.append("IP Header : " + destIP);
                    stringbuffer2.append(", CU Header : " + sourceAddress);
                    bdhb(stringbuffer2.toString(),  cliInetaddress,  cliInetaddress, datagramPacket.getPort());
                    stringbuffer2 = new StringBuffer();
                    stringbuffer2.append("Client " + username + " ");
                    stringbuffer2.append("Address mismatch between IP and CU  " + destIP + ", " + sourceAddress);
                    stringbuffer2.append(", Conference " + d8kh3hz1.BDHbLFzv());
                    log(stringbuffer2.toString());
                    break;
                }
                flag1 = true;
                System.arraycopy(abyte1, 0, buffer, 12, 4);
            }
            if(!config.bdHBlfz().ktdblhbDHb() && !config.bdHBlfz().ktdblhBdhb( cliInetaddress))
            {
                StringBuffer stringbuffer3 = new StringBuffer(config.bdHBlfz().Bdhb(destIP));
                bdhb(stringbuffer3.toString(),  cliInetaddress,  cliInetaddress, datagramPacket.getPort());
                stringbuffer3 = new StringBuffer();
                stringbuffer3.append("Client " + username + " ");
                stringbuffer3.append("ADMIT DENIED from " + destIP + ":" + sourceAddress + " (Reflector list)");
                log(stringbuffer3.toString());
                break;
            }
            if(!d8kh3hz1.BdHbLfzv().ktdblhbDHb() && !d8kh3hz1.BdHbLfzv().ktdblhBdhb( cliInetaddress))
            {
                StringBuffer stringbuffer4 = new StringBuffer(d8kh3hz1.BdHbLfzv().Bdhb(destIP));
                bdhb(stringbuffer4.toString(),  cliInetaddress,  cliInetaddress, datagramPacket.getPort());
                stringbuffer4 = new StringBuffer();
                stringbuffer4.append("Client " + username + " ");
                stringbuffer4.append("ADMIT DENIED from " + destIP + ":" + sourceAddress + " (Conference list)");
                log(stringbuffer4.toString());
                break;
            }
            if(config.BDHbLfz().ktdblhBdhb( cliInetaddress))
            {
                StringBuffer stringbuffer5 = new StringBuffer(config.BDHbLfz().Bdhb( cliInetaddress));
                bdhb(stringbuffer5.toString(),  cliInetaddress,  cliInetaddress, datagramPacket.getPort());
                stringbuffer5 = new StringBuffer();
                stringbuffer5.append("Client " + username + " ");
                stringbuffer5.append("DENIED from " + destIP + ":" + sourceAddress + " (Reflector list)");
                log(stringbuffer5.toString());
                break;
            }
            if(d8kh3hz1.bdHBLfzv().ktdblhBdhb( cliInetaddress))
            {
                StringBuffer stringbuffer6 = new StringBuffer(d8kh3hz1.bdHBLfzv().Bdhb( cliInetaddress));
                bdhb(stringbuffer6.toString(),  cliInetaddress,  cliInetaddress, datagramPacket.getPort());
                stringbuffer6 = new StringBuffer();
                stringbuffer6.append("Client " + username + " ");
                stringbuffer6.append("DENIED from " + destIP + ":" + sourceAddress + " (Conference list)");
                log(stringbuffer6.toString());
                break;
            }
            if(d8kh3hz1.bDhbLfZv() && !d8kh3hz1.bDHbLfZv())
                buffer[54] |= 0x80;
            else
                buffer[54] &= 0x7f;
            if(isClientSeen)
            {
                try
                {
                    clientInfo.bdhBLFz(buffer);
                    System.arraycopy(clientInfo.BdhBlFz(), 0, buffer, 12, 4);
                    d8kh3hz1.bDhBlfzv(buffer, dataType, packetAddressInfo);
                    bdHb(d8kh3hz1.BDHbLFzv(), buffer, dataType);
                    if(d8kh3hz1.bDHbLfZv())
                        bDhb(d8kh3hz1.BDHbLFzv(), buffer, dataType);
                    d8kh3hz1.BDhBlfzv(buffer);
                    if(d8kh3hz1.bDHBLfZv())
                        d8kh3hz1.bdhBlFZv(buffer, dataType, packetAddressInfo);
                }
                catch(NullPointerException _ex)
                {
                    config.getLogger().BdhB("Datagram : run : NullPointerException : 2", (short)3);
                }
                break;
            }
            synchronized(d8kh3hz1)
            {
                try
                {
                    clientInfo = new ClientInfo(d8kh3hz1, packetAddressInfo, username, buffer, lTHt);
                    if(clientInfo.bdhBlFz() == null)
                    {
                        StringBuffer stringbuffer7 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + config.BdhbLFz() + "\r\r(" + config.bdhbLFz() + " addresses)");
                        clientInfo.BDHblfz(stringbuffer7.toString());
                        stringbuffer7 = new StringBuffer();
                        stringbuffer7.append("Client " + username + " ");
                        stringbuffer7.append("DENIED from " + destIP + ":");
                        stringbuffer7.append(sourceAddress);
                        stringbuffer7.append(" because of reflector.virtual.address");
                        log(stringbuffer7.toString());
                        return;
                    }
                    clientInfo.BDHblfZ(flag1);
                    if(clientInfo.BdHbLFz() && clientInfo.BDHBLfz() && d8kh3hz1.bdhblfzv(clientInfo))
                    {
                        clientInfo.parseClientCount(packetLength);
                        StringBuffer stringbuffer8 = new StringBuffer();
                        stringbuffer8.append("Client " + username + " ");
                        stringbuffer8.append("logs in from " + destIP);
                        stringbuffer8.append(":" + sourceAddress);
                        stringbuffer8.append("(" + clientInfo.bdhBlFz().getHostAddress() + ")");
                        stringbuffer8.append(", Conference " + d8kh3hz1.BDHbLFzv());
                        stringbuffer8.append(", \"" + clientInfo.bDhBlFz() + "\"");
                        log(stringbuffer8.toString());
                        stringbuffer8 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + d8kh3hz1.bDHbLFzv());
                        for(int j4 = 0; j4 < 3; j4++)
                            clientInfo.BdHblfz(stringbuffer8.toString().getBytes().length + 26 + 4);

                        clientInfo.BDhBLFz(stringbuffer8.toString());
                        System.arraycopy(clientInfo.BdhBlFz(), 0, buffer, 12, 4);
                        d8kh3hz1.bDhBlfzv(buffer, dataType, packetAddressInfo);
                        bdHb(d8kh3hz1.BDHbLFzv(), buffer, dataType);
                        if(d8kh3hz1.bDHbLfZv())
                            bDhb(d8kh3hz1.BDHbLFzv(), buffer, dataType);
                        d8kh3hz1.BDhBlfzv(buffer);
                        if(d8kh3hz1.bDHBLfZv())
                            d8kh3hz1.bdhBlFZv(buffer, dataType, packetAddressInfo);
                    } else
                    {
                        clientInfo.BdhBlfz();
                    }
                }
                catch(NullPointerException _ex)
                {
                    config.getLogger().BdhB("Datagram : run : NullPointerException : 3", (short)3);
                }
            }
            break;

        case DATA204: // '\023'
            break;

        case DATAOCCLOSE: // '\002'
            try
            {
                if(!isClientSeen)
                    break;
                Conference d8kh3hz2 = clientInfo.BdHBlfz();
                String s17 = clientInfo.BdHBLfz();
                System.arraycopy(clientInfo.BdhBlFz(), 0, buffer, 12, 4);
                if(d8kh3hz2.bDHblFZv(clientInfo))
                {
                    StringBuffer stringbuffer9 = new StringBuffer();
                    stringbuffer9.append("Client " + s17 + " ");
                    stringbuffer9.append("logs out from " + destIP);
                    stringbuffer9.append(":" + sourceAddress);
                    stringbuffer9.append("(" + clientInfo.bdhBlFz().getHostAddress() + ")");
                    stringbuffer9.append(", Conference " + d8kh3hz2.BDHbLFzv());
                    log(stringbuffer9.toString());
                }
                short word4 = Utils.BDhb(buffer, 26);
                short word5 = Utils.BDhb(buffer, 24);
                int j3 = word5 - 12 * word4;
                byte abyte8[] = new byte[j3];
                System.arraycopy(buffer, 0, abyte8, 0, 56);
                System.arraycopy(Utils.short2bytes((short)0), 0, abyte8, 26, 2);
                System.arraycopy(Utils.short2bytes((short)j3), 0, abyte8, 24, 2);
                System.arraycopy(buffer, 56 + 12 * word4, abyte8, 56, j3 - 56);
                for(int k2 = 0; k2 < 3; k2++)
                {
                    d8kh3hz2.BDHblfzv(abyte8, dataType, packetAddressInfo);
                    bdHb(d8kh3hz2.BDHbLFzv(), abyte8, dataType);
                    if(d8kh3hz2.bDHbLfZv())
                        bDhb(d8kh3hz2.BDHbLFzv(), abyte8, dataType);
                    d8kh3hz2.BDhBlfzv(abyte8);
                }

            }
            catch(NullPointerException _ex)
            {
                config.getLogger().BdhB("Datagram : run : NullPointerException : 4", (short)3);
            }
            break;

        case 3: // '\003'       //video
        case 23: // '\027'
            try
            {
                if(!isClientSeen)
                    break;
                System.arraycopy(clientInfo.BdhBlFz(), 0, buffer, 12, 4);
                Conference conference = clientInfo.BdHBlfz();
                if(isPrivateMessage)
                {
                    conference.forward(buffer, dataType, packetAddressInfo);
                    break;
                }
                conference.bDhbLfzv(buffer, dataType, packetAddressInfo);
                bdHb(conference.BDHbLFzv(), buffer, dataType);
                if(conference.bDHbLfZv())
                    bDhb(conference.BDHbLFzv(), buffer, dataType);
                if(conference.bdHBLfZv())
                {
                    conference.BDhBlfzv(buffer);
                } else
                {
                    Vector vector = new Vector();
                    for(Enumeration enumeration = conference.BDHBLFzv().elements(); enumeration.hasMoreElements();)
                    {
                        dl01l9z dl01l9z1 = (dl01l9z)enumeration.nextElement();
                        if(!dl01l9z1.bDhbLf().equals( cliInetaddress) && dl01l9z1.bDHBlf().containsKey(clientInfo.bdhBlFz()) && !vector.contains(dl01l9z1.bDhbLf()))
                            vector.add(dl01l9z1.bDhbLf());
                    }

                    conference.BdHBlfzv(buffer, vector);
                }
                if(conference.bDHBLfZv())
                    conference.bdhBlFZv(buffer, dataType, packetAddressInfo);
            }
            catch(NullPointerException _ex)
            {
                config.getLogger().BdhB("Datagram : run : NullPointerException : 5", (short)3);
            }
            break;

        case DATAAUDIO: // '\004'
            try
            {
                if(!isClientSeen)
                    break;
                System.arraycopy(clientInfo.BdhBlFz(), 0, buffer, 12, 4);
                Conference d8kh3hz4 = clientInfo.BdHBlfz();
                if(isPrivateMessage)
                {
                    d8kh3hz4.forward(buffer, dataType, packetAddressInfo);
                    break;
                }
                d8kh3hz4.bdhBlfzv(buffer, dataType, packetAddressInfo);
                bdHb(d8kh3hz4.BDHbLFzv(), buffer, dataType);
                if(d8kh3hz4.bDHbLfZv())
                    bDhb(d8kh3hz4.BDHbLFzv(), buffer, dataType);
                if(d8kh3hz4.bdHBLfZv())
                {
                    d8kh3hz4.BDhBlfzv(buffer);
                } else
                {
                    Vector vector1 = new Vector();
                    for(Enumeration enumeration1 = d8kh3hz4.BDHBLFzv().elements(); enumeration1.hasMoreElements();)
                    {
                        dl01l9z dl01l9z2 = (dl01l9z)enumeration1.nextElement();
                        if(!dl01l9z2.bDhbLf().equals( cliInetaddress) && dl01l9z2.bdHBlf().containsKey(clientInfo.bdhBlFz()) && !vector1.contains(dl01l9z2.bDhbLf()))
                            vector1.add(dl01l9z2.bDhbLf());
                    }

                    d8kh3hz4.BdHBlfzv(buffer, vector1);
                }
                if(d8kh3hz4.bDHBLfZv())
                    d8kh3hz4.bdhBlFZv(buffer, dataType, packetAddressInfo);
            }
            catch(NullPointerException _ex)
            {
                config.getLogger().BdhB("Datagram : run : NullPointerException : 6", (short)3);
            }
            break;

        case 9: // '\t'
            try
            {
                if(!isClientSeen)
                    break;
                System.arraycopy(clientInfo.BdhBlFz(), 0, buffer, 12, 4);
                Conference d8kh3hz5 = clientInfo.BdHBlfz();
                String s18 = clientInfo.BdHBLfz();
                if(Utils.readStr(buffer, 26 + buffer[27]).equals(s18))
                {
                    if(isPrivateMessage)
                    {
                        d8kh3hz5.forward(buffer, dataType, packetAddressInfo);
                    } else
                    {
                        d8kh3hz5.BdhbLfzv(buffer, dataType, packetAddressInfo);
                        bdHb(d8kh3hz5.BDHbLFzv(), buffer, dataType);
                        if(d8kh3hz5.bDHbLfZv())
                            bDhb(d8kh3hz5.BDHbLFzv(), buffer, dataType);
                        d8kh3hz5.BDhBlfzv(buffer);
                    }
                } else
                if(buffer[34] == 0)
                {
                    StringBuffer stringbuffer10 = new StringBuffer();
                    stringbuffer10.append("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\rNickname spoofing is not allowed");
                    bdhb(stringbuffer10.toString(),  cliInetaddress,  cliInetaddress, datagramPacket.getPort());
                    stringbuffer10 = new StringBuffer();
                    stringbuffer10.append("Client " + s18 + " ");
                    stringbuffer10.append("SPOOFS from " + destIP);
                    stringbuffer10.append(":" + sourceAddress);
                    log(stringbuffer10.toString());
                    dlkh89z dlkh89z3 = new dlkh89z(packetAddressInfo, 600L, 7);
                    config.bdhBLFz().bdhb(dlkh89z3);
                }
                if(d8kh3hz5.bDHBLfZv())
                    d8kh3hz5.bdhBlFZv(buffer, dataType, packetAddressInfo);
            }
            catch(NullPointerException _ex)
            {
                config.getLogger().BdhB("Datagram : run : NullPointerException : 7", (short)3);
            }
            break;

        case 5: // '\005'
            try
            {
                if(!isClientSeen)
                    break;
                System.arraycopy(clientInfo.BdhBlFz(), 0, buffer, 12, 4);
                Conference d8kh3hz6 = clientInfo.BdHBlfz();
                if(isPrivateMessage)
                {
                    d8kh3hz6.forward(buffer, dataType, packetAddressInfo);
                    break;
                }
                d8kh3hz6.BDHblfzv(buffer, dataType, packetAddressInfo);
                bdHb(d8kh3hz6.BDHbLFzv(), buffer, dataType);
                if(d8kh3hz6.bDHbLfZv())
                    bDhb(d8kh3hz6.BDHbLFzv(), buffer, dataType);
                d8kh3hz6.BDhBlfzv(buffer);
                if(d8kh3hz6.bDHBLfZv())
                    d8kh3hz6.bdhBlFZv(buffer, dataType, packetAddressInfo);
            }
            catch(NullPointerException _ex)
            {
                config.getLogger().BdhB("Datagram : run : NullPointerException : 8", (short)3);
            }
            break;

        case 6: // '\006'
            try
            {
                if(!isClientSeen)
                    break;
                System.arraycopy(clientInfo.BdhBlFz(), 0, buffer, 12, 4);
                Conference d8kh3hz7 = clientInfo.BdHBlfz();
                if(isPrivateMessage)
                {
                    d8kh3hz7.forward(buffer, dataType, packetAddressInfo);
                    break;
                }
                d8kh3hz7.BDHblfzv(buffer, dataType, packetAddressInfo);
                bdHb(d8kh3hz7.BDHbLFzv(), buffer, dataType);
                if(d8kh3hz7.bDHbLfZv())
                    bDhb(d8kh3hz7.BDHbLFzv(), buffer, dataType);
                d8kh3hz7.BDhBlfzv(buffer);
                if(d8kh3hz7.bDHBLfZv())
                    d8kh3hz7.bdhBlFZv(buffer, dataType, packetAddressInfo);
            }
            catch(NullPointerException _ex)
            {
                config.getLogger().BdhB("Datagram : run : NullPointerException : 9", (short)3);
            }
            break;

        case 7: // '\007'
            try
            {
                if(isClientSeen)
                    clientInfo.BdHblfZ(buffer);
            }
            catch(NullPointerException _ex)
            {
                config.getLogger().BdhB("Datagram : run : NullPointerException : 10", (short)3);
            }
            break;

        case DATARATEREPLY: // '\b'
            try
            {
                if(isClientSeen)
                    clientInfo.applyRateControlResponse(buffer);
            }
            catch(NullPointerException _ex)
            {
                config.getLogger().BdhB("Datagram : run : NullPointerException : 11", (short)3);
            }
            break;

        case 10: // '\n'
            d210l8z d210l8z1 = (d210l8z) config.bDhBlfz().get( cliInetaddress);
            if(d210l8z1 != null)
            {
                String s3 = Utils.convertToIPform(buffer, 12);
                d210l8z1.bdHB(lTHt);
                d210l8z1.Bdhb(s3);
            } else
            {
                config.getLogger().logStr("Unknown Peer reflector, " + destIP);
            }
            break;

        case 22: // '\026'
            d210l8z d210l8z2 = (d210l8z) config.bDhBlfz().get( cliInetaddress);
            if(d210l8z2 != null)
            {
                String s4 = Utils.convertToIPform(buffer, 12);
                d210l8z2.bdHB(lTHt);
                d210l8z2.Bdhb(s4);
            }
            break;

        case 11: // '\013'
            d210l8z d210l8z3 = (d210l8z) config.bDhBlfz().get( cliInetaddress);
            if(d210l8z3 != null && d210l8z3.BdhB())
            {
                String s5 = Utils.convertToIPform(buffer, 12);
                String s8 = Utils.convertToIPform(buffer, 4);
                if(Utils.compare(buffer, 4, new byte[4], 0, 4) || Utils.compare(buffer, 4, config.bdHBlfZ().getAddress(), 0, 4))
                {
                    int j = config.bdhBLfZ(Utils.readShort(buffer, 2));
                    if(j == -1 || !Ltht[j].BdhBLfZv())
                        break;
                    Conference d8kh3hz8 = Ltht[j];
                    short word0 = Utils.BDhb(buffer, 24);
                    byte abyte2[] = new byte[word0 - 26];
                    System.arraycopy(buffer, 26, abyte2, 0, word0 - 26);
                    int l1 = determineDataType(abyte2);
                    String s1 = Utils.convertToIPform(abyte2, 12);
                    InetAddress inetaddress2;
                    try
                    {
                        inetaddress2 = InetAddress.getByName(s1);
                    }
                    catch(UnknownHostException _ex)
                    {
                        inetaddress2 = null;
                    }
                    InetAddress inetaddress3;
                    try
                    {
                        inetaddress3 = InetAddress.getByName(s5);
                    }
                    catch(UnknownHostException _ex)
                    {
                        inetaddress3 = null;
                    }
                    int i4 = Utils.readShort(buffer, 36);
                    d0khlhz d0khlhz2 = new d0khlhz(inetaddress3, cliPort, inetaddress2, i4);
                    boolean flag3 = abyte2[1] == 1;
                    if(Utils.compare(buffer, 12, config.bdHBlfZ().getAddress(), 0, 4))
                    {
                        StringBuffer stringbuffer11 = new StringBuffer();
                        stringbuffer11.append("Redundant configuration " + s1 + "(" + d210l8z3.bdhB() + ":" + s5 + ") This should not occur");
                        log(stringbuffer11.toString());
                        break;
                    }
                    Hashtable hashtable = config.BDhBlfz();
                    dl01l9z dl01l9z11 = (dl01l9z)hashtable.get(inetaddress2);
                    if(dl01l9z11 != null)
                    {
                        if(!dl01l9z11.bdhbLf().equals(destIP))
                        {
                            byte abyte0[] = new byte[4];
                            System.arraycopy(inetaddress2.getAddress(), 0, abyte0, 0, 4);
                            d0k2l6z d0k2l6z1 = (d0k2l6z)d8kh3hz8.BDhbLfzv().get( cliInetaddress);
                            d0k2l6z1.BdhbL(abyte0, 1, lTHt.bdhb(), destIP, 21764);
                            break;
                        }
                        isClientSeen = true;
                    } else
                    {
                        isClientSeen = false;
                    }
                    switch(l1)
                    {
                    case 1: // '\001'
                        String s19 = Utils.readStr(abyte2, 32);
                        if(isClientSeen)
                        {
                            try
                            {
                                dl01l9z dl01l9z3 = (dl01l9z)d8kh3hz8.BDHBLFzv().get(inetaddress2);
                                dl01l9z3.BdhBlF(abyte2);
                                d8kh3hz8.bDhBlfzv(abyte2, l1, d0khlhz2);
                                d8kh3hz8.bdHBlfzv(abyte2, destIP, s5);
                                bdHb(d8kh3hz8.BDHbLFzv(), abyte2, l1);
                            }
                            catch(NullPointerException _ex)
                            {
                                config.getLogger().BdhB("Datagram : run : NullPointerException : 13", (short)3);
                            }
                            break label0;
                        }
                        synchronized(d8kh3hz8)
                        {
                            try
                            {
                                dl01l9z dl01l9z4 = new dl01l9z(d8kh3hz8, s1, destIP, d210l8z3.bdhB(), s5, s19, abyte2, lTHt, d0khlhz2);
                                if(dl01l9z4.bDHblF() && d8kh3hz8.Bdhblfzv(dl01l9z4))
                                {
                                    StringBuffer stringbuffer12 = new StringBuffer();
                                    stringbuffer12.append("Client " + s19 + " ");
                                    stringbuffer12.append("logs in from " + s1);
                                    stringbuffer12.append("(" + d210l8z3.bdhB() + ":" + s5 + ")");
                                    stringbuffer12.append(", Conference " + d8kh3hz8.BDHbLFzv());
                                    stringbuffer12.append(", \"" + dl01l9z4.BdHBLf() + "\"");
                                    log(stringbuffer12.toString());
                                    d8kh3hz8.bDhBlfzv(abyte2, l1, d0khlhz2);
                                    d8kh3hz8.bdHBlfzv(abyte2, destIP, s5);
                                    bdHb(d8kh3hz8.BDHbLFzv(), abyte2, l1);
                                }
                            }
                            catch(NullPointerException _ex)
                            {
                                config.getLogger().BdhB("Datagram : run : NullPointerException : 14", (short)3);
                            }
                        }
                        break label0;

                    case 2: // '\002'
                        try
                        {
                            if(!isClientSeen)
                                break label0;
                            dl01l9z dl01l9z5 = (dl01l9z)d8kh3hz8.BDHBLFzv().get(inetaddress2);
                            String s20 = dl01l9z5.BDHbLf();
                            d8kh3hz8.BDHblfzv(abyte2, l1, d0khlhz2);
                            d8kh3hz8.bdHBlfzv(abyte2, destIP, s5);
                            bdHb(d8kh3hz8.BDHbLFzv(), abyte2, l1);
                            if(d8kh3hz8.BDHblFZv(dl01l9z5))
                            {
                                StringBuffer stringbuffer13 = new StringBuffer();
                                stringbuffer13.append("Client " + s20 + " ");
                                stringbuffer13.append("logs out from " + s1);
                                stringbuffer13.append("(" + d210l8z3.bdhB() + ":" + s5 + ")");
                                stringbuffer13.append(", Conference " + d8kh3hz8.BDHbLFzv());
                                log(stringbuffer13.toString());
                            }
                        }
                        catch(NullPointerException _ex)
                        {
                            config.getLogger().BdhB("Datagram : run : NullPointerException : 15", (short)3);
                        }
                        break label0;

                    case 3: // '\003'
                    case 23: // '\027'
                        d8kh3hz8.bDhbLfzv(abyte2, l1, d0khlhz2);
                        bdHb(d8kh3hz8.BDHbLFzv(), abyte2, l1);
                        if(d8kh3hz8.BDhBLfZv(s1))
                        {
                            d8kh3hz8.bdHBlfzv(abyte2, destIP, s5);
                            break label0;
                        }
                        Vector vector2 = new Vector();
                        for(Enumeration enumeration2 = d8kh3hz8.BDHBLFzv().elements(); enumeration2.hasMoreElements();)
                        {
                            dl01l9z dl01l9z6 = (dl01l9z)enumeration2.nextElement();
                            if(!dl01l9z6.bDhbLf().equals( cliInetaddress) && dl01l9z6.BDHBlf().containsKey(inetaddress2) && !vector2.contains(dl01l9z6.bDhbLf()))
                                vector2.add(dl01l9z6.bDhbLf());
                        }

                        d8kh3hz8.bDHBlfzv(abyte2, vector2, s5, destIP);
                        break label0;

                    case 4: // '\004'
                        if(flag3)
                        {
                            d8kh3hz8.forward(abyte2, l1, d0khlhz2);
                            Vector vector3 = new Vector();
                            for(Enumeration enumeration3 = d8kh3hz8.BDHBLFzv().elements(); enumeration3.hasMoreElements();)
                            {
                                dl01l9z dl01l9z7 = (dl01l9z)enumeration3.nextElement();
                                if(!dl01l9z7.bDhbLf().equals( cliInetaddress) && dl01l9z7.BdHBlf().containsKey(inetaddress2) && !vector3.contains(dl01l9z7.bDhbLf()))
                                    vector3.add(dl01l9z7.bDhbLf());
                            }

                            d8kh3hz8.bDHBlfzv(abyte2, vector3, s5, destIP);
                            break label0;
                        }
                        d8kh3hz8.bdhBlfzv(abyte2, l1, d0khlhz2);
                        bdHb(d8kh3hz8.BDHbLFzv(), abyte2, l1);
                        if(d8kh3hz8.BDhBLfZv(s1))
                        {
                            d8kh3hz8.bdHBlfzv(abyte2, destIP, s5);
                            break label0;
                        }
                        Vector vector4 = new Vector();
                        for(Enumeration enumeration4 = d8kh3hz8.BDHBLFzv().elements(); enumeration4.hasMoreElements();)
                        {
                            dl01l9z dl01l9z8 = (dl01l9z)enumeration4.nextElement();
                            if(!dl01l9z8.bDhbLf().equals( cliInetaddress) && dl01l9z8.BdHBlf().containsKey(inetaddress2) && !vector4.contains(dl01l9z8.bDhbLf()))
                                vector4.add(dl01l9z8.bDhbLf());
                        }

                        d8kh3hz8.bDHBlfzv(abyte2, vector4, s5, destIP);
                        break label0;

                    case 9: // '\t'
                        if(flag3)
                        {
                            d8kh3hz8.forward(abyte2, l1, d0khlhz2);
                        } else
                        {
                            d8kh3hz8.BdhbLfzv(abyte2, l1, d0khlhz2);
                            bdHb(d8kh3hz8.BDHbLFzv(), abyte2, l1);
                            d8kh3hz8.bdHBlfzv(abyte2, destIP, s5);
                        }
                        break label0;

                    case 5: // '\005'
                    case 6: // '\006'
                        if(flag3)
                        {
                            d8kh3hz8.forward(abyte2, l1, d0khlhz2);
                        } else
                        {
                            d8kh3hz8.BDHblfzv(abyte2, l1, d0khlhz2);
                            bdHb(d8kh3hz8.BDHbLFzv(), abyte2, l1);
                            d8kh3hz8.bdHBlfzv(abyte2, destIP, s5);
                        }
                        break;

                    case 20: // '\024'
                        d8kh3hz8.bDHBLFZv(true, s1,  cliInetaddress);
                        d8kh3hz8.bdHBlfzv(abyte2, destIP, s5);
                        BDhb(d8kh3hz8, abyte2, l1);
                        break;

                    case 21: // '\025'
                        d8kh3hz8.bDHBLFZv(false, s1,  cliInetaddress);
                        d8kh3hz8.bdHBlfzv(abyte2, destIP, s5);
                        BDhb(d8kh3hz8, abyte2, l1);
                        break;

                    case 7: // '\007'
                    case 8: // '\b'
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
                    case 22: // '\026'
                    default:
                        StringBuffer stringbuffer14 = new StringBuffer();
                        stringbuffer14.append("Invalid data type arrived ");
                        stringbuffer14.append("raw DataType  " + Utils.BDhb(abyte2, 22));
                        stringbuffer14.append(" ," + s1 + "(" + destIP + ":" + s5 + ")");
                        log(stringbuffer14.toString());
                        break;
                    }
                } else
                {
                    short word1 = Utils.BDhb(buffer, 24);
                    byte abyte3[] = new byte[word1 - 26];
                    System.arraycopy(buffer, 26, abyte3, 0, word1 - 26);
                    int k = config.bdhBLfZ(Utils.readShort(buffer, 2));
                    Ltht[k].bdHBlfzv(abyte3, destIP, s5);
                }
            } else
            {
                config.getLogger().logStr("Unknown Peer reflector, " + destIP);
            }
            break;

        case 12: // '\f'
            if(config.bDhBlfz().containsKey( cliInetaddress))
            {
                String s6 = Utils.convertToIPform(buffer, 12);
                String s9 = Utils.convertToIPform(buffer, 4);
                int l = config.bdhBLfZ(Utils.readShort(buffer, 2));
                short word2 = Utils.BDhb(buffer, 24);
                byte abyte4[] = new byte[word2 - 26];
                System.arraycopy(buffer, 26, abyte4, 0, word2 - 26);
                if(Utils.compare(buffer, 4, config.bdHBlfZ().getAddress(), 0, 4))
                {
                    String s14 = Utils.convertToIPform(abyte4, 0);
                    InetAddress inetaddress6;
                    try
                    {
                        inetaddress6 = InetAddress.getByName(s14);
                    }
                    catch(UnknownHostException _ex)
                    {
                        inetaddress6 = null;
                    }
                    clientInfo = (ClientInfo)Ltht[l].BdHBLFzv().get(inetaddress6);
                    if(clientInfo == null)
                        break;
                    int j2 = Utils.readShort(abyte4, 4);
                    byte abyte7[] = new byte[j2];
                    System.arraycopy(buffer, 32, abyte7, 0, j2);
                    String s15 = new String(abyte7);
                    bdhb(s15, clientInfo.bdhBLfz(), clientInfo.bdhBLfz(), clientInfo.bdhblFz());
                    abyte7 = s15.getBytes();
                    for(int l2 = 0; l2 < abyte7.length; l2++)
                        if(abyte7[l2] == 13)
                            abyte7[l2] = 32;

                    s15 = new String(abyte7);
                    log("KILL recieved from " + s6 + ", \"" + s15 + "\" to " + s14);
                    break;
                }
                for(Enumeration enumeration5 = Ltht[l].BDHBLFzv().elements(); enumeration5.hasMoreElements();)
                    try
                    {
                        dl01l9z dl01l9z9 = (dl01l9z)enumeration5.nextElement();
                        if(dl01l9z9.bDhBLf().equals(s9))
                        {
                            d0k2l6z d0k2l6z2 = (d0k2l6z)Ltht[l].BDhbLfzv().get(dl01l9z9.bDhbLf());
                            d0k2l6z2.BdhbL(abyte4, 1, s6, s9, 21762);
                            break label0;
                        }
                    }
                    catch(NullPointerException _ex) { }

            } else
            {
                config.getLogger().logStr("Unknown Peer reflector, " + destIP);
            }
            break;

        case 13: // '\r'
            if(config.bDhBlfz().containsKey( cliInetaddress))
            {
                String address = Utils.convertToIPform(buffer, 12);
                String destAddress = Utils.convertToIPform(buffer, 4);
                int i1 = config.bdhBLfZ(Utils.readShort(buffer, 2));
                short packetlength = Utils.BDhb(buffer, 24);
                byte abyte5[] = new byte[packetlength - 26];
                System.arraycopy(buffer, 26, abyte5, 0, packetlength - 26);
                if(Utils.compare(buffer, 4, config.bdHBlfZ().getAddress(), 0, 4))
                {
                    String s12 = Utils.convertToIPform(abyte5, 0);
                    InetAddress inetaddress5;
                    try
                    {
                        inetaddress5 = InetAddress.getByName(s12);
                    }
                    catch(UnknownHostException _ex)
                    {
                        inetaddress5 = null;
                    }
                    int i2 = Utils.readShort(abyte5, 4);
                    byte abyte6[] = new byte[i2];
                    System.arraycopy(buffer, 32, abyte6, 0, i2);
                    String s13 = new String(abyte6);
                    BDHb(s13, inetaddress5);
                    abyte6 = s13.getBytes();
                    for(int i3 = 0; i3 < abyte6.length; i3++)
                        if(abyte6[i3] == 13)
                            abyte6[i3] = 32;

                    s13 = new String(abyte6);
                    log("MSG recieved from " + address + ", \"" + s13 + "\" to " + s12);
                    break;
                }
                for(Enumeration enumeration6 = Ltht[i1].BDHBLFzv().elements(); enumeration6.hasMoreElements();)
                    try
                    {
                        dl01l9z dl01l9z10 = (dl01l9z)enumeration6.nextElement();
                        if(dl01l9z10.bDhBLf().equals(destAddress))
                        {
                            d0k2l6z d0k2l6z3 = (d0k2l6z)Ltht[i1].BDhbLfzv().get(dl01l9z10.bDhbLf());
                            d0k2l6z3.BdhbL(abyte5, 1, address, destAddress, 21763);
                            break label0;
                        }
                    }
                    catch(NullPointerException _ex) { }

            } else
            {
                config.getLogger().logStr("Unknown Peer reflector, " + destIP);
            }
            break;

        case 14: // '\016'
            if(config.bDhBlfz().containsKey( cliInetaddress))
            {
                int j1 = config.bdhBLfZ(Utils.readShort(buffer, 2));
                if(j1 != -1)
                {
                    d0k2l6z d0k2l6z4 = (d0k2l6z)Ltht[j1].BDhbLfzv().get( cliInetaddress);
                    d0k2l6z4.bdhbl(Utils.convertToIPform(buffer, 26));
                }
            } else
            {
                config.getLogger().logStr("Unknown Peer reflector, " + destIP);
            }
            break;

        case 15: // '\017'
            if(config.bDhBlfz().containsKey( cliInetaddress))
            {
                int k1 = config.bdhBLfZ(Utils.readShort(buffer, 2));
                if(k1 != -1)
                {
                    d0k2l6z d0k2l6z5 = (d0k2l6z)Ltht[k1].BDhbLfzv().get( cliInetaddress);
                    d0k2l6z5.bDhBl(Utils.convertToIPform(buffer, 26));
                }
            } else
            {
                config.getLogger().logStr("Unknown Peer reflector, " + destIP);
            }
            break;

        case 17: // '\021'
            StringBuffer stringbuffer15 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + config.BdhBLfz());
            bdhb(stringbuffer15.toString(),  cliInetaddress,  cliInetaddress, 7648);
            bdhb(stringbuffer15.toString(),  cliInetaddress,  cliInetaddress, 7649);
            bdhb(stringbuffer15.toString(),  cliInetaddress,  cliInetaddress, datagramPacket.getPort());
            if(config.bdhBLfz())
                try
                {
                    synchronized(config)
                    {
                        if(!config.BDHbLfz().ktdblhBdhb( cliInetaddress))
                        {
                            config.BDHbLfz().bdhb(destIP, config.BdhBLfz());
                            stringbuffer15 = new StringBuffer();
                            if(isClientSeen)
                                stringbuffer15.append("Client " + clientInfo.BdHBLfz() + "(" + clientInfo.bdhBlFz().getHostAddress() + ")");
                            else
                                stringbuffer15.append("The address " + destIP);
                            stringbuffer15.append(" is added to the Reflector Deny list.");
                            config.getLogger().logStr(stringbuffer15.toString());
                        }
                    }
                }
                catch(UnknownHostException _ex) { }
            stringbuffer15 = new StringBuffer();
            String s11 = Utils.convertToIPform(buffer, 4);
            try
            {
                InetAddress inetaddress4;
                try
                {
                    inetaddress4 = InetAddress.getByName(s11);
                }
                catch(UnknownHostException _ex)
                {
                    inetaddress4 = null;
                }
                if(isClientSeen)
                    stringbuffer15.append("Client " + clientInfo.BdHBLfz() + "(" + clientInfo.bdhBlFz().getHostAddress() + ") tried to boot ");
                else
                    stringbuffer15.append(destIP + " tried to boot ");
                d0khlhz d0khlhz3 = config.BdHBlfZ().BdHbl(inetaddress4);
                if(d0khlhz3 != null)
                {
                    ClientInfo dlk3l7z2 = (ClientInfo) config.bDHbLFz().get(d0khlhz3);
                    if(dlk3l7z2 != null)
                        stringbuffer15.append(dlk3l7z2.BdHBLfz() + "(" + s11 + ")");
                    else
                        stringbuffer15.append(s11);
                } else
                {
                    dl01l9z dl01l9z12 = (dl01l9z) config.BDhBlfz().get(inetaddress4);
                    if(dl01l9z12 != null)
                        stringbuffer15.append(dl01l9z12.BDHbLf() + "(" + s11 + ")");
                    else
                        stringbuffer15.append(s11);
                }
            }
            catch(NullPointerException _ex)
            {
                stringbuffer15 = new StringBuffer();
                stringbuffer15.append(destIP + " tried to boot " + s11);
            }
            stringbuffer15.append(", but filtered.");
            config.getLogger().logStr(stringbuffer15.toString());
            break;

        case 16: // '\020'
        case 18: // '\022'
        case 20: // '\024'
        case 21: // '\025'
        default:
            StringBuffer stringbuffer16 = new StringBuffer();
            stringbuffer16.append("Invalid data type arrived ");
            stringbuffer16.append("raw DataType  " + Utils.BDhb(buffer, 22));
            stringbuffer16.append(" ," + destIP);
            log(stringbuffer16.toString());
            break;
        }
    }

    private ConfigLoader config;
    private Conference Ltht[];
    private DatagramPacket datagramPacket;
    private byte buffer[];
    private int packetLength;
    private int dataType;
    private MainReceiver lTHt;
    private static final int LTHt = 600;
}

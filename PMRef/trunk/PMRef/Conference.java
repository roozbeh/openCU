// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.*;

// Referenced classes of package PMRef:
//            dlkhlhz, d0khlhz, dl0hlhz, d21hlhz, 
//            Constants, d2k49hz, d6khl0z, dlk3l7z, 
//            d0k2l6z, dl01l9z, d210l8z, dlkh89z, 
//            d4kh98z, d2khlh9, TimerObserver, dlk9lh4

class Conference
        implements Constants, TimerObserver
{

    Conference()
    {
        LThTLf = new dlkhlhz();
        ltHTLf = new dl0hlhz();
        LtHTLf = new dlkhlhz();
        lTHTLf = new d2k49hz();
        LTHTLf = new Hashtable();
        lthtlF = new Hashtable();
        LthtlF = new Hashtable();
        lThtlF = new d4kh98z();
        lTHtLf = false;
        LThTlF = new Hashtable();
    }

    Conference(int i, ConfigLoader d2khlh9_1)
    {
        this();
        lthtlf = i;
        config = d2khlh9_1;
        LthTLf = d2khlh9_1.bdHBLFz().getProperty("conference.name-" + i, "Conference No." + i);
        try
        {
            LTHtlf = Integer.parseInt(d2khlh9_1.bdHBLFz().getProperty("conference.timelimit-" + BDHbLFzv(), (new Integer(d2khlh9_1.bdhBlfZ())).toString()));
            if(LTHtlf < 0)
                throw new NumberFormatException();
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("Proparties file Error : conference.timelimit-");
            stringbuffer.append((new Integer(BDHbLFzv())).toString() + " ");
            stringbuffer.append(", using the default value");
            stringbuffer.append("(" + (new Integer(d2khlh9_1.bdhBlfZ())).toString() + ")");
            BdHblFZv(stringbuffer.toString());
            LTHtlf = d2khlh9_1.bdhBlfZ();
        }
        try
        {
            lthTlf = Integer.parseInt(d2khlh9_1.bdHBLFz().getProperty("conference.timelimit.penalty-" + BDHbLFzv(), (new Integer(d2khlh9_1.bDhBlfZ())).toString()));
            if(lthTlf < 0)
                throw new NumberFormatException();
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer1 = new StringBuffer();
            stringbuffer1.append("Proparties file Error : conference.timelimit.penalty-");
            stringbuffer1.append((new Integer(BDHbLFzv())).toString() + " ");
            stringbuffer1.append(", using the default value");
            stringbuffer1.append("(" + (new Integer(d2khlh9_1.bDhBlfZ())).toString() + ")");
            BdHblFZv(stringbuffer1.toString());
            lthTlf = d2khlh9_1.bDhBlfZ();
        }
        try
        {
            lThtlf = Short.parseShort(d2khlh9_1.bdHBLFz().getProperty("conference.max.participants-" + BDHbLFzv(), (new Short(d2khlh9_1.bdHBlFz())).toString()));
            if(lThtlf > d2khlh9_1.bdHBlFz())
            {
                StringBuffer stringbuffer2 = new StringBuffer();
                stringbuffer2.append("Proparties file Error : conference.max.participants-" + BDHbLFzv());
                stringbuffer2.append(" must be equal to or less than ");
                stringbuffer2.append("reflector.max.participants");
                stringbuffer2.append(", using this value");
                stringbuffer2.append("(" + d2khlh9_1.bdHBlFz() + ")");
                BdHblFZv(stringbuffer2.toString());
                lThtlf = d2khlh9_1.bdHBlFz();
            }
            if(lThtlf < 0)
                throw new NumberFormatException();
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer3 = new StringBuffer();
            stringbuffer3.append("Proparties file Error : conference.max.participants-");
            stringbuffer3.append((new Integer(BDHbLFzv())).toString() + " ");
            stringbuffer3.append(", using this value");
            stringbuffer3.append("(" + (new Short(d2khlh9_1.bdHBlFz())).toString() + ")");
            BdHblFZv(stringbuffer3.toString());
            lThtlf = d2khlh9_1.bdHBlFz();
        }
        try
        {
            ltHtlf = Short.parseShort(d2khlh9_1.bdHBLFz().getProperty("conference.max.senders-" + BDHbLFzv(), (new Short(d2khlh9_1.bDHBlFz())).toString()));
            short word0 = d2khlh9_1.bDHBlFz() >= lThtlf ? lThtlf : d2khlh9_1.bDHBlFz();
            if(ltHtlf > word0)
            {
                StringBuffer stringbuffer12 = new StringBuffer();
                stringbuffer12.append("Proparties file Error : conference.max.senders-" + BDHbLFzv());
                stringbuffer12.append(" must be equal to or less than ");
                stringbuffer12.append(d2khlh9_1.bDHBlFz() >= lThtlf ? new String("conference.max.participants-" + BDHbLFzv()) : "reflector.max.senders");
                stringbuffer12.append(", using this value");
                stringbuffer12.append("(" + word0 + ")");
                BdHblFZv(stringbuffer12.toString());
                ltHtlf = word0;
            }
            if(ltHtlf < 0)
                throw new NumberFormatException();
        }
        catch(NumberFormatException _ex)
        {
            short word2 = d2khlh9_1.bDHBlFz() >= lThtlf ? lThtlf : d2khlh9_1.bDHBlFz();
            StringBuffer stringbuffer4 = new StringBuffer();
            stringbuffer4.append("Proparties file Error : conference.max.senders-");
            stringbuffer4.append((new Integer(BDHbLFzv())).toString() + " ");
            stringbuffer4.append(", using this value");
            stringbuffer4.append("(" + word2 + ")");
            BdHblFZv(stringbuffer4.toString());
            ltHtlf = word2;
        }
        try
        {
            lTHtlf = Short.parseShort(d2khlh9_1.bdHBLFz().getProperty("conference.max.lurkers-" + BDHbLFzv(), (new Short(d2khlh9_1.bDHBLfz())).toString()));
            short word1 = d2khlh9_1.bDHBLfz() >= lThtlf ? lThtlf : d2khlh9_1.bDHBLfz();
            if(lTHtlf > word1)
            {
                StringBuffer stringbuffer13 = new StringBuffer();
                stringbuffer13.append("Proparties file Error : conference.max.lurkers-" + BDHbLFzv());
                stringbuffer13.append(" must be equal to or less than ");
                stringbuffer13.append(d2khlh9_1.bDHBLfz() >= lThtlf ? new String("conference.max.participants-" + BDHbLFzv()) : "reflector.max.lurkers");
                stringbuffer13.append(", using this value");
                stringbuffer13.append("(" + word1 + ")");
                BdHblFZv(stringbuffer13.toString());
                lTHtlf = word1;
            }
            if(lTHtlf < 0)
                throw new NumberFormatException();
        }
        catch(NumberFormatException _ex)
        {
            short word3 = d2khlh9_1.bDHBLfz() >= lThtlf ? lThtlf : d2khlh9_1.bDHBLfz();
            StringBuffer stringbuffer5 = new StringBuffer();
            stringbuffer5.append("Proparties file Error : conference.max.lurkers-");
            stringbuffer5.append((new Integer(BDHbLFzv())).toString() + " ");
            stringbuffer5.append(", using this value");
            stringbuffer5.append("(" + word3 + ")");
            BdHblFZv(stringbuffer5.toString());
            lTHtlf = word3;
        }
        try
        {
            LthTlf = Integer.parseInt(d2khlh9_1.bdHBLFz().getProperty("conference.max.max.send-" + BDHbLFzv(), (new Short(d2khlh9_1.BDhblFz())).toString()));
            if(LthTlf < 1 || LthTlf > 65535)
                throw new NumberFormatException();
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer6 = new StringBuffer();
            stringbuffer6.append("Proparties file Error : , conference.max.max.send-");
            stringbuffer6.append((new Integer(BDHbLFzv())).toString() + " ");
            stringbuffer6.append(", using the default value");
            stringbuffer6.append("(" + (new Short(d2khlh9_1.BDhblFz())).toString() + ")");
            BdHblFZv(stringbuffer6.toString());
            LthTlf = d2khlh9_1.BDhblFz();
        }
        try
        {
            lThTlf = Integer.parseInt(d2khlh9_1.bdHBLFz().getProperty("conference.max.min.send-" + BDHbLFzv(), (new Short(d2khlh9_1.BdhBlFz())).toString()));
            if(lThTlf < 1 || lThTlf > 65535)
                throw new NumberFormatException();
            if(lThTlf > LthTlf)
            {
                StringBuffer stringbuffer7 = new StringBuffer();
                stringbuffer7.append("Proparties file Error : conference.max.min.send-" + BDHbLFzv());
                stringbuffer7.append(", using the value of conference.max.max.send-" + BDHbLFzv());
                stringbuffer7.append("(" + LthTlf + ")");
                BdHblFZv(stringbuffer7.toString());
                lThTlf = LthTlf;
            }
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer8 = new StringBuffer();
            stringbuffer8.append("Proparties file Error : , conference.max.min.send-");
            stringbuffer8.append((new Integer(BDHbLFzv())).toString() + " ");
            stringbuffer8.append(", using the default value");
            stringbuffer8.append("(" + (new Short(d2khlh9_1.BdhBlFz())).toString() + ")");
            BdHblFZv(stringbuffer8.toString());
            lThTlf = d2khlh9_1.BdhBlFz();
        }
        try
        {
            LThTlf = Integer.parseInt(d2khlh9_1.bdHBLFz().getProperty("conference.max.max.recv-" + BDHbLFzv(), (new Short(d2khlh9_1.bdhblFz())).toString()));
            if(LThTlf < 1 || LThTlf > 65535)
                throw new NumberFormatException();
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer9 = new StringBuffer();
            stringbuffer9.append("Proparties file Error : , conference.max.max.recv-");
            stringbuffer9.append((new Integer(BDHbLFzv())).toString() + " ");
            stringbuffer9.append(", using the default value");
            stringbuffer9.append("(" + (new Integer(d2khlh9_1.bdhblFz())).toString() + ")");
            BdHblFZv(stringbuffer9.toString());
            LThTlf = d2khlh9_1.bdhblFz();
        }
        try
        {
            ltHTlf = Integer.parseInt(d2khlh9_1.bdHBLFz().getProperty("conference.max.min.recv-" + BDHbLFzv(), (new Short(d2khlh9_1.bDHblFz())).toString()));
            if(ltHTlf < 1 || ltHTlf > 65535)
                throw new NumberFormatException();
            if(ltHTlf > LThTlf)
            {
                StringBuffer stringbuffer10 = new StringBuffer();
                stringbuffer10.append("Proparties file Error : conference.max.min.recv-" + BDHbLFzv());
                stringbuffer10.append(", using the value of conference.max.max.recv-" + BDHbLFzv());
                stringbuffer10.append("(" + LThTlf + ")");
                BdHblFZv(stringbuffer10.toString());
                ltHTlf = LThTlf;
            }
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer11 = new StringBuffer();
            stringbuffer11.append("Proparties file Error : , conference.max.min.recv-");
            stringbuffer11.append((new Integer(BDHbLFzv())).toString() + " ");
            stringbuffer11.append(", using the default value");
            stringbuffer11.append("(" + (new Short(d2khlh9_1.bDHblFz())).toString() + ")");
            BdHblFZv(stringbuffer11.toString());
            ltHTlf = d2khlh9_1.bDHblFz();
        }
        LtHTlf = (new Boolean(d2khlh9_1.bdHBLFz().getProperty("conference.private-" + BDHbLFzv(), "false"))).booleanValue();
        lTHTlf = (new Boolean(d2khlh9_1.bdHBLFz().getProperty("conference.allowaudio-" + BDHbLFzv(), "true"))).booleanValue();
        LTHTlf = (new Boolean(d2khlh9_1.bdHBLFz().getProperty("conference.recvonly-" + BDHbLFzv(), "false"))).booleanValue();
        lthtLf = (new Boolean(d2khlh9_1.bdHBLFz().getProperty("conference.selfreflect-" + BDHbLFzv(), "false"))).booleanValue();
        LthtLf = (new Boolean(d2khlh9_1.bdHBLFz().getProperty("conference.allowchat-" + BDHbLFzv(), "true"))).booleanValue();
        lThtLf = (new Boolean(d2khlh9_1.bdHBLFz().getProperty("conference.nogeek-" + BDHbLFzv(), "false"))).booleanValue();
        LtHtLf = (new Boolean(d2khlh9_1.bdHBLFz().getProperty("conference.monitor-" + BDHbLFzv(), "false"))).booleanValue();
        if(LtHtLf)
            d2khlh9_1.bDhbLFz().add(this);
        if(d2khlh9_1.bdHBLFz().getProperty("conference.password-" + BDHbLFzv(), "").equals(""))
        {
            LThtLf = false;
        } else
        {
            LThtLf = true;
            try
            {
                LThtlF = MessageDigest.getInstance("MD5");
                LThtlF.reset();
                LThtlF.update(d2khlh9_1.bdHBLFz().getProperty("conference.password-" + BDHbLFzv(), "").getBytes());
                ltHtlF = LThtlF.digest();
            }
            catch(Exception _ex) { }
        }
        if(d2khlh9_1.bdHBLFz().getProperty("conference.whitepine.encoded.password.string-" + BDHbLFzv(), "").equals(""))
        {
            LThtLf = false;
        } else
        {
            LThtLf = true;
            lthTlF = true;
            bdHblfzV(d2khlh9_1.bdHBLFz().getProperty("conference.whitepine.encoded.password.string-" + BDHbLFzv(), ""));
        }
        lthTlF = (new Boolean(d2khlh9_1.bdHBLFz().getProperty("conference.use.whitepine.password-" + BDHbLFzv(), "false"))).booleanValue();
        if(lthTlF)
            LThtLf = true;
        LTHtLf = (new Boolean(d2khlh9_1.bdHBLFz().getProperty("conference.returnpassdiff-" + BDHbLFzv(), "false"))).booleanValue();
        if(LTHtLf)
        {
            lthTlF = true;
            LThtLf = true;
        }
    }

    synchronized boolean bdhblfzv(ClientInfo dlk3l7z1)
    {
        String s = dlk3l7z1.BdHBLfz();
        String s1 = dlk3l7z1.bDhBlfz();
        String s2 = dlk3l7z1.bDHBlfz();
        InetAddress inetaddress = dlk3l7z1.bdhBLfz();
        d0khlhz d0khlhz1 = dlk3l7z1.bdHBlfz();
        dlkh89z dlkh89z1 = lThtlF.Bdhb(d0khlhz1);
        if(dlkh89z1 != null)
        {
            StringBuffer stringbuffer = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + config.BdhBLFz());
            stringbuffer.append("   (" + dlkh89z1.bdHb() + " seconds)");
            dlk3l7z1.BDHblfz(stringbuffer.toString());
            return false;
        }
        if (config.bdHbLfZ() && !config.bDHBlfz().ktdblhBdhb(inetaddress))
        {
            StringBuffer stringbuffer1;
            if(config.bdHBlFz() != 0)
                stringbuffer1 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + config.BdHBlFz() + "\r\r( " + config.bdHBlFz() + " participants)");
            else
                stringbuffer1 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + config.bdhbLfz());
            dlk3l7z1.BDHblfz(stringbuffer1.toString());
            stringbuffer1 = new StringBuffer();
            stringbuffer1.append("Client " + s + " ");
            stringbuffer1.append("DENIED from " + s1);
            stringbuffer1.append(" because of reflector.max.participants");
            BdHblFZv(stringbuffer1.toString());
            return false;
        }
        if(bdHbLfZv() && !config.bDHBlfz().ktdblhBdhb(inetaddress) && !BDHbLfzv().ktdblhBdhb(inetaddress))
        {
            StringBuffer stringbuffer2;
            if(BDHBlFzv() != 0)
                stringbuffer2 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + bdhbLFzv() + "\r\r( " + BDHBlFzv() + " participants)");
            else
                stringbuffer2 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + BdhBLfzv());
            dlk3l7z1.BDHblfz(stringbuffer2.toString());
            stringbuffer2 = new StringBuffer();
            stringbuffer2.append("Client " + s + " ");
            stringbuffer2.append("DENIED from " + s1);
            stringbuffer2.append(" because of conference.max.participants-");
            stringbuffer2.append(lthtlf);
            BdHblFZv(stringbuffer2.toString());
            return false;
        }
        if(dlk3l7z1.bdHbLFz())
        {
            if(config.BdHbLfZ() && !config.bDHBlfz().ktdblhBdhb(inetaddress))
            {
                StringBuffer stringbuffer3 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + config.BDHBlFz() + "\r\r(" + config.bDHBlFz() + " senders)");
                dlk3l7z1.BDHblfz(stringbuffer3.toString());
                stringbuffer3 = new StringBuffer();
                stringbuffer3.append("Client " + s + " ");
                stringbuffer3.append("DENIED from " + s1);
                stringbuffer3.append(" because of reflector.max.senders");
                BdHblFZv(stringbuffer3.toString());
                return false;
            }
        } else
        if(config.BDhbLfZ() && !config.bDHBlfz().ktdblhBdhb(inetaddress))
        {
            StringBuffer stringbuffer4 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + config.BDHBLfz() + "\r\r(" + config.BdHBLfz() + " lurkers)");
            dlk3l7z1.BDHblfz(stringbuffer4.toString());
            stringbuffer4 = new StringBuffer();
            stringbuffer4.append("Client " + s + " ");
            stringbuffer4.append("DENIED from " + s1);
            stringbuffer4.append(" because of reflector.max.lurkers");
            BdHblFZv(stringbuffer4.toString());
            return false;
        }
        if(dlk3l7z1.bdHbLFz())
        {
            if(BdHbLfZv() && !config.bDHBlfz().ktdblhBdhb(inetaddress) && !BDHbLfzv().ktdblhBdhb(inetaddress))
            {
                StringBuffer stringbuffer5 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + bDhbLFzv() + "\r\r(" + BdhbLFzv() + " senders)");
                dlk3l7z1.BDHblfz(stringbuffer5.toString());
                stringbuffer5 = new StringBuffer();
                stringbuffer5.append("Client " + s + " ");
                stringbuffer5.append("DENIED from " + s1);
                stringbuffer5.append(" because of conference.max.senders-");
                stringbuffer5.append(lthtlf);
                BdHblFZv(stringbuffer5.toString());
                return false;
            }
        } else
        if(BDhbLfZv() && !config.bDHBlfz().ktdblhBdhb(inetaddress) && !BDHbLfzv().ktdblhBdhb(inetaddress))
        {
            StringBuffer stringbuffer6 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + bDhblFzv() + "\r\r(" + bdhblFzv() + " lurkers)");
            dlk3l7z1.BDHblfz(stringbuffer6.toString());
            stringbuffer6 = new StringBuffer();
            stringbuffer6.append("Client " + s + " ");
            stringbuffer6.append("DENIED from " + s1);
            stringbuffer6.append(" because of conference.max.lurkers-");
            stringbuffer6.append(lthtlf);
            BdHblFZv(stringbuffer6.toString());
            return false;
        }
        if(LTHTLf.containsKey(dlk3l7z1.bdhBlFz()))
            return false;
        LTHTLf.put(dlk3l7z1.bdhBlFz(), dlk3l7z1);
        Lthtlf++;
        config.Bdhblfz();
        if(dlk3l7z1.bdHbLFz())
        {
            LThtlf++;
            config.BDhblfz();
        } else
        {
            LtHtlf++;
            config.bdhblfz();
        }
        bdHBLFzv().put(d0khlhz1, dlk3l7z1);
        return true;
    }

    synchronized boolean Bdhblfzv(dl01l9z dl01l9z1)
    {
        String s = dl01l9z1.BDHbLf();
        String s1 = dl01l9z1.bDHblf();
        String s3 = dl01l9z1.bdhbLf();
        String s2 = dl01l9z1.bDhBLf();
        InetAddress inetaddress = dl01l9z1.BdhbLf();
        dlkh89z dlkh89z1 = lThtlF.Bdhb(dl01l9z1.BDHblf());
        if(dlkh89z1 != null)
        {
            StringBuffer stringbuffer = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + config.BdhBLFz());
            stringbuffer.append("   (" + dlkh89z1.bdHb() + " seconds)");
            dl01l9z1.bdHblf(stringbuffer.toString());
            return false;
        }
        if(config.bdHbLfZ() && !config.bDHBlfz().ktdblhBdhb(inetaddress))
        {
            StringBuffer stringbuffer1;
            if(BDHBlFzv() != 0)
                stringbuffer1 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + bdhbLFzv() + "\r\r( " + BDHBlFzv() + " participants)");
            else
                stringbuffer1 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + config.bdhbLfz());
            dl01l9z1.bdHblf(stringbuffer1.toString());
            stringbuffer1 = new StringBuffer();
            stringbuffer1.append("Client " + s + " ");
            stringbuffer1.append("DENIED from " + s1);
            stringbuffer1.append(" because of reflector.max.participants");
            BdHblFZv(stringbuffer1.toString());
            return false;
        }
        if(bdHbLfZv() && !config.bDHBlfz().ktdblhBdhb(inetaddress) && !BDHbLfzv().ktdblhBdhb(inetaddress))
        {
            StringBuffer stringbuffer2;
            if(BDHBlFzv() != 0)
                stringbuffer2 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + bdhbLFzv() + "\r\r( " + BDHBlFzv() + " participants)");
            else
                stringbuffer2 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + BdhBLfzv());
            dl01l9z1.bdHblf(stringbuffer2.toString());
            stringbuffer2 = new StringBuffer();
            stringbuffer2.append("Client " + s + " ");
            stringbuffer2.append("DENIED from " + s1);
            stringbuffer2.append(" because of conference.max.participants-");
            stringbuffer2.append(lthtlf);
            BdHblFZv(stringbuffer2.toString());
            return false;
        }
        if(dl01l9z1.BdHblF())
        {
            if(config.BdHbLfZ() && !config.bDHBlfz().ktdblhBdhb(inetaddress))
            {
                StringBuffer stringbuffer3 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + config.BDHBlFz() + "\r\r(" + config.bDHBlFz() + " senders)");
                dl01l9z1.bdHblf(stringbuffer3.toString());
                stringbuffer3 = new StringBuffer();
                stringbuffer3.append("Client " + s + " ");
                stringbuffer3.append("DENIED from " + s1);
                stringbuffer3.append(" because of reflector.max.senders");
                BdHblFZv(stringbuffer3.toString());
                return false;
            }
        } else
        if(config.BDhbLfZ() && !config.bDHBlfz().ktdblhBdhb(inetaddress))
        {
            StringBuffer stringbuffer4 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + config.BDHBLfz() + "\r\r(" + config.BdHBLfz() + " lurkers)");
            dl01l9z1.bdHblf(stringbuffer4.toString());
            stringbuffer4 = new StringBuffer();
            stringbuffer4.append("Client " + s + " ");
            stringbuffer4.append("DENIED from " + s1);
            stringbuffer4.append(" because of reflector.max.lurkers");
            BdHblFZv(stringbuffer4.toString());
            return false;
        }
        if(dl01l9z1.BdHblF())
        {
            if(BdHbLfZv() && !config.bDHBlfz().ktdblhBdhb(inetaddress) && !BDHbLfzv().ktdblhBdhb(inetaddress))
            {
                StringBuffer stringbuffer5 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + bDhbLFzv() + "\r\r(" + BdhbLFzv() + " senders)");
                dl01l9z1.bdHblf(stringbuffer5.toString());
                stringbuffer5 = new StringBuffer();
                stringbuffer5.append("Client " + s + " ");
                stringbuffer5.append("DENIED from " + s1);
                stringbuffer5.append(" because of conference.max.senders-");
                stringbuffer5.append(lthtlf);
                BdHblFZv(stringbuffer5.toString());
                return false;
            }
        } else
        if(BDhbLfZv() && !config.bDHBlfz().ktdblhBdhb(inetaddress) && !BDHbLfzv().ktdblhBdhb(inetaddress))
        {
            StringBuffer stringbuffer6 = new StringBuffer("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + bDhblFzv() + "\r\r(" + bdhblFzv() + " lurkers)");
            dl01l9z1.bdHblf(stringbuffer6.toString());
            stringbuffer6 = new StringBuffer();
            stringbuffer6.append("Client " + s + " ");
            stringbuffer6.append("DENIED from " + s1);
            stringbuffer6.append(" because of conference.max.lurkers-");
            stringbuffer6.append(lthtlf);
            BdHblFZv(stringbuffer6.toString());
            return false;
        }
        if(lthtlF.containsKey(dl01l9z1.BdhbLf()))
            return false;
        lthtlF.put(dl01l9z1.BdhbLf(), dl01l9z1);
        Lthtlf++;
        config.Bdhblfz();
        if(dl01l9z1.BdHblF())
        {
            LThtlf++;
            config.BDhblfz();
        } else
        {
            LtHtlf++;
            config.bdhblfz();
        }
        bdHbLfzv().put(dl01l9z1.BdhbLf(), dl01l9z1);
        return true;
    }

    byte[] bDhblfzv(byte abyte0[])
    {
        short word1 = Utils.BDhb(abyte0, 26);
        short word0 = Utils.BDhb(abyte0, 24);
        int i = word0 - 12 * (word1 - 1);
        byte abyte1[] = new byte[i];
        System.arraycopy(abyte0, 0, abyte1, 0, 56);
        System.arraycopy(Utils.short2bytes((short)1), 0, abyte1, 26, 2);
        System.arraycopy(Utils.short2bytes((short)i), 0, abyte1, 24, 2);
        System.arraycopy(abyte0, 56 + 12 * word1, abyte1, 68, word0 - (56 + 12 * word1));
        return abyte1;
    }

    void BDhblfzv(byte abyte0[], byte abyte1[], ClientInfo dlk3l7z1)
    {
        short word0 = Utils.BDhb(abyte0, 26);
        for(int i = 0; i < word0; i++)
        {
            int j = 56 + 12 * i;
            if(!Utils.compare(abyte0, j, dlk3l7z1.BdhBlFz(), 0, 4))
                continue;
            System.arraycopy(dlk3l7z1.BDHBlfz(), 0, abyte1, 56, 4);
            System.arraycopy(abyte0, j + 4, abyte1, 60, 8);
            break;
        }

    }

    synchronized void bdHblfzv()
    {
        LtHtlf++;
        LThtlf--;
    }

    synchronized void BdHblfzv()
    {
        LtHtlf--;
        LThtlf++;
    }

    void bDHblfzv()
    {
        lThTlF = null;
    }

    void BDHblfzv(byte abyte0[], int i, d0khlhz d0khlhz1)
    {
        for(Enumeration enumeration = LTHTLf.elements(); enumeration.hasMoreElements();)
            try
            {
                ClientInfo clientInfo = (ClientInfo)enumeration.nextElement();
                d0khlhz d0khlhz2 = clientInfo.bdHBlfz();
                if(!d0khlhz2.equals(d0khlhz1))
                    clientInfo.bDhblfZ(abyte0, i);
            }
            catch(NullPointerException _ex)
            {
                config.getLogger().BdhB("Conference : forward : NullPointerException 2", (short)3);
            }

    }

    void bdhBlfzv(byte abyte0[], int i, d0khlhz d0khlhz1)
    {
        ClientInfo dlk3l7z2 = (ClientInfo) config.bDHbLFz().get(d0khlhz1);
        dl01l9z dl01l9z1 = null;
        InetAddress inetaddress;
        InetAddress inetaddress1;
        if(dlk3l7z2 != null)
        {
            inetaddress = d0khlhz1.bDhb();
            inetaddress1 = dlk3l7z2.bdhBlFz();
        } else
        {
            inetaddress = d0khlhz1.bdhb();
            inetaddress1 = inetaddress;
        }
        for(Enumeration enumeration = LTHTLf.elements(); enumeration.hasMoreElements();)
            try
            {
                ClientInfo dlk3l7z1 = (ClientInfo)enumeration.nextElement();
                d0khlhz d0khlhz2 = dlk3l7z1.bdHBlfz();
                if(!d0khlhz2.equals(d0khlhz1))
                    if(bDhBLfZv())
                    {
                        if(bDHbLfzv().ktdblhBdhb(inetaddress) || dlk3l7z2 == null)
                        {
                            if(dlk3l7z1.bdHbLfz().containsKey(inetaddress1))
                                dlk3l7z1.bDhblfZ(abyte0, i);
                            if(dlk3l7z1.BdHbLfz().containsKey(inetaddress1))
                                dlk3l7z1.bDhblfZ(abyte0, i);
                        }
                    } else
                    if(!bdhbLfZv())
                    {
                        if(bDHbLfzv().ktdblhBdhb(inetaddress) || dlk3l7z2 == null)
                        {
                            if(dlk3l7z1.bdHbLfz().containsKey(inetaddress1) && (dlk3l7z2.bdHbLFz() || dlk3l7z1.bdhbLFz()))
                                dlk3l7z1.bDhblfZ(abyte0, i);
                            if(dlk3l7z1.BdHbLfz().containsKey(inetaddress1) && (dl01l9z1.BdHblF() || dlk3l7z1.bdhbLFz()))
                                dlk3l7z1.bDhblfZ(abyte0, i);
                        }
                    } else
                    if(BDhBLfZv(inetaddress.getHostAddress()))
                    {
                        dlk3l7z1.bDhblfZ(abyte0, i);
                    } else
                    {
                        if(dlk3l7z1.bdHbLfz().containsKey(inetaddress1))
                            dlk3l7z1.bDhblfZ(abyte0, i);
                        if(dlk3l7z1.BdHbLfz().containsKey(inetaddress1))
                            dlk3l7z1.bDhblfZ(abyte0, i);
                    }
            }
            catch(NullPointerException _ex)
            {
                config.getLogger().BdhB("Conference : forward : NullPointerException 2", (short)3);
            }

    }

    void forward(byte abyte0[], int i, d0khlhz d0khlhz1)
    {
        InetAddress inetaddress = d0khlhz1.bDhb();
        String s = Utils.convertToIPform(abyte0, 4);
        boolean flag = false;
        dl01l9z dl01l9z1 = null;
        Vector vector = null;
        InetAddress inetaddress1;
        try
        {
            inetaddress1 = InetAddress.getByName(s);
        }
        catch(UnknownHostException _ex)
        {
            return;
        }
        try
        {
            ClientInfo dlk3l7z2 = (ClientInfo)LTHTLf.get(inetaddress);
            ClientInfo dlk3l7z1 = (ClientInfo)LTHTLf.get(inetaddress1);
            if(dlk3l7z1 == null)
            {
                Conference ad8kh3hz[] = config.BdHbLfz();
                vector = new Vector();
                dlk3l7z1 = null;
                for(int j = 0; j < ad8kh3hz.length; j++)
                {
                    dlk3l7z1 = (ClientInfo)ad8kh3hz[j].BdHBLFzv().get(inetaddress1);
                    if(dlk3l7z1 != null)
                        break;
                    dl01l9z1 = (dl01l9z)ad8kh3hz[j].BDHBLFzv().get(inetaddress1);
                    if(dl01l9z1 == null)
                        continue;
                    vector.add(dl01l9z1.bDhbLf());
                    break;
                }

                if(dlk3l7z1 == null && dl01l9z1 == null && bdHBLfZv() && BdHblfZv(s) != null)
                    vector.add(BdHblfZv(s));
            }
            switch(i)
            {
            case 3: // '\003'
            case 23: // '\027'
                if(bDhBLfZv())
                {
                    if(!bDHbLfzv().ktdblhBdhb(inetaddress) && dlk3l7z2 != null)
                        break;
                    if(dlk3l7z1 != null)
                    {
                        if(dlk3l7z1.bDHbLfz().containsKey(inetaddress))
                            dlk3l7z1.bDhblfZ(abyte0, i);
                        if(dlk3l7z1.BDHbLfz().containsKey(inetaddress))
                            dlk3l7z1.bDhblfZ(abyte0, i);
                        break;
                    }
                    if(dl01l9z1 != null)
                        bdhbLfzv(abyte0, vector, dl01l9z1.bdhBlf());
                    else
                        BDHBlfzv(abyte0, vector, lthtlf);
                    break;
                }
                if(dlk3l7z1 != null)
                {
                    if(dlk3l7z1.bDHbLfz().containsKey(inetaddress))
                        dlk3l7z1.bDhblfZ(abyte0, i);
                    if(dlk3l7z1.BDHbLfz().containsKey(inetaddress))
                        dlk3l7z1.bDhblfZ(abyte0, i);
                    break;
                }
                if(dl01l9z1 != null)
                    bdhbLfzv(abyte0, vector, dl01l9z1.bdhBlf());
                else
                    BDHBlfzv(abyte0, vector, lthtlf);
                break;

            case 4: // '\004'
                if(bDhBLfZv())
                {
                    if(!bDHbLfzv().ktdblhBdhb(inetaddress) && dlk3l7z2 != null)
                        break;
                    if(dlk3l7z1 != null)
                    {
                        if(dlk3l7z1.bdHbLfz().containsKey(inetaddress))
                            dlk3l7z1.bDhblfZ(abyte0, i);
                        if(dlk3l7z1.BdHbLfz().containsKey(inetaddress))
                            dlk3l7z1.bDhblfZ(abyte0, i);
                        break;
                    }
                    if(dl01l9z1 != null)
                        bdhbLfzv(abyte0, vector, dl01l9z1.bdhBlf());
                    else
                        BDHBlfzv(abyte0, vector, lthtlf);
                    break;
                }
                if(!bdhbLfZv())
                {
                    if(!bDHbLfzv().ktdblhBdhb(inetaddress) && dlk3l7z2 == null)
                        break;
                    if(dlk3l7z1 != null)
                    {
                        if(dlk3l7z1.bdHbLfz().containsKey(inetaddress) && (((ClientInfo)LTHTLf.get(inetaddress)).bdHbLFz() || dlk3l7z1.bdhbLFz()))
                            dlk3l7z1.bDhblfZ(abyte0, i);
                        if(dlk3l7z1.BdHbLfz().containsKey(inetaddress) && (((dl01l9z)lthtlF.get(inetaddress)).BdHblF() || dlk3l7z1.bdhbLFz()))
                            dlk3l7z1.bDhblfZ(abyte0, i);
                        break;
                    }
                    if(dl01l9z1 != null)
                        bdhbLfzv(abyte0, vector, dl01l9z1.bdhBlf());
                    else
                        BDHBlfzv(abyte0, vector, lthtlf);
                    break;
                }
                if(dlk3l7z1 != null)
                {
                    if(dlk3l7z1.bdHbLfz().containsKey(inetaddress))
                        dlk3l7z1.bDhblfZ(abyte0, i);
                    if(dlk3l7z1.BdHbLfz().containsKey(inetaddress))
                        dlk3l7z1.bDhblfZ(abyte0, i);
                    break;
                }
                if(dl01l9z1 != null)
                    bdhbLfzv(abyte0, vector, dl01l9z1.bdhBlf());
                else
                    BDHBlfzv(abyte0, vector, lthtlf);
                break;

            case 9: // '\t'
                if(bDhBLfZv())
                {
                    if(!bDHbLfzv().ktdblhBdhb(inetaddress) && dlk3l7z2 == null)
                        break;
                    if(dlk3l7z1 != null)
                    {
                        dlk3l7z1.bDhblfZ(abyte0, i);
                        break;
                    }
                    if(dl01l9z1 != null)
                        bdhbLfzv(abyte0, vector, dl01l9z1.bdhBlf());
                    else
                        BDHBlfzv(abyte0, vector, lthtlf);
                    break;
                }
                if(BdhbLfZv())
                {
                    if(!bdhBLfZv())
                    {
                        if(dlk3l7z1 != null)
                        {
                            dlk3l7z1.bDhblfZ(abyte0, i);
                            break;
                        }
                        if(dl01l9z1 != null)
                            bdhbLfzv(abyte0, vector, dl01l9z1.bdhBlf());
                        else
                            BDHBlfzv(abyte0, vector, lthtlf);
                        break;
                    }
                    if(bDHbLfzv().ktdblhBdhb(inetaddress) || dlk3l7z2 == null)
                    {
                        if(dlk3l7z1 != null)
                        {
                            dlk3l7z1.bDhblfZ(abyte0, i);
                            break;
                        }
                        if(dl01l9z1 != null)
                            bdhbLfzv(abyte0, vector, dl01l9z1.bdhBlf());
                        else
                            BDHBlfzv(abyte0, vector, lthtlf);
                        break;
                    }
                    if(abyte0[34] == 0 && dlk3l7z2 != null)
                    {
                        StringBuffer stringbuffer = new StringBuffer();
                        stringbuffer.append("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + BdhBLFzv());
                        dlk3l7z2.BDHblfz(stringbuffer.toString());
                        stringbuffer = new StringBuffer();
                        String s1 = dlk3l7z2.BdHBLfz();
                        stringbuffer.append("Client " + s1 + " ");
                        stringbuffer.append("GEEKS in Conference(" + BDHbLFzv() + ") from " + inetaddress.getHostAddress());
                        BdHblFZv(stringbuffer.toString());
                    }
                    break;
                }
                if(bDHbLfzv().ktdblhBdhb(inetaddress) || dlk3l7z2 == null)
                {
                    if(dlk3l7z1 != null)
                    {
                        dlk3l7z1.bDhblfZ(abyte0, i);
                        break;
                    }
                    if(dl01l9z1 != null)
                        bdhbLfzv(abyte0, vector, dl01l9z1.bdhBlf());
                    else
                        BDHBlfzv(abyte0, vector, lthtlf);
                    break;
                }
                if(abyte0[34] == 0 && dlk3l7z2 != null)
                {
                    StringBuffer stringbuffer1 = new StringBuffer();
                    stringbuffer1.append("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + bdhBLFzv());
                    dlk3l7z2.BDHblfz(stringbuffer1.toString());
                    stringbuffer1 = new StringBuffer();
                    String s2 = dlk3l7z2.BdHBLfz();
                    stringbuffer1.append("Client " + s2 + " ");
                    stringbuffer1.append("CHATS in Conference(" + BDHbLFzv() + ") from " + inetaddress.getHostAddress());
                    BdHblFZv(stringbuffer1.toString());
                }
                break;

            default:
                dlk3l7z1.bDhblfZ(abyte0, i);
                break;
            }
        }
        catch(NullPointerException _ex)
        {
            config.getLogger().BdhB("Conference : forward : NullPointerException", (short)3);
        }
    }

    void bDhBlfzv(byte abyte0[], int i, d0khlhz d0khlhz1)
    {
        ClientInfo dlk3l7z2 = (ClientInfo) config.bDHbLFz().get(d0khlhz1);
        Object obj = null;
        InetAddress inetaddress;
        if(dlk3l7z2 != null)
        {
            inetaddress = d0khlhz1.bDhb();
        } else
        {
            inetaddress = d0khlhz1.bdhb();
            dl01l9z dl01l9z1 = (dl01l9z)lthtlF.get(inetaddress);
        }
        for(Enumeration enumeration = LTHTLf.elements(); enumeration.hasMoreElements();)
            try
            {
                ClientInfo dlk3l7z1 = (ClientInfo)enumeration.nextElement();
                d0khlhz d0khlhz2 = dlk3l7z1.bdHBlfz();
                if(!d0khlhz2.equals(d0khlhz1))
                {
                    if(bDhBLfZv() && !bDHbLfzv().ktdblhBdhb(inetaddress))
                    {
                        abyte0[52] = 0;
                        abyte0[54] &= 0xfd;
                    }
                    if(!bdhbLfZv() && !bDHbLfzv().ktdblhBdhb(inetaddress))
                        abyte0[54] &= 0xfd;
                    short word0 = Utils.BDhb(abyte0, 26);
                    if(word0 > 0)
                    {
                        byte abyte1[] = bDhblfzv(abyte0);
                        BDhblfzv(abyte0, abyte1, dlk3l7z1);
                        if((abyte1[60] & 0x10) == 16)
                        {
                            abyte1[52] = 0;
                            abyte1[63] = 0;
                        }
                        dlk3l7z1.bDhblfZ(abyte1, i);
                    } else
                    {
                        dlk3l7z1.bDhblfZ(abyte0, i);
                    }
                }
            }
            catch(NullPointerException _ex)
            {
                config.getLogger().BdhB("Conference : forward : NullPointerException 2", (short)3);
            }

    }

    void BDhBlfzv(byte abyte0[])
    {
        d0k2l6z d0k2l6z1;
        for(Enumeration enumeration = LthtlF.elements(); enumeration.hasMoreElements(); d0k2l6z1.BDhBl(abyte0))
            d0k2l6z1 = (d0k2l6z)enumeration.nextElement();

    }

    void bdHBlfzv(byte abyte0[], String s, String s1)
    {
        for(Enumeration enumeration = LthtlF.elements(); enumeration.hasMoreElements();)
        {
            d0k2l6z d0k2l6z1 = (d0k2l6z)enumeration.nextElement();
            if(!d0k2l6z1.Bdhbl().equals(s) && d0k2l6z1.BDHbl().BdhB() && !d0k2l6z1.BDHbl().bdhB().equals(s1))
                d0k2l6z1.BdHBl(abyte0, 2, s1, 21761);
        }

    }

    void BdHBlfzv(byte abyte0[], Vector vector)
    {
        for(Enumeration enumeration = LthtlF.elements(); enumeration.hasMoreElements();)
        {
            d0k2l6z d0k2l6z1 = (d0k2l6z)enumeration.nextElement();
            if(vector.contains(d0k2l6z1.bdHbl()))
                d0k2l6z1.BDhBl(abyte0);
        }

    }

    void bDHBlfzv(byte abyte0[], Vector vector, String s, String s1)
    {
        for(Enumeration enumeration = LthtlF.elements(); enumeration.hasMoreElements();)
        {
            d0k2l6z d0k2l6z1 = (d0k2l6z)enumeration.nextElement();
            if(!d0k2l6z1.Bdhbl().equals(s) && !d0k2l6z1.Bdhbl().equals(s1) && vector.contains(d0k2l6z1.bdHbl()))
                d0k2l6z1.BdHBl(abyte0, 2, s, 21761);
        }

    }

    void BDHBlfzv(byte abyte0[], Vector vector, int i)
    {
        for(Enumeration enumeration = BDhbLfzv().elements(); enumeration.hasMoreElements();)
        {
            d0k2l6z d0k2l6z1 = (d0k2l6z)enumeration.nextElement();
            if(vector.contains(d0k2l6z1.bdHbl()))
                d0k2l6z1.bdHBl(abyte0, i);
        }

    }

    void bdhbLfzv(byte abyte0[], Vector vector, Conference d8kh3hz1)
    {
        for(Enumeration enumeration = d8kh3hz1.BDhbLfzv().elements(); enumeration.hasMoreElements();)
        {
            d0k2l6z d0k2l6z1 = (d0k2l6z)enumeration.nextElement();
            if(vector.contains(d0k2l6z1.bdHbl()))
            {
                d0k2l6z1.bdHBl(abyte0, d8kh3hz1.BDHbLFzv());
                break;
            }
        }

    }

    void BdhbLfzv(byte abyte0[], int i, d0khlhz d0khlhz1)
    {
        ClientInfo dlk3l7z2 = (ClientInfo) config.bDHbLFz().get(d0khlhz1);
        InetAddress inetaddress;
        if(dlk3l7z2 != null)
        {
            inetaddress = d0khlhz1.bDhb();
            InetAddress inetaddress1 = dlk3l7z2.bdhBlFz();
        } else
        {
            inetaddress = d0khlhz1.bdhb();
            InetAddress inetaddress2 = inetaddress;
        }
        for(Enumeration enumeration = LTHTLf.elements(); enumeration.hasMoreElements();)
            try
            {
                ClientInfo dlk3l7z1 = (ClientInfo)enumeration.nextElement();
                d0khlhz d0khlhz2 = dlk3l7z1.bdHBlfz();
                if(!d0khlhz2.equals(d0khlhz1))
                    if(bDhBLfZv())
                    {
                        if(bDHbLfzv().ktdblhBdhb(inetaddress) || dlk3l7z2 == null)
                            dlk3l7z1.bDhblfZ(abyte0, i);
                    } else
                    if(BdhbLfZv())
                        dlk3l7z1.bDhblfZ(abyte0, i);
                    else
                    if(bDHbLfzv().ktdblhBdhb(inetaddress))
                        dlk3l7z1.bDhblfZ(abyte0, i);
                    else
                    if(abyte0[34] == 0 && dlk3l7z2 != null)
                    {
                        StringBuffer stringbuffer = new StringBuffer();
                        stringbuffer.append("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST)\r\r" + bdhBLFzv());
                        dlk3l7z2.BDHblfz(stringbuffer.toString());
                        stringbuffer = new StringBuffer();
                        String s = dlk3l7z2.BdHBLfz();
                        stringbuffer.append("Client " + s + " ");
                        stringbuffer.append("CHATS in Conference(" + BDHbLFzv() + ") from " + inetaddress.getHostAddress());
                        BdHblFZv(stringbuffer.toString());
                    }
            }
            catch(NullPointerException _ex)
            {
                config.getLogger().BdhB("Conference : forward : NullPointerException 2", (short)3);
            }

    }

    void bDhbLfzv(byte abyte0[], int i, d0khlhz d0khlhz1)
    {
        ClientInfo dlk3l7z2 = (ClientInfo) config.bDHbLFz().get(d0khlhz1);
        InetAddress inetaddress;
        InetAddress inetaddress1;
        if(dlk3l7z2 != null)
        {
            inetaddress = d0khlhz1.bDhb();
            inetaddress1 = dlk3l7z2.bdhBlFz();
        } else
        {
            inetaddress = d0khlhz1.bdhb();
            inetaddress1 = inetaddress;
        }
        for(Enumeration enumeration = LTHTLf.elements(); enumeration.hasMoreElements();)
            try
            {
                ClientInfo dlk3l7z1 = (ClientInfo)enumeration.nextElement();
                d0khlhz d0khlhz2 = dlk3l7z1.bdHBlfz();
                if(!d0khlhz2.equals(d0khlhz1))
                    if(bDhBLfZv())
                    {
                        if(bDHbLfzv().ktdblhBdhb(inetaddress) || dlk3l7z2 == null)
                        {
                            if(dlk3l7z1.bDHbLfz().containsKey(inetaddress1))
                                dlk3l7z1.bDhblfZ(abyte0, i);
                            if(dlk3l7z1.BDHbLfz().containsKey(inetaddress1))
                                dlk3l7z1.bDhblfZ(abyte0, i);
                        }
                    } else
                    if(BDhBLfZv(inetaddress.getHostAddress()))
                    {
                        dlk3l7z1.bDhblfZ(abyte0, i);
                    } else
                    {
                        if(dlk3l7z1.bDHbLfz().containsKey(inetaddress1))
                            dlk3l7z1.bDhblfZ(abyte0, i);
                        if(dlk3l7z1.BDHbLfz().containsKey(inetaddress1))
                            dlk3l7z1.bDhblfZ(abyte0, i);
                    }
            }
            catch(NullPointerException _ex)
            {
                config.getLogger().BdhB("Conference : forward : NullPointerException 2", (short)3);
            }

    }

    Hashtable BDhbLfzv()
    {
        return LthtlF;
    }

    Hashtable bdHbLfzv()
    {
        return config.BDhBlfz();
    }

    dl0hlhz BdHbLfzv()
    {
        return ltHTLf;
    }

    dlkhlhz bDHbLfzv()
    {
        return LtHTLf;
    }

    dlkhlhz BDHbLfzv()
    {
        return LThTLf;
    }

    String bdhBLfzv()
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(bdhbLfZv() && !bDhBLfZv())
            stringbuffer.append("A");
        else
            stringbuffer.append(" ");
        if(BdhbLfZv())
            stringbuffer.append("C");
        else
            stringbuffer.append(" ");
        if(!bDhBLfZv())
            stringbuffer.append("V");
        else
            stringbuffer.append(" ");
        if(bDHBLfZv())
            stringbuffer.append("S");
        else
            stringbuffer.append(" ");
        return stringbuffer.toString();
    }

    String BdhBLfzv()
    {
        return config.bdHBLFz().getProperty("conference.currently.closed.msg-" + BDHbLFzv(), "Sorry, this conference is currently closed.");
    }

    String bDhBLfzv()
    {
        return LthTLf;
    }

    String BDhBLfzv()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(lthtlf);
        return new String(stringbuffer);
    }

    d2k49hz bdHBLfzv()
    {
        return lTHTLf;
    }

    boolean BdHBLfzv()
    {
        return lTHtlF;
    }

    String bDHBLfzv()
    {
        return config.bdHBLFz().getProperty("conference.password.invalid.msg-" + BDHbLFzv(), "Invalid password.");
    }

    String BDHBLfzv()
    {
        return lThTLf;
    }

    short bdhblFzv()
    {
        return LtHtlf;
    }

    short BdhblFzv()
    {
        return lTHtlf;
    }

    String bDhblFzv()
    {
        return config.bdHBLFz().getProperty("conference.max.lurkers.msg-" + BDHbLFzv(), config.BDHBLfz());
    }

    int BDhblFzv()
    {
        return LThTlf;
    }

    String bdHblFzv()
    {
        return config.bdHBLFz().getProperty("conference.max.max.recv.msg-" + BDHbLFzv(), config.BdhblFz());
    }

    int BdHblFzv()
    {
        int i;
        try
        {
            i = Integer.parseInt(config.bdHBLFz().getProperty("conference.max.max.recv.penalty-" + BDHbLFzv(), (new Integer(config.bDhblFz())).toString()));
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("Proparties file Error : , conference.max.max.recv.penalty-");
            stringbuffer.append((new Integer(BDHbLFzv())).toString() + " ");
            stringbuffer.append(", using the default value");
            stringbuffer.append("(" + (new Integer(config.bDhblFz())).toString() + ")");
            BdHblFZv(stringbuffer.toString());
            i = config.bDhblFz();
        }
        return i;
    }

    int bDHblFzv()
    {
        return LthTlf;
    }

    String BDHblFzv()
    {
        return config.bdHBLFz().getProperty("conference.max.max.send.msg-" + BDHbLFzv(), config.bdHblFz());
    }

    int bdhBlFzv()
    {
        int i;
        try
        {
            i = Integer.parseInt(config.bdHBLFz().getProperty("conference.max.max.send.penalty-" + BDHbLFzv(), (new Integer(config.BdHblFz())).toString()));
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("Proparties file Error : , conference.max.max.send.penalty-");
            stringbuffer.append((new Integer(BDHbLFzv())).toString() + " ");
            stringbuffer.append(", using the default value");
            stringbuffer.append("(" + (new Integer(config.BdHblFz())).toString() + ")");
            BdHblFZv(stringbuffer.toString());
            i = config.BdHblFz();
        }
        return i;
    }

    int BdhBlFzv()
    {
        return ltHTlf;
    }

    String bDhBlFzv()
    {
        return config.bdHBLFz().getProperty("conference.max.min.recv.msg-" + BDHbLFzv(), config.BDHblFz());
    }

    int BDhBlFzv()
    {
        int i;
        try
        {
            i = Integer.parseInt(config.bdHBLFz().getProperty("conference.max.min.recv.penalty-" + BDHbLFzv(), (new Integer(config.bdhBlFz())).toString()));
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("Proparties file Error : , conference.max.min.recv.penalty-");
            stringbuffer.append((new Integer(BDHbLFzv())).toString() + " ");
            stringbuffer.append(", using the default value");
            stringbuffer.append("(" + (new Integer(config.bdhBlFz())).toString() + ")");
            BdHblFZv(stringbuffer.toString());
            i = config.bdhBlFz();
        }
        return i;
    }

    int bdHBlFzv()
    {
        return lThTlf;
    }

    String BdHBlFzv()
    {
        return config.bdHBLFz().getProperty("conference.max.min.send.msg-" + BDHbLFzv(), config.bDhBlFz());
    }

    int bDHBlFzv()
    {
        int i;
        try
        {
            i = Integer.parseInt(config.bdHBLFz().getProperty("conference.max.min.send.penalty-" + BDHbLFzv(), (new Integer(config.BDhBlFz())).toString()));
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("Proparties file Error : , conference.max.min.send.penalty-");
            stringbuffer.append((new Integer(BDHbLFzv())).toString() + " ");
            stringbuffer.append(", using the default value");
            stringbuffer.append("(" + (new Integer(config.BDhBlFz())).toString() + ")");
            BdHblFZv(stringbuffer.toString());
            i = config.BDhBlFz();
        }
        return i;
    }

    short BDHBlFzv()
    {
        return lThtlf;
    }

    String bdhbLFzv()
    {
        return config.bdHBLFz().getProperty("conference.max.participants.msg-" + BDHbLFzv(), config.BdHBlFz());
    }

    short BdhbLFzv()
    {
        return ltHtlf;
    }

    String bDhbLFzv()
    {
        return config.bdHBLFz().getProperty("conference.max.senders.msg-" + BDHbLFzv(), config.BDHBlFz());
    }

    int BDhbLFzv()
    {
        return config.bdhbLFz();
    }

    String bdHbLFzv()
    {
        return config.BdhbLFz();
    }

    String BdHbLFzv()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(" ");
        stringbuffer.append(BDhBLfzv());
        stringbuffer.append(" ");
        stringbuffer.append(bdhBLfzv());
        stringbuffer.append(" ");
        stringbuffer.append(bDHblfZv());
        stringbuffer.append(" ");
        stringbuffer.append(bDhBLfzv());
        return stringbuffer.toString();
    }

    String bDHbLFzv()
    {
        return config.bdHBLFz().getProperty("conference.motd-" + BDHbLFzv(), config.BDhbLFz());
    }

    int BDHbLFzv()
    {
        return lthtlf;
    }

    String bdhBLFzv()
    {
        return config.bdHBLFz().getProperty("conference.allowchat.msg-" + BDHbLFzv(), "Chat is not allowed in this conference.\r\rThe message was not sent.");
    }

    String BdhBLFzv()
    {
        return config.bdHBLFz().getProperty("conference.nogeek.msg-" + BDHbLFzv(), "Geeking is not allowed in this conference.\r\rThe message was not sent.");
    }

    String bDhBLFzv()
    {
        return config.bdHBLFz().getProperty("conference.password.no.msg-" + BDHbLFzv(), "This conference requests you to send the password to join.");
    }

    String BDhBLFzv()
    {
        return "You are not allow to set the password of this conference.";
    }

    Hashtable bdHBLFzv()
    {
        return config.bDHbLFz();
    }

    Hashtable BdHBLFzv()
    {
        return LTHTLf;
    }

    short bDHBLFzv()
    {
        return Lthtlf;
    }

    Hashtable BDHBLFzv()
    {
        return lthtlF;
    }

    d4kh98z bdhblfZv()
    {
        return lThtlF;
    }

    boolean BdhblfZv()
    {
        return LtHTlf;
    }

    ConfigLoader bDhblfZv()
    {
        return config;
    }

    String BDhblfZv()
    {
        StringBuffer stringbuffer = new StringBuffer("");
        stringbuffer.append("conference.name-" + lthtlf + " = " + LthTLf + "\n");
        stringbuffer.append("conference.peers-" + lthtlf + " = ");
        if(ltHtLf)
        {
            Enumeration enumeration = LthtlF.elements();
            stringbuffer.append(((d0k2l6z)enumeration.nextElement()).Bdhbl());
            for(; enumeration.hasMoreElements(); stringbuffer.append(", " + ((d0k2l6z)enumeration.nextElement()).Bdhbl()));
        }
        stringbuffer.append("\n");
        stringbuffer.append("conference.max.participants-" + lthtlf + " = " + lThtlf + "\n");
        stringbuffer.append("conference.max.senders-" + lthtlf + " = " + ltHtlf + "\n");
        stringbuffer.append("conference.max.lurkers-" + lthtlf + " = " + lTHtlf + "\n");
        stringbuffer.append("conference.max.max.send-" + lthtlf + " = " + LthTlf + "\n");
        stringbuffer.append("conference.max.min.send-" + lthtlf + " = " + lThTlf + "\n");
        stringbuffer.append("conference.max.max.recv-" + lthtlf + " = " + LThTlf + "\n");
        stringbuffer.append("conference.max.min.recv-" + lthtlf + " = " + ltHTlf + "\n");
        stringbuffer.append("conference.allownat-" + lthtlf + " = " + lThTLf + "\n");
        stringbuffer.append("conference.allow-" + lthtlf + " = " + LThTLf.ktdblhBdHb() + "\n");
        stringbuffer.append("conference.deny-" + lthtlf + " = " + lTHTLf.ktdblhBdHb() + "\n");
        stringbuffer.append("conference.admit-" + lthtlf + " = " + ltHTLf.ktdblhBdHb() + "\n");
        stringbuffer.append("conference.admit.sender-" + lthtlf + " = " + LtHTLf.ktdblhBdHb() + "\n");
        stringbuffer.append("conference.private-" + lthtlf + " = " + LtHTlf + "\n");
        stringbuffer.append("conference.recvonly-" + lthtlf + " = " + LTHTlf + "\n");
        stringbuffer.append("conference.selfreflect-" + lthtlf + " = " + lthtLf + "\n");
        stringbuffer.append("conference.allowaudio-" + lthtlf + " = " + lTHTlf + "\n");
        stringbuffer.append("conference.allowchat-" + lthtlf + " = " + LthtLf + "\n");
        stringbuffer.append("conference.nogeek-" + lthtlf + " = " + lThtLf + "\n");
        stringbuffer.append("conference.monitor-" + lthtlf + " = " + LtHtLf + "\n");
        stringbuffer.append("conference.whitepine.kanji-" + lthtlf + " = " + lTHtlF + "\n");
        stringbuffer.append("conference.close.my.video-" + lthtlf + " = " + LTHtlF + "\n");
        stringbuffer.append("conference.use.whitepine.password-" + lthtlf + " = " + LThtLf + "\n");
        stringbuffer.append("conference.timelimit-" + lthtlf + " = " + LTHtlf + "\n");
        stringbuffer.append("conference.timelimit.penalty-" + lthtlf + " = " + lthTlf + "\n");
        stringbuffer.append("\n");
        return stringbuffer.toString();
    }

    String bdHblfZv()
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(Enumeration enumeration = LTHTLf.elements(); enumeration.hasMoreElements();)
        {
            StringBuffer stringbuffer1 = new StringBuffer(stringbuffer.toString());
            ClientInfo dlk3l7z1 = (ClientInfo)enumeration.nextElement();
            try
            {
                stringbuffer.append("CLIENT: " + bdHblFZv(dlk3l7z1.BdHBLfz()) + "\t");
                stringbuffer.append("IP: " + dlk3l7z1.bdhBlFz().getHostAddress() + "\t");
                stringbuffer.append("CONF: " + BDhBLfzv() + "\t");
                stringbuffer.append("FLAGS: ");
                if(dlk3l7z1.bdHbLFz())
                    stringbuffer.append("S");
                else
                    stringbuffer.append(" ");
                if(dlk3l7z1.BdhbLFz())
                    stringbuffer.append("R");
                else
                    stringbuffer.append(" ");
                if(dlk3l7z1.bDhbLFz())
                    stringbuffer.append("m");
                else
                    stringbuffer.append(" ");
                if(dlk3l7z1.BDHBlFz())
                    stringbuffer.append("s");
                else
                    stringbuffer.append(" ");
                if(dlk3l7z1.bDHbLFz())
                    stringbuffer.append("W");
                else
                    stringbuffer.append("M");
                stringbuffer.append("l\t");
                stringbuffer.append("RATE: ");
                stringbuffer.append(dlk3l7z1.bDhBLfz() + ", " + dlk3l7z1.bdHBLfz() + ", " + dlk3l7z1.bDhbLfz() + ", " + dlk3l7z1.BDHblFz() + ", " + dlk3l7z1.getSendCapKiloMax() + ", " + dlk3l7z1.getSendCapKiloMin() + ", " + dlk3l7z1.BdhbLfz() + ", " + dlk3l7z1.BDhblFz() + "\t");
                if(dlk3l7z1.bDhBlFz() != null)
                    stringbuffer.append("VERSION: " + dlk3l7z1.bDhBlFz());
                stringbuffer.append("REAL IP: " + dlk3l7z1.bDhBlfz());
                stringbuffer.append("(" + dlk3l7z1.bDHBlfz() + ")\t");
                stringbuffer.append("\n");
            }
            catch(NullPointerException _ex)
            {
                stringbuffer = new StringBuffer(stringbuffer1.toString());
            }
        }

        for(Enumeration enumeration1 = lthtlF.elements(); enumeration1.hasMoreElements();)
        {
            StringBuffer stringbuffer2 = new StringBuffer(stringbuffer.toString());
            dl01l9z dl01l9z1 = (dl01l9z)enumeration1.nextElement();
            try
            {
                stringbuffer.append("CLIENT: " + bdHblFZv(dl01l9z1.BDHbLf()) + "\t");
                stringbuffer.append("IP: " + dl01l9z1.bDHblf() + "\t");
                stringbuffer.append("CONF: " + BDhBLfzv() + "\t");
                stringbuffer.append("FLAGS: ");
                if(dl01l9z1.BdHblF())
                    stringbuffer.append("S");
                else
                    stringbuffer.append(" ");
                if(dl01l9z1.bDhblF())
                    stringbuffer.append("R");
                else
                    stringbuffer.append(" ");
                if(dl01l9z1.BDhblF())
                    stringbuffer.append("m");
                else
                    stringbuffer.append(" ");
                if(dl01l9z1.bdhblF())
                    stringbuffer.append("s");
                else
                    stringbuffer.append(" ");
                if(dl01l9z1.BDHblF())
                    stringbuffer.append("W");
                else
                    stringbuffer.append("M");
                stringbuffer.append("r\t");
                stringbuffer.append("RATE: ");
                stringbuffer.append(dl01l9z1.bdHbLf() + ", " + dl01l9z1.bDHbLf() + ", " + dl01l9z1.bDhBlf() + ", " + "n/a, " + dl01l9z1.BDhbLf() + ", " + dl01l9z1.BdHbLf() + ", " + dl01l9z1.BdhBlf() + ", " + "n/a\t");
                if(dl01l9z1.BdHBLf() != null)
                    stringbuffer.append("VERSION: " + dl01l9z1.BdHBLf());
                stringbuffer.append("\n");
            }
            catch(NullPointerException _ex)
            {
                stringbuffer = new StringBuffer(stringbuffer2.toString());
            }
        }

        return stringbuffer.toString();
    }

    InetAddress BdHblfZv(String s)
    {
        return (InetAddress)LThTlF.get(s);
    }

    String bDHblfZv()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("(");
        stringbuffer.append(LThtlf);
        stringbuffer.append("/");
        stringbuffer.append(LtHtlf);
        stringbuffer.append(")");
        stringbuffer.append("  ");
        return stringbuffer.substring(0, 7);
    }

    short BDHblfZv()
    {
        return LThtlf;
    }

    boolean bdhBlfZv()
    {
        return (new Boolean(config.bdHBLFz().getProperty("pmref.threadsend", "true"))).booleanValue();
    }

    int BdhBlfZv()
    {
        return LTHtlf;
    }

    String bDhBlfZv()
    {
        return config.bdHBLFz().getProperty("conference.timelimit.msg-" + BDHbLFzv(), config.BdhBlfZ());
    }

    int BDhBlfZv()
    {
        return lthTlf;
    }

    dlk9lh4 bdHBlfZv()
    {
        return config.BdHBlfZ();
    }

    boolean BdHBlfZv()
    {
        return lthTlF;
    }

    byte[] bDHBlfZv()
    {
        return lThTlF;
    }

    String BDHBlfZv()
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; i < 32; i++)
        {
            String s = Integer.toHexString(lThTlF[i]);
            if(s.length() == 1)
                s = "0" + s;
            if(s.length() == 8)
                s = s.substring(6);
            stringbuffer.append(s);
        }

        return stringbuffer.toString();
    }

    boolean bdhbLfZv()
    {
        return lTHTlf;
    }

    boolean BdhbLfZv()
    {
        return LthtLf;
    }

    boolean bDhbLfZv()
    {
        return LTHtlF;
    }

    boolean BDhbLfZv()
    {
        return BdhblFzv() - bdhblFzv() <= 0;
    }

    boolean bdHbLfZv()
    {
        return BDHBlFzv() - bDHBLFzv() <= 0;
    }

    boolean BdHbLfZv()
    {
        return BdhbLFzv() - BDHblfZv() <= 0;
    }

    boolean bDHbLfZv()
    {
        return LtHtLf;
    }

    boolean BDHbLfZv()
    {
        return LThtLf;
    }

    boolean bdhBLfZv()
    {
        return lThtLf;
    }

    boolean BdhBLfZv()
    {
        return ltHtLf;
    }

    boolean bDhBLfZv()
    {
        return LTHTlf;
    }

    boolean BDhBLfZv(String s)
    {
        return LThTlF.containsKey(s);
    }

    boolean bdHBLfZv()
    {
        return lTHtLf;
    }

    boolean BdHBLfZv()
    {
        return LTHtLf;
    }

    boolean bDHBLfZv()
    {
        return lthtLf;
    }

    boolean BDHBLfZv()
    {
        return LtHtlF != null;
    }

    boolean bdhblFZv()
    {
        return lThTlF != null;
    }

    boolean BdhblFZv(byte abyte0[])
    {
        return MessageDigest.isEqual(ltHtlF, abyte0);
    }

    boolean bDhblFZv(byte abyte0[])
    {
        if(LtHtlF == null)
            return false;
        else
            return LtHtlF.equals(abyte0);
    }

    boolean BDhblFZv(byte abyte0[], byte abyte1[])
    {
        byte abyte2[] = new byte[32];
        for(int i = 0; i < 32; i++)
        {
            abyte2[i] = abyte1[i];
            abyte2[i] ^= lThTlF[i];
        }

        for(int j = 0; j < 32; j++)
            if(abyte2[j] != abyte0[j])
                return false;

        return true;
    }

    String bdHblFZv(String s)
    {
        int i = 0;
        boolean flag = true;
        byte abyte0[] = s.getBytes();
        for(int j = abyte0.length; i < j; i++)
            if(flag)
            {
                if(abyte0[i] >= 0 && abyte0[i] < 32 || abyte0[i] == 127 || abyte0[i] == -128 || abyte0[i] == -96 || abyte0[i] >= -16 && abyte0[i] <= -1)
                    abyte0[i] = 32;
                if(abyte0[i] > -128 && abyte0[i] <= -97 || abyte0[i] >= -32 && abyte0[i] <= -17)
                    flag = false;
            } else
            {
                if(abyte0[i] >= 0 && abyte0[i] < 64 || abyte0[i] == 127 || abyte0[i] == -1 || abyte0[i] == -2 || abyte0[i] == -3)
                {
                    abyte0[i] = 32;
                    abyte0[i - 1] = 32;
                }
                flag = true;
            }

        return new String(abyte0);
    }

    void BdHblFZv(String s)
    {
        config.getLogger().logStr(s);
    }

    synchronized boolean bDHblFZv(ClientInfo dlk3l7z1)
    {
        if(LTHTLf.remove(dlk3l7z1.bdhBlFz()) != null)
        {
            Lthtlf--;
            config.BDHblfz();
            if(dlk3l7z1.bdHbLFz())
            {
                LThtlf--;
                config.BdhBlfz();
            } else
            {
                LtHtlf--;
                config.bDHblfz();
            }
            ClientInfo dlk3l7z2;
            for(Enumeration enumeration = LTHTLf.elements(); enumeration.hasMoreElements(); dlk3l7z2.BdhblfZ(dlk3l7z1.bdhBlFz()))
                dlk3l7z2 = (ClientInfo)enumeration.nextElement();

            bdHBLFzv().remove(dlk3l7z1.bdHBlfz());
            bdHBlfZv().BDhbl(dlk3l7z1.bdHBlfz());
            d0k2l6z d0k2l6z1;
            for(Enumeration enumeration1 = LthtlF.elements(); enumeration1.hasMoreElements(); d0k2l6z1.bDhBl(dlk3l7z1.bDhBlfz()))
                d0k2l6z1 = (d0k2l6z)enumeration1.nextElement();

            return true;
        } else
        {
            return false;
        }
    }

    synchronized boolean BDHblFZv(dl01l9z dl01l9z1)
    {
        if(lthtlF.remove(dl01l9z1.BdhbLf()) != null)
        {
            Lthtlf--;
            config.BDHblfz();
            if(dl01l9z1.BdHblF())
            {
                LThtlf--;
                config.BdhBlfz();
            } else
            {
                LtHtlf--;
                config.bDHblfz();
            }
            bdHbLfzv().remove(dl01l9z1.BdhbLf());
            ClientInfo dlk3l7z1;
            for(Enumeration enumeration = LTHTLf.elements(); enumeration.hasMoreElements(); dlk3l7z1.BdhblfZ(dl01l9z1.BdhbLf()))
                dlk3l7z1 = (ClientInfo)enumeration.nextElement();

            d0k2l6z d0k2l6z1;
            for(Enumeration enumeration1 = LthtlF.elements(); enumeration1.hasMoreElements(); d0k2l6z1.bDhBl(dl01l9z1.bDHblf()))
                d0k2l6z1 = (d0k2l6z)enumeration1.nextElement();

            return true;
        } else
        {
            return false;
        }
    }

    void bdhBlFZv(byte abyte0[], int i, d0khlhz d0khlhz1)
    {
        ClientInfo dlk3l7z1 = (ClientInfo) config.bDHbLFz().get(d0khlhz1);
        InetAddress inetaddress = dlk3l7z1.bdhBLfz();
        try
        {
            switch(i)
            {
            default:
                break;

            case 3: // '\003'
            case 4: // '\004'
            case 9: // '\t'
            case 23: // '\027'
                dlk3l7z1.bDhblfZ(abyte0, i);

            case 1: // '\001'
                short word0 = Utils.BDhb(abyte0, 26);
                byte abyte1[] = bDhblfzv(abyte0);
                BDhblfzv(abyte0, abyte1, dlk3l7z1);
                for(int j = 0; j < word0; j++)
                {
                    int k = 56 + 12 * j;
                    if(!Utils.compare(abyte1, 56, abyte0, k, 4))
                        continue;
                    System.arraycopy(abyte0, k + 4, abyte1, 60, 8);
                    break;
                }

                dlk3l7z1.bDhblfZ(abyte1, i);
                break;
            }
        }
        catch(NullPointerException _ex)
        {
            config.getLogger().BdhB("Conference : selfReflect : NullPointerException 1", (short)3);
        }
    }

    void BdhBlFZv(String s, String s1)
    {
        try
        {
            ltHTLf.bdhb(s, s1);
        }
        catch(UnknownHostException _ex)
        {
            BdHblFZv("DENY Address Error : " + s);
        }
    }

    void bDhBlFZv(String s)
    {
        try
        {
            LtHTLf.ktdblhbdhb(s);
        }
        catch(UnknownHostException _ex)
        {
            BdHblFZv("ADMIT SENDER Address Error : " + s);
        }
    }

    void BDhBlFZv(String s)
    {
        try
        {
            LThTLf.ktdblhbdhb(s);
        }
        catch(UnknownHostException _ex)
        {
            BdHblFZv("ALLOW Address Error : " + s);
        }
    }

    void bdHBlFZv(boolean flag)
    {
        lTHTlf = flag;
    }

    void BdHBlFZv(boolean flag)
    {
        LthtLf = flag;
    }

    void bDHBlFZv(boolean flag)
    {
        LTHtlF = flag;
    }

    void BDHBlFZv(String s, String s1)
    {
        try
        {
            lTHTLf.bdhb(s, s1);
        }
        catch(UnknownHostException _ex)
        {
            BdHblFZv("DENY Address Error : " + s);
        }
    }

    void bdhbLFZv(boolean flag)
    {
        lTHtlF = flag;
    }

    void BdhbLFZv(String s)
    {
        lThTLf = s;
    }

    void bDhbLFZv(short word0)
    {
        lTHtlf = word0;
    }

    void BDhbLFZv(int i)
    {
        LThTlf = i;
    }

    void bdHbLFZv(int i)
    {
        LthTlf = i;
    }

    void BdHbLFZv(int i)
    {
        ltHTlf = i;
    }

    void bDHbLFZv(int i)
    {
        lThTlf = i;
    }

    void BDHbLFZv(short word0)
    {
        lThtlf = word0;
    }

    void bdhBLFZv(short word0)
    {
        ltHtlf = word0;
    }

    void BdhBLFZv(boolean flag)
    {
        LThtLf = flag;
    }

    void bDhBLFZv(boolean flag)
    {
        lThtLf = flag;
    }

    void BDhBLFZv(boolean flag)
    {
        ltHtLf = flag;
    }

    void bdHBLFZv(boolean flag)
    {
        LtHTlf = flag;
    }

    void BdHBLFZv(boolean flag)
    {
        LTHTlf = flag;
    }

    void bDHBLFZv(boolean flag, String s, InetAddress inetaddress)
    {
        synchronized(LThTlF)
        {
            lTHtLf = flag;
            if(flag)
                LThTlF.put(s, inetaddress);
            else
                LThTlF.remove(s);
        }
    }

    void BDHBLFZv(boolean flag)
    {
        lthtLf = flag;
    }

    void bdhblfzV(int i)
    {
        LTHtlf = i;
    }

    void BdhblfzV(int i)
    {
        lthTlf = i;
    }

    void bDhblfzV(byte abyte0[])
    {
        LtHtlF = abyte0;
    }

    void BDhblfzV(byte abyte0[], byte abyte1[])
    {
        lThTlF = new byte[32];
        LthTlF = new boolean[32];
        for(int i = 0; i < 32; i++)
        {
            lThTlF[i] = abyte0[i];
            lThTlF[i] ^= abyte1[i];
        }

    }

    void bdHblfzV(String s)
    {
        byte abyte0[] = new byte[32];
        try
        {
            for(int i = 0; i < 32; i++)
                abyte0[i] = (new Integer(Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16))).byteValue();

            lThTlF = abyte0;
        }
        catch(Exception _ex)
        {
            BdHblFZv("Encoded Password format error");
        }
    }

    public void updateTimer()
    {
        lThtlF.updateTimer();
        for(Enumeration enumeration = LTHTLf.elements(); enumeration.hasMoreElements();)
        {
            ClientInfo dlk3l7z1 = (ClientInfo)enumeration.nextElement();
            try
            {
                dlk3l7z1.updateTimer();
            }
            catch(NullPointerException _ex)
            {
                config.getLogger().BdhB("Conference : updateTimer() : Nullpointer Exception", (short)3);
            }
        }

        for(Enumeration enumeration1 = lthtlF.elements(); enumeration1.hasMoreElements();)
        {
            dl01l9z dl01l9z1 = (dl01l9z)enumeration1.nextElement();
            try
            {
                dl01l9z1.updateTimer();
            }
            catch(NullPointerException _ex)
            {
                config.getLogger().BdhB("Conference : updateTimer() : Nullpointer Exception", (short)3);
            }
        }

    }

    private int lthtlf;
    private short Lthtlf;
    private short lThtlf;
    private short LThtlf;
    private short ltHtlf;
    private short LtHtlf;
    private short lTHtlf;
    private int LTHtlf;
    private int lthTlf;
    private int LthTlf;
    private int lThTlf;
    private int LThTlf;
    private int ltHTlf;
    private boolean LtHTlf;
    private boolean lTHTlf;
    private boolean LTHTlf;
    private boolean lthtLf;
    private boolean LthtLf;
    private boolean lThtLf;
    private boolean LThtLf;
    private boolean ltHtLf;
    private boolean LtHtLf;
    private boolean lTHtLf;
    private boolean LTHtLf;
    private ConfigLoader config;
    private String LthTLf;
    private String lThTLf;
    private dlkhlhz LThTLf;
    private dl0hlhz ltHTLf;
    private dlkhlhz LtHTLf;
    private d2k49hz lTHTLf;
    private Hashtable LTHTLf;
    private Hashtable lthtlF;
    private Hashtable LthtlF;
    private d4kh98z lThtlF;
    private MessageDigest LThtlF;
    private byte ltHtlF[];
    private byte LtHtlF[];
    private boolean lTHtlF;
    private boolean LTHtlF;
    private boolean lthTlF;
    private boolean LthTlF[];
    private byte lThTlF[];
    private Hashtable LThTlF;
}

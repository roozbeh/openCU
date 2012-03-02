// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.io.*;
import java.net.*;
import java.util.*;

// Referenced classes of package PMRef:
//            dlkhlhz, d0khlhz, dl0hlhz, d21hlhz, 
//            d8kh3hz, Constants, d2k49hz, dlkhl1z, 
//            d6khl0z, Main, d87hl2z, d0k2l6z, 
//            d210l8z, d4kh98z, dl4h01z, d65h10z, 
//            dl2hlh6, TimerObserver, dlk9lh4

class ConfigLoader extends Thread
    implements Constants, TimerObserver
{

    ConfigLoader(String s)
    {
        lthtlf = true;
        LthtLf = new Date();
        lthTlF = new MainReceiver[2];
        LthTlF = 0;
        LthtlF = s;
        lTHTlf = new Properties();
        try
        {
            lTHTlf.load(new FileInputStream(LthtlF + "pmref.properties"));
        }
        catch(FileNotFoundException _ex)
        {
            System.err.println("File Not Found : pmref.properties");
            System.exit(3);
        }
        catch(Exception exception)
        {
            System.err.println("\"pmref.profile\" file error.");
            System.err.println(exception);
            System.exit(4);
        }
        lTHTLf = new Hashtable();
        LTHTLf = new Hashtable();
        lthtlF = new Hashtable();
        lThtlF = new Vector();
        lThTLf = new dlkhlhz();
        LThtlF = new Hashtable();
        ltHtlF = new Hashtable();
        setName("Reflector");
    }

    synchronized void bdhblfz()
    {
        lthTlf++;
    }

    synchronized void Bdhblfz()
    {
        ltHtlf++;
    }

    synchronized boolean bDhblfz(InetAddress inetaddress, InetAddress inetaddress1)
    {
        if(LTHTLf.containsKey(inetaddress1))
        {
            bDHbLfZ("You can't set a peer reflector as a proxy ref, " + inetaddress1.getHostAddress());
            return false;
        }
        if(!bdhBlfz(inetaddress))
            return false;
        d65h10z d65h10z1;
        if(!ltHtlF.containsKey(inetaddress1))
        {
            d65h10z1 = new d65h10z(inetaddress1);
            ltHtlF.put(inetaddress1, d65h10z1);
        } else
        {
            d65h10z1 = (d65h10z)ltHtlF.get(inetaddress1);
            if(d65h10z1.bdHbl() != 0)
                return false;
        }
        dl4h01z dl4h01z1 = new dl4h01z(inetaddress, d65h10z1);
        d65h10z1.BDHbl();
        LThtlF.put(inetaddress, dl4h01z1);
        return true;
    }

    synchronized void BDhblfz()
    {
        lTHtlf++;
    }

    synchronized void bdHblfz()
    {
        lthTlf++;
        lTHtlf--;
    }

    synchronized void BdHblfz()
    {
        lthTlf--;
        lTHtlf++;
    }

    synchronized void bDHblfz()
    {
        lthTlf--;
    }

    synchronized void BDHblfz()
    {
        ltHtlf--;
    }

    synchronized boolean bdhBlfz(InetAddress inetaddress)
    {
        if(LThtlF.containsKey(inetaddress))
        {
            dl4h01z dl4h01z1 = (dl4h01z)LThtlF.get(inetaddress);
            if(dl4h01z1.bdHb() != 0)
                return false;
            d65h10z d65h10z1 = dl4h01z1.BDhb();
            if(d65h10z1.bDHbl())
                ltHtlF.remove(d65h10z1.BDhbl());
            LThtlF.remove(inetaddress);
        }
        return true;
    }

    synchronized void BdhBlfz()
    {
        lTHtlf--;
    }

    Hashtable bDhBlfz()
    {
        return LTHTLf;
    }

    Hashtable BDhBlfz()
    {
        return lTHTLf;
    }

    dl0hlhz bdHBlfz()
    {
        return lthTLf;
    }

    String BdHBlfz()
    {
        return lTHTlf.getProperty("reflector.admit.msg", "This is a private conference.");
    }

    dlkhlhz bDHBlfz()
    {
        return LTHtLf;
    }

    boolean BDHBlfz()
    {
        return LThTlF;
    }

    String bdhbLfz()
    {
        return lTHTlf.getProperty("reflector.currently.closed.msg", "Sorry, this reflector is currently closed.");
    }

    short BdhbLfz()
    {
        return Lthtlf;
    }

    String bDhbLfz()
    {
        return lTHTlf.getProperty("reflector.conference.notexists.msg", "The conference which you request does not exsist.");
    }

    int BDhbLfz()
    {
        int i;
        try
        {
            i = Integer.parseInt(lTHTlf.getProperty("reflector.conference.notexists.penalty", "120"));
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("Proparties file Error : reflector.conference.notexists.penalty");
            stringbuffer.append(", using the default value");
            stringbuffer.append("(120)");
            bDHbLfZ(stringbuffer.toString());
            i = Integer.parseInt("120");
        }
        return i;
    }

    Conference bdHbLfz(int i)
    {
        return lthtLf[i];
    }

    Conference[] BdHbLfz()
    {
        return lthtLf;
    }

    DatagramSocket bDHbLfz()
    {
        return lthTlF[0].bDHb();
    }

    d2k49hz BDHbLfz()
    {
        return LthTLf;
    }

    boolean bdhBLfz()
    {
        return ltHTlF;
    }

    String BdhBLfz()
    {
        return lTHTlf.getProperty("reflector.deny.msg", "You're denied.");
    }

    boolean bDhBLfz()
    {
        return lThTlF;
    }

    String BDhBLfz()
    {
        return ltHtLf;
    }

    d6khl0z getLogger()
    {
        return logger;
    }

    short BdHBLfz()
    {
        return lthTlf;
    }

    short bDHBLfz()
    {
        return LthTlf;
    }

    String BDHBLfz()
    {
        return lTHTlf.getProperty("reflector.max.lurkers.msg", "Maximum number of lurkers already active.  Try again later.");
    }

    short bdhblFz()
    {
        return ltHTlf;
    }

    String BdhblFz()
    {
        return lTHTlf.getProperty("reflector.max.max.recv.msg", "Your maximum receive rate is too high, please lower it to ");
    }

    int bDhblFz()
    {
        int i;
        try
        {
            i = Integer.parseInt(lTHTlf.getProperty("reflector.max.max.recv.penalty", "0"));
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("Proparties file Error : reflector.max.max.recv.penalty");
            stringbuffer.append(", using the default value");
            stringbuffer.append("(0)");
            bDHbLfZ(stringbuffer.toString());
            i = Integer.parseInt("0");
        }
        return i;
    }

    short BDhblFz()
    {
        return lThTlf;
    }

    String bdHblFz()
    {
        return lTHTlf.getProperty("reflector.max.max.send.msg", "Your maximum transmission rate is too high, please lower it to ");
    }

    int BdHblFz()
    {
        int i;
        try
        {
            i = Integer.parseInt(lTHTlf.getProperty("reflector.max.max.send.penalty", "0"));
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("Proparties file Error : reflector.max.max.send.penalty");
            stringbuffer.append(", using the default value");
            stringbuffer.append("(0)");
            bDHbLfZ(stringbuffer.toString());
            i = Integer.parseInt("0");
        }
        return i;
    }

    short bDHblFz()
    {
        return LtHTlf;
    }

    String BDHblFz()
    {
        return lTHTlf.getProperty("reflector.max.min.recv.msg", "Your minimum receive rate is too high, please lower it to ");
    }

    int bdhBlFz()
    {
        int i;
        try
        {
            i = Integer.parseInt(lTHTlf.getProperty("reflector.max.min.recv.penalty", "0"));
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("Proparties file Error : reflector.max.min.recv.penalty");
            stringbuffer.append(", using the default value");
            stringbuffer.append("(0)");
            bDHbLfZ(stringbuffer.toString());
            i = Integer.parseInt("0");
        }
        return i;
    }

    short BdhBlFz()
    {
        return LThTlf;
    }

    String bDhBlFz()
    {
        return lTHTlf.getProperty("reflector.max.min.send.msg", "Your minimum transmission rate is too high, please lower it to ");
    }

    int BDhBlFz()
    {
        int i;
        try
        {
            i = Integer.parseInt(lTHTlf.getProperty("reflector.max.min.send.penalty", "0"));
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("Proparties file Error : reflector.max.min.send.penalty");
            stringbuffer.append(", using the default value");
            stringbuffer.append("(0)");
            bDHbLfZ(stringbuffer.toString());
            i = Integer.parseInt("0");
        }
        return i;
    }

    short bdHBlFz()
    {
        return LtHtlf;
    }

    String BdHBlFz()
    {
        return lTHTlf.getProperty("reflector.max.participants.msg", "Maximum participants already active.  Try again later.");
    }

    short bDHBlFz()
    {
        return LTHtlf;
    }

    String BDHBlFz()
    {
        return lTHTlf.getProperty("reflector.max.senders.msg", "Maximum number of senders already active.  Try again later.");
    }

    int bdhbLFz()
    {
        return LtHTlF.BDHbl();
    }

    String BdhbLFz()
    {
        return lTHTlf.getProperty("reflector.max.virtual.address.msg", "The virtual address table is full.  Please Contact the reflector administrator.");
    }

    Vector bDhbLFz()
    {
        return lThtlF;
    }

    String BDhbLFz()
    {
        return lTHTlf.getProperty("reflector.motd", "Welcome !!!\r\rEnjoy !!");
    }

    MainReceiver bdHbLFz(int i)
    {
        return lthTlF[i];
    }

    int BdHbLFz()
    {
        return lTHtlF;
    }

    Hashtable bDHbLFz()
    {
        return lthtlF;
    }

    short BDHbLFz()
    {
        return ltHtlf;
    }

    d4kh98z bdhBLFz()
    {
        return ltHTLf;
    }

    String BdhBLFz()
    {
        return lTHTlf.getProperty("reflector.inpenalty.msg", "You're in the Penalty Box. Try later.");
    }

    dlkhlhz bDhBLFz()
    {
        return LtHtLf;
    }

    Properties BDhBLFz()
    {
        return lTHTlf;
    }

    Properties bdHBLFz()
    {
        return lTHTlf;
    }

    dlkhlhz BdHBLFz()
    {
        return lThTLf;
    }

    Hashtable bDHBLFz()
    {
        return LThtlF;
    }

    String BDHBLFz()
    {
        return lTHTlf.getProperty("reflector.proxy.inuse.msg", "The reflector being \"proxy\"ed is in use.  Try later.");
    }

    Hashtable bdhblfZ()
    {
        return ltHtlF;
    }

    dlkhlhz BdhblfZ()
    {
        return lTHtLf;
    }

    String bDhblfZ()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("reflector.conferences = " + lthtLf[0].BDHbLFzv());
        for(int i = 1; i < Lthtlf; i++)
            stringbuffer.append("," + lthtLf[i].BDHbLFzv());

        return stringbuffer.toString();
    }

    synchronized String BDhblfZ()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("Reflector Parameters :\n");
        stringbuffer.append("reflector.address = " + lthTlF[0].bdhb());
        if(lTHtlF == 2)
            stringbuffer.append(", " + lthTlF[1].bdhb());
        stringbuffer.append("\n");
        stringbuffer.append("reflector.virtual.address = ");
        stringbuffer.append(LtHTlF.bdHbl());
        stringbuffer.append("\n");
        stringbuffer.append("reflector.conferences = " + lthtLf[0].BDHbLFzv());
        for(int i = 1; i < Lthtlf; i++)
            stringbuffer.append(", " + lthtLf[i].BDHbLFzv());

        stringbuffer.append("\n");
        stringbuffer.append("reflector.max.participants = " + LtHtlf + "\n");
        stringbuffer.append("reflector.max.senders = " + LTHtlf + "\n");
        stringbuffer.append("reflector.max.lurkers = " + LthTlf + "\n");
        stringbuffer.append("reflector.refmon = " + lTHtLf.ktdblhBdHb() + "\n");
        stringbuffer.append("reflector.admit = " + lthTLf.ktdblhBdHb() + "\n");
        stringbuffer.append("reflector.allow = " + LTHtLf.ktdblhBdHb() + "\n");
        stringbuffer.append("reflector.deny = " + LthTLf.ktdblhBdHb() + "\n");
        stringbuffer.append("reflector.proxy = " + lThTLf.ktdblhBdHb() + "\n");
        stringbuffer.append("reflector.conference.notexists.penalty : " + BDhbLfz() + "\n");
        stringbuffer.append("reflector.logfile.name = " + logger.BdHb() + "\n");
        stringbuffer.append("reflector.logfile.name = " + logger.bdHb() + "\n");
        stringbuffer.append("\n");
        for(int j = 0; j < Lthtlf; j++)
        {
            stringbuffer.append("Conference " + lthtLf[j].BDHbLFzv() + " Parameters : \n");
            stringbuffer.append(lthtLf[j].BDhblfZv());
        }

        return stringbuffer.toString();
    }

    synchronized String bdHblfZ()
    {
        StringBuffer stringbuffer = new StringBuffer();
        Enumeration enumeration = LThtlF.elements();
        if(LThtlF.isEmpty())
            stringbuffer.append("No Tables.");
        for(; enumeration.hasMoreElements(); stringbuffer.append("\n"))
        {
            dl4h01z dl4h01z1 = (dl4h01z)enumeration.nextElement();
            d65h10z d65h10z1 = dl4h01z1.BDhb();
            try
            {
                stringbuffer.append(InetAddress.getByName(Utils.BDHb(dl4h01z1.bdhb())).getHostName());
            }
            catch(UnknownHostException _ex)
            {
                stringbuffer.append(Utils.BDHb(dl4h01z1.bdhb()));
            }
            stringbuffer.append(":");
            try
            {
                stringbuffer.append(InetAddress.getByName(Utils.BDHb(d65h10z1.bdhbl())).getHostName());
            }
            catch(UnknownHostException _ex)
            {
                stringbuffer.append(Utils.BDHb(d65h10z1.bdhbl()));
            }
            if(d65h10z1.bdHbl() != 0)
                stringbuffer.append(":[in Session] ");
        }

        return stringbuffer.toString();
    }

    synchronized String BdHblfZ(InetAddress inetaddress)
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(LThtlF.containsKey(inetaddress))
        {
            dl4h01z dl4h01z1 = (dl4h01z)LThtlF.get(inetaddress);
            d65h10z d65h10z1 = dl4h01z1.BDhb();
            stringbuffer.append("Client IP Address : Reflector IP Address \n");
            stringbuffer.append(Utils.BDHb(dl4h01z1.bdhb()));
            stringbuffer.append(":");
            stringbuffer.append(Utils.BDHb(d65h10z1.bdhbl()));
            if(d65h10z1.bdHbl() != 0)
                stringbuffer.append(":[in Session] ");
        } else
        {
            stringbuffer.append("No Tables.");
        }
        return stringbuffer.toString();
    }

    boolean bDHblfZ()
    {
        return lthtlf;
    }

    short BDHblfZ()
    {
        return lTHtlf;
    }

    int bdhBlfZ()
    {
        return lThtlf;
    }

    String BdhBlfZ()
    {
        return lTHTlf.getProperty("reflector.timelimit.msg", "Connection time limit reached.");
    }

    int bDhBlfZ()
    {
        return LThtlf;
    }

    Date BDhBlfZ()
    {
        return LthtLf;
    }

    InetAddress bdHBlfZ()
    {
        return lTHTlF;
    }

    dlk9lh4 BdHBlfZ()
    {
        return LtHTlF;
    }

    void bDHBlfZ()
    {
        StringTokenizer stringtokenizer = new StringTokenizer(lTHTlf.getProperty("reflector.conferences", "0"), ",");
        int ai[] = new int[stringtokenizer.countTokens()];
        while(stringtokenizer.hasMoreTokens()) 
        {
            String s = stringtokenizer.nextToken();
            try
            {
                int i = Integer.parseInt(s);
                if(i > 65535 || i < 0)
                    throw new NumberFormatException();
                ai[Lthtlf] = i;
                Lthtlf++;
            }
            catch(NumberFormatException _ex)
            {
                System.err.println("Number Format Error : " + s);
                System.exit(2);
            }
        }
        lthtLf = new Conference[Lthtlf];
        for(short word0 = 0; word0 < Lthtlf; word0++)
        {
            lthtLf[word0] = new Conference(ai[word0], this);
            String s6 = lTHTlf.getProperty("conference.allownat-" + ai[word0], BDhBLfz());
            if(s6.equals("Never") || s6.equals("PrivateOnly") || s6.equals("All"))
            {
                lthtLf[word0].BdhbLFZv(s6);
            } else
            {
                StringBuffer stringbuffer = new StringBuffer();
                stringbuffer.append("Proparties file Error : conference.allownat-");
                stringbuffer.append(", using the default value");
                stringbuffer.append("(" + BDhBLfz() + ")");
                bDHbLfZ(stringbuffer.toString());
                lthtLf[word0].BdhbLFZv(BDhBLfz());
            }
            s6 = lTHTlf.getProperty("conference.allow-" + ai[word0], "");
            if(!s6.equals(""))
            {
                String s1;
                for(StringTokenizer stringtokenizer1 = new StringTokenizer(s6, ","); stringtokenizer1.hasMoreTokens(); lthtLf[word0].BDhBlFZv(s1))
                    s1 = stringtokenizer1.nextToken();

            }
            s6 = lTHTlf.getProperty("conference.admit-" + ai[word0], "");
            if(!s6.equals(""))
            {
                String s2;
                for(StringTokenizer stringtokenizer2 = new StringTokenizer(s6, ","); stringtokenizer2.hasMoreTokens(); lthtLf[word0].BdhBlFZv(s2, lTHTlf.getProperty("conference.admit.msg-" + ai[word0], BdHBlfz())))
                    s2 = stringtokenizer2.nextToken();

            }
            s6 = lTHTlf.getProperty("conference.admit.sender-" + ai[word0], "");
            if(!s6.equals(""))
            {
                String s3;
                for(StringTokenizer stringtokenizer3 = new StringTokenizer(s6, ","); stringtokenizer3.hasMoreTokens(); lthtLf[word0].bDhBlFZv(s3))
                    s3 = stringtokenizer3.nextToken();

            }
            s6 = lTHTlf.getProperty("conference.deny-" + ai[word0], "");
            if(!s6.equals(""))
            {
                String s4;
                for(StringTokenizer stringtokenizer4 = new StringTokenizer(s6, ","); stringtokenizer4.hasMoreTokens(); lthtLf[word0].BDHBlFZv(s4, lTHTlf.getProperty("conference.deny.msg-" + ai[word0], BdhBLfz())))
                    s4 = stringtokenizer4.nextToken();

            }
            s6 = lTHTlf.getProperty("conference.peers-" + ai[word0], "");
            if(!s6.equals(""))
            {
                for(StringTokenizer stringtokenizer5 = new StringTokenizer(s6, ","); stringtokenizer5.hasMoreTokens();)
                {
                    String s5 = stringtokenizer5.nextToken();
                    try
                    {
                        InetAddress inetaddress = InetAddress.getByName(s5);
                        if(!LTHTLf.containsKey(inetaddress))
                        {
                            d210l8z d210l8z1 = new d210l8z(this, inetaddress.getHostAddress());
                            LTHTLf.put(inetaddress, d210l8z1);
                            LThTLf.bdhb(d210l8z1);
                        }
                        if(!lthtLf[word0].BDhbLfzv().containsKey(inetaddress))
                        {
                            d210l8z d210l8z2 = (d210l8z)LTHTLf.get(inetaddress);
                            d0k2l6z d0k2l6z1 = new d0k2l6z(d210l8z2, lthtLf[word0]);
                            lthtLf[word0].BDhbLfzv().put(inetaddress, d0k2l6z1);
                            lthtLf[word0].BDhBLFZv(true);
                        }
                    }
                    catch(UnknownHostException _ex)
                    {
                        bDHbLfZ("PEER Address Error : " + s6);
                    }
                }

            }
            lthtLf[word0].bdhbLFZv((new Boolean(lTHTlf.getProperty("conference.whitepine.kanji-" + ai[word0], (new Boolean(bDhBLfz())).toString()))).booleanValue());
            lthtLf[word0].bDHBlFZv((new Boolean(lTHTlf.getProperty("conference.close.my.video-" + ai[word0], (new Boolean(BDHBlfz())).toString()))).booleanValue());
        }

    }

    void BDHBlfZ()
    {
        short word0 = 0;
        short word1 = 0;
        long l = 0L;
        String s1 = "";
        String s = lTHTlf.getProperty("reflector.logfile.name", "pmref.log");
        try
        {
            s1 = lTHTlf.getProperty("reflector.logfile.rotation", "1");
            word0 = Short.parseShort(s1);
            s1 = lTHTlf.getProperty("reflector.logfile.limit", "50000");
            l = Long.parseLong(s1);
            s1 = lTHTlf.getProperty("reflector.logfile.level", "1");
            word1 = Short.parseShort(s1);
        }
        catch(NumberFormatException _ex)
        {
            System.err.println("Number Format Error : " + s1);
            System.exit(2);
        }
        File afile[] = File.listRoots();
        boolean flag = false;
        for(int i = 0; i < afile.length; i++)
        {
            if(!s.startsWith(afile[i].getAbsolutePath()))
                continue;
            flag = true;
            break;
        }

        if(flag)
            logger = new d6khl0z(s, word0, l, word1);
        else
            logger = new d6khl0z(LthtlF + s, word0, l, word1);
    }

    void bdhbLfZ()
    {
        for(StringTokenizer stringtokenizer = new StringTokenizer(lTHTlf.getProperty("reflector.proxy", ""), ","); stringtokenizer.hasMoreTokens();)
        {
            String s = stringtokenizer.nextToken();
            try
            {
                lThTLf.ktdblhbdhb(s);
            }
            catch(UnknownHostException _ex)
            {
                bDHbLfZ("ADMIT Address Error : " + s);
            }
        }

        for(StringTokenizer stringtokenizer1 = new StringTokenizer(lTHTlf.getProperty("reflector.proxy.table", ""), ","); stringtokenizer1.hasMoreTokens();)
        {
            String s1 = stringtokenizer1.nextToken();
            StringTokenizer stringtokenizer2 = new StringTokenizer(s1, ":");
            try
            {
                String s2 = stringtokenizer2.nextToken();
                String s3 = stringtokenizer2.nextToken();
                bDhblfz(InetAddress.getByName(s2), InetAddress.getByName(s3));
            }
            catch(Exception _ex)
            {
                bDHbLfZ("Proxy Table Error(address error)" + s1);
            }
        }

    }

    void BdhbLfZ()
    {
        String s = lTHTlf.getProperty("reflector.virtual.address", "127.0.0.0/24");
        if(!s.equals(""))
            try
            {
                LtHTlF = new dlk9lh4(s);
                lTHTlF = LtHTlF.bdhbl(new d0khlhz(InetAddress.getLocalHost(), 7648, InetAddress.getLocalHost(), 7648));
                StringBuffer stringbuffer = new StringBuffer();
                stringbuffer.append("Virtual address mode (" + s + ")");
                bDHbLfZ(stringbuffer.toString());
            }
            catch(Exception _ex)
            {
                StringBuffer stringbuffer1 = new StringBuffer();
                stringbuffer1.append("Proparties file Error : reflector.virtual.address");
                bDHbLfZ(stringbuffer1.toString());
            }
        else
            try
            {
                StringBuffer stringbuffer2 = new StringBuffer();
                stringbuffer2.append("Real address mode is not supported anymore");
                bDHbLfZ(stringbuffer2.toString());
                LtHTlF = new dlk9lh4("127.0.0.0/24");
                lTHTlF = LtHTlF.bdhbl(new d0khlhz(InetAddress.getLocalHost(), 7648, InetAddress.getLocalHost(), 7648));
                stringbuffer2 = new StringBuffer();
                stringbuffer2.append("Virtual address mode (127.0.0.0/24)");
                bDHbLfZ(stringbuffer2.toString());
            }
            catch(Exception _ex)
            {
                StringBuffer stringbuffer3 = new StringBuffer();
                stringbuffer3.append("Proparties file Error : reflector.virtual.address");
                bDHbLfZ(stringbuffer3.toString());
            }
        try
        {
            lThtlf = Integer.parseInt(lTHTlf.getProperty("reflector.timelimit", "0"));
            if(lThtlf < 0)
                throw new NumberFormatException();
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer4 = new StringBuffer();
            stringbuffer4.append("Proparties file Error : reflector.timelimit");
            stringbuffer4.append(", using the default value");
            stringbuffer4.append("(0)");
            bDHbLfZ(stringbuffer4.toString());
            lThtlf = Integer.parseInt("0");
        }
        try
        {
            LThtlf = Integer.parseInt(lTHTlf.getProperty("reflector.timelimit.penalty", "900"));
            if(LThtlf < 0)
                throw new NumberFormatException();
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer5 = new StringBuffer();
            stringbuffer5.append("Proparties file Error : reflector.timelimit.penalty");
            stringbuffer5.append(", using the default value");
            stringbuffer5.append("(900)");
            bDHbLfZ(stringbuffer5.toString());
            LThtlf = Integer.parseInt("900");
        }
        try
        {
            LtHtlf = Short.parseShort(lTHTlf.getProperty("reflector.max.participants", "20"));
            if(LtHtlf > LtHTlF.BDHbl() - 1)
            {
                StringBuffer stringbuffer6 = new StringBuffer();
                stringbuffer6.append("Proparties file Error : reflector.max.participants");
                stringbuffer6.append(" must be equal to or less than ");
                stringbuffer6.append("the number of virtual addresses");
                stringbuffer6.append(", using the default value");
                stringbuffer6.append("(20)");
                bDHbLfZ(stringbuffer6.toString());
                LtHtlf = Short.parseShort("20");
            }
            if(LtHtlf < 1)
                throw new NumberFormatException();
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer7 = new StringBuffer();
            stringbuffer7.append("Proparties file Error : reflector.max.participants");
            stringbuffer7.append(", using the default value");
            stringbuffer7.append("(20)");
            bDHbLfZ(stringbuffer7.toString());
            LtHtlf = Short.parseShort("20");
        }
        try
        {
            LTHtlf = Short.parseShort(lTHTlf.getProperty("reflector.max.senders", "20"));
            if(LTHtlf > LtHtlf)
            {
                StringBuffer stringbuffer8 = new StringBuffer();
                stringbuffer8.append("Proparties file Error : reflector.max.senders");
                stringbuffer8.append(" must be equal to or less than ");
                stringbuffer8.append("reflector.max.participants");
                stringbuffer8.append(", using this value");
                stringbuffer8.append("(" + LtHtlf + ")");
                bDHbLfZ(stringbuffer8.toString());
                LTHtlf = LtHtlf;
            }
            if(LTHtlf < 0)
                throw new NumberFormatException();
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer9 = new StringBuffer();
            stringbuffer9.append("Proparties file Error : reflector.max.senders");
            stringbuffer9.append(", using this value");
            stringbuffer9.append("(" + LtHtlf + ")");
            bDHbLfZ(stringbuffer9.toString());
            LTHtlf = LtHtlf;
        }
        try
        {
            LthTlf = Short.parseShort(lTHTlf.getProperty("reflector.max.lurkers", "20"));
            if(LthTlf > LtHtlf)
            {
                StringBuffer stringbuffer10 = new StringBuffer();
                stringbuffer10.append("Proparties file Error : reflector.max.lurkers");
                stringbuffer10.append(" must be equal to or less than ");
                stringbuffer10.append("reflector.max.participants");
                stringbuffer10.append(", using this value");
                stringbuffer10.append("(" + LtHtlf + ")");
                bDHbLfZ(stringbuffer10.toString());
                LthTlf = LtHtlf;
            }
            if(LthTlf < 0)
                throw new NumberFormatException();
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer11 = new StringBuffer();
            stringbuffer11.append("Proparties file Error : reflector.max.lurkers");
            stringbuffer11.append(", using this value");
            stringbuffer11.append("(" + LtHtlf + ")");
            bDHbLfZ(stringbuffer11.toString());
            LthTlf = LtHtlf;
        }
        try
        {
            lThTlf = Short.parseShort(lTHTlf.getProperty("reflector.max.max.send", "40"));
            if(lThTlf < 1)
                throw new NumberFormatException();
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer12 = new StringBuffer();
            stringbuffer12.append("Proparties file Error : reflector.max.max.send");
            stringbuffer12.append(", using the default value");
            stringbuffer12.append("(40)");
            bDHbLfZ(stringbuffer12.toString());
            lThTlf = Short.parseShort("40");
        }
        try
        {
            LThTlf = Short.parseShort(lTHTlf.getProperty("reflector.max.min.send", "10"));
            if(LThTlf < 1)
                throw new NumberFormatException();
            if(LThTlf > lThTlf)
            {
                StringBuffer stringbuffer13 = new StringBuffer();
                stringbuffer13.append("Proparties file Error : reflector.max.min.send");
                stringbuffer13.append(", using the value of reflector.max.max.send");
                stringbuffer13.append("(" + lThTlf + ")");
                bDHbLfZ(stringbuffer13.toString());
                LThTlf = lThTlf;
            }
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer14 = new StringBuffer();
            stringbuffer14.append("Proparties file Error : reflector.max.min.send");
            stringbuffer14.append(", using the default value");
            stringbuffer14.append("(10)");
            bDHbLfZ(stringbuffer14.toString());
            LThTlf = Short.parseShort("10");
        }
        try
        {
            ltHTlf = Short.parseShort(lTHTlf.getProperty("reflector.max.max.recv", "120"));
            if(ltHTlf < 1)
                throw new NumberFormatException();
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer15 = new StringBuffer();
            stringbuffer15.append("Proparties file Error : reflector.max.max.recv");
            stringbuffer15.append(", using the default value");
            stringbuffer15.append("(120)");
            bDHbLfZ(stringbuffer15.toString());
            ltHTlf = Short.parseShort("120");
        }
        try
        {
            LtHTlf = Short.parseShort(lTHTlf.getProperty("reflector.max.min.recv", "20"));
            if(LtHTlf < 1)
                throw new NumberFormatException();
            if(LtHTlf > ltHTlf)
            {
                StringBuffer stringbuffer16 = new StringBuffer();
                stringbuffer16.append("Proparties file Error : reflector.max.min.recv");
                stringbuffer16.append(", using the value of reflector.max.max.recv");
                stringbuffer16.append("(" + ltHTlf + ")");
                bDHbLfZ(stringbuffer16.toString());
                LtHTlf = ltHTlf;
            }
        }
        catch(NumberFormatException _ex)
        {
            StringBuffer stringbuffer17 = new StringBuffer();
            stringbuffer17.append("Proparties file Error : reflector.max.min.recv");
            stringbuffer17.append(", using the default value");
            stringbuffer17.append("(20)");
            bDHbLfZ(stringbuffer17.toString());
            LtHTlf = Short.parseShort("20");
        }
        lTHtLf = new dlkhlhz();
        for(StringTokenizer stringtokenizer = new StringTokenizer(lTHTlf.getProperty("reflector.refmon", "127.0.0.1"), ","); stringtokenizer.hasMoreTokens();)
        {
            s = stringtokenizer.nextToken();
            try
            {
                lTHtLf.ktdblhbdhb(s);
            }
            catch(UnknownHostException _ex)
            {
                bDHbLfZ("Admin Address Error : " + s);
            }
        }

        LtHtLf = new dlkhlhz();
        try
        {
            LtHtLf.ktdblhbdhb("10.0.0.0/8");
            LtHtLf.ktdblhbdhb("172.16.0.0/12");
            LtHtLf.ktdblhbdhb("192.168.0.0/16");
            LtHtLf.ktdblhbdhb("169.254.0.0/16");
        }
        catch(UnknownHostException _ex)
        {
            bDHbLfZ("Private Address Error");
        }
        s = lTHTlf.getProperty("reflector.allownat", "All");
        if(s.equals("Never") || s.equals("PrivateOnly") || s.equals("All"))
        {
            BdhBLfZ(s);
        } else
        {
            StringBuffer stringbuffer18 = new StringBuffer();
            stringbuffer18.append("Proparties file Error : reflector.allownat");
            stringbuffer18.append(", using the default value");
            stringbuffer18.append("(All)");
            bDHbLfZ(stringbuffer18.toString());
            BdhBLfZ("All");
        }
        LTHtLf = new dlkhlhz();
        for(StringTokenizer stringtokenizer1 = new StringTokenizer(lTHTlf.getProperty("reflector.allow", ""), ","); stringtokenizer1.hasMoreTokens();)
        {
            String s1 = stringtokenizer1.nextToken();
            if(!s1.equals(""))
                try
                {
                    LTHtLf.ktdblhbdhb(s1);
                }
                catch(UnknownHostException _ex)
                {
                    bDHbLfZ("ALLOW Address Error : " + s1);
                }
        }

        lthTLf = new dl0hlhz();
        String s5 = lTHTlf.getProperty("reflector.admit.msg", "This is a private conference.");
        for(StringTokenizer stringtokenizer2 = new StringTokenizer(lTHTlf.getProperty("reflector.admit", ""), ","); stringtokenizer2.hasMoreTokens();)
        {
            String s2 = stringtokenizer2.nextToken();
            if(!s2.equals(""))
                try
                {
                    lthTLf.bdhb(s2, s5);
                }
                catch(UnknownHostException _ex)
                {
                    bDHbLfZ("ADMIT Address Error : " + s2);
                }
        }

        LthTLf = new d2k49hz();
        s5 = lTHTlf.getProperty("reflector.deny.msg", "You're denied.");
        for(StringTokenizer stringtokenizer3 = new StringTokenizer(lTHTlf.getProperty("reflector.deny", ""), ","); stringtokenizer3.hasMoreTokens();)
        {
            String s3 = stringtokenizer3.nextToken();
            if(!s3.equals(""))
                try
                {
                    LthTLf.bdhb(s3, s5);
                }
                catch(UnknownHostException _ex)
                {
                    bDHbLfZ("DENY Address Error : " + s3);
                }
        }

        try
        {
            LTHtlF = new MainReceiver(this, InetAddress.getByName("127.0.0.1"));
        }
        catch(UnknownHostException _ex)
        {
            bDHbLfZ("Unkown Host Address");
            System.exit(9);
        }
        String s7 = lTHTlf.getProperty("reflector.address", "");
        if(s7.equals(""))
        {
            try
            {
                lTHtlF = 1;
                lthTlF[0] = new MainReceiver(this, InetAddress.getLocalHost());
                lThtLf = InetAddress.getLocalHost().getHostAddress();
                LThtLf = InetAddress.getLocalHost().getAddress();
                bDHbLfZ("reflector.address is not set, using the primary interface address, " + lThtLf);
            }
            catch(UnknownHostException _ex)
            {
                bDHbLfZ("Unkown Host Address");
                System.exit(9);
            }
        } else
        {
            lTHtlF = 0;
            for(StringTokenizer stringtokenizer4 = new StringTokenizer(s7, ","); stringtokenizer4.hasMoreTokens() && lTHtlF < 2;)
            {
                String s4 = stringtokenizer4.nextToken();
                try
                {
                    String s6 = InetAddress.getByName(s4).getHostAddress();
                    lthTlF[lTHtlF] = new MainReceiver(this, InetAddress.getByName(s4));
                    lTHtlF++;
                    bDHbLfZ("Using the address, " + s4);
                }
                catch(UnknownHostException _ex)
                {
                    bDHbLfZ("Unkown Host Address:" + s4);
                    System.exit(9);
                }
            }

            try
            {
                lThtLf = InetAddress.getLocalHost().getHostAddress();
                LThtLf = InetAddress.getLocalHost().getAddress();
            }
            catch(UnknownHostException _ex)
            {
                bDHbLfZ("Unkown Host Address");
                System.exit(9);
            }
        }
        if(lTHtlF == 2)
        {
            LtHtlF = true;
            bDHbLfZ("Proxy Enabled");
        } else
        {
            LtHtlF = false;
            bDHbLfZ("Proxy Disabled(Addresses not set)");
        }
        lThTlF = (new Boolean(lTHTlf.getProperty("reflector.whitepine.kanji", "false"))).booleanValue();
        LThTlF = (new Boolean(lTHTlf.getProperty("reflector.close.my.video", "true"))).booleanValue();
        ltHTlF = (new Boolean(lTHTlf.getProperty("reflector.deny.booters", "false"))).booleanValue();
    }

    boolean bDhbLfZ()
    {
        return LtHtlF;
    }

    boolean BDhbLfZ()
    {
        return bDHBLfz() - BdHBLfz() <= 0;
    }

    boolean bdHbLfZ()
    {
        return bdHBlFz() - BDHbLFz() <= 0;
    }

    boolean BdHbLfZ()
    {
        return bDHBlFz() - BDHblfZ() <= 0;
    }

    void bDHbLfZ(String s)
    {
        logger.logStr(s);
    }

    void BDHbLfZ()
    {
        Main.Bdhb = true;
        lthtlf = false;
        LTHtlF.bDhB();
        for(int i = 0; i < lTHtlF; i++)
            lthTlF[i].bDhB();

    }

    public void run()
    {
        BDHBlfZ();
        bDHbLfZ("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST) Starts");
        LThTLf = new dl2hlh6(1000L);
        LThTLf.setDaemon(true);
        LThTLf.setPriority(8);
        LThTLf.start();
        BdhbLfZ();
        bDHBlfZ();
        if(LtHtlF)
            bdhbLfZ();
        for(int i = 0; i < lthtLf.length; i++)
            LThTLf.bdhb(lthtLf[i]);

        ltHTLf = new d4kh98z();
        LThTLf.bdhb(ltHTLf);
        LtHTLf = new dlkhl1z(this);
        if(LtHTLf.bdhb() != 0)
            LThTLf.bdhb(LtHTLf);
        LThTLf.bdhb(this);
        if(lTHtlF == 2)
        {
            lthTlF[0].BDhB(lthTlF[1]);
            lthTlF[1].BDhB(lthTlF[0]);
        }
        LTHtlF.setPriority(5);
        LTHtlF.setDaemon(true);
        LTHtlF.start();
        for(int j = 0; j < lTHtlF; j++)
        {
            lthTlF[j].setPriority(5);
            lthTlF[j].setDaemon(true);
            lthTlF[j].start();
        }

        while(lthtlf) 
        {
            try
            {
                LTHtlF.join(2000L);
            }
            catch(Exception _ex) { }
            if(!lthtlf)
            {
                if(Main.Bdhb && LTHtlF.isAlive())
                {
                    Main.Bdhb = false;
                    bDHbLfZ("PMRef can't RESTART on this condition, instead TERMinating");
                }
                try
                {
                    lthTlF[0].join(2000L);
                }
                catch(Exception _ex) { }
                if(!lthtlf)
                {
                    if(Main.Bdhb && lthTlF[0].isAlive())
                    {
                        Main.Bdhb = false;
                        bDHbLfZ("PMRef can't RESTART on this condition, instead TERMinating");
                    }
                    if(lTHtlF == 2)
                    {
                        try
                        {
                            lthTlF[1].join(2000L);
                        }
                        catch(Exception _ex) { }
                        if(!lthtlf && Main.Bdhb && lthTlF[1].isAlive())
                        {
                            Main.Bdhb = false;
                            bDHbLfZ("PMRef can't RESTART on this condition, instead TERMinating");
                        }
                    }
                }
            }
        }
        logger.BDhb();
        bDHbLfZ("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST) Ends");
        logger.Bdhb();
        logger.bdhb();
    }

    int bdhBLfZ(int i)
    {
        for(int j = 0; j < Lthtlf; j++)
            if(lthtLf[j].BDHbLFzv() == i)
                return j;

        return -1;
    }

    void BdhBLfZ(String s)
    {
        ltHtLf = s;
    }

    void bDhBLfZ(short word0)
    {
        LthTlf = word0;
    }

    void BDhBLfZ(short word0)
    {
        LtHtlf = word0;
    }

    void bdHBLfZ(short word0)
    {
        LTHtlf = word0;
    }

    void BdHBLfZ()
    {
        Main.Bdhb = false;
        lthtlf = false;
        LTHtlF.bdHB();
        for(int i = 0; i < lTHtlF; i++)
            lthTlF[i].bdHB();

    }

    String bDHBLfZ()
    {
        Runtime runtime = Runtime.getRuntime();
        return "Memory :\n(Free / Total) : " + runtime.freeMemory() + " / " + runtime.totalMemory();
    }

    String BDHBLfZ()
    {
        int j = 1;
        int k = 0;
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        int k1 = 0;
        int l1 = 0;
        int i2 = 0;
        int j2 = 0;
        int k2 = 0;
        int l2 = 0;
        int i3 = 0;
        int j3 = 0;
        StringBuffer stringbuffer = new StringBuffer();
        int i = Thread.activeCount();
        Thread athread[] = new Thread[i];
        i = Thread.enumerate(athread);
        stringbuffer.append("Threads : \n");
        stringbuffer.append("Total # of Threads : " + i);
        for(int k3 = 0; k3 < i; k3++)
            if(athread[k3].getName().equals("Reflector"))
                k++;
            else
            if(athread[k3].getName().equals("Timer"))
                l++;
            else
            if(athread[k3].getName().equals("Netinterface"))
                i1++;
            else
            if(athread[k3].getName().equals("Refmon"))
                j1++;
            else
            if(athread[k3].getName().equals("RefmonConnection"))
                j1++;
            else
            if(athread[k3].getName().equals("ConfMenu"))
                l1++;
            else
            if(athread[k3].getName().equals("ConfMenuConnection"))
                l1++;
            else
            if(athread[k3].getName().equals("ConfMenuRelay"))
                l1++;
            else
            if(athread[k3].getName().equals("ConfMenuTransfer"))
                j2++;
            else
            if(athread[k3].getName().equals("Datagram"))
                l2++;
            else
            if(athread[k3].getName().equals("DatagramProxy"))
                l2++;
            else
            if(athread[k3].getName().equals("CUPacket"))
                j3++;

        stringbuffer.append("\n");
        stringbuffer.append("Reflector Thread : " + (k + j));
        stringbuffer.append("\n");
        stringbuffer.append("Timer Thread : " + l);
        stringbuffer.append("\n");
        stringbuffer.append("NetInterface Thread : " + i1);
        stringbuffer.append("\n");
        stringbuffer.append("RefMon Thread : " + j1);
        stringbuffer.append("\n");
        stringbuffer.append("ConfMenu Thread : " + l1);
        stringbuffer.append("\n");
        stringbuffer.append("Incoming Datagram Thread : " + l2);
        stringbuffer.append("\n");
        stringbuffer.append("Outgoing Datagram Thread : " + j3);
        stringbuffer.append("\n");
        stringbuffer.append("Others : " + (i - j - k - l - i1 - j1 - k1 - l1 - i2 - j2 - k2 - l2 - i3 - j3) + "\n");
        return stringbuffer.toString() + "\n";
    }

    public void updateTimer()
    {
        d65h10z d65h10z1;
        for(Enumeration enumeration = ltHtlF.elements(); enumeration.hasMoreElements(); d65h10z1.updateTimer())
            d65h10z1 = (d65h10z)enumeration.nextElement();

        LthTlF++;
        if(LthTlF == 300)
        {
            logger.BDhb();
            LthTlF = 0;
        }
    }

    private boolean lthtlf;
    private short Lthtlf;
    private int lThtlf;
    private int LThtlf;
    private short ltHtlf;
    private short LtHtlf;
    private short lTHtlf;
    private short LTHtlf;
    private short lthTlf;
    private short LthTlf;
    private short lThTlf;
    private short LThTlf;
    private short ltHTlf;
    private short LtHTlf;
    private Properties lTHTlf;
    private d6khl0z logger;
    private Conference lthtLf[];
    private Date LthtLf;
    private String lThtLf;
    private byte LThtLf[];
    private String ltHtLf;
    private dlkhlhz LtHtLf;
    private dlkhlhz lTHtLf;
    private dlkhlhz LTHtLf;
    private dl0hlhz lthTLf;
    private d2k49hz LthTLf;
    private dlkhlhz lThTLf;
    private dl2hlh6 LThTLf;
    private d4kh98z ltHTLf;
    private dlkhl1z LtHTLf;
    private Hashtable lTHTLf;
    private Hashtable LTHTLf;
    private Hashtable lthtlF;
    private String LthtlF;
    private Vector lThtlF;
    private Hashtable LThtlF;
    private Hashtable ltHtlF;
    private boolean LtHtlF;
    private int lTHtlF;
    private MainReceiver LTHtlF;
    private MainReceiver lthTlF[];
    private int LthTlF;
    private boolean lThTlF;
    private boolean LThTlF;
    private boolean ltHTlF;
    private dlk9lh4 LtHTlF;
    private InetAddress lTHTlF;
}

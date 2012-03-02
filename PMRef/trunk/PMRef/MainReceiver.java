// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.io.IOException;
import java.net.*;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package PMRef:
//            d4k6lhz, d8kh3hz, Constants, d09h5hz,
//            dlk58hz, d6khl0z, dl4h01z, d65h10z, 
//            dl8967z, d2khlh9

class MainReceiver extends Thread
    implements Constants
{

    MainReceiver(ConfigLoader d2khlh9_1, InetAddress inetaddress)
    {
        BdHB = d2khlh9_1;
        lTHTl = inetaddress;
        lthtl = inetaddress.getHostAddress();
        Lthtl = inetaddress.getAddress();
        ltHTl = d2khlh9_1.bDHBLFz();
        LtHTl = d2khlh9_1.bdhblfZ();
        lThTl = false;
        lThtl = true;
        setName("Netinterface");
    }

    String bdhb()
    {
        return lthtl;
    }

    byte[] Bdhb()
    {
        return Lthtl;
    }

    MainReceiver bDhb()
    {
        return LTHTl;
    }

    Conference BDhb(int i)
    {
        return BdHB.bdHbLfz(i);
    }

    boolean bdHb()
    {
        return lThTl;
    }

    PacketParserThread BdHb()
    {
        return packetParserThread;
    }

    DatagramSocket bDHb()
    {
        return LtHtl;
    }

    InetAddress BDHb()
    {
        return lTHTl;
    }

    ConfigLoader bdhB()
    {
        return BdHB;
    }

    void BdhB(String s)
    {
        BdHB.getLogger().logStr(s);
    }

    void bDhB()
    {
        lThtl = false;
        interrupt();
    }

    public void run()
    {
        for(int i = 0; i < BdHB.BdhbLfz(); i++)
        {
            if(BdHB.bdHbLfz(i).BdhblfZv())
                continue;
            lThTl = true;
            break;
        }

        LThtl = new d4k6lhz(BdHB, this);
        LThtl.setDaemon(true);
        LThtl.setPriority(3);
        LThtl.start();
        ltHtl = new dl8967z(BdHB, this);
        ltHtl.setDaemon(true);
        ltHtl.setPriority(9);
        ltHtl.start();
        try
        {
            LtHtl = new DatagramSocket(7648, lTHTl);
        }
        catch(SocketException _ex)
        {
            BdhB("DataSocket Creation Error : The UDP port 7648 is being used. A CU Reflector/CU Client program is running.  Terminate it and try to run this again.");
            BdhB("Poor Man's Reflector v1.9.14 (2006/06/03 13:59:17 JST) Ends");
            System.exit(8);
        }
        while(lThtl) 
        {
            LTHtl = new byte[8192];
            lTHtl = new DatagramPacket(LTHtl, 8192);
            try
            {
                LtHtl.receive(lTHtl);
            }
            catch(IOException _ex)
            {
                if(!lThtl)
                    break;
                BdhB("DataSocket Receive Error");
                try
                {
                    LtHtl = new DatagramSocket(7648, lTHTl);
                    break;
                }
                catch(SocketException _ex2)
                {
                    BdhB("DataSocket Creation Error : The UDP port 7648 is being used. A CU Reflector/CU Client program is running.  Terminate it and try to run this again.");
                    System.exit(8);
                }
            }
            InetAddress inetaddress = lTHtl.getAddress();
            dl4h01z dl4h01z1 = (dl4h01z)BdHB.bDHBLFz().get(inetaddress);
            d65h10z d65h10z1 = (d65h10z)BdHB.bdhblfZ().get(inetaddress);
            if(!BdHB.bDhbLfZ() || dl4h01z1 == null && d65h10z1 == null)
            {
                packetParserThread = new PacketParserThread(BdHB, this, lTHtl);
                packetParserThread.setDaemon(true);
                packetParserThread.setPriority(6);
                packetParserThread.start();
            } else
            if(dl4h01z1 != null)
            {
                dl4h01z1.bDHb(lTHtl.getPort());
                LthTl = new dlk58hz(BdHB, this, lTHtl, dl4h01z1, d65h10z1, true);
                LthTl.setDaemon(true);
                LthTl.setPriority(6);
                LthTl.start();
            } else
            {
                dl4h01z dl4h01z2 = (dl4h01z)ltHTl.get(d65h10z1.bDhbl());
                byte abyte0[] = lTHtl.getData();
                if(dl4h01z2 != null && abyte0[22] != 85)
                {
                    LthTl = new dlk58hz(BdHB, this, lTHtl, dl4h01z2, d65h10z1, false);
                    LthTl.setDaemon(true);
                    LthTl.setPriority(6);
                    LthTl.start();
                } else
                {
                    packetParserThread = new PacketParserThread(BdHB, this, lTHtl);
                    packetParserThread.setDaemon(true);
                    packetParserThread.setPriority(6);
                    packetParserThread.start();
                }
            }
        }
        LtHtl.close();
        LThtl.bdhb();
        ltHtl.bdhb();
    }

    void BDhB(MainReceiver d87hl2z1)
    {
        LTHTl = d87hl2z1;
    }

    void bdHB()
    {
        lThtl = false;
        interrupt();
    }

    ConfigLoader BdHB;
    private String lthtl;
    private byte Lthtl[];
    private boolean lThtl;
    private d4k6lhz LThtl;
    private dl8967z ltHtl;
    private DatagramSocket LtHtl;
    private DatagramPacket lTHtl;
    private byte LTHtl[];
    private PacketParserThread packetParserThread;
    private dlk58hz LthTl;
    private boolean lThTl;
    private Vector LThTl;
    private Hashtable ltHTl;
    private Hashtable LtHTl;
    private InetAddress lTHTl;
    private MainReceiver LTHTl;
    static final int bDHB = 8192;
}

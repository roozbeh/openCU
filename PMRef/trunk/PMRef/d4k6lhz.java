// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Referenced classes of package PMRef:
//            d21hlhz, dl45lhz, d654lhz, Constants, 
//            d6khl0z, d87hl2z, dl4h01z, d65h10z, 
//            d2khlh9

class d4k6lhz extends Thread
    implements Constants
{

    d4k6lhz(ConfigLoader d2khlh9_1, MainReceiver d87hl2z1)
    {
        LTht = d2khlh9_1;
        ltHt = d87hl2z1;
        try
        {
            ltht = new ServerSocket(7648, 10, d87hl2z1.BDHb());
        }
        catch(IOException _ex)
        {
            bDhb("ServerSocket Error : Conference Menu disabled");
        }
        setName("ConfMenu");
    }

    void bdhb()
    {
        try
        {
            ltht.close();
            if(lTht != null)
                lTht.close();
        }
        catch(IOException _ex) { }
    }

    MainReceiver Bdhb()
    {
        return ltHt;
    }

    void bDhb(String s)
    {
        LTht.getLogger().logStr(s);
    }

    void BDhb()
    {
        try
        {
            DataOutputStream dataoutputstream = new DataOutputStream(Ltht.getOutputStream());
            byte abyte0[] = new byte[8];
            int i = abyte0.length;
            byte abyte1[] = Utils.int2bytes(3);
            System.arraycopy(abyte1, 0, abyte0, 0, 4);
            abyte1 = Utils.int2bytes(i);
            System.arraycopy(abyte1, 0, abyte0, 4, 4);
            dataoutputstream.write(abyte0, 0, i);
            dataoutputstream.flush();
            Ltht.close();
        }
        catch(IOException _ex) { }
    }

    public void run()
    {
        if(ltht == null)
            return;
        try
        {
            do
            {
                Ltht = ltht.accept();
                dl4h01z dl4h01z1 = (dl4h01z)LTht.bDHBLFz().get(Ltht.getInetAddress());
                if(LTht.bDhbLfZ() && dl4h01z1 != null)
                {
                    d654lhz d654lhz1 = new d654lhz(Ltht, ((dl4h01z)LTht.bDHBLFz().get(Ltht.getInetAddress())).BDhb().BDhbl(), 7648, LTht);
                    d654lhz1.setPriority(4);
                    d654lhz1.start();
                } else
                if(ltHt.bdHb())
                {
                    dl45lhz dl45lhz1 = new dl45lhz(Ltht, LTht, ltHt);
                    dl45lhz1.setPriority(4);
                    dl45lhz1.start();
                } else
                {
                    BDhb();
                }
            } while(true);
        }
        catch(IOException _ex) { }
        if(LTht.bDHblfZ())
            bDhb("ServerSocket Error : The client socket error(ConfMenu)");
    }

    private ServerSocket ltht;
    private Socket Ltht;
    private Socket lTht;
    private ConfigLoader LTht;
    private MainReceiver ltHt;
}

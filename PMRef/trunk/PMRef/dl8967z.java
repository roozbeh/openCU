// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.io.IOException;
import java.net.*;

// Referenced classes of package PMRef:
//            dlkhlhz, Constants, d6khl0z, d87hl2z, 
//            d09876z, d2khlh9

class dl8967z extends Thread
    implements Constants
{

    dl8967z(ConfigLoader d2khlh9_1, MainReceiver d87hl2z1)
    {
        lTht = d2khlh9_1;
        LTht = d87hl2z1;
        try
        {
            ltht = new ServerSocket(7649, 10, d87hl2z1.BDHb());
        }
        catch(IOException _ex)
        {
            Bdhb("ServerSocket Error : Refmon disabled");
        }
        setName("Refmon");
    }

    void bdhb()
    {
        try
        {
            ltht.close();
            if(Ltht != null)
                Ltht.close();
        }
        catch(IOException _ex) { }
    }

    void Bdhb(String s)
    {
        lTht.getLogger().logStr(s);
    }

    public void run()
    {
        if(ltht == null)
            return;
        try
        {
            do
            {
                Socket socket = ltht.accept();
                if(lTht.BdhblfZ().ktdblhBdhb(socket.getInetAddress()) || lTht.BdHBLFz().ktdblhBdhb(socket.getInetAddress()))
                {
                    Bdhb("Refmon connection estabrished from " + socket.getInetAddress().getHostName());
                    d09876z d09876z1 = new d09876z(socket, lTht, LTht);
                    d09876z1.setPriority(10);
                    d09876z1.start();
                } else
                {
                    socket.close();
                    Bdhb("Refmon connection denied from " + socket.getInetAddress().getHostName());
                }
            } while(true);
        }
        catch(IOException _ex) { }
        if(lTht.bDHblfZ())
            Bdhb("ServerSocket Error : The client socket error(RefMon)");
    }

    private ServerSocket ltht;
    private Socket Ltht;
    private ConfigLoader lTht;
    private MainReceiver LTht;
}

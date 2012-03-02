// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

// Referenced classes of package PMRef:
//            d21hlhz, dlkh2hz, Constants, d6khl0z, 
//            d2khlh9, d87hl2z

class d654lhz extends Thread
    implements Constants
{

    d654lhz(Socket socket, InetAddress inetaddress, int i, ConfigLoader d2khlh9_1)
    {
        ltht = socket;
        lthT = inetaddress;
        LthT = i;
        lTHt = d2khlh9_1;
        setName("ConfMenuRelay");
    }

    void bdhb(String s)
    {
        lTHt.getLogger().logStr(s);
    }

    public void run()
    {
        try
        {
            Ltht = new Socket(lthT, LthT);
        }
        catch(IOException _ex)
        {
            try
            {
                DataOutputStream dataoutputstream = new DataOutputStream(ltht.getOutputStream());
                byte abyte0[] = new byte[8];
                int i = abyte0.length;
                byte abyte1[] = Utils.int2bytes(3);
                System.arraycopy(abyte1, 0, abyte0, 0, 4);
                abyte1 = Utils.int2bytes(i);
                System.arraycopy(abyte1, 0, abyte0, 4, 4);
                dataoutputstream.write(abyte0, 0, i);
                dataoutputstream.flush();
                ltht.close();
            }
            catch(IOException _ex2) { }
            return;
        }
        try
        {
            lTht = new DataInputStream(ltht.getInputStream());
            LTht = new DataOutputStream(ltht.getOutputStream());
        }
        catch(IOException _ex)
        {
            bdhb("Get Stream Error");
            return;
        }
        try
        {
            ltHt = new DataInputStream(Ltht.getInputStream());
            LtHt = new DataOutputStream(Ltht.getOutputStream());
        }
        catch(IOException _ex)
        {
            bdhb("Get Stream Error");
            return;
        }
        lThT = new dlkh2hz(ltHt, LTht, lTHt);
        LThT = new dlkh2hz(lTht, LtHt, lTHt);
        lThT.start();
        LThT.start();
        try
        {
            lThT.join();
            ltht.close();
            LThT.join();
            Ltht.close();
        }
        catch(InterruptedException _ex) { }
        catch(IOException _ex) { }
    }

    private Socket ltht;
    private Socket Ltht;
    private DataInputStream lTht;
    private DataOutputStream LTht;
    private DataInputStream ltHt;
    private DataOutputStream LtHt;
    private ConfigLoader lTHt;
    private MainReceiver LTHt;
    private InetAddress lthT;
    private int LthT;
    private dlkh2hz lThT;
    private dlkh2hz LThT;
}

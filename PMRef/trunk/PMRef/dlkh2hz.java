// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.io.*;

// Referenced classes of package PMRef:
//            Constants, d6khl0z, d2khlh9

class dlkh2hz extends Thread
    implements Constants
{

    dlkh2hz(DataInputStream datainputstream, DataOutputStream dataoutputstream, ConfigLoader d2khlh9_1)
    {
        ltht = datainputstream;
        Ltht = dataoutputstream;
        lTht = d2khlh9_1;
        setName("ConfMenuTransfer");
    }

    void bdhb(String s)
    {
        lTht.getLogger().logStr(s);
    }

    public void run()
    {
        byte abyte0[] = new byte[256];
        try
        {
            for(int i = ltht.read(abyte0, 0, 256); i != -1; i = ltht.read(abyte0, 0, 256))
            {
                Ltht.write(abyte0, 0, i);
                Ltht.flush();
            }

        }
        catch(IOException _ex)
        {
            return;
        }
    }

    private DataInputStream ltht;
    private DataOutputStream Ltht;
    private ConfigLoader lTht;
}

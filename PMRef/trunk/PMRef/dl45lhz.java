// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.io.*;
import java.net.Socket;

// Referenced classes of package PMRef:
//            d21hlhz, d8kh3hz, Constants, d6khl0z, 
//            d2khlh9, d87hl2z

class dl45lhz extends Thread
    implements Constants
{

    dl45lhz(Socket socket, ConfigLoader d2khlh9_1, MainReceiver d87hl2z)
        throws IOException
    {
        ltht = socket;
        LTht = d2khlh9_1;
        LtHt = d87hl2z;
        ltHt = d2khlh9_1.BdHbLfz();
        Ltht = new DataInputStream(socket.getInputStream());
        lTht = new DataOutputStream(socket.getOutputStream());
        setName("ConfMenuConnection");
    }

    void bdhb(String s)
    {
        LTht.getLogger().logStr(s);
    }

    public void run()
    {
        boolean flag = true;
        boolean flag1 = true;
        try
        {
            while(flag) 
            {
                byte abyte0[] = new byte[8];
                int j1 = Ltht.read(abyte0, 0, 8);
                if(j1 == -1)
                {
                    flag = false;
                    break;
                }
                int i1 = Utils.readInt(abyte0, 0);
                switch(i1)
                {
                case 2: // '\002'
                    flag1 = false;
                    int i = Utils.readInt(abyte0, 4) - 8;
                    if(i > 0)
                    {
                        abyte0 = new byte[i];
                        int k1 = Ltht.read(abyte0, 0, i);
                        if(k1 == -1)
                        {
                            flag = false;
                            break;
                        }
                    }
                    byte abyte2[] = new byte[8];
                    i = abyte2.length;
                    byte abyte4[] = Utils.int2bytes(4);
                    System.arraycopy(abyte4, 0, abyte2, 0, 4);
                    abyte4 = Utils.int2bytes(i);
                    System.arraycopy(abyte4, 0, abyte2, 4, 4);
                    lTht.write(abyte2, 0, i);
                    lTht.flush();
                    break;

                case 6: // '\006'
                    int j = Utils.readInt(abyte0, 4) - 8;
                    if(j > 0)
                    {
                        abyte0 = new byte[j];
                        int l1 = Ltht.read(abyte0, 0, j);
                        if(l1 == -1)
                        {
                            flag = false;
                            break;
                        }
                    }
                    j = 0;
                    StringBuffer stringbuffer = new StringBuffer();
                    for(int k2 = 0; k2 < ltHt.length; k2++)
                        if(!ltHt[k2].BdhblfZv())
                        {
                            j += ltHt[k2].BdHbLFzv().getBytes().length;
                            stringbuffer.append(ltHt[k2].BdHbLFzv());
                            stringbuffer.append('\n');
                            j++;
                        }

                    byte abyte3[] = new byte[j += 8];
                    byte abyte5[] = Utils.int2bytes(7);
                    System.arraycopy(abyte5, 0, abyte3, 0, 4);
                    abyte5 = Utils.int2bytes(j);
                    System.arraycopy(abyte5, 0, abyte3, 4, 4);
                    abyte0 = stringbuffer.toString().getBytes();
                    for(int l2 = 0; l2 < abyte0.length; l2++)
                        abyte3[l2 + 8] = abyte0[l2];

                    lTht.write(abyte3, 0, j);
                    lTht.flush();
                    if(flag1)
                        flag = false;
                    break;

                case 5: // '\005'
                    int k = Utils.readInt(abyte0, 4) - 8;
                    if(k > 0)
                    {
                        abyte0 = new byte[k];
                        int i2 = Ltht.read(abyte0, 0, k);
                        if(i2 == -1)
                        {
                            flag = false;
                            break;
                        }
                    }
                    flag = false;
                    break;

                case 3: // '\003'
                case 4: // '\004'
                default:
                    int l = Utils.readInt(abyte0, 4) - 8;
                    if(l > 0)
                    {
                        byte abyte1[] = new byte[l];
                        int j2 = Ltht.read(abyte1, 0, l);
                        if(j2 == -1)
                            flag = false;
                    }
                    break;
                }
            }
        }
        catch(IOException _ex)
        {
            bdhb("IO Error : ConfMenuConnection");
        }
        finally
        {
            try
            {
                ltht.close();
            }
            catch(IOException ioexception)
            {
                bdhb(ioexception.toString());
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

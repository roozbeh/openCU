// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

// Referenced classes of package PMRef:
//            d21hlhz, Constants

class d4321hz
    implements Constants
{

    d4321hz(String s)
        throws UnknownHostException, NumberFormatException
    {
        ltht = s;
        StringTokenizer stringtokenizer = new StringTokenizer(s, "/");
        if(stringtokenizer.countTokens() == 1)
        {
            Ltht = InetAddress.getByName(s).getHostAddress();
            lTht = 0;
        } else
        {
            String s1 = stringtokenizer.nextToken();
            int i = Integer.parseInt(stringtokenizer.nextToken());
            Ltht = InetAddress.getByName(s1).getHostAddress();
            lTht = i;
        }
        LtHt = InetAddress.getByName(Ltht);
        LTht = LtHt.getAddress();
        if(LTht.length == 4)
            ltHt = true;
        else
            ltHt = false;
        if(ltHt && (lTht < 0 || lTht > 32))
            throw new NumberFormatException();
        else
            return;
    }

    boolean bdhb(InetAddress inetaddress)
    {
        return LtHt.equals(bdHb(inetaddress, lTht));
    }

    String Bdhb()
    {
        return ltht;
    }

    InetAddress bDhb()
    {
        return LtHt;
    }

    int BDhb()
    {
        return lTht;
    }

    static InetAddress bdHb(InetAddress inetaddress, int i)
    {
        byte abyte0[] = inetaddress.getAddress();
        if(i != 0)
        {
            for(int j = abyte0.length - 1; j >= 0; j--)
            {
                if(i <= j * 8)
                {
                    abyte0[j] = 0;
                    continue;
                }
                if(i % 8 != 0 && i % 8 <= 7)
                {
                    abyte0[j] &= 0xfe;
                    if(i % 8 <= 6)
                    {
                        abyte0[j] &= 0xfc;
                        if(i % 8 <= 5)
                        {
                            abyte0[j] &= 0xf8;
                            if(i % 8 <= 4)
                            {
                                abyte0[j] &= 0xf0;
                                if(i % 8 <= 3)
                                {
                                    abyte0[j] &= 0xe0;
                                    if(i % 8 <= 2)
                                    {
                                        abyte0[j] &= 0xc0;
                                        if(i % 8 <= 1)
                                            abyte0[j] &= 0x80;
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            }

        }
        InetAddress inetaddress1;
        try
        {
            inetaddress1 = InetAddress.getByName(Utils.BDHb(abyte0));
        }
        catch(UnknownHostException _ex)
        {
            inetaddress1 = null;
        }
        return inetaddress1;
    }

    private String ltht;
    private String Ltht;
    private int lTht;
    private byte LTht[];
    private boolean ltHt;
    private InetAddress LtHt;
    static final String BdHb = "/";
}

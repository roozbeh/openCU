// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.io.*;
import java.text.DateFormat;
import java.util.*;

// Referenced classes of package PMRef:
//            d21hlhz, Constants

class d6khl0z
    implements Constants
{

    d6khl0z(String s, short word0, long l, short word1)
    {
        LTht = s;
        ltht = word0;
        lTht = l;
        Ltht = word1;
        ltHt = new String("");
        lTHt = -1;
        LthT = new Hashtable();
        try
        {
            if(word0 != 0)
                bDHb();
            LTHt = new File(LTht);
            lthT = new PrintStream(new FileOutputStream(LTHt));
        }
        catch(IOException _ex)
        {
            System.err.println("File Open  Error : " + s);
            System.exit(5);
        }
    }

    synchronized void bdhb()
    {
        lthT.close();
    }

    synchronized void Bdhb()
    {
        String s = new String("Unkn");
        byte abyte1[] = s.getBytes();
        int i = 4 + abyte1.length;
        byte abyte0[] = new byte[i];
        System.arraycopy(Utils.ishort2Bytes(269), 0, abyte0, 0, 2);
        System.arraycopy(Utils.ishort2Bytes(i), 0, abyte0, 2, 2);
        System.arraycopy(abyte1, 0, abyte0, 4, abyte1.length);
        for(Enumeration enumeration = LthT.elements(); enumeration.hasMoreElements();)
        {
            DataOutputStream dataoutputstream = (DataOutputStream)enumeration.nextElement();
            try
            {
                dataoutputstream.write(abyte0, 0, abyte0.length);
            }
            catch(IOException _ex) { }
        }

    }

    synchronized boolean bDhb(String s)
    {
        DataOutputStream dataoutputstream = (DataOutputStream)LthT.remove(s);
        if(dataoutputstream != null)
        {
            String s1 = new String("Unkn");
            byte abyte1[] = s1.getBytes();
            int i = 4 + abyte1.length;
            byte abyte0[] = new byte[i];
            System.arraycopy(Utils.ishort2Bytes(269), 0, abyte0, 0, 2);
            System.arraycopy(Utils.ishort2Bytes(i), 0, abyte0, 2, 2);
            System.arraycopy(abyte1, 0, abyte0, 4, abyte1.length);
            try
            {
                dataoutputstream.write(abyte0, 0, abyte0.length);
            }
            catch(IOException _ex) { }
            return true;
        } else
        {
            return false;
        }
    }

    synchronized void BDhb()
    {
        if(lTHt > 0)
        {
            String s;
            switch(lTHt)
            {
            case 1: // '\001'
                s = LtHt + ltHt;
                break;

            case 2: // '\002'
                s = new String("[" + DateFormat.getDateTimeInstance(1, 1).format(new Date()) + "]  " + "The previous message repeated " + "twice.");
                break;

            default:
                s = new String("[" + DateFormat.getDateTimeInstance(1, 1).format(new Date()) + "]  " + "The previous message repeated " + lTHt + " times.");
                break;
            }
            lthT.println(s);
            if(!LthT.isEmpty())
            {
                byte abyte0[] = s.getBytes();
                int i = 4 + abyte0.length;
                byte abyte1[] = new byte[i];
                System.arraycopy(Utils.ishort2Bytes(269), 0, abyte1, 0, 2);
                System.arraycopy(Utils.ishort2Bytes(i), 0, abyte1, 2, 2);
                System.arraycopy(abyte0, 0, abyte1, 4, abyte0.length);
                for(Enumeration enumeration = LthT.elements(); enumeration.hasMoreElements();)
                {
                    DataOutputStream dataoutputstream = (DataOutputStream)enumeration.nextElement();
                    try
                    {
                        dataoutputstream.write(abyte1, 0, abyte1.length);
                    }
                    catch(IOException _ex)
                    {
                        for(Enumeration enumeration1 = LthT.keys(); enumeration1.hasMoreElements();)
                        {
                            String s1 = (String)enumeration1.nextElement();
                            DataOutputStream dataoutputstream1 = (DataOutputStream)LthT.get(s1);
                            if(dataoutputstream1.equals(dataoutputstream))
                                LthT.remove(s1);
                        }

                    }
                }

            }
            lTHt = -1;
            ltHt = new String("");
        }
    }

    long bdHb()
    {
        return lTht;
    }

    String BdHb()
    {
        return LTht;
    }

    synchronized void bDHb()
    {
        File file = new File(LTht + "." + ltht);
        file.delete();
        if(ltht != 1)
        {
            for(int i = ltht - 1; i > 0; i--)
            {
                file = new File(LTht + "." + i);
                if(file.exists())
                {
                    File file1 = new File(LTht + "." + (i + 1));
                    file.renameTo(file1);
                }
            }

        }
        file = new File(LTht);
        if(file.exists())
        {
            File file2 = new File(LTht + ".1");
            file.renameTo(file2);
        }
    }

    synchronized void BDHb(String s, DataOutputStream dataoutputstream)
    {
        LthT.put(s, dataoutputstream);
    }

    synchronized void logStr(String s)
    {
        System.out.println(s);
        if(lTht != 0L)
            if(ltHt.equals(s))
            {
                lTHt++;
                if(lTHt == 1)
                    LtHt = new String("[" + DateFormat.getDateTimeInstance(1, 1).format(new Date()) + "]  ");
            } else
            {
                if(lTHt > 0)
                {
                    String s1;
                    switch(lTHt)
                    {
                    case 1: // '\001'
                        s1 = LtHt + ltHt;
                        break;

                    case 2: // '\002'
                        s1 = new String("[" + DateFormat.getDateTimeInstance(1, 1).format(new Date()) + "]  " + "The previous message repeated " + "twice.");
                        break;

                    default:
                        s1 = new String("[" + DateFormat.getDateTimeInstance(1, 1).format(new Date()) + "]  " + "The previous message repeated " + lTHt + " times.");
                        break;
                    }
                    lthT.println(s1);
                    if(!LthT.isEmpty())
                    {
                        byte abyte0[] = s1.getBytes();
                        int i = 4 + abyte0.length;
                        byte abyte2[] = new byte[i];
                        System.arraycopy(Utils.ishort2Bytes(269), 0, abyte2, 0, 2);
                        System.arraycopy(Utils.ishort2Bytes(i), 0, abyte2, 2, 2);
                        System.arraycopy(abyte0, 0, abyte2, 4, abyte0.length);
                        for(Enumeration enumeration = LthT.elements(); enumeration.hasMoreElements();)
                        {
                            DataOutputStream dataoutputstream = (DataOutputStream)enumeration.nextElement();
                            try
                            {
                                dataoutputstream.write(abyte2, 0, abyte2.length);
                            }
                            catch(IOException _ex)
                            {
                                for(Enumeration enumeration2 = LthT.keys(); enumeration2.hasMoreElements();)
                                {
                                    String s3 = (String)enumeration2.nextElement();
                                    DataOutputStream dataoutputstream2 = (DataOutputStream)LthT.get(s3);
                                    if(dataoutputstream2.equals(dataoutputstream))
                                        LthT.remove(s3);
                                }

                            }
                        }

                    }
                }
                String s2 = new String("[" + DateFormat.getDateTimeInstance(1, 1).format(new Date()) + "]  " + s);
                lthT.println(s2);
                if(!LthT.isEmpty())
                {
                    byte abyte1[] = s2.getBytes();
                    int j = 4 + abyte1.length;
                    byte abyte3[] = new byte[j];
                    System.arraycopy(Utils.ishort2Bytes(269), 0, abyte3, 0, 2);
                    System.arraycopy(Utils.ishort2Bytes(j), 0, abyte3, 2, 2);
                    System.arraycopy(abyte1, 0, abyte3, 4, abyte1.length);
                    for(Enumeration enumeration1 = LthT.elements(); enumeration1.hasMoreElements();)
                    {
                        DataOutputStream dataoutputstream1 = (DataOutputStream)enumeration1.nextElement();
                        try
                        {
                            dataoutputstream1.write(abyte3, 0, abyte3.length);
                        }
                        catch(IOException _ex)
                        {
                            for(Enumeration enumeration3 = LthT.keys(); enumeration3.hasMoreElements();)
                            {
                                String s4 = (String)enumeration3.nextElement();
                                DataOutputStream dataoutputstream3 = (DataOutputStream)LthT.get(s4);
                                if(dataoutputstream3.equals(dataoutputstream1))
                                    LthT.remove(s4);
                            }

                        }
                    }

                }
                lTHt = 0;
                ltHt = new String(s);
                if(LTHt.length() > lTht)
                {
                    s = new String("LOGLIMIT reached, lotating and switching, see the newer log file from now on.");
                    ltHt = new String(s);
                    lthT.println("[" + DateFormat.getDateTimeInstance(1, 1).format(new Date()) + "]  " + s);
                    bDHb();
                    try
                    {
                        LTHt = new File(LTht);
                        lthT = new PrintStream(new FileOutputStream(LTHt));
                    }
                    catch(IOException _ex)
                    {
                        System.err.println("File Open  Error : " + LTht);
                        System.exit(5);
                    }
                }
            }
    }

    void BdhB(String s, short word0)
    {
        if(word0 <= Ltht)
            logStr(s);
    }

    private short ltht;
    private short Ltht;
    private long lTht;
    private String LTht;
    private String ltHt;
    private String LtHt;
    private int lTHt;
    private File LTHt;
    private PrintStream lthT;
    private Hashtable LthT;
}

// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.io.File;

// Referenced classes of package PMRef:
//            Constants, d2khlh9

public class Main
    implements Constants
{

    public Main()
    {
    }

    public static void main(String args[])
    {
        bDhb = (new File(System.getProperty("java.class.path"))).getAbsolutePath();
        if(bDhb.endsWith("/."))
            bDhb = bDhb.substring(0, bDhb.lastIndexOf("."));
        else
        if(bDhb.endsWith("PMRef.jar"))
            bDhb = bDhb.substring(0, bDhb.lastIndexOf("PMRef.jar"));
        else
        if(bDhb.endsWith("pmref.jar"))
            bDhb = bDhb.substring(0, bDhb.lastIndexOf("pmref.jar"));
        else
            bDhb = "";
        if(args.length <= 0 || !args[0].equals("-nocheck"))
//            try
//            {
//                BDhb = new File(bDhb + ".pmref.lock");
//                if(!BDhb.createNewFile())
//                {
//                    System.err.println("\nThe lock file exists.\nPMRef is already running, or PMRef ended abnormally\nlast time.  Check whether PMRef is running.  If not,\nremove \".pmref.lock\" file in the same directory where\n\"PMRef.jar\" is in, and try again .\n");
//                    System.exit(1);
//                }
//                BDhb.deleteOnExit();
//            }
//            catch(Exception exception)
//            {
//                System.err.println("\".pmref.lock\" file error.");
//                System.err.println(exception);
//                System.exit(4);
//            }
        if(System.getProperty("java.version").compareTo("1.2") < 0)
        {
            System.err.println("Java version error.  Use Version 1.2 or later.");
            System.exit(255);
        }
        do
        {
            bdhb = new ConfigLoader(bDhb);
            bdhb.setPriority(5);
            bdhb.start();
            try
            {
                bdhb.join();
            }
            catch(Exception _ex) { }
        } while(Bdhb);
    }

    static ConfigLoader bdhb;
    static boolean Bdhb;
    static String bDhb;
    static File BDhb;
}

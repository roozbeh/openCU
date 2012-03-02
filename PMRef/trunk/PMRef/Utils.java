// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;


class Utils {

    Utils()
    {
    }

    static boolean compare(byte abyte0[], int i, byte abyte1[], int j, int k)
    {
        for(int l = 0; l < k; l++)
            if(abyte0[i + l] != abyte1[j + l])
                return false;

        return true;
    }

    static boolean compare(byte abyte0[], byte abyte1[], int i)
    {
        for(int j = 0; j < i; j++)
            if(abyte0[j] != abyte1[j])
                return false;

        return true;
    }

    static int readInt(byte abyte0[], int i)
    {
        int j = 0;
        if(abyte0[i] < 0)
            j += (256 + abyte0[i]) * 0x1000000;
        else
            j += abyte0[i] * 0x1000000;
        if(abyte0[i + 1] < 0)
            j += (256 + abyte0[i + 1]) * 0x10000;
        else
            j += abyte0[i + 1] * 0x10000;
        if(abyte0[i + 2] < 0)
            j += (256 + abyte0[i + 2]) * 256;
        else
            j += abyte0[i + 2] * 256;
        if(abyte0[i + 3] < 0)
            j += 256 + abyte0[i + 3];
        else
            j += abyte0[i + 3];
        return j;
    }

    static short BDhb(byte abyte0[], int i)
    {
        short word0 = 0;
        if(abyte0[i] < 0)
            word0 += (256 + abyte0[i]) * 256;
        else
            word0 += abyte0[i] * 256;
        if(abyte0[i + 1] < 0)
            word0 += 256 + abyte0[i + 1];
        else
            word0 += abyte0[i + 1];
        return word0;
    }

    static String bdHb(byte byte0)
    {
        short word0;
        if(byte0 < 0)
            word0 = (short)(256 + byte0);
        else
            word0 = byte0;
        return (new Short(word0)).toString();
    }

    static long BdHb(byte abyte0[], int i)
    {
        long l = 0L;
        if(abyte0[i] < 0)
            l += (256L + (long)abyte0[i]) * 0x1000000L;
        else
            l += (long)abyte0[i] * 0x1000000L;
        if(abyte0[i + 1] < 0)
            l += (256L + (long)abyte0[i + 1]) * 0x10000L;
        else
            l += (long)abyte0[i + 1] * 0x10000L;
        if(abyte0[i + 2] < 0)
            l += (256L + (long)abyte0[i + 2]) * 256L;
        else
            l += (long)abyte0[i + 2] * 256L;
        if(abyte0[i + 3] < 0)
            l += 256L + (long)abyte0[i + 3];
        else
            l += abyte0[i + 3];
        return l;
    }

    static int readShort(byte abyte0[], int i)
    {
        int j = 0;
        if(abyte0[i] < 0)
            j += (256 + abyte0[i]) * 256;
        else
            j += abyte0[i] * 256;
        if(abyte0[i + 1] < 0)
            j += 256 + abyte0[i + 1];
        else
            j += abyte0[i + 1];
        return j;
    }

    static String BDHb(byte abyte0[])
    {
        return convertToIPform(abyte0, 0);
    }

    static String convertToIPform(byte abyte0[], int i)
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(int j = 0; j < 3; j++)
        {
            stringbuffer.append(bdHb(abyte0[j + i]));
            stringbuffer.append(".");
        }

        stringbuffer.append(bdHb(abyte0[3 + i]));
        return stringbuffer.toString();
    }

    static byte[] int2bytes(int i)
    {
        byte abyte0[] = new byte[4];
        abyte0[0] = (byte)(i / 0x1000000);
        abyte0[1] = (byte)((i % 0x1000000) / 0x10000);
        abyte0[2] = (byte)((i % 0x1000000 % 0x10000) / 256);
        abyte0[3] = (byte)(i % 256);
        return abyte0;
    }

    static String readStr(byte abyte0[], int i)
    {
        byte byte0 = abyte0[i];
        byte abyte1[] = new byte[byte0];
        System.arraycopy(abyte0, i + 1, abyte1, 0, byte0);
        return new String(abyte1);
    }

    static byte[] short2bytes(short word0)
    {
        byte abyte0[] = new byte[2];
        abyte0[0] = (byte)(word0 / 256);
        abyte0[1] = (byte)(word0 % 256);
        return abyte0;
    }

    static byte[] long2Bytes(long l)
    {
        byte abyte0[] = new byte[4];
        long l1 = l % 0x100000000L;
        abyte0[0] = (byte)(int)(l1 / 0x1000000L);
        abyte0[1] = (byte)(int)((l1 % 0x1000000L) / 0x10000L);
        abyte0[2] = (byte)(int)((l1 % 0x1000000L % 0x10000L) / 256L);
        abyte0[3] = (byte)(int)(l1 % 256L);
        return abyte0;
    }

    static byte[] ishort2Bytes(int i)
    {
        byte abyte0[] = new byte[2];
        int j = i % 0x10000;
        abyte0[0] = (byte)(j / 256);
        abyte0[1] = (byte)(j % 256);
        return abyte0;
    }
}

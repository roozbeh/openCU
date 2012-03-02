// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package PMRef;

import java.io.IOException;
import java.net.*;

// Referenced classes of package PMRef:
//            d21hlhz, Constants

class CuseemePacket
        implements Runnable, Constants
{

    CuseemePacket()
    {
    }

    CuseemePacket(byte abyte0[])
    {
        this(abyte0, true);
    }

    CuseemePacket(byte abyte0[], boolean flag)
    {
        bDHbL = flag;
        buffer = new byte[abyte0.length];
        System.arraycopy(abyte0, 0, buffer, 0, abyte0.length);
    }

    public void run()
    {
        try
        {
            threadFunc();
        }
        catch(Exception _ex) { }
    }

    void threadFunc()
        throws UnknownHostException, SocketException, IOException
    {
        int packetLength = Utils.readShort(buffer, 24);
        try
        {
            packet = new DatagramPacket(buffer, 0, packetLength, destAddr, destPort);
            socket.send(packet);
        }
        catch(Exception _ex) { }
    }

    void sendToAddress(InetAddress inetaddress, int i)
        throws UnknownHostException, SocketException, IOException
    {
        destPort = i;
        destAddr = inetaddress;
        if(bDHbL)
        {
            Thread thread = new Thread(this);
            thread.setPriority(7);
            thread.setName("CUPacket");
            thread.start();
        } else
        {
            threadFunc();
        }
    }

    void setDataType(int i)
    {
        byte abyte0[] = Utils.ishort2Bytes(i);
        System.arraycopy(abyte0, 0, buffer, 22, 2);
    }

    void setSocket(DatagramSocket datagramsocket)
    {
        socket = datagramsocket;
    }

    void setDestAddr(String s)
    {
        try
        {
            byte abyte0[] = InetAddress.getByName(s).getAddress();
            System.arraycopy(abyte0, 0, buffer, 4, 4);
        }
        catch(UnknownHostException _ex) { }
    }

    void setDestAddr2(InetAddress inetaddress)
    {
        System.arraycopy(inetaddress.getAddress(), 0, buffer, 4, 4);
    }

    void setDestFamily(int i)
    {
        byte abyte0[] = Utils.ishort2Bytes(i);
        System.arraycopy(abyte0, 0, buffer, 0, 2);
    }

    void setDestport(int i)
    {
        byte abyte0[] = Utils.ishort2Bytes(i);
        System.arraycopy(abyte0, 0, buffer, 2, 2);
    }

    void setSourceAddr()
    {
        try
        {
            byte abyte0[] = InetAddress.getLocalHost().getAddress();
            System.arraycopy(abyte0, 0, buffer, 12, 4);
        }
        catch(UnknownHostException _ex) { }
    }

    void setSourceAddr2(byte abyte0[])
    {
        System.arraycopy(abyte0, 0, buffer, 12, 4);
    }

    void setMessageType(int i)
    {
        byte abyte0[] = Utils.ishort2Bytes(i);
        System.arraycopy(abyte0, 0, buffer, 20, 2);
    }

    void setPacketLength(int i)
    {
        byte abyte0[] = Utils.ishort2Bytes(i);
        System.arraycopy(abyte0, 0, buffer, 24, 2);
    }

    void setSequenceNumber(long l)
    {
        byte abyte0[] = Utils.long2Bytes(l);
        System.arraycopy(abyte0, 0, buffer, 16, 4);
    }

    void setSourceAddress3(String s)
    {
        try
        {
            byte abyte0[] = InetAddress.getByName(s).getAddress();
            System.arraycopy(abyte0, 0, buffer, 12, 4);
        }
        catch(UnknownHostException _ex) { }
    }

    void setSourceAddress4(InetAddress inetaddress)
    {
        System.arraycopy(inetaddress.getAddress(), 0, buffer, 12, 4);
    }

    void setSourceFamily(int i)
    {
        byte abyte0[] = Utils.ishort2Bytes(i);
        System.arraycopy(abyte0, 0, buffer, 8, 2);
    }

    void setSourcePort(int i)
    {
        byte abyte0[] = Utils.ishort2Bytes(i);
        System.arraycopy(abyte0, 0, buffer, 10, 2);
    }

    InetAddress destAddr;
    int destPort;
    DatagramSocket socket;
    DatagramPacket packet;
    byte buffer[];
    boolean bDHbL;
}

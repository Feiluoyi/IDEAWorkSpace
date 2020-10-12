import org.junit.Test;

import java.io.IOException;
import java.net.*;

public class UDPtest {
    //发送端
    @Test
    public void send() throws IOException {
        DatagramSocket socket=new DatagramSocket();
        String str="使用udp协议的信息";
        byte[] buffer = str.getBytes();
        InetAddress inet = Inet4Address.getByName("127.0.0.1");
        DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length,inet,8892);

        socket.send(packet);

        socket.close();

    }
    @Test
    //接收端
    public void receiver() throws IOException {
        DatagramSocket socket = new DatagramSocket(8892);
        byte[] buffer = new byte[100];
        DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);

        socket.receive(packet);
        System.out.println(new String(packet.getData(),0,packet.getLength()));

    }
}

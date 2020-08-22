package InternetEx._02Socket_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receive {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket=new DatagramSocket(12001);
        byte[] bytes=new byte[1024];
        try {
            while(bytes[0]!='b'&&bytes[1]!='y'&&bytes[2]!='e')
            //while(!new String(bytes,0,2).equals("bye"))
            {
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                socket.receive(packet);
                String str=new String(bytes,0, bytes.length);
                System.out.println("Sever Receive :" + str);
                //转换成大写,发回去,现在还不对
//                bytes=str.toUpperCase().getBytes();
//                packet=new DatagramPacket(bytes, bytes.length);
//                socket.send(packet);
            }
            System.out.println("接收端已经退出");
        }
        catch(Exception e){
            System.out.println("发现错误");
        }
        finally {
            socket.close();
        }
    }
}

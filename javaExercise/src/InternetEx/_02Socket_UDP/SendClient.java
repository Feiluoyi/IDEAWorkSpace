package InternetEx._02Socket_UDP;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class SendClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket=new DatagramSocket();
        Scanner scanner=new Scanner(System.in);
        String str=scanner.nextLine();

        while(str!=null){
             byte[] bytes=str.getBytes();
             DatagramPacket packet=new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"),12001);
             socket.send(packet);
//             socket.receive(packet);
//             String str1=packet.toString();
//             System.out.println("Server :"+str1);
             if(str.equals("bye")) break;
             str=scanner.nextLine();
        }

        System.out.println("发送端退出");
        socket.close();
    }

}

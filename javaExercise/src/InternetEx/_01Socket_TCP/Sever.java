package InternetEx._01Socket_TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端程序
 * 当前存在的问题:没有尝试关闭服务端的指令
 * 没有将欢迎套接字和连接套接字线程分离
 */
public class Sever {
    public static void main(String[] args) {
        try {
            ServerSocket sever=new ServerSocket(12000);
            Socket socket=sever.accept();

            InputStreamReader socIn=new InputStreamReader(socket.getInputStream());
            BufferedReader socBuf=new BufferedReader(socIn);

            PrintWriter socOut=new PrintWriter(socket.getOutputStream());

            //System.out.println("Client :"+socBuf.readLine());
            String readLine=socBuf.readLine();
            while (!readLine.equals("bye")){
                String sendBack=readLine.toUpperCase();
                System.out.println("Sever send :"+sendBack);
                socOut.println(sendBack);
                socOut.flush();

                readLine=socBuf.readLine();
            }
            socIn.close();
            socOut.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

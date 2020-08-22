package InternetEx._01Socket_TCP;
/**
 * 客户端程序
 * 当前存在的问题
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 12000);

            InputStreamReader sysIn=new InputStreamReader(System.in);
            BufferedReader sysBuf=new BufferedReader(sysIn);

            InputStreamReader socIn=new InputStreamReader(socket.getInputStream());
            BufferedReader socBuf=new BufferedReader(socIn);

            PrintWriter socOut=new PrintWriter(socket.getOutputStream());

            String readLine=sysBuf.readLine();
            while(!readLine.equals("bye")){
                socOut.println(readLine);
                socOut.flush();
                System.out.println("Client :"+readLine);

                System.out.println("Sever :"+socBuf.readLine());
                readLine=sysBuf.readLine();
            }
            socOut.println("bye");
            socIn.close();
            socIn.close();
            socOut.close();
            socket.close();
        }
        catch (Exception e){
            System.out.println("发生错误");
        }
    }
}

import jdk.internal.util.xml.impl.Input;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPex {
    @Test
    public void client(){
        Socket socket= null;
        OutputStream ops= null;
        try {
            socket = new Socket("127.0.0.1",8890);
            ops = socket.getOutputStream();

            ops.write("你好,我是客户端".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ops!=null){
                    ops.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket!=null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    @Test
    public void sever(){
        ServerSocket ss= null;
        Socket so= null;
        InputStream ips= null;
        ByteArrayOutputStream bai= null;
        try {
            ss = new ServerSocket(8890);
            so = ss.accept();

            ips = so.getInputStream();

            bai = new ByteArrayOutputStream();
            byte[] bytes=new byte[5];
            int len;
            while((len=ips.read(bytes))!=-1){
                bai.write(bytes,0,len);
            }
            System.out.println(bai.toString());
            System.out.println("接收到从"+so.getInetAddress()+"发送的数据");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bai!=null){
                    bai.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(ips!=null) {
                    ips.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(so!=null){
                    so.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(ss!=null){
                    ss.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 客户端向服务端发送图片并接受服务器的收到回执
     * @throws IOException
     */
    @Test
    public void client2() throws IOException{
        //1.定义socket,文件,输入流和输出流
        File file=new File("cat.jpg");
        Socket so=new Socket("127.0.0.10",8891);
        OutputStream os=so.getOutputStream();
        FileInputStream fis=new FileInputStream(file);
        //2.定义数组,读取文件发送
        byte[] buffer=new byte[20];
        int len1;

        while((len1=fis.read(buffer))!=-1){
            os.write(buffer);
        }
        //3.关闭输出流
        so.shutdownOutput();
        //4.接受服务器发送的信息
        InputStream is= so.getInputStream();
        ByteArrayOutputStream bao=new ByteArrayOutputStream();
        while ((len1= is.read(buffer))!=-1){
            bao.write(buffer,0,len1);
        }
        System.out.println(bao.toString());
        //5.关闭资源
        bao.close();
        is.close();
        so.close();
        os.close();
    }

    @Test
    public void sever2() throws IOException {
        //1.定义服务器socket,文件,输入流,输出流
        ServerSocket ss=new ServerSocket(8891);
        Socket socket=ss.accept();
        File file1=new File("cat1.jpg");
        InputStream is=socket.getInputStream();
        FileOutputStream fos=new FileOutputStream(file1);
        //2.读取文件
        int len2;
        byte[] buffer2=new byte[20];
        while((len2=is.read(buffer2))!=-1){
            fos.write(buffer2,0,len2);
        }
        //3.发送接受回执
        OutputStream os=socket.getOutputStream();
        os.write("接收到图片".getBytes());
        //4.关闭回执
        os.close();
        fos.close();
        is.close();
        socket.close();
        ss.close();
    }
}

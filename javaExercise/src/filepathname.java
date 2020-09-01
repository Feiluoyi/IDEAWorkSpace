import org.junit.Test;
import org.junit.internal.runners.statements.RunAfters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;

public class filepathname implements Runnable {
    public static int sum=0;
    @Override
    public void run() {
        System.out.println("靖旭是个傻子,笑死我了");
    }

    public static void main(String[] args) {
        filepathname filepathname=new filepathname();
        Thread thread=new Thread(filepathname);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();
    }
    @Test
    public void test1(){
        //Windows使用反斜杠,为避免转义,前面加了转义字符,但是也可以用url常用的/
        //File.seperator 和位置用+连接起来,可以避免使用杠
        File file=new File("he.txt");
        if(file.exists()){
            file.delete();
            System.out.println("文件删除成功");
        }
        else{
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("文件创建成功");
        }
    }
    @Test
    public void test2(){
        File file1=new File("D:\\浏览器下载\\VNote");
        print(file1);
        System.out.println(sum);
    }
    private void print(File file1){
        if(file1.isFile()){
            System.out.println(file1.getName());
            System.out.println(file1.length()/1000);
            sum+=file1.length()/1000;
        }
        else if(file1.isDirectory()){
            File[] files=file1.listFiles();
            for(File f:files){
                print(f);
            }
        }
        else return;
    }
}

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class IOtest {
    //把文件的内容读取并打印
    @Test
    public void print() {
        FileReader fileReader= null;
        try {
            //1.实例化file对象,指明要操作的文件
            File file=new File("hello.txt");  //相对路径相较于当前module
            //2.提供具体的流
            fileReader = new FileReader(file);
            //3.数据的读入
            char[] chars=new char[5];
            int len;
            while(( len=fileReader.read(chars))!=-1){
                String str=new String(chars,0,len);
                System.out.print(str);
            }
        }
        catch (IOException e) { e.printStackTrace(); }
        finally {
            //4.手动关闭流
            try { if(fileReader!=null) fileReader.close(); }
            catch (IOException e) { e.printStackTrace();}
        }


    }
}

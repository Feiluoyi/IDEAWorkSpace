import org.junit.Test;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class IOtest {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        while(scan.hasNext()){
            String str=scan.next();
            if(str.equals("bye")){
                System.out.println("程序退出,拜拜了您内");
                break;
            }
            else System.out.println(str);
        }
    }
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
    @Test
    /**
     * 使用字符流对文本文件进行复制
     */
    public void test() {
        //1.创建文件
        FileReader fr= null;
        FileWriter fw= null;
        try {
            File file=new File("hello.txt");
            File file1=new File("hello1.txt");
            if(file1.exists()){
                file1.delete();
            }
            //2.提供具体的流
            fr = new FileReader(file);
            fw = new FileWriter(file1,true);
            //3.使用流
            char[] chars=new char[5];
            int len;
            while((len=fr.read(chars))!=-1) {
                fw.write(chars,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭文件流
            try {
                if(fw!=null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fr!=null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    /**
     * 使用字节流对图片进行复制
     */
    public void test4() throws IOException {
        FileInputStream fi= null;
        FileOutputStream fo= null;
        try {
            File file=new File("cat.jpg");
            File file1=new File("cat1.jpg");

            fi = new FileInputStream(file);
            fo = new FileOutputStream(file1);

            byte[] bte=new byte[20];
            int len;
            while((len=fi.read(bte))!=-1){
                fo.write(bte,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fo!=null)
                fo.close();
            try {
                if(fi!=null)
                    fi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public  void test5(){
        char[] ca=new char[]{'靖','旭','今','天','像','个','傻','子'};
            System.out.print(ca[5]+" ");

        System.out.println();
    }
    public void copyFile(String filePath1,String filePath2){
        FileInputStream fi= null;
        FileOutputStream fo= null;
        try {
            File file=new File(filePath1);
            File file1=new File(filePath2);

            fi = new FileInputStream(file);
            fo = new FileOutputStream(file1);

            byte[] bte=new byte[1024];
            int len;
            while((len=fi.read(bte))!=-1){
                fo.write(bte,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fi!=null)
                    fi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test6(){

        long start=System.currentTimeMillis();
        copyFile("00.avi","01.avi");
        long time=System.currentTimeMillis()-start;
        System.out.println(time);
    }
    @Test
    public void scannertest(){
        Scanner scanner=new Scanner(System.in);
        String next = scanner.next();
        System.out.println(next);
    }
    @Test
    /**
     * 缓冲流的使用
     */
    public void test7(){
        long start=System.currentTimeMillis();
        BufferedInputStream bis= null;
        BufferedOutputStream bos= null;
        try {
            File file=new File("10.avi");
            File file2=new File("11.avi");

            FileInputStream fis=new FileInputStream(file);
            FileOutputStream fos=new FileOutputStream(file2);

            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            byte[] bt=new byte[1024];
            int len;

            while((len=bis.read(bt))!=-1){
                bos.write(bt,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bis!=null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bos!=null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(System.currentTimeMillis()-start);
    }
    @Test
    public void test9(){
        int[] nums1={1,2};
        int[] nums2={3,4};
        test8(nums1,nums2);
    }
    public double test8(int[] nums1, int[] nums2) {
        int n=nums1.length+nums2.length;
        int[] result=new int[n];
        int i=0,j=0;
        for(int k=0;k<n;k++){
            if(i>=nums1.length) result[k]=nums2[j++];
            else if(j>=nums2.length) result[k]=nums1[i++];
            else if(nums1[i]>nums2[j]) result[k]=nums2[j++];
            else result[k]=nums1[i++];
        }
        for(int kkk:result){
            System.out.println(kkk);
        }
        if((n&1)==1) return (double)result[n/2];
        return ((double)(result[n/2]+result[n/2+1]))/2;
    }
    @Test
    public void test10() throws UnknownHostException {
        InetAddress add1 = Inet4Address.getByName("192.186.2.242");
        System.out.println(add1);
        InetAddress add2= Inet4Address.getByName("www.baidu.com");
        System.out.println(add2);
        InetAddress add3 = Inet4Address.getLocalHost();
        System.out.println(add3);

    }

}

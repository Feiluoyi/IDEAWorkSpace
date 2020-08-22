package _04ThreadLocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *两个线程打印日期,没什么问题
 */
public class _02ThreadLocalNormalUsage {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date=new _02ThreadLocalNormalUsage().date(10);
                System.out.println(date);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date=new _02ThreadLocalNormalUsage().date(20);
                System.out.println(date);
            }
        }).start();
    }
    public String date(int seconds){
        //参数的单位是毫秒,从1970.1.1 00:00:00 开始计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }
}

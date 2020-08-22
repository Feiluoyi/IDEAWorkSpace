package _04ThreadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *多个线程打印日期
 */
public class _03ThreadLocalNormalUsage {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <30 ; i++) {
            int finalI=i;            //这里为什么不能直接用循环条件里面的i呢
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String date=new _03ThreadLocalNormalUsage().date(10*finalI);
                    System.out.println(date);
                }
            }).start();
            Thread.sleep(50);
        }

    }
    public String date(int seconds){
        //参数的单位是毫秒,从1970.1.1 00:00:00 开始计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }
}

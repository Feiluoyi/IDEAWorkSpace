package _04ThreadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *使用ThreadLocal解决上一节的问题
 *每个线程设置一个独有的dateformat对象,这样既保证了效率也不会产生线程安全问题
 */
public class _05ThreadLocalNormalUsage {
    private static ExecutorService threadPool=Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <1000 ; i++) {
            int finalI=i;   //这里为什么不能直接用循环条件里面的i呢
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date=new _05ThreadLocalNormalUsage().date(10*finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();

    }
    public String date(int seconds){
        //参数的单位是毫秒,从1970.1.1 00:00:00 开始计时
        Date date = new Date(1000 * seconds);
//        SimpleDateFormat simpleDateFormat =
        SimpleDateFormat simpleDateFormat=ThreadSafeFormatter.dateFormatThreadLocal2.get();
        return simpleDateFormat.format(date);
    }
}

class ThreadSafeFormatter{
     public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal=new ThreadLocal<SimpleDateFormat>(){
         @Override
         protected SimpleDateFormat initialValue() {
             return new SimpleDateFormat("yy-MM-dd hh:mm:ss");
         }
     };
     public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal2=
             ThreadLocal.withInitial(()->new SimpleDateFormat("yy-MM-dd hh:mm:ss"));
}

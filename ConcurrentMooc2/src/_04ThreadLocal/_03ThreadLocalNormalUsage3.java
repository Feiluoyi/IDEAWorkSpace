package _04ThreadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *使用线程池来打印1000个任务,将SimpleDateFormat外置为静态发生线程安全问题
 */
public class _03ThreadLocalNormalUsage3 {
    private static ExecutorService threadPool=Executors.newFixedThreadPool(10);
    //SimpleDateFormat放在外面作为静态对象,出现了问题
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <1000 ; i++) {
            int finalI=i;   //这里为什么不能直接用循环条件里面的i呢
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date=new _03ThreadLocalNormalUsage3().date(10*finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();

    }
    public String date(int seconds){
        //参数的单位是毫秒,从1970.1.1 00:00:00 开始计时
        Date date = new Date(1000 * seconds);
        return simpleDateFormat.format(date);
    }
}

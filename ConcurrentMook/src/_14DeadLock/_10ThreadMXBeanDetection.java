package _14DeadLock;

import javax.management.MXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class _10ThreadMXBeanDetection implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        _10ThreadMXBeanDetection r1=new _10ThreadMXBeanDetection();
        _10ThreadMXBeanDetection r2=new _10ThreadMXBeanDetection();
        r1.flag=0;
        r2.flag=1;
        Thread thread1=new Thread(r1);
        Thread thread2=new Thread(r2);
        thread1.start();
        thread2.start();
        Thread.sleep(1000);   //睡一会免得检测不到就过去了
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        if(deadlockedThreads!=null&&deadlockedThreads.length>0){
            for (int i = 0; i < deadlockedThreads.length; i++) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(deadlockedThreads[i]);
                System.out.println("发现死锁"+threadInfo.getThreadName());
            }
        }
    }
    static Object o1=new Object();
    static Object o2=new Object();
    int flag;
    @Override
    public void run() {
        System.out.println("flag="+flag);
        if(flag==0)
        {
            synchronized (o1)
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                }
            }
        }
        if(flag==1)
        {
            synchronized (o2)
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                }
            }
        }
    }
}

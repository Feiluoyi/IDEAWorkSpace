package _12JMM_1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 演示重排序的情况,因为不总是一样,就多次重复直到达到某个条件再停止
 */
public class _06OutOfOrderExcution {
    private static int x=0,y=0;
    private static int a=0,b=0;

    public static void main(String[] args) throws InterruptedException {
        int i=0;
        for (;;)
        {
            i++;
            x=0;y=0;a=0;b=0;
        CountDownLatch latch=new CountDownLatch(1);
        Thread one=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a=1;
                x=b;
            }
        });
        Thread two=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b=1;
                y=a;
            }
        });
        one.start();
        two.start();
        latch.countDown();
        one.join();
        two.join();
        String result="第"+i+"次"+"x="+x+", y="+y;
        if(x==0&&y==0) {System.out.println(result); return;}
        else System.out.println(result);
        }

    }
}

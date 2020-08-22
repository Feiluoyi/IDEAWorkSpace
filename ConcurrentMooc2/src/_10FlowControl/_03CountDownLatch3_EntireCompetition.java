package _10FlowControl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟100米跑步,5名参赛者都准备好了,只等裁判员一声令下,同时起跑
 */
public class _03CountDownLatch3_EntireCompetition {
    public static void main(String[] args) {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(5);
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no=i+1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("运动员" + no + "准备好了");
                    try {
                        begin.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("运动员" + no + "跑啦");
                    try {
                        Thread.sleep((long)(Math.random()*1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("运动员" + no + "到达终点");
                    end.countDown();
                }
            };
            pool.submit(runnable);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            begin.countDown();
        }
        System.out.println("开跑!");
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("比赛结束");
        pool.shutdown();
    }
}

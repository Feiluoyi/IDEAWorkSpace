package _10FlowControl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示CountDownLatch用法1,一个线程等待多个线程完成之后在进行后续动作
 */
public class _02CountDownLatch1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService threadpool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no=i+1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("No." + no + "检查完成");
                        latch.countDown();
                    }
                }
            };
            threadpool.submit(runnable);
        }
        System.out.println("等待检查完成.......");
        latch.await();
        threadpool.shutdown();
        System.out.println("所有检查已经完成");
    }
}

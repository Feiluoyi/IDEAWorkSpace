package _10FlowControl;
/**
 * 尝试使用semaphore
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class _05SemaPhore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3,true);
        ExecutorService pool = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 100; i++) {
            final int no=i+1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(no + "拿到了许可证");
                    try {
                        Thread.sleep((long)(Math.random()*1000));
                        System.out.println("  "+no + "释放了了许可证");
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            pool.submit(runnable);
        }
        pool.shutdown();
    }
}

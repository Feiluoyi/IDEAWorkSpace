package _03ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示内存不够用
 */
public class _04FixedThreadPoolOOM {

    public static void main(String[] args) {
    ExecutorService executorService=Executors.newFixedThreadPool(1);
        for (int i = 0; i <Integer.MAX_VALUE; i++) {
            executorService.execute(new SubThread());
        }
    }
}
class SubThread implements  Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

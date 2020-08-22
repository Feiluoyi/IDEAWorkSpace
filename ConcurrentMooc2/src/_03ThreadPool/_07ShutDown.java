package _03ThreadPool;

import sun.awt.windows.ThemeReader;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 关闭线程池shutdown方法
 */
public class _07ShutDown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i <100 ; i++) {
            executorService.execute(new ShutDownTask());
        }
        Thread.sleep(1500);
        List<Runnable> runnablelists = executorService.shutdownNow();
        System.out.println(runnablelists);

//        System.out.println(executorService.isShutdown());
//        executorService.shutdown();
//        System.out.println(executorService.isShutdown());
//        System.out.println(executorService.isTerminated());
//        TimeUnit.SECONDS.sleep(10);
//        System.out.println(executorService.isTerminated());

//        executorService.execute(new ShutDownTask());//使用shutdown方法之后添加新任务会抛出异常
    }
}
class ShutDownTask implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断了");
//            e.printStackTrace();
        }
    }
}
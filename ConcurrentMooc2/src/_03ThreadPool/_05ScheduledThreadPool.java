package _03ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 周期线程池
 */
public class _05ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(10);
        for (int i = 0; i <1000 ; i++) {
//            schedulePool.schedule(new Task(),500,TimeUnit.MILLISECONDS);
            schedulePool.scheduleAtFixedRate(new Task(),1,3,TimeUnit.SECONDS);
        }
    }
}

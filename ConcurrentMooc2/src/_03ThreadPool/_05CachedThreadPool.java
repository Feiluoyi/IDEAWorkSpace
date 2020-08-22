package _03ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _05CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i <1000 ; i++) {
            executorService.execute(new Task());
        }
    }
}

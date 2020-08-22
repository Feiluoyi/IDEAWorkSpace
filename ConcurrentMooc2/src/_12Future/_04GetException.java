package _12Future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * get方法运行过程中抛出异常,直到get方法运行时才抛出异常
 */
public class _04GetException {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Integer> f = executorService.submit(new CallableTask1());
        try {
            Thread.sleep(500);
            System.out.println(f.isDone());
            System.out.println(f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        executorService.shutdown();
    }
    static class CallableTask1 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException();
        }
    }
}

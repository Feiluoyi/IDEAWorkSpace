package _12Future;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 批量提交任务时,用List批量接收结果
 */
public class _03MultiFutures {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        ArrayList<Future> futures = new ArrayList<Future>();
        for (int i = 0; i < 20; i++) {
            Future<Integer> future = executorService.submit(new CallableTask());
            futures.add(future);
        }
        for (int i = 0; i < 20; i++) {
            Future<Integer> future=futures.get(i);
            try {
                Integer number=future.get();
                System.out.println(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(300);
            return new Random().nextInt(1000);
        }
    }
}

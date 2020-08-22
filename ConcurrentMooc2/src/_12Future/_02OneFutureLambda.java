package _12Future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 演示一个Future的用法,lambda表达式
 */
public class _02OneFutureLambda {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //直接写一个callable接口的对象
        Callable<Integer> callable=()->{
            Thread.sleep(3000);
            return new Random().nextInt(1000);
        };
        Future<Integer> f = executorService.submit(callable);
        try {
            System.out.println(f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

}

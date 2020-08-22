package _12Future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 演示FutureTask用法
 */
public class _07FutureTask {
    public static void main(String[] args) {
        FutureTask<Integer> futureTask=new FutureTask<>(new CallableTask2());
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    static class CallableTask2 implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("正在计算");
            int sum=0;
            for (int i = 0; i < 100; i++) {
                sum+=i;
            }
            Thread.sleep(1000);
            return sum;
        }
    }
}

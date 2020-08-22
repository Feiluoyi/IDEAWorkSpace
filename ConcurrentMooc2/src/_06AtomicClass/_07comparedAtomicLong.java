package _06AtomicClass;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class _07comparedAtomicLong {
    public static void main(String[] args) {
        AtomicLong atomicLong=new AtomicLong(0);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        Long start=System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(new Task(atomicLong));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()){}
        Long end =System.currentTimeMillis();
        System.out.println("计算结果 :"+atomicLong);
        System.out.println("用时 :"+(float)(end-start)/1000+"秒");
    }

    private static class Task implements Runnable{
        private AtomicLong atomicLong;

        public Task(AtomicLong atomicLong) {
            this.atomicLong = atomicLong;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                atomicLong.incrementAndGet();
            }
        }
    }
}

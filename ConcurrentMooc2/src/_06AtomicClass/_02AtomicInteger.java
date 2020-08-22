package _06AtomicClass;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示原子整形的基本用法
 */
public class _02AtomicInteger implements Runnable {
    private static final AtomicInteger atomicint=new AtomicInteger();
    private static volatile int count;
    private void atomicIncerement(){
        atomicint.incrementAndGet();
    }
    private void intIncrement(){
        count++;
    }
    public void get(){
        System.out.println(atomicint);
        System.out.println(count);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            atomicIncerement();
            intIncrement();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        _02AtomicInteger atomicInteger=new _02AtomicInteger();
        Thread thread1 = new Thread(atomicInteger);
        Thread thread2 = new Thread(atomicInteger);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        atomicInteger.get();
    }
}

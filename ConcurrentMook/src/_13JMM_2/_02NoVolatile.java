package _13JMM_2;

import java.util.concurrent.atomic.AtomicInteger;

public class _02NoVolatile implements Runnable{
    volatile int a;
    AtomicInteger realIndex=new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable=new _02NoVolatile();
        Thread thread1=new Thread(runnable);
        Thread thread2=new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(((_02NoVolatile)runnable).a);
        System.out.println(((_02NoVolatile)runnable).realIndex);
    }
    @Override
    public void run() {
        for (int i = 0; i <10000 ; i++) {
            a++;
            realIndex.incrementAndGet();
        }
    }
}

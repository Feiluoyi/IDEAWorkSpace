package _06AtomicClass;
/**
 * AtomicIntegerFieldUpdater常规演示
 */

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class _06AtomicIntegerFieldUpdater implements Runnable{
     Classmate tom=new Classmate();
     Classmate peter=new Classmate();
    public static AtomicIntegerFieldUpdater atomicIntegerFieldUpdater=
            AtomicIntegerFieldUpdater.newUpdater(Classmate.class,"score");//指定怎么处理

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            peter.score++;
            atomicIntegerFieldUpdater.getAndIncrement(tom);//在这里指定处理那个对象
        }
    }
    public static void main(String[] args) throws InterruptedException {
        _06AtomicIntegerFieldUpdater atom=new _06AtomicIntegerFieldUpdater();
        Thread thread1=new Thread(atom);
        Thread thread2=new Thread(atom);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("tom :"+atom.tom.score);
        System.out.println("peter :"+atom.peter.score);
    }

    public static class Classmate{
        volatile int score;
    }
}

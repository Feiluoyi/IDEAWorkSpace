package _06AtomicClass;

import jdk.nashorn.internal.ir.ReturnNode;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 演示原子数组的使用方法
 */
public class _04AtomicArray {
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArray atomicArray=new AtomicIntegerArray(1000);
        Decrement decrement=new Decrement(atomicArray);
        Increment increment=new Increment(atomicArray);
        Thread[] threads=new Thread[100];
        Thread[] threads2=new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(decrement);
            threads2[i]=new Thread(increment);
            threads[i].start();
            threads2[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
            threads2[i].join();
        }
        for (int i = 0; i < 1000; i++) {
            //if(atomicArray.get(i)!=0){
                System.out.println(atomicArray.get(i)+":"+i);
            //}
        }
        System.out.println("运行结束");
    }



}
class Increment implements Runnable {
    private AtomicIntegerArray atomicIntegerArray;

    public Increment(AtomicIntegerArray atomicIntegerArray) {
        this.atomicIntegerArray = atomicIntegerArray;
    }

    @Override
    public void run() {
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            atomicIntegerArray.getAndIncrement(i);
        }
    }
}
class Decrement implements Runnable{
    private AtomicIntegerArray atomicIntegerArray;

    public Decrement(AtomicIntegerArray atomicIntegerArray) {
        this.atomicIntegerArray = atomicIntegerArray;
    }
    @Override
    public void run() {
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            atomicIntegerArray.getAndDecrement(i);
        }
    }
}

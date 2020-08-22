package _05Lock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示读写锁的普通用法
 */
public class _12ReentrantReadWriteLock {
    private static ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock=reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock=reentrantReadWriteLock.writeLock();
    public static void write(){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"正在写入");
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("完成写入");
        }finally {
            writeLock.unlock();
        }
    }
    public static void read(){
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"正在读取");
            try {
                Thread.sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"完成读取");
        }finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(()->read()).start();
        new Thread(()->read()).start();
        new Thread(()->read()).start();
        new Thread(()->read()).start();
        new Thread(()->write()).start();
        new Thread(()->read()).start();
    }
}

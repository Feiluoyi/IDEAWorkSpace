package _14DeadLock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用trylock避免死锁
 */
public class _16TryLockDeadLock implements Runnable{
    int flag=1;
    static Lock lock1=new ReentrantLock();
    static Lock lock2=new ReentrantLock();

    public static void main(String[] args) {
        _16TryLockDeadLock r1=new _16TryLockDeadLock();
        _16TryLockDeadLock r2=new _16TryLockDeadLock();
        r1.flag=1;
        r2.flag=0;
        new Thread(r1).start();
        new Thread(r2).start();
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(flag==1){
                try {
                    if(lock1.tryLock(800, TimeUnit.MILLISECONDS)){
                        System.out.println("线程1获取到了锁1");
                        Thread.sleep(new Random().nextInt(1000));
                        if(lock2.tryLock(700,TimeUnit.MILLISECONDS)){
                            System.out.println("线程1获取到了两把锁");
                            lock2.unlock();
                            lock1.unlock();
                            break;
                        }
                        else{
                            lock1.unlock();
                            System.out.println("线程1获取锁2失败,已重试");
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }
                    else{
                        System.out.println("线程1获取锁1失败,已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(flag==0){
                try {
                    if(lock2.tryLock(3000, TimeUnit.MILLISECONDS)){
                        System.out.println("线程2获取到了锁2");
                        Thread.sleep(new Random().nextInt(1000));
                        if(lock1.tryLock(3000,TimeUnit.MILLISECONDS)){
                            System.out.println("线程2获取到了两把锁");
                            lock1.unlock();
                            lock2.unlock();
                            break;
                        }
                        else{
                            lock2.unlock();
                            System.out.println("线程2获取锁1失败,已重试");
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }
                    else{
                        System.out.println("线程2获取锁2失败,已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
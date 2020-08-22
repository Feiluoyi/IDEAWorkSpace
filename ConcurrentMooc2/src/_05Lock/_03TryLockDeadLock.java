package _05Lock;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用tryLock演示避免死锁
 */
public class _03TryLockDeadLock implements Runnable {
    private int flag = 1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        _03TryLockDeadLock r1=new _03TryLockDeadLock();
        _03TryLockDeadLock r2=new _03TryLockDeadLock();
        r1.flag=1;
        r2.flag=2;
        new Thread(r1).start();
        new Thread(r2).start();
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (flag == 1) {
                try {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "获取到了锁1");
                            Thread.sleep(new Random().nextInt(1000));
                            if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println(Thread.currentThread() + "获取到了锁2");
                                    System.out.println(Thread.currentThread() + "获取到了两把锁");
                                    break;     //退出循环
                                } finally {
                                    lock2.unlock();
                                    Thread.sleep(new Random().nextInt(1000));
                                }
                            } else {
                                System.out.println(Thread.currentThread() + "获取锁2失败已重试");
                            }
                        } finally {
                            lock1.unlock();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "获取锁1失败已重试");
                    }
                } catch (InterruptedException e) {
                    e.getStackTrace();
                }
            } else if (flag == 2) {
                try {
                    if (lock2.tryLock(3000, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "获取到了锁2");
                            Thread.sleep(new Random().nextInt(1000));
                            if (lock1.tryLock(3000, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println(Thread.currentThread() + "获取到了锁1");
                                    System.out.println(Thread.currentThread() + "获取到了两把锁");
                                    break;     //退出循环
                                } finally {
                                    lock1.unlock();
                                    Thread.sleep(new Random().nextInt(1000));
                                }
                            } else {
                                System.out.println(Thread.currentThread() + "获取锁1失败已重试");
                            }
                        } finally {
                            lock2.unlock();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "获取锁2失败已重试");
                    }
                } catch (InterruptedException e) {
                    e.getStackTrace();
                }
            }
        }
    }
}

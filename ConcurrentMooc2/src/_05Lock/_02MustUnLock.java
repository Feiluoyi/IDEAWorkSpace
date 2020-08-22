package _05Lock;

import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock不会像synchronized一样自动释放锁,而是需要在finally中手动释放锁
 */
public class _02MustUnLock {
    private static Lock lock=new ReentrantLock();
    public static void main(String[] args) {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"开始执行");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        finally {
            lock.unlock();
        }
    }
}

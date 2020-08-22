package _05Lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 演示自旋锁
 */
public class _18SpinLock {
    private AtomicReference<Thread> sign=new AtomicReference<>();
    public void lock(){
        Thread current=Thread.currentThread();
        while(!sign.compareAndSet(null,current)){
            System.out.println(Thread.currentThread().getName()+"获取锁失败,正在重试");
        }
    }
    public void unlock(){
        Thread current = Thread.currentThread();
        sign.compareAndSet(current,null);
    }

    public static void main(String[] args) {
        _18SpinLock spinLock=new _18SpinLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("开始尝试获取自旋锁");
                spinLock.lock();
                System.out.println(Thread.currentThread().getName() + Thread.currentThread().getName() + "获取到了自旋锁");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    spinLock.unlock();
                    System.out.println(Thread.currentThread().getName() + Thread.currentThread().getName() + "释放了自旋锁");
                }
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}

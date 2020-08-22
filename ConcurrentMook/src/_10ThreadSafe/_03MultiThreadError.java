package _10ThreadSafe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述: 第一种:运行结果出错
 * 演示计数减少,找出出错的具体位置
 */
public class _03MultiThreadError implements Runnable{
    private  int index=0;
    static _03MultiThreadError instance=new _03MultiThreadError();
    static AtomicInteger realIndex=new AtomicInteger();
    static AtomicInteger wrongIndex=new AtomicInteger();
    static volatile CyclicBarrier cyclicBarrier1=new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2=new CyclicBarrier(2);
    final boolean mark[]=new boolean[20001];
    public static void main(String[] args) throws InterruptedException {
        Thread thread1=new Thread(instance);
        Thread thread2=new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("表面上的结果是:"+instance.index);
        System.out.println("真正运行的结果是:"+realIndex.get());
        System.out.println("错误的结果是:"+wrongIndex.get());
    }
    @Override
    public void run() {
        mark[0]=true;
        for (int i = 0; i < 10000; i++) {
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) { e.printStackTrace(); } catch (BrokenBarrierException e) { e.printStackTrace(); }
            index++;
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
                } catch (InterruptedException e) { e.printStackTrace(); } catch (BrokenBarrierException e) { e.printStackTrace(); }
            realIndex.incrementAndGet();
            synchronized (instance) {
                if (mark[index]&&mark[index-1]) {
                    System.out.println("发生错误"+index);
                    wrongIndex.incrementAndGet();
                }
                mark[index] = true;
            }
        }
    }
}

package _03ThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示每个任务执行前后都可以放钩子函数
 */
public class _08PsuseableThreadPool extends ThreadPoolExecutor {
    public _08PsuseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
    public _08PsuseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }
    public _08PsuseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }
    public _08PsuseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    private boolean isPause;    //标记线程是否处于暂停状态
    private final ReentrantLock lock=new ReentrantLock();//为了让布尔值的并发修改是安全的的,上一把锁
    private Condition unpaused=lock.newCondition();      //condition是由lock引出的,后续章节会讲

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        lock.lock();
        try {
            while (isPause) {
                unpaused.await();
            }
        }
        catch (InterruptedException e) {
                    e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    //暂停方法
    private void pause() {
        lock.lock();                      //使用try catch是为了防止出现问题不释放锁
        try{
            isPause=true;
        }
        finally {
            lock.unlock();
        }
    }
    //恢复函数
    public void resume(){
        lock.lock();
        try{
            isPause=false;
            unpaused.signalAll();
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        _08PsuseableThreadPool psuseableThreadPool =
                new _08PsuseableThreadPool(10, 20,
                        10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                System.out.println("被执行了");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i <1000 ; i++) {
            psuseableThreadPool.execute(runnable);
        }
            Thread.sleep(1500);
        psuseableThreadPool.pause();
        System.out.println("线程池被暂停了");
        Thread.sleep(1500);
        psuseableThreadPool.resume();
        System.out.println("线程池被恢复了");
    }
}

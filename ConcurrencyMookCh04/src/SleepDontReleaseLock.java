import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SleepDontReleaseLock implements Runnable {
    /**
     *
     */
    private static final Lock lock=new ReentrantLock();

    public static void main(String[] args) {
        SleepDontReleaseLock sl=new SleepDontReleaseLock();
        new Thread(sl).start();
        new Thread(sl).start();
    }
    @Override
    public void run() {
        lock.lock();
        System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁");
        try {
            //Thread.sleep(5000);
            TimeUnit.MINUTES.sleep(3);
            System.out.println("线程"+Thread.currentThread().getName()+"释放了锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}

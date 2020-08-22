package _10FlowControl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class _06Condition {
    private ReentrantLock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    void method(){
        lock.lock();
        System.out.println("条件不满足,卡住");
        try {
            condition.await();
            System.out.println("满足条件,向下进行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
    void method2() throws InterruptedException {
        lock.lock();
        try {
            Thread.sleep(5000);
            condition.signal();
            System.out.println("方法2已将条件准备好了");
            Thread.sleep(5000);
            System.out.println("方法2释放锁");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        _06Condition condition1=new _06Condition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    condition1.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        condition1.method();
    }
}

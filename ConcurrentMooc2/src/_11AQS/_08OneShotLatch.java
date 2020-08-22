package _11AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 利用AQS实现一次性门闩
 */
public class _08OneShotLatch {
        public void await(){
            sync.acquireShared(0);
        }
        public void signal(){
            sync.releaseShared(0);
        }
        private final Sync sync=new Sync();
    private static class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected int tryAcquireShared(int arg) {
            return (getState()==1)?1:-1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    public static void main(String[] args) {
        _08OneShotLatch oneShotLatch = new _08OneShotLatch();
        for (int i = 0; i < 10; i++) {
            final int no=i+1;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程"+no+"准备运行");
                    oneShotLatch.await();
                    System.out.println("线程"+no+"被放行");
                }
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程计时结束");
        oneShotLatch.signal();
    }
}

public class AlternatePrintWaitAndNotify {
    /**
     * 使用wait和notify关键字完成更加准确的操作
     */
    private static int count=0;
    private static final Object lock=new Object();
    public static void main(String[] args) {
         //拿到锁就打印
         //打印完唤醒其他线程,并休眠
        new Thread(new TurningRunner()).start();
        new Thread(new TurningRunner()).start();
    }
    static class TurningRunner implements Runnable
    {
        @Override
        public void run() {
            while(count<=100)
            {
                synchronized (lock)
                {
                    System.out.println(Thread.currentThread().getName()+":"+count++);
                    lock.notify();
                    if(count<=100)
                    try {
                        //如果任务还没结束,就让出当前的锁
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


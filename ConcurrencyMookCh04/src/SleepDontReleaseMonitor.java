public class SleepDontReleaseMonitor implements Runnable{
    /**
     *
     */
    public static void main(String[] args) {
        SleepDontReleaseMonitor sl=new SleepDontReleaseMonitor();
        new Thread(sl).start();
        new Thread(sl).start();

    }

    @Override
    public void run() {
        syn();
    }
    private synchronized void syn()
    {
        System.out.println(Thread.currentThread().getName()+"获取了锁");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"退出了锁");

    }
}


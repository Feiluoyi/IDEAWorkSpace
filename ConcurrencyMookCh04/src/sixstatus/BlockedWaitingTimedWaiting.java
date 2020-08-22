package sixstatus;

public class BlockedWaitingTimedWaiting implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting runnable=new BlockedWaitingTimedWaiting();
        Thread thread1=new Thread(runnable);
        Thread thread2=new Thread(runnable);
        thread1.start();
        thread2.start();
        Thread.sleep(5);
        System.out.println("thread1:"+thread1.getState());
        System.out.println("thread2:"+thread2.getState());
        Thread.sleep(1200);
        System.out.println("thread1:"+thread1.getState());
        System.out.println("thread2:"+thread2.getState());
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            //wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

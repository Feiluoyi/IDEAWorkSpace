package JoinMethod;

public class Join {
    static Object lock=new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1=new Thread(new TT());
        thread1.start();
        Thread thread2=new Thread(new TT());
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("所有线程执行完毕");
    }
    static class TT implements Runnable
    {
        @Override
        public void run() {
            synchronized (lock)
            {
                System.out.println(Thread.currentThread().getName()+"开始执行");
                try {
                    Thread.sleep(400);
                    System.out.println(Thread.currentThread().getName()+"执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

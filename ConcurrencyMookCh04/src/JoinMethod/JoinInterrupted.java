package JoinMethod;

public class JoinInterrupted {
    public static void main(String[] args) {
        Thread mainThread=Thread.currentThread();
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程开始执行");
                try {
                    mainThread.interrupt();
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName()+"执行完");
                } catch (InterruptedException e) {
                    System.out.println("子线程被中断");
                    //e.printStackTrace();
                }
            }
        });
        thread1.start();
        System.out.println("等待子线程执行完毕");
        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"遇到中断");
           // thread1.interrupt();
            //e.printStackTrace();
        }
        System.out.println("子线程执行完毕");
    }
}

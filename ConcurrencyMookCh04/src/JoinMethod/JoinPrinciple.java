package JoinMethod;

/**
 * join方法的代替写法
 */
public class JoinPrinciple {
    static Object lock=new Object();
    public static void main(String[] args) throws InterruptedException {
        //new TT().run(); 这么写可以在主线程中直接调用run方法
        Thread mainThread=Thread.currentThread();
        Thread thread1=new Thread(new TT(),"试验线程");
        Thread thread2=new Thread(new TT(),"试验线程2");
        thread1.start();
        thread2.start();
        //thread1.join();
        synchronized (thread1)
        {
            thread1.wait();
        }
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
                    Thread.sleep(4000);
                    System.out.println(Thread.currentThread().getName()+"执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

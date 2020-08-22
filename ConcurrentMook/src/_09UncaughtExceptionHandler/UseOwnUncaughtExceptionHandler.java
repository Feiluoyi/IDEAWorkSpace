package _09UncaughtExceptionHandler;

public class UseOwnUncaughtExceptionHandler implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        //Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));
        new Thread(new UseOwnUncaughtExceptionHandler(),"线程1").start();Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(),"线程2").start();Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(),"线程3").start();Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(),"线程4").start();
    }
    @Override
    public void run(){
        throw new RuntimeException();
    }
}

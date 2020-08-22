package StopThreads;

import java.util.Map;

public class throwException implements Runnable{
    @Override
    public void run() {
        while(true)
        {
            System.out.println("go");
            try {
                syn();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
    private void syn() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(new throwException());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}

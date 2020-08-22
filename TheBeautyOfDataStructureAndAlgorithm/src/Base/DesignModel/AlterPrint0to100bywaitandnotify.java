package Base.DesignModel;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.PriorityQueue;

public class AlterPrint0to100bywaitandnotify {
    private static Object lock=new Object();
    private static int count=0;
    static class TurningPrinter implements Runnable
    {
        @Override
        public void run() {
            while(count<=100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName()+":"+count);
                    count++;
                    lock.notify();
                    if(count<=100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) { e.printStackTrace(); }
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        TurningPrinter printer=new TurningPrinter();
//        new Thread(printer).start();
//        new Thread(printer).start();
        new Thread(new TurningPrinter(),"偶数").start();
        Thread.sleep(100);
        new Thread(new TurningPrinter(),"奇数").start();
    }
}

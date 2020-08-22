public class MethodLock implements Runnable{
    Object lock1=new Object();
    Object lock2=new Object();

    public static void main(String[] args) {

        MethodLock me1=new MethodLock();

        Thread thread1=new Thread(me1);
        Thread thread2=new Thread(me1);
        thread1.start();
        thread2.start();
        while(thread1.isAlive()||thread2.isAlive()){}
        System.out.println("All Finished");
    }
    @Override
    public void run() {
        synchronized (this){
            System.out.println("Lock1"+ Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                System.out.println("Lock1 "+Thread.currentThread().getName()+"Finished");
            }
        }
//        synchronized (lock2){
//            System.out.println("Lock2"+ Thread.currentThread().getName());
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            finally {
//                System.out.println("Lock2 "+Thread.currentThread().getName()+"Finished");
//            }
//        }
    }
}

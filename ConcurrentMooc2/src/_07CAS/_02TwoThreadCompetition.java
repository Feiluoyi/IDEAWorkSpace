package _07CAS;

/**
 * cas等价代码
 */
public class _02TwoThreadCompetition implements Runnable{
    private int value;
    public synchronized int compareAndSwap(int exceptedValue,int newValue){
        int oldValue=value;
        if(oldValue==exceptedValue){
            value=newValue;
        }
        return oldValue;
    }

    public static void main(String[] args) throws InterruptedException {
        _02TwoThreadCompetition r=new _02TwoThreadCompetition();
        Thread t1 = new Thread(r,"Thread1");
        Thread t2 = new Thread(r,"Thread2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(r.value);
    }

    @Override
    public void run() {
        compareAndSwap(0,1);
    }
}

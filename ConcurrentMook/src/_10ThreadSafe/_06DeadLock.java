package _10ThreadSafe;

public class _06DeadLock implements Runnable{
    int flag;
    static Object o1=new Object();
    static Object o2=new Object();
    public static void main(String[] args) {
        _06DeadLock dl1=new _06DeadLock();
        _06DeadLock dl2=new _06DeadLock();
        dl1.flag=1;
        dl2.flag=0;
        new Thread(dl1).start();
        new Thread(dl2).start();
    }
    @Override
    public void run() {
        if(flag==1){
            synchronized (o1)
            {
                try {
                    Thread.sleep(500);
                    System.out.println("flag="+flag);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2)
                {

                }
            }
        }
        if(flag==0){
            synchronized (o2)
            {
                try {
                    Thread.sleep(500);
                    System.out.println("flag="+flag);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1)
                {

                }
            }
        }
    }
}

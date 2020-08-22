
public class AlternatePrintSynchronized {
    /**新建两个线程
     * 两个线程之间使用synchronized关键字通信
     * 使用位运算来处理奇偶数
     * @param args
     */
    private static int count;
    private static final Object lock=new Object();
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(count<100)
                {
                    synchronized (lock)
                    {
                        if((count&1)==0)
                        {
                            System.out.println(Thread.currentThread().getName()+":"+count++);
                        }
                    }
                }
            }
        },"偶数").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(count<100)
                {
                    synchronized (lock)
                    {
                        if((count&1)==1)
                        {
                            System.out.println(Thread.currentThread().getName()+":"+count++);
                        }
                    }
                }
            }
        },"奇数").start();
    }
}

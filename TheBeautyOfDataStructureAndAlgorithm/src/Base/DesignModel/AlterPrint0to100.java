package Base.DesignModel;

/**
 * 使用synchronized关键字实现
 */
public class AlterPrint0to100 {
    private static Object lock=new Object();
    private static int count=0;
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(count<=100) {
                    synchronized (lock) {
                        if ((count & 1) == 0) {
                            System.out.println(Thread.currentThread().getName() + count);
                            count++;
                        }
                    }
                }
            }
        },"偶数:").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(count<=99) {
                    synchronized (lock) {
                        if ((count & 1) == 1) {
                            System.out.println(Thread.currentThread().getName() + count);
                            count++;
                        }
                    }
                }
            }
        },"奇数:").start();
    }
}

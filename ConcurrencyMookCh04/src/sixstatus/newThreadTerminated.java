package sixstatus;

/**
 * 展示线程new runnable和terminated状态
 * 即使正在运行也是runnable状态
 */
public class newThreadTerminated implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1300; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Thread thread=new Thread(new newThreadTerminated());
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());
    }
}

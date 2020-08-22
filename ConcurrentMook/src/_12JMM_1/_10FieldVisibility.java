package _12JMM_1;

/**
 * 演示可见性带来的问题
 */
public class _10FieldVisibility {
    int a=1;
    volatile int b=2;
    public void change()
    {
        a=3;
        b=a;
    }
    public void print(){
        System.out.println("b="+b+",a="+a);
    }

    public static void main(String[] args) {
        while (true) {
            _10FieldVisibility visibility = new _10FieldVisibility();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    visibility.change();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    visibility.print();
                }
            }).start();
        }
    }
}

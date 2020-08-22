package _14DeadLock;

import _10ThreadSafe.Observer.Observer;
import sun.java2d.pipe.SpanClipRenderer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class _05SimpleDeadLock implements Runnable{
    public static void main(String[] args) {
        _05SimpleDeadLock r1=new _05SimpleDeadLock();
        _05SimpleDeadLock r2=new _05SimpleDeadLock();
        r1.flag=0;
        r2.flag=1;
        Thread thread1=new Thread(r1);
        Thread thread2=new Thread(r2);
        thread1.start();
        thread2.start();
    }
    static Object o1=new Object();
    static Object o2=new Object();
    int flag;
    @Override
    public void run() {
        System.out.println("flag="+flag);
        if(flag==0)
        {
            synchronized (o1)
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                }
            }
        }
        if(flag==1)
        {
            synchronized (o2)
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                }
            }
        }
    }
}

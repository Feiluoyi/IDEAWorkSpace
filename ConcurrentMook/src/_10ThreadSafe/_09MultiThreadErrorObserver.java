package _10ThreadSafe;

import _13JMM_2._10SingletonMode8;

/**
 * 使用观察者模式
 */
public class _09MultiThreadErrorObserver {
    int count;
    public _09MultiThreadErrorObserver(MySource source){
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                System.out.println("\n我得到的数字是:"+count);
            }
        });
        for (int i = 0; i <10000 ; i++) {
            System.out.print(i);
        }
        count=100;//把这句移到监听器前面,监听器得到的数字就是100了
    }

    public static void main(String[] args) {
        _10SingletonMode8.INSTANCE.whatEver();
        MySource mySource=new MySource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySource.eventCome(new Event() {
                });
            }
        }).start();
        _09MultiThreadErrorObserver multiThreadErrorObserver=new _09MultiThreadErrorObserver(mySource);
    }

    static class MySource{
        private EventListener listener;
        void registerListener(EventListener eventListener)
        {
            this.listener=eventListener;
        }
        void eventCome(Event e){
            if(listener!=null)
            {
                listener.onEvent(e);
            }else{
                System.out.println("还没初始化完毕");
            }
        }
    }
    interface EventListener{
        void onEvent(Event e);
    }
    interface Event{}
}

package _10ThreadSafe;

/**
 *用工厂模式修复逸出问题
 */
public class _12FactoryModeRectifyErrorInObserver {
    int count;
    private EventListener listener;
    private  _12FactoryModeRectifyErrorInObserver(MySource source){//把构造方法用private保护起来
        listener=new EventListener() {
            @Override
            public void onEvent(_12FactoryModeRectifyErrorInObserver.Event e) {
                System.out.println("\n我得到的数字是:"+count);
            }
        };
        for (int i = 0; i <10000 ; i++) {
            System.out.print(i);
        }
        count=100;//把这句移到监听器前面,监听器得到的数字就是100了
    }
    public static _12FactoryModeRectifyErrorInObserver gerInstance(MySource source){
        _12FactoryModeRectifyErrorInObserver safe=new _12FactoryModeRectifyErrorInObserver(source);
        source.registerListener(safe.listener);
        return safe;
    }

    public static void main(String[] args) {
        _12FactoryModeRectifyErrorInObserver.MySource mySource=new _12FactoryModeRectifyErrorInObserver.MySource();
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
        _12FactoryModeRectifyErrorInObserver multiThreadErrorObserver=new _12FactoryModeRectifyErrorInObserver(mySource);
    }

    static class MySource{
        private _12FactoryModeRectifyErrorInObserver.EventListener listener;
        void registerListener(_12FactoryModeRectifyErrorInObserver.EventListener eventListener)
        {
            this.listener=eventListener;
        }
        void eventCome(_12FactoryModeRectifyErrorInObserver.Event e){
            if(listener!=null)
            {
                listener.onEvent(e);
            }else{
                System.out.println("还没初始化完毕");
            }
        }
    }
    interface EventListener{
        void onEvent(_12FactoryModeRectifyErrorInObserver.Event e);
    }
    interface Event{}
}

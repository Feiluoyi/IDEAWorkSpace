package _10ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * 在构造函数中新开线程
 */
public class _10MultiThreadError_newThreadInConstrutor {
    private Map<String,String> states;
    public _10MultiThreadError_newThreadInConstrutor()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                states=new HashMap<>();
                states.put("1","周一");
                states.put("2","周二");
                states.put("3","周三");
                states.put("4","周四");
            }
        }).start();

    }
    public Map<String,String> getStates()
    {
        return states;
    }

    public static void main(String[] args) {
        _10MultiThreadError_newThreadInConstrutor multi=new _10MultiThreadError_newThreadInConstrutor();
        Map<String,String> map=multi.getStates();
        System.out.println(map.get("1"));//其实子线程中的赋值还没完成,所以会报空指针异常
    }
}

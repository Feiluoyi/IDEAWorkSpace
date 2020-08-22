package _10ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * 发布逸出
 */
public class _07MultiThreadError {
    private Map<String,String> states;
    public _07MultiThreadError()
    {
        states=new HashMap<>();
        states.put("1","周一");
        states.put("2","周二");
        states.put("3","周三");
        states.put("4","周四");
    }
    public Map<String,String> getStates()
    {
        //return states;
        return new HashMap<>(states);  //修改的方法返回的是副本
    }

    public static void main(String[] args) {
        _07MultiThreadError multi=new _07MultiThreadError();
        Map<String,String> map=multi.getStates();
        System.out.println(map.get("1"));
        map.remove("1");
        System.out.println(multi.getStates().get("1"));
    }
}

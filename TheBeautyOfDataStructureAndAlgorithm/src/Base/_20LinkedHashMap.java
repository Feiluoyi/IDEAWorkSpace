package Base;

import sun.awt.image.ImageWatched;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class _20LinkedHashMap {
    public static void main(String[] args) {
        //LinkedHashMap需要将AccessOrder设置为true才能按照读取的顺序再排序
        LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>(10,0.75f,true);
        map.put(2,"aa");
        map.put(1,"aa");
        map.put(3,"aa");
        map.put(5,"aa");
        map.put(4,"aa");
        for (Map.Entry e:map.entrySet()){ System.out.print(e.getKey()); }
        System.out.println();
        map.put(3,"bb");
        for (Map.Entry e:map.entrySet()){ System.out.print(e.getKey()); }
        System.out.println();
        map.get(1);
        for (Map.Entry e:map.entrySet()){ System.out.print(e.getKey()); }
//        LinkedHashMap<Integer, Integer> m = new LinkedHashMap<>(10, 0.75f, true);
//        m.put(3, 11);
//        m.put(1, 12);
//        m.put(5, 23);
//        m.put(2, 22);
//
//        m.put(3, 26);
//        m.get(5);
//
//        for (Map.Entry e : m.entrySet()) {
//            System.out.println(e.getKey());
//        }
    }
}

package LeetCodeEx;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class _347 {
    public int[] topKFrequent(int[] nums, int k) {
        //先试用HashMap统计出现的频率
        HashMap<Integer,Integer> map =new HashMap<>();
        for(int z:nums){
            map.put(z,map.getOrDefault(z,0)+1);
        }
        System.out.println(map);
        PriorityQueue<Integer> queue=new PriorityQueue<>((o1, o2)->map.get(o1)-map.get(o2));
        for(int num:map.keySet()){
            queue.add(num);
            if(queue.size()>k) queue.poll();
        }
        int[] result=new int[k];
        for(int i=0;i<k;i++){
            result[i]=queue.poll();
        }
        return result;
    }
    public static void main(String[] args) {
        //下面的测试证明了TreeMap对于负数的key也是符合数学规则的
//        TreeMap<Integer,Integer> tmap=new TreeMap<>();
//        tmap.put(-1,4);
//        tmap.put(-2,4);
//        for(Map.Entry<Integer,Integer> entry:tmap.entrySet()){
//            System.out.println(entry.getKey()+" "+entry.getValue());
//        }
        _347 str=new _347();
        for(int k: str.topKFrequent(new int[]{1,2},2)){
            System.out.println(k);
        }
    }
}

package LeetCodeEx;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _451 {
    public String frequencySort(String s) {
        HashMap<Character,Integer> map=new HashMap<>();
        char[] words=s.toCharArray();
        for(char c:words){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for(Map.Entry<Character,Integer> entry:map.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
        PriorityQueue<Character> queue=new PriorityQueue<>((c1, c2)->map.get(c2)-map.get(c1));
        for(Map.Entry<Character,Integer> entry:map.entrySet()){
            queue.add(entry.getKey());
        }
        StringBuilder res=new StringBuilder();
        while(!queue.isEmpty()){
            char temp=queue.poll();
            for(int k=0;k<map.get(temp);k++){
                res.append(temp);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        _451 test=new _451();
        String s=test.frequencySort("tree");
        System.out.println(s);
    }
}

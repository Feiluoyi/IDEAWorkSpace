package Base;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

public class _09QueueTest {
//    public static void main(String[] args) {
//        ArrayDeque<Integer> qrr=new ArrayDeque<>();
//        PriorityQueue<Integer> pq=new PriorityQueue<>();
//        pq.add(5);
//        pq.add(4);
//        pq.add(6);
//        pq.add(3);
//        System.out.println(pq.remove());
//    }
public static void main(String[] args) {
            ArrayDeque<String> ad = new ArrayDeque<String>();

            ad.add("D");
            ad.offerFirst("E");
            ad.addFirst("F");
            ad.offer("A");
            ad.addLast("B");
            ad.offerLast("C");
            LinkedList<Integer> list=new LinkedList<>();
            list.add(5);
            System.out.println("队列中的元素：" + ad.toString());
//            System.out.println("队列中的第一个元素：" + ad.peekFirst());
//            System.out.println("移除队列中的元素：" + ad.remove());
//            System.out.println("队列中的元素：" + ad.toString());
//            System.out.println("移除队列中的元素：" + ad.pollLast());
//            System.out.println("队列中的元素：" + ad.toString());
        }
    }



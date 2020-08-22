package BaseClasssTest;

public class PriorityQueue {
    public static void main(String[] args) {
        java.util.PriorityQueue<Integer> pq=new java.util.PriorityQueue<>();
        pq.add(1);
        pq.add(3);
        pq.add(2);
        while(!pq.isEmpty()) {
            System.out.println(pq.remove());
        }
    }
}

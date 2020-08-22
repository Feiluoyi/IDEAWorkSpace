package _10FlowControl;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class _08CyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier1=new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("这5个人到齐,出发");
            }
        });
        for (int i = 0; i < 10; i++) {
            new Thread(new Task(i, cyclicBarrier1)).start();
        }
    }
    static class Task implements Runnable{
        private int id;
        private CyclicBarrier cyclicBarrier;

        public Task(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep((long)(Math.random()*1000));
                System.out.println("  人员"+id+"准备出发");
                try {
                    cyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("人员"+id+"已经出发");
        }
    }
}

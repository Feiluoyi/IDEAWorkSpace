package _10FlowControl;

import java.util.PriorityQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示用condition实现生产者消费者模式
 */
public class _07Condition_ProducerAndConsumer {
    private final int queuesize=10;
    private ReentrantLock lock=new ReentrantLock();
    private PriorityQueue<Integer> queue=new PriorityQueue<>(queuesize);
    private Condition notEmpty=lock.newCondition();
    private Condition notFull=lock.newCondition();

    public static void main(String[] args) {
        _07Condition_ProducerAndConsumer pac=new _07Condition_ProducerAndConsumer();
        pac.new Consumer().start();
        pac.new Producer().start();
    }
    class Consumer extends Thread{
        @Override
        public void run() {
            consumer();
        }

        private void consumer() {
            while (true) {
            lock.lock();
            try {
                    while (queue.size()==0){
                        try {
                            System.out.println("队列为空,等待元素");
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                        queue.poll();
                        System.out.println("从队列中拿走了一个元素,现在还剩"+queue.size()+"元素");
                        notFull.signal();
                }finally {
                    lock.unlock();
                }
            }
        }
    }
    class Producer extends Thread{
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size()==queuesize){
                        try {
                            System.out.println(" 队列已满,等待使用");
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.add(1);
                    System.out.println(" 向队列中添加了一个元素,现在还剩"+queue.size()+"元素");
                    notEmpty.signal();
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}

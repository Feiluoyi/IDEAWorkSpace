package _14DeadLock;

/**
 * 演示哲学家就餐导致的死锁
 */
public class _13DiningPhilosophers {
    public static class Philosopher implements Runnable{
        private Object leftChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }
        private Object rightChopstick;
        @Override
        public void run() {
            while(true){
                doAction("Thinking");
                synchronized (leftChopstick){
                    doAction("拿起左边的筷子");
                    synchronized (rightChopstick){
                        doAction("拿起右边的筷子");
                        doAction("放下右边的筷子");
                    }
                    doAction("放下左边的筷子");
                }
            }
        }
        private void doAction(String action){
            System.out.println(Thread.currentThread().getName()+" "+action);
            try {
                Thread.sleep((long) (Math.random()*10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Philosopher[] philosophers=new Philosopher[5];
        Object[] chopsticks=new Object[philosophers.length];
        for (int i = 0; i < philosophers.length; i++) {
            chopsticks[i]=new Object();
        }
        for (int i = 0; i < philosophers.length; i++) {
            if(i==philosophers.length-1)                         //改变最后一个哲学家拿起筷子的顺序,先右后左
                philosophers[i]=new Philosopher(chopsticks[0],chopsticks[i]);
            else {
                philosophers[i] = new Philosopher(chopsticks[i], chopsticks[(i + 1) % philosophers.length]);
            }
            new Thread(philosophers[i],"哲学家"+(i+1)+"号").start();
        }
    }
}

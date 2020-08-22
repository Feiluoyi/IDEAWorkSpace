package Base.DesignModel;

import java.util.Date;
import java.util.LinkedList;

public class ProducerConsumer {
    public static void main(String[] args) {
        EventStorage storage=new EventStorage();
        Producer producer=new Producer(storage);
        Consumer consumer=new Consumer(storage);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

}
class Producer implements Runnable
{
    EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++) {
            try {
                storage.put();
                System.out.println("将第"+i+"个产品放进了仓库.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Consumer implements Runnable
{
    EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++) {
            try {
                storage.get();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class EventStorage
{
    private int maxSize;
    private LinkedList<Date> storage;

    public EventStorage() {
        this.maxSize = 10;
        this.storage = new LinkedList<Date>();
    }
    public synchronized void put() throws InterruptedException {
        while(storage.size()==maxSize){wait();}
        storage.add(new Date());
        System.out.println("仓库目前有"+storage.size()+"个产品");
        notifyAll();
    }
    public synchronized void get() throws InterruptedException {
        while(storage.size()==0) wait();
        System.out.println("拿到了产品"+storage.poll()+"现在仓库还剩下"+storage.size());
        notifyAll();
    }
}
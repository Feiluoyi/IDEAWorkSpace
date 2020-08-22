package _14DeadLock;

import java.util.Random;

public class _19LiveLock {
    public static void main(String[] args) {
        Diner husband=new Diner("牛郎");
        Diner wife=new Diner("织女");
        Spoon spoon=new Spoon(husband);
        new Thread(new Runnable() {
            @Override
            public void run() {
                husband.eatWith(spoon,wife);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                wife.eatWith(spoon,husband);
            }
        }).start();
    }
    static class Spoon{
        private Diner owener;

        public Spoon(Diner owener) { this.owener = owener; }
        public Diner getOwener() { return owener; }
        public void setOwener(Diner owener) { this.owener = owener; }
        public synchronized void use(){ System.out.println(owener.name+"正在吃饭"); }
    }
    static class Diner{
        private String name;
        private boolean isHungry;

        public Diner(String name) {
            this.name = name;
            isHungry=true;
        }
        public void eatWith(Spoon spoon, Diner spouse){
            while(isHungry) {
                if (spoon.owener!=this) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                Random random=new Random();
                if (spouse.isHungry&&random.nextInt(100)<99) {
                    spoon.setOwener(spouse);
                    System.out.println(name + ": 亲爱的" + spouse.name + "你先吃吧");
                    continue;
                }
                spoon.use();
                isHungry=false;
                System.out.println(name+"已经吃完啦");
                spoon.setOwener(spouse);
            }
        }
    }
}

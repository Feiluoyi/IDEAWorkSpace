package _14DeadLock;

public class _06TransferMoney implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        _06TransferMoney r1=new _06TransferMoney();
        _06TransferMoney r2=new _06TransferMoney();
        r1.flag=1;
        r2.flag=0;
        Thread thread1=new Thread(r1);
        Thread thread2=new Thread(r2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("a的余额"+a.balance);
        System.out.println("b的余额"+b.balance);
    }
    static Account a=new Account(500);
    static Account b=new Account(500);
    static Object lock=new Object();
    int flag=1;
    @Override
    public void run() {
        if(flag==1){
            transfer(a,b,200);
        }
        if(flag==0)
        {
            transfer(b,a,200);
        }
    }
    public static void transfer(Account from,Account to,int amount) {
        class Helper{
            public void transfer(){
                if(from.balance<amount){
                    System.out.println("余额不足");
                    //return;
                }
                else {
                    from.balance-=amount;
                    to.balance+=amount;
                    System.out.println("转账成功"+amount+"元");
                }
            }
        }
        int fromHashCode=System.identityHashCode(from);  //利用哈希值进进行比较,实现换序锁
        int toHashCode=System.identityHashCode(to);
        if(fromHashCode<toHashCode) {
            synchronized (from) {
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        }
        else if(toHashCode<fromHashCode){
            synchronized (to) {
                synchronized (from) {
                    new Helper().transfer();
                }
            }
        }
        else{                                                //相等的情况就让两个线程去争第三方锁
            synchronized (lock) {
                synchronized (to) {
                    synchronized (from) {
                        new Helper().transfer();
                    }
            }
        }
    }

}
    static class Account
    {
        int balance;
        public Account(int balance)
        {
            this.balance=balance;
        }
    }
}

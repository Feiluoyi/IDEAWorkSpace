package _14DeadLock;
/**
 * 多人转账出现死锁的情况
 * 使用的是上一节的静态类和静态方法transfer
 */

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Random;

public class _07MultiTransfer {
    private static final int NUM_ACCOUNTS =500;
    private static final int NUM_MONEY =1000 ;
    private static final int NUM_ITERATIONS = 1000;
    private static final int NUM_THREAD =20 ;

    public static void main(String[] args) throws InterruptedException {
        Random random=new Random();
        _06TransferMoney.Account[] accounts=new _06TransferMoney.Account[NUM_ACCOUNTS];
        for (int i = 0; i <accounts.length ; i++) {                      //随机初始化账户的余额
             accounts[i]=new _06TransferMoney.Account(NUM_MONEY);
        }
        class TransferThread extends Thread{
            @Override
            public void run() {                                          //每个线程转账NUM_ITERATIONS次
                for (int i = 0; i <NUM_ITERATIONS ; i++) {
                    int fromAcount=random.nextInt(NUM_ACCOUNTS);         //随机生成的from,to,amount参数
                    int toAcount=random.nextInt(NUM_ACCOUNTS);
                    int amount=random.nextInt(NUM_MONEY);
                    _06TransferMoney.transfer(accounts[fromAcount],accounts[toAcount],amount);
                }
                System.out.println("运行结束");
            }
        }
        for (int i = 0; i < NUM_THREAD; i++) {          //产生1000个线程
            new TransferThread().start();
        }
    }
}

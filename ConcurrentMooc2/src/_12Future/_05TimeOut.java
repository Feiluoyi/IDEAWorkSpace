package _12Future;

import java.util.concurrent.*;

/**
 * 演示get的超时方法,并调用cancel方法取消
 * cancel方法传入参数true/false参数的不同意义
 */
public class _05TimeOut {
    private static final Ad DEFAULTAd = new Ad("无网络的默认广告");
    private static final ExecutorService executors = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        _05TimeOut time=new _05TimeOut();
        time.printAd();
    }
    static class FetchTask implements Callable<Ad> {
        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("sleep期间被中断");
                return new Ad("被中断时的广告");
            }
            return new Ad("旅游订票哪家强,某程");
        }
    }

    public void printAd() {
        Future<Ad> f = executors.submit(new FetchTask());
        Ad ad;
        try {
            ad = f.get(2, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            ad=new Ad("异常,显示默认广告");
            System.out.println("");
        } catch (InterruptedException e) {
           // e.printStackTrace();
            ad=new Ad("中断,显示默认广告");
        } catch (TimeoutException e) {
            ad=new Ad("超时,显示默认广告");
            System.out.println("超时,未获取到广告");
            boolean cancel = f.cancel(true);
            System.out.println("Cancel :"+cancel);
            //e.printStackTrace();
        }
        executors.shutdown();
        System.out.println(ad);
    }
    static class Ad {
        private String name;

        public Ad(String name) {
                this.name = name;
        }

        @Override
        public String toString() {
                return "Ad{" +
                        "name='" + name + '\'' +
                        '}';
        }
    }

}


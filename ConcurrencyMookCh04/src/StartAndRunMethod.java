public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable=()->{
            System.out.println(Thread.currentThread().getName());
        };
        runnable.run();    //执行结果main
        new Thread(runnable).start();
    }
}

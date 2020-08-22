package _04ThreadLocal;

public class _16ThreadLocalNP {
    ThreadLocal<Long> threadLocal=new ThreadLocal<>();
    public void set(Long a){
        threadLocal.set(a);
    }
    public Long get(){
        return threadLocal.get();
    }

    public static void main(String[] args) {
        _16ThreadLocalNP threadLocalNP=new _16ThreadLocalNP();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(threadLocalNP.get());
                threadLocalNP.set(1212L);
                System.out.println(threadLocalNP.get());
            }
        }).start();
    }
}

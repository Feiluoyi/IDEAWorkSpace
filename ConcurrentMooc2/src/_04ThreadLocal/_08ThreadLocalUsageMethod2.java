package _04ThreadLocal;

/**
 * 演示ThreadLocal用法2,避免传递参数的麻烦
 */
public class _08ThreadLocalUsageMethod2 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Service1().process1();
            }
        }).start();
    }
}
class User{
    String name;
    public User(String name) {
        this.name = name;
    }
}
class UserContextHolder{
    public  static ThreadLocal<User> holder=new ThreadLocal<>();
}
class Service1{
    public void process1(){
        User user=new User("你看我的头像牛掰吗");
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}
class Service2{
    public void process(){
        User user=UserContextHolder.holder.get();
        System.out.println("Service2 获得信息:"+user.name);
        new Service3().process();
    }
}
class Service3{
    public void process(){
        User user=UserContextHolder.holder.get();
        System.out.println("Service3 获得信息:"+user.name);
    }
}
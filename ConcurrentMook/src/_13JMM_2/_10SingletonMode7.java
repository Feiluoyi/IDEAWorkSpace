package _13JMM_2;

/**
 * 静态内部类,推荐使用
 */
public class _10SingletonMode7 {
    private _10SingletonMode7(){}
    private static class SingletonInstance{
        private static final SingletonInstance INSTANCE=new SingletonInstance();
    }
    public static SingletonInstance _10SingletonMode7()
    {
        return SingletonInstance.INSTANCE;
    }
}

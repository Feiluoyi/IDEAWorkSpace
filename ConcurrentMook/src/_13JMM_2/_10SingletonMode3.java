package _13JMM_2;

/**
 * 懒汉式,线程不安全,不可用
 */
public class _10SingletonMode3 {
    private static _10SingletonMode3 INSTANCE;
    private _10SingletonMode3(){};
    public static _10SingletonMode3 getINSTANCE()
    {
        if(INSTANCE==null) {       //如果INSTANCE是空,就创建新实例,但多线程竞争就可能出错
            INSTANCE = new _10SingletonMode3();
        }
        return INSTANCE;
    }
}

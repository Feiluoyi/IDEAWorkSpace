package _13JMM_2;

/**
 * 懒汉式,线程安全,但是不推荐
 */
public class _10SingletonMode4 {
    private static _10SingletonMode4 INSTANCE;
    private _10SingletonMode4(){};
    public synchronized static _10SingletonMode4 getINSTANCE()
    {
        if(INSTANCE==null) {       //如果INSTANCE是空,就创建新实例,但多线程竞争就可能出错
            INSTANCE = new _10SingletonMode4();
        }
        return INSTANCE;
    }
}

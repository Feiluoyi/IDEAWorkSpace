package _13JMM_2;

/**
 * 双重检查,线程安全,推荐面试使用
 */
public class _10SingletonMode6 {
    private volatile static _10SingletonMode6 INSTANCE;
    private _10SingletonMode6(){};
    public  static _10SingletonMode6 getINSTANCE()
    {
        if(INSTANCE==null) {
            synchronized (_10SingletonMode6.class) {//如果INSTANCE是空,就创建新实例,但多线程竞争就可能出错
                if (INSTANCE == null) {        //在锁的里面再进行检查
                    INSTANCE = new _10SingletonMode6();
                }
            }
        }
        return INSTANCE;
    }
}

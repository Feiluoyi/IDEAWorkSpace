package _13JMM_2;

/**
 * 饿汉式,静态常量式
 */
public class _09SingletonMode1 {
    private static final _09SingletonMode1 INSTANCE=new _09SingletonMode1();
    private _09SingletonMode1(){};//构造函数次数省略内容
    public static _09SingletonMode1 getInstance()
    {
        return INSTANCE;
    }
}

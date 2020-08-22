package _13JMM_2;

/**
 * 饿汉式,静态代码块,和1好像没什么本质区别
 */
public class _09SingletonMode2 {
    private static final _09SingletonMode2 INSTANCE;
    static {
        INSTANCE=new _09SingletonMode2();
        //其他的代码
    }
    private _09SingletonMode2(){};//构造函数次数省略内容
    public static _09SingletonMode2 getInstance()
    {
        return INSTANCE;
    }
}

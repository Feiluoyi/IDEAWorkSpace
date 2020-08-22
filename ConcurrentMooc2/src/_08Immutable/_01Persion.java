package _08Immutable;

import java.time.Period;

public class _01Persion {
    final int age=18;
    final String name="alice";
}
class TestFinal{
    public static void main(String[] args) {
        _01Persion persion=new _01Persion();
        //persion 的变量不能被修改
        //persion.age=19;
    }
}

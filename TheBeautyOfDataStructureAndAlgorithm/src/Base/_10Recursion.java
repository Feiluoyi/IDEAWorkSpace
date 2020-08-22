package Base;

import java.util.ArrayList;

/**
 * 用递归方法计算上台阶的方法数量
 * 每次可以上一个或者两个台阶
 * 输入的值就是台阶数量
 */
public class _10Recursion {
    public static void main(String[] args) {
        System.out.println(findways(20));
    }
    public static int findways(int x)
    {
        if(x==1) return 1;
        if(x==2) return 2;
        return findways(x-1)+findways(x-2);
    }
}

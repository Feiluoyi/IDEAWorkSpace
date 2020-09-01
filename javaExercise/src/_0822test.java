import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

public class _0822test {
    HashSet<Character> set=new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
    public String reverseVowels(String s) {
        if(s==null) return null;
        char[] cha=s.toCharArray();
        int i=-1,j=cha.length;
        while(i<=j){
            while(!set.contains(cha[++i])) if(i==cha.length) break;
            while(!set.contains(cha[--j])) if(j==0) break;
            char temp=cha[i];
            cha[i]=cha[j];
            cha[j]=temp;
        }
        return new String(cha);
    }
    public static void main(String[] args) {
        int[][][][] arr=new int[2][2][2][2];
        //数组初始就是0,不赋值也没什么问题
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                for (int k = 0; k < arr[i][j].length; k++) {
                    for (int l = 0; l < arr[i][j][k].length; l++) {
                        arr[i][j][k][l]=(int)(Math.random()*100);
                    }
                }
            }
        }
        //Arrays.fill(arr,1);多维数组不可以这么用,还是需要循环
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                for (int k = 0; k < arr[i][j].length; k++) {
                    for (int l = 0; l < arr[i][j][k].length; l++) {
                        System.out.print(arr[i][j][k][l]+" ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
            System.out.println();
        }

        BigDecimal big=new BigDecimal(3.00e10);
        BigDecimal big2=new BigDecimal("3.4");
        BigDecimal big3=new BigDecimal("2.3");
        BigDecimal big4=new BigDecimal("0.3");
        double dd=3.5;
        double dd2=3.4;
        System.out.println(dd+dd2);
        System.out.println(big.add(big2));
        System.out.println(big3);
        System.out.println(big.divide(big4, 2, 0));
        System.out.println(big.toEngineeringString());
    }
}

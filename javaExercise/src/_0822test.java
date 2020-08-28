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

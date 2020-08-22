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
        _0822test te=new _0822test();
        System.out.println(te.reverseVowels("hello"));
    }
}

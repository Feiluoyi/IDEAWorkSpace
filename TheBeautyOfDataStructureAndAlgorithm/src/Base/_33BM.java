package Base;

import com.sun.glass.ui.Size;

/**
 * 尝试实现课程中的基本的BM算法
 */
public class _33BM {
    private static final int SIZE=256;

    public static void main(String[] args) {
        String str="aabaabbbaabbbbabaaab";
        String sub="abaa";
        _33BM bmMethod=new _33BM();
        System.out.println(bmMethod.bm(str, sub));
        System.out.println(str.length()+","+sub.length());
    }
    public int bm(String str,String sub){
         return bm(str.toCharArray(),str.length(),sub.toCharArray(),sub.length());
    }
    private int bm(char[] a,int n,char[] b,int m){
        int[] bc=new int[SIZE];
        generateBC(b,m,bc);
        int[] suffix=new int[m];
        boolean[] prefix= new boolean[m];
        generateGS(b,m,suffix,prefix);
        int i=0;
        while (i<=n-m){
            int j;
            for(j=m-1;j>=0;--j){
                if(a[i+j]!=b[j]) break;
            }
            //这个地方需要注意
            if(j<0) return i;
            int x=j-bc[(int)a[i+j]];
            int y=0;
            if(j<m-1){           //如果j=m-1证明只有 没有 字符完成匹配
                y=removeByGS(m,j,suffix,prefix);
            }
            i=i+Math.max(x,y);
        }
        return -1;

    }
    //生成好后缀的suffix和prefix数组
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < m; i++) {
            suffix[i]=-1;
            prefix[i]=false;
        }
        for (int i = 0; i < m; i++) {
            int j=i;
            int k=0;
            while(j>=0&&b[j]==b[m-1-k]){
                j--;
                k++;
                suffix[i]=j+1;
            }
            if(j==-1) prefix[i]=true;
        }
    }

    //构造坏字符查找列表的最简单方法,通常应该是HashMap
    private void generateBC(char[] b,int m,int[] bc){
        for (int i = 0; i < SIZE; i++) {
            bc[i]=-1;
        }
        for (int i = 1; i < m; i++) {
            int val=(int)b[i];
            bc[val]=i;
        }
    }
    //计算按照好后缀的向后移动距离
    private int removeByGS(int m,int j,int[] suffix,boolean[] prefix){
        int k=m-1-j;
        //如果找到了和后缀相同的字符串,返回移动距离
        if(suffix[k]!=-1) return j-suffix[k]+1;
        for (int i=j+2; i <=m-1 ; i++) {
            if(prefix[m-i]==true) return i;
        }
        return m;
    }
}

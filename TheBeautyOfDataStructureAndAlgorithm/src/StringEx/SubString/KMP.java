package StringEx.SubString;

public class KMP {
    public static void main(String[] args) {
        String str1="aaaabaaaaaabaaab";
        String sub="aaaaaab";
//        String str1="cafdabcaabcabddfdsfdsabdc";
//        String sub="abcabdd";
        KMP kmp=new KMP(str1,sub);
        System.out.println(kmp.findIndex());
        System.out.println(kmp.count);
    }
    private String text;
    private String sub;
    private int count=0;
    public KMP(String text,String sub)    //KMP构造函数
    {
        this.text=text;
        this.sub=sub;
        index_KMP(this.text,this.sub);
    }
    private int[] getNext(String string)   //获取next数组的方法,暂定数组下标从 0 开始
    {                                      //<大话数据结构>以c语言编写,从1开始,算了从1开始,我哭了
        int i=1,j=0;
        char[] subchar=sub.toCharArray();
        int[] next=new int[subchar.length+1];
        next[1]=0;
        while(i<subchar.length)
        {
            if(j==0||(subchar[i-1]==subchar[j-1]))
            {
                ++i; ++j;
                next[i]=j;
            }
            else j=next[j];
            count++;
        }
        return next;
    }
    private int index_KMP(String text,String sub)
    {
        int i=0;
        int j=0;
        int[] next=getNext(sub);
        while(i<text.length()&&j<sub.length())
        {
            if(j==0||text.charAt(i)==sub.charAt(j))
            {
                ++i; ++j;
            }
            else j=next[j];
        }
        if(j>=sub.length()) return i-sub.length();
        else return 0;
    }
    public int findIndex()
    {
        if(sub==null||sub.length()==0) return -1;
        else return index_KMP(text,sub);
    }
    public int getCount(){return count;}
}

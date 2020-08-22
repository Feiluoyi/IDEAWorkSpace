package StringEx.SubString;

public class KMPnextval {
        public static void main(String[] args) {
            String str1="aaaabaaaaaabaaab";
            String sub="aaaaaab";
//        String str1="cafdabcaabcabddfdsfdsabdc";
//        String sub ="abcabdd";
            KMPnextval kmp2=new KMPnextval(str1,sub);
            System.out.println(kmp2.findIndex());
            System.out.println(kmp2.count);
            kmp2.getNextVal();
        }
        private String text;
        private String sub;
        private int count=0;
        public KMPnextval(String text,String sub)    //KMP构造函数
        {
            this.text=text;
            this.sub=sub;
            index_KMP(this.text,this.sub);
        }
        //生成nextval数组
        private int[] getNext(String string)   //获取next数组的方法,暂定数组下标从 0 开始
        {                                      //<大话数据结构>以c语言编写,从1开始,算了从1开始,我哭了
            int i=1,j=0;
            char[] subchar=sub.toCharArray();
            int[] nextval=new int[subchar.length+1];
            nextval[1]=0;
            while(i<subchar.length)
            {
                if(j==0||(subchar[i-1]==subchar[j-1]))
                {
                    ++i; ++j;
                    if(subchar[i-1]!=subchar[j-1]) nextval[i]=j;
                    else nextval[i]=nextval[j];
                }
                else j=nextval[j];
                count++;
            }
            return nextval;
        }
        //找到子字符串在主串的起始位置
        private int index_KMP(String text,String sub)
        {
            /**
             * 这部分不能再按照<大话数据结构>里面的原封不动的写
             * 存在一种情况,移动后i和j其实不相等,但是因为用了获得条件,
             * 满足j==0,i和j就直接++,而跳过了当前不同的位置
             */
            int i=0;
            int j=0;
            int[] nextval=getNext(sub);
            while(i<text.length()&&j<sub.length())
            {
                if(j==0&&text.charAt(i)!=sub.charAt(j)) ++i;
                if(text.charAt(i)==sub.charAt(j)){ ++i;++j;}
                else j=nextval[j];
//                if(j==0||text.charAt(i)==sub.charAt(j))
//                {
//                    ++i; ++j;
//                }
//                else j=nextval[j];
            }
            if(j>=sub.length()) return i-sub.length();
            else return -1;
        }
        //返回下标,并增加了空串的判断条件
        public int findIndex()
        {
            if(sub==null||sub.length()==0) return -1;
            else return index_KMP(text,sub);
        }
        public int getCount(){return count;}
        public void getNextVal()
        {
            for (int i:getNext(sub)) { System.out.print(i+" "); }
            System.out.println();
        }
    }


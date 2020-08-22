package Base;

public class _12MergeSortAndQuickSort {
    public static void main(String[] args) {
        int[] a=new int[10];
        for (int i = 0; i <a.length ; i++) {
            a[i]=(int)(Math.random()*100);
        }
        for (int i:a) { System.out.print(i+" "); }
        System.out.println();
        //_00TestArrayIsSorted.isSorted(a);
        _12MergeSortAndQuickSort.quickSort(a);
        for (int i:a) { System.out.print(i+" "); }
        System.out.println();
        //测试数组是不是已经有序
        //_00TestArrayIsSorted.isSorted(a);
    }
    private static int[] Aux;
    public static void mergeSort(int[] a)   //归并排序方法的入口
    {
        if(a.length<=1)return;
        Aux=new int[a.length];
        mergeSort(a,0,a.length-1);
    }
    private static void mergeSort(int[] a,int lo,int hi)
    {
        if(lo>=hi) return;
        int mid=(hi+lo)/2;//不明白这里和(lo+hi)/2有什么区别,测试了一下并没有区别
        mergeSort(a,lo,mid);
        mergeSort(a,mid+1,hi);
        merge(a,lo,mid,hi);
    }
    private static void merge(int[] a, int lo, int mid, int hi) {
        int i=lo,j=mid+1;
        for(int k=lo;k<=hi;k++) {Aux[k]=a[k];}
        for(int k=lo;k<=hi;k++)
        {
            if(i>mid)               a[k]=Aux[j++];
            else if(j>hi)           a[k]=Aux[i++];
            else if(Aux[i]<=Aux[j]) a[k]=Aux[i++];   //注意这里的aux数组,将归并段的元素复制到aux中作为数据源
            else                    a[k]=Aux[j++];   //不要写成数组a[]
        }
    }
    public static void quickSort(int[] a)
    {
        if(a.length<=1) return;
        quickSort(a,0,a.length-1);
    }
    private static void quickSort(int[] a,int lo,int hi)
    {
        if(lo>=hi) return;
        int j=partition(a,lo,hi);
        quickSort(a,lo,j-1);
        quickSort(a,j+1,hi);
    }
    private static int partition(int[] a,int lo,int hi)
    {
        int i=lo,j=hi+1;
        int v=a[lo];
        while(true)
        {
            while(a[++i]<v) if(i==hi) break;    //对于有序的数列,设置边界条件,防止指针越界
            while(a[--j]>v) if(j==lo) break;    //同上,j终止的位置要么是第一个不大于a[lo]的位置,
            if(i>=j) break;                     //要么是左边界,那a[lo]自己和自己交换
            {
                int temp=a[i];                  //将i和j位置元素交换
                a[i]=a[j];
                a[j]=temp;
            }
        }
        int temp=a[lo];                         //交换lo和j位置的元素
        a[lo]=a[j];
        a[j]=temp;
        return j;
    }
}

package Base;

import sun.awt.image.ImageWatched;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class _13LinearSort {
    public static void main(String[] args) {
        int[] arr=new int[30];
        for (int i=0;i <arr.length;i++)
        {
            arr[i]=(int)(Math.random()*10);
        }
        for (int i: arr) { System.out.print(i+" "); }System.out.println();
        countingSort(arr);
        for (int i: arr) { System.out.print(i+" "); }System.out.println();
        LinkedList<Integer> list=new LinkedList<>();
        Collections.sort(list);
        Arrays.sort(arr);
    }
    public static void countingSort(int[] a)
    {
        if(a.length<=1) return;
        //扫描整个数组,找出最大元素
        int max=a[0];
        int[] aux=new int[a.length];
        for (int i=1;i<a.length;i++)
        {
            if(a[i]>max) max=a[i];
        }
        int[] count=new int[max+1];
        //统计各个桶中元素的数量
        for (int i = 0; i <a.length ; i++) {
            count[a[i]]++;
        }
        //进行顺序相加
        for (int i = 1; i <count.length ; i++) {
            count[i]=count[i]+count[i-1];
        }
        //从后相前遍历数组,将元素放到合适的位置上
        for (int i = a.length-1; i >=0 ; i--) {
            int index=count[a[i]]-1;
            aux[index]=a[i];
            count[a[i]]--;
        }
        for (int i = 0; i < a.length; i++) {
            a[i]=aux[i];
        }
    }
}

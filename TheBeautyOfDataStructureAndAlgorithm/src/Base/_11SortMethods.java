package Base;

import java.util.concurrent.TimeUnit;

public class _11SortMethods {
    public static void main(String[] args) {
        int[] randomInt=new int[100];
        for (int i = 0; i <randomInt.length ; i++) {
            randomInt[i]=(int)(Math.random()*100);
        }
        for (int i:randomInt) {
            System.out.print(i+" ");
        }
        System.out.println();
        Bubble(randomInt);
        for (int i:randomInt) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
    public static void Bubble(int[] a )   //冒泡排序
    {
        if(a.length<=1) return;
        int length=a.length;
        for(int i=0;i<length;i++)
        {
            boolean flag=false;
            for(int j=0;j<length-i-1;j++)
            {
                if(a[j]>a[j+1])
                {
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                    flag=true;
                }
            }
            if(flag=false) return;
        }
    }
    public static void Insertion(int[] a)   //插入排序
    {
        if(a.length<=1) return;
        int length=a.length;
        for(int i=0;i<length;i++)     //这里从1开始更好,因为0不需要进行比较
        {
            int value=a[i];
            int j=i-1;
            for (; j >=0 ; j--)
            {
                if(a[j]>value)  a[j+1]=a[j];
                else break;    //如果不大于,证明后面的也不需要再比较了
            }
            a[j+1]=value;
        }
    }
    public static void Selection(int[] a)
    {
        if(a.length<=1)return;
        for(int i=0;i<a.length;i++)
        {
            int min=a[i];
            //找到其余元素中最小的
            int index=-1;
            for(int j=i+1;j<a.length;j++)
            {
                if(a[j]<min) {
                    min = a[j];
                    index = j;
                }
            }
            //让当前位置和其余最小的元素交换
            if(index!=-1) {
                a[index] = a[i];
                a[i] = min;
            }
        }
    }
}

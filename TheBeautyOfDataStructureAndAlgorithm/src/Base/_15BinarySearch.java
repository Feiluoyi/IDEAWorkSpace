package Base;

import java.util.Scanner;

public class _15BinarySearch {
    private static final int NUM_ARRAY=10;
    private static final int NUM_RANGE=10;

    public static void main(String[] args) {
        int[] randomInt=new int[NUM_ARRAY];
        for (int i = 0; i <randomInt.length ; i++) {
            randomInt[i]=(int)(Math.random()*NUM_RANGE);
        }
        _12MergeSortAndQuickSort.quickSort(randomInt);
        for (int i : randomInt) {
            System.out.print(i+" ");
        }
        System.out.println();
        Scanner scanner=new Scanner(System.in);
        int number=scanner.nextInt();
        System.out.println(binaryFloor(randomInt,number));
    }
    public static int binarySearch(int[] a,int num)
    {
        if(a==null) return -2;
        int low=0,high=a.length-1;
        while(low<=high)
        {
            int mid=low+(high-low)/2;
            if(num<a[mid]) high=mid-1;
            else if(num>a[mid]) low=mid+1;
            else return mid;
        }
        return -1;
    }
    public static int binaryFinst(int[] a,int num){
        int low=0,high=a.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(num<a[mid]) high=mid-1;
            else if(num>a[mid]) low=mid+1;
            else{
                if(mid==0||a[mid-1]!=num) return mid;
                else high=mid-1;
            }
        }
        return -1;
    }
    public static int binaryLast(int[] a,int num)
    {
        int low=0,high=a.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(num<a[mid]) high=mid-1;
            else if(num>a[mid]) low=mid+1;
            else{
                if(mid==a.length-1||a[mid+1]!=num) return mid;
                else low=mid+1;
            }
        }
        return -1;
    }
    public static int binaryCeiling(int[] a,int num){   //查找大于等于的第一个元素
        int low=0,high=a.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(num<=a[mid]){
                if(mid==0||a[mid-1]<num) return mid;
                else high=mid-1;
            }
            else low=mid+1;
        }
        return -1;
    }
    public static int binaryFloor(int[] a,int num){    //查找小于等于指定数字的最大元素
        int low=0,high=a.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(num<a[mid]) high=mid-1;
            else{
                if(mid==a.length-1||a[mid+1]>num) return mid;
                else low=mid+1;
            }
        }
        return -1;
    }
}

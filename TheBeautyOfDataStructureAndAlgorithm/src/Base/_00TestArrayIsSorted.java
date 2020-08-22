package Base;

public class _00TestArrayIsSorted {
    public static void isSorted(int[] a)
    {
        boolean isSorted=true;
        for(int i=0;i<a.length-1;i++)
        {
            if(a[i]>a[i+1]) isSorted=false;
        }
        System.out.println(isSorted);
    }
}

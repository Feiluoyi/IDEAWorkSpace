package Base;

public class _11SortMethodsTimeTest {
    public static void main(String[] args) {
        int[] randomInt=new int[10000];
        for (int i = 0; i <randomInt.length ; i++) {
            randomInt[i]=(int)(Math.random()*100);
        }
        int[] ran1=new int[randomInt.length];
        int[] ran2=new int[randomInt.length];
        int[] ran3=new int[randomInt.length];
        for (int i = 0; i <randomInt.length ; i++) {
            ran1[i]=randomInt[i];
            ran2[i]=randomInt[i];
            ran3[i]=randomInt[i];
        }
        long startTime1=System.currentTimeMillis();
        _11SortMethods.Bubble(ran1);
        long endTime1=System.currentTimeMillis();
        System.out.println("Bubble time="+(endTime1-startTime1));

        long startTime2=System.currentTimeMillis();
        _11SortMethods.Insertion(ran2);
        long endTime2=System.currentTimeMillis();
        System.out.println("Insertion time="+(endTime2-startTime2));

        long startTime3=System.currentTimeMillis();
        _11SortMethods.Selection(ran3);
        long endTime3=System.currentTimeMillis();
        System.out.println("Selection time="+(endTime3-startTime3));
    }
}

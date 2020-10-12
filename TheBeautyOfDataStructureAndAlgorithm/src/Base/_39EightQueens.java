package Base;

/**
 * 练习8皇后的回溯算法
 */
public class _39EightQueens {
    int[] result=new int[8];
    int count=0;

    public static void main(String[] args) {
        _39EightQueens queens=new _39EightQueens();
        long start=System.currentTimeMillis();
        queens.cal8Queens(0);
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(queens.count);
    }

    public void cal8Queens(int row){
        if(row==8){
            printQueen(result);
            count++;
            return;
        }
        for (int column = 0; column < 8; column++) {
            if (isOK(row,column)) {
                result[row]=column;
                cal8Queens(row+1);
            }
        }
    }
    private boolean isOK(int row,int column){
        int left=column,mid=column,right=column;
        for (int i = row-1; i >=0 ; i--) {
            left=left-1;
            right=right+1;
            if(result[i]==mid||result[i]==left||result[i]==right) return false;
        }
        return true;
    }
    private void printQueen(int[] result){
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if(result[row]==column) {
                        System.out.print("Q ");
                }
                else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
            System.out.println();
    }
}
class Solution {
    List<List<String>> arr=new ArrayList<List<String>>();
    int rows;
    int[] result;
    public List<List<String>> solveNQueens(int n) {
        rows=n;
        result=new int[rows];
        //生成结果的方法
        queens(0);
        return arr;
    }
    private void queens(int row){
        if(row==rows){
            //将结果放入数组中
            arr.add(arrange(result));
            return;
        }
        for(int column=0;column<rows;column++){
            if(isRight(row,column)){
                result[row]=column;
                queens(row+1);
            }
        }
    }
    private boolean isRight(int row,int column){
        int left=column-1;
        int right=column+1;
        for(int i=row-1;i>=0;i--){
            if(result[i]==column||result[row]==left||result[row]==right)  return false;
            else {
                left--;
                right++;
            }
        }
        return true;
    }
}

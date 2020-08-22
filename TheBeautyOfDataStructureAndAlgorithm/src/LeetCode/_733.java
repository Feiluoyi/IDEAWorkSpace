package LeetCode;

public class _733 {
    public static void main(String[] args) {
        _733 is=new _733();
        int[][] image= {{1,1,1},{1,1,0},{1,0,1}};
        int sr=1;
        int sc=1;
        int newColor=2;
        is.floodFill(image,sr,sc,newColor);
        for (int[] ints : image) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }
    }
    //用深度优先搜索实现,因为我不喜欢广度优先搜索
    int[] dx={1,0,0,-1};
    int[] dy={0,1,-1,0};
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int currentColor=image[sr][sc];
        if(currentColor!=newColor){
            dfs(image,sr,sc,currentColor,newColor);
        }
        return image;
    }
    private void dfs(int[][] image, int x, int y,int color, int newColor){
        if(image[x][y]==color){
            image[x][y]=newColor;
            //
            for(int i=0;i<4;i++){
                int mx=x+dx[i];
                int my=y+dy[i];
                if(mx>=0&&my>=0&&mx<image.length&&my<image[0].length){
                    dfs(image,mx,my,color,newColor);
                }
            }
        }
    }
}

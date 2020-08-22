import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
         Scanner input =new Scanner(new FileInputStream(
                    "D:\\Java\\FilesPath\\mediumG.txt"));
        Graph graph = new Graph(input);
        Scanner in2=new Scanner(System.in);
        int parameter=in2.nextInt(); //新建了一个键盘输入
            System.out.print(parameter + "连通分量:");
            for (int t : graph.adj(parameter))
                System.out.print(t + " ");
            System.out.println("超出图元素范围");
        System.out.println();
        //<editor-fold> 深度优先搜索测试
//        DepthFirstSearch dfs=new DepthFirstSearch(graph,parameter);
//        int parameter2= in2.nextInt();
//        System.out.println(dfs.connected(parameter2));
//        System.out.println("节点数:"+graph.V()+" 当前节点连通数:"+dfs.count());
        //</editor-fold>
        //<editor-fold> 深度优先搜索,寻找路径(应该是不确定的路径)测试
        dfsPathSearch dps=new dfsPathSearch(graph,parameter);
        int parameter2=in2.nextInt();
        System.out.println(dps.hasPath(parameter2));
        int parameter3=in2.nextInt();
        Stack<Integer> st=dps.pathTo(parameter3);
        while(!st.isEmpty())
        {
            if(st.peek()==parameter)System.out.print(st.peek()+" to "+parameter3+": "+st.pop()+" ");
            else System.out.print(st.pop()+" ");
        }
        System.out.println();
        //</editor-fold>
        //<editor-fold> 广度优先搜索,最短路径测试
        BreathFirstSearch bfsPath=new BreathFirstSearch(graph,parameter);
        //System.out.println(bfsPath.hasPath(parameter2));
        int parameter4=in2.nextInt();
        Stack<Integer> st2=bfsPath.pathTo(parameter4);
        while(!st2.isEmpty())
        {
            if(st2.peek()==parameter)System.out.print(st2.peek()+" to "+parameter3+": "+st2.pop()+" ");
            else System.out.print(st2.pop()+" ");
        }
        //</editor-fold>
    }
}

import javafx.scene.layout.Priority;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ConnectedComponent {
    private boolean[] marked;
    private int[] id;
    private int count=0;
    public ConnectedComponent(Graph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        for (int s = 0; s < g.V(); s++) {
            if(!marked[s]) {
                dfs(g, s);
                count++;
            }
        }
    }
    private void dfs(Graph g,int v)
    {
        marked[v]=true;
        id[v]=count;
        for(int t:g.adj(v))
        {
            if(!marked[t])
               dfs(g,t);
        }
    }
    public boolean isConnected(int t,int v){return id[t]==id[v];}
    public int count(){return count;}
    public int id(int v) {return id[v];}


    public static void main(String[] args) throws FileNotFoundException {
        Scanner input =new Scanner(new FileInputStream(
                "D:\\Java\\FilesPath\\tinyG.txt"));
        Graph graph = new Graph(input);
        ConnectedComponent cc=new ConnectedComponent(graph);
        System.out.println(cc.count);
        for(int i=0;i<cc.count;i++) {
            for (int j = 0; j < graph.V(); j++)
                if (cc.id[j] == i) System.out.print(j + " ");
            System.out.println();
        }
    }
}

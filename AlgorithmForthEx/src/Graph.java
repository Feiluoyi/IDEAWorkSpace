import java.util.LinkedList;
import java.util.Scanner;
import java.util.Scanner;
public class Graph {
    private final int V;
    private int E;
    private LinkedList<Integer>[] adj;
    public Graph(int v)
    {
        V = v;
        E = 0;
        this.adj =(LinkedList<Integer>[]) new LinkedList[V];
        for (int i = 0; i <V ; i++)
        {
            adj[i]=new LinkedList<Integer>();
        }
    }
    public Graph(Scanner in)    //将文件流传入构造函数
    {
        this(in.nextInt());
        E=in.nextInt();
        for (int i = 0; i <E ; i++) {
            int v=in.nextInt();
            int w=in.nextInt();
            addEdge(v,w);
        }
    }
    public int V(){return V;}
    public int E() {return E;}
    public void addEdge(int v,int t)
    {
        adj[v].add(t);
        adj[t].add(v);
        //E++;
    }
    public Iterable<Integer> adj(int v)
    {
        return adj[v];
    }
}

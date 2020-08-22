public class DepthFirstSearch {
    private boolean[] marked;
    private int count;
    DepthFirstSearch(Graph graph,int s)
    {
        marked=new boolean[graph.V()];
        dfs(graph,s);
    }

    private void dfs(Graph graph, int v)
    {
        marked[v] = true;
        count++;
        for (int t : graph.adj(v))
        if(!marked[t])  { dfs(graph, t); }
    }
    public boolean connected(int v)
    {return marked[v];}
    public int count(){return count;}
}

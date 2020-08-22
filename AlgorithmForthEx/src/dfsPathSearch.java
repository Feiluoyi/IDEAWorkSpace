import java.util.Stack;

public class dfsPathSearch {
    private final int s;
    private int[] pathTo;
    private boolean[] remark;
    dfsPathSearch(Graph g, int s)
    {
        pathTo=new int[g.V()];
        remark=new boolean[g.V()];
        this.s=s;
        dfs(g,s);
    }
    private void dfs(Graph g, int w) {
        remark[w]=true;
        for(int t:g.adj(w))
        {
            if(!remark[t])
            {
                pathTo[t]=w;
                dfs(g,t);
            }
        }
    }
    public boolean hasPath(int t){return remark[t];}
    public Stack<Integer> pathTo(int t)
    {
        if(!remark[t]) return null;
        Stack<Integer> st=new Stack<>();
        for(int v=t;v!=s;v=pathTo[v])
        {
            st.push(v);
        }
        st.push(s);
        return st;
    }

}

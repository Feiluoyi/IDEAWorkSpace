import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class BreathFirstSearch {
    private boolean[]  remark;
    private int[] pathTo;
    private final int s;
    public BreathFirstSearch(Graph g, int s)
    {
        remark=new boolean[g.V()];
        pathTo=new int[g.V()];
        this.s=s;
        bfs(g,s);
    }
    private void bfs(Graph g,int s)
    {
        remark[s]=true;
        LinkedBlockingQueue<Integer> queue=new LinkedBlockingQueue<>();
        queue.add(s);      //开始写的是s
        while (!queue.isEmpty())
        {
            int t=queue.remove();    //remove 方法应该是取出元素吧
            for(int w:g.adj(t))
                if(!remark[w])
                {
                    remark[w]=true;
                    pathTo[w]=t;
                    queue.add(w);
                }
        }
    }
    public boolean hasPath(int v){return remark[v];}
    public Stack<Integer> pathTo(int v)
    {
        Stack<Integer> stack=new Stack<>();
        for(int t=v;t!=s;t=pathTo[t])
            stack.push(t);
        stack.push(s);
        return stack;
    }

}

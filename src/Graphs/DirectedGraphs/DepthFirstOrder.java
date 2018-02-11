package Graphs.DirectedGraphs;

import Fundamentals.Bags_Queues_Stacks.Queue;
import Fundamentals.Bags_Queues_Stacks.Stack;
import Graphs.UndirectedGraphs.GraphAlgorithmBase;
import Graphs.UndirectedGraphs.GraphBase;

public class DepthFirstOrder<Vertex> extends GraphAlgorithmBase<Vertex>
{
    private Queue<Vertex> preOrder; // 即DFS的调用顺序
    private Queue<Vertex> postOrder;// 即DFS完成的顺序
    private Stack<Vertex> reversePost;// 即DFS完成的反序，即拓扑顺序
    private GraphBase<Vertex> graph;

    public DepthFirstOrder(GraphBase<Vertex> G)
    {
        super(G);
        this.graph = G;
        preOrder = new Queue<>();
        postOrder = new Queue<>();
        reversePost = new Stack<>();

        for (Vertex v : graph.vertices())
            if (!isVisited(v))
                dfs(v);
    }

    private void dfs(Vertex v)
    {
        preOrder.enqueue(v);
        visited.put(v, true);

        visitAdjacentVerticesOf(v);

        postOrder.enqueue(v);
        reversePost.push(v);
    }

    private void visitAdjacentVerticesOf(Vertex v)
    {
        for (Vertex w : graph.adjacentVerticesOf(v))
            if (!isVisited(w))
                dfs(w);
    }

    public Iterable<Vertex> preOrder()
    {
        return preOrder;
    }

    public Iterable<Vertex> postOrder()
    {
        return postOrder;
    }

    public Iterable<Vertex> reversePost()
    {
        return reversePost;
    }
}

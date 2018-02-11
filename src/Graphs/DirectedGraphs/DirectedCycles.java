package Graphs.DirectedGraphs;

import Fundamentals.Bags_Queues_Stacks.Stack;
import Graphs.UndirectedGraphs.GraphBase;
import Searching.HashTables.SeparateChainingHashST;
import Graphs.UndirectedGraphs.GraphAlgorithmBase;

public class DirectedCycles<Vertex> extends GraphAlgorithmBase<Vertex>
{
    private SeparateChainingHashST<Vertex, Boolean> onStack;
    private SeparateChainingHashST<Vertex, Vertex> edgeTo;
    private Stack<Vertex> cycle = null;

    public DirectedCycles(GraphBase<Vertex> graph)
    {
        super(graph);
        onStack = new SeparateChainingHashST<>();
        edgeTo  = new SeparateChainingHashST<>();
        for (Vertex v : graph.vertices())
            if (!isVisited(v)) dfs(graph, v);
    }

    /**
     * Found one way from a vertex to {@code v} every time you call this function
     * @param v end vertex of the path
     */
    private void dfs(GraphBase<Vertex> graph, Vertex v)
    {
        markOnStackAndVisited(v);
        for (Vertex w : graph.adjacentVerticesOf(v))
        {
            if (hasCycle())
                return;
            else if (!isVisited(w))
            {
                edgeTo.put(w, v);
                dfs(graph, w);
            }
            else if (isOnStack(v))
                prepareCycle(w, v);
        }
        onStack.put(v, false);
    }

    private void markOnStackAndVisited(Vertex v)
    {
        onStack.put(v, true);
        visited.put(v, true);
    }

    public boolean hasCycle()
    {
        return cycle != null;
    }

    private boolean isOnStack(Vertex v)
    {
        return onStack.contains(v) && onStack.get(v);
    }

    public Iterable<Vertex> cycle()
    {
        return cycle;
    }

    public Stack<Vertex> cycleAsStack()
    {
        return cycle;
    }

    private void prepareCycle(Vertex start, Vertex end)
    {
        cycle = new Stack<>();
        for (Vertex cur = end; cur != start; cur = edgeTo.get(cur))
            cycle.push(cur);
        cycle.push(start);
        cycle.push(end);
    }
}
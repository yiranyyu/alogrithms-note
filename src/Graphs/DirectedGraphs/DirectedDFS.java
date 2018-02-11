package Graphs.DirectedGraphs;

import Graphs.UndirectedGraphs.GraphAlgorithmBase;

public class DirectedDFS<Vertex> extends GraphAlgorithmBase<Vertex>
{
    private Digraph<Vertex> graph;
    public DirectedDFS(Digraph<Vertex> graph, Vertex start)
    {
        super(graph);
        this.graph = graph;
        dfs(start);
    }

    public DirectedDFS(Digraph<Vertex> graph, Iterable<Vertex> vertices)
    {
        super(graph);
        if (vertices == null)
            throw new NullPointerException("vertices can't be null");

        this.graph = graph;
        for (Vertex v : vertices)
            if (!isVisited(v))
                dfs(v);
    }

    private void dfs(Vertex v)
    {
        visited.put(v, true);
        for (Vertex w : graph.adjacentVerticesOf(v))
            if (!isVisited(w))
                dfs(w);
    }

    public boolean reachableTo(Vertex v)
    {
        return isVisited(v);
    }
}

package Graphs.DirectedGraphs;

import Fundamentals.Bags_Queues_Stacks.List;
import Searching.HashTables.SeparateChainingHashST;
import Graphs.UndirectedGraphs.GraphAlgorithmBase;

public class Degrees<Vertex> extends GraphAlgorithmBase<Vertex>
{
    private Digraph<Vertex> graph;
    private SeparateChainingHashST<Vertex, Integer> out;
    private SeparateChainingHashST<Vertex, Integer> in;

    public Degrees(Digraph<Vertex> graph)
    {
        super(graph);
        init(graph);
        for (Vertex v : graph.vertices())
            if (!isVisited(v))
                dfs(v);
    }

    private void init(Digraph<Vertex> digraph)
    {
        this.graph = digraph;
        out = new SeparateChainingHashST<>();
        in  = new SeparateChainingHashST<>();
        for (Vertex v : graph.vertices())
        {
            in.put(v, 0);
            out.put(v, 0);
        }
    }

    private void dfs(Vertex v)
    {
        visited.put(v, true);

        int outCount = 0;
        for (Vertex w : graph.adjacentVerticesOf(v))
        {
            outCount++;
            in.put(w, in.get(w) + 1);
            if (!isVisited(w))
                dfs(w);
        }
        out.put(v, outCount);
    }

    public int inDegree(Vertex to)
    {
        return in.get(to);
    }

    public int outDegree(Vertex from)
    {
        return out.get(from);
    }

    public Iterable<Vertex> startVertices()
    {
        List<Vertex> starts = new List<>();
        for (Vertex v : graph.vertices())
        {
            if (in.get(v).equals(0))
                starts.push(v);
        }
        return starts;
    }

    public Iterable<Vertex> sinkVertices()
    {
        List<Vertex> sinks = new List<>();
        for (Vertex v : graph.vertices())
        {
            if (out.get(v).equals(0))
                sinks.push(v);
        }
        return sinks;
    }

    public boolean isMap()
    {
        for (Vertex v : graph.vertices())
            if (!out.get(v).equals(1))
                return false;
        return true;
    }
}

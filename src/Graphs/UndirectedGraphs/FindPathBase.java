package Graphs.UndirectedGraphs;

import Fundamentals.Bags_Queues_Stacks.Stack;
import Searching.HashTables.LinearProbingHashST;

import java.util.NoSuchElementException;

public class FindPathBase<Vertex>
    extends GraphAlgorithmBase<Vertex>
{
    protected LinearProbingHashST<Vertex, Vertex> edgeTo;
    protected Vertex source;

    public FindPathBase(GraphBase<Vertex> G, Vertex start)
    {
        super(G);
        edgeTo = new LinearProbingHashST<>(G.numberOfVertices());
        this.source = start;
    }

    public boolean hasPathTo(Vertex v)
    {
        return isVisited(v);
    }

    public Iterable<Vertex> pathTo(Vertex v)
    {
        if (!hasPathTo(v)) throw new NoSuchElementException("not have path to " + v + " in " + this.getClass());

        Stack<Vertex> path = new Stack<>();

        for (Vertex prev = v; prev != source; prev = edgeTo.get(prev))
            path.push(prev);
        path.push(source);
        return path;
    }
}

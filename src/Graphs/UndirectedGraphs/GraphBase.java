package Graphs.UndirectedGraphs;

import java.util.NoSuchElementException;

abstract public class GraphBase<Vertex>
{
    protected static final String NULL_VERTEX = "Can't process null vertex in graph";
    protected static final String NOT_COLLECTED_VERTEX = "Can't process vertex not int the graph";

    protected int verticesNumber = 0;
    protected int edgesNumber = 0;

    public int numberOfVertices()
    {
        return verticesNumber;
    }

    public int numberOfEdges()
    {
        return edgesNumber;
    }

    public static double avgDegree(GraphBase G)
    {
        return 2.0 * G.numberOfEdges() / G.numberOfVertices();
    }

    abstract public Iterable<Vertex> vertices();
    abstract public Iterable<Vertex> adjacentVerticesOf(Vertex v);

    abstract public boolean contains(Vertex v);

    protected void checkContains(Vertex v)
    {
        if (!contains(v))
            throw new NoSuchElementException(NOT_COLLECTED_VERTEX);
    }
}

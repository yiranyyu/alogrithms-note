package Graphs.MST;

import Searching.HashTables.LinearProbingHashST;
import Graphs.UndirectedGraphs.Bag;
import Graphs.UndirectedGraphs.GraphBase;

public class EdgeWeightedGraph<Vertex, Weight extends Comparable<Weight>>
    extends GraphBase<Vertex>
{
    protected LinearProbingHashST<Vertex, Bag<Edge<Vertex, Weight>>> map;

    public EdgeWeightedGraph()
    {
        map = new LinearProbingHashST<>();
    }

    public Iterable<Edge<Vertex, Weight>> adjacentEdgesOf(Vertex v)
    {
        return map.get(v);
    }

    public Iterable<Edge<Vertex, Weight>> edges()
    {
        Bag<Edge<Vertex, Weight>> edgeList = new Bag<>();
        for (Vertex v : map.keys())
            for (Edge<Vertex, Weight> edge : adjacentEdgesOf(v))
                edgeList.add(edge);
        return edgeList;
    }

    @Override
    public Iterable<Vertex> vertices()
    {
        return map.keys();
    }

    @Override
    public Iterable<Vertex> adjacentVerticesOf(Vertex v)
    {
        return map.keys();
    }

    /**
     * add two reference to same edge object
     */
    public void addEdge(Edge<Vertex, Weight> e)
    {
        if (contains(e)) return;

        Vertex v = e.either();
        Vertex w = e.other(v);
        if (!contains(v))
        {
            map.put(v, new Bag<>());
            ++verticesNumber;
        }
        if (!contains(w))
        {
            map.put(w, new Bag<>());
            ++verticesNumber;
        }

        map.get(v).add(e);
        map.get(w).add(e);
        edgesNumber++;
    }

    public boolean contains(Vertex v)
    {
        return map.contains(v);
    }

    public boolean contains(Edge<Vertex, Weight> e)
    {
        Vertex v = e.either();
        return contains(v) && map.get(v).contains(e);
    }

    public Vertex peek()
    {
        return map.peek();
    }
}

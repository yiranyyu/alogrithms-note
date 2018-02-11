package Graphs.UndirectedGraphs;

import Searching.HashTables.LinearProbingHashST;

import static Stuff.myAssert.assertNotNull;

abstract public class GraphBasedOnVertex<Vertex>
    extends GraphBase<Vertex>
{
    protected LinearProbingHashST<Vertex, Bag<Vertex>> map;

    protected GraphBasedOnVertex()
    {
        map = new LinearProbingHashST<>(verticesNumber);
    }

    /**
     * @return true if {@code v} is in graph, otherwise false
     */
    public boolean contains(Vertex v)
    {
        assert v != null : "searchIn null in Graph";
        return map.contains(v);
    }

    /**
     * @return a collection of iterable collection
     */
    public Iterable<Vertex> vertices()
    {
        return map.keys();
    }

    /**
     * Add one new vertex with no edges
     */
    public void addVertex(Vertex v)
    {
        assertNotNull(v, NULL_VERTEX);

        if (!map.contains(v))
        {
            map.put(v, new Bag<>());
            ++verticesNumber;
        }
    }

    public int degreeOf(Vertex v)
    {
        return map.get(v).size();
    }

    public Iterable<Vertex> adjacentVerticesOf(Vertex v)
    {
        return map.get(v);
    }

    public int maxDegree()
    {
        int max = 0;
        for (Vertex v : vertices())
            if (degreeOf(v) > max) max = degreeOf(v);
        return max;
    }

    public Vertex peekVertex()
    {
        return map.peek();
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder(verticesNumber + " vertices, " + edgesNumber + " edges\n");
        for (Vertex v : vertices())
        {
            s.append(v).append(": ");
            for (Vertex w : adjacentVerticesOf(v))
                s.append(w).append(" ");
            s.append('\n');
        }
        return s.toString();
    }
}

package Graphs.UndirectedGraphs;

import Stuff.myAssert;

public class Graph<Vertex>
        extends GraphBasedOnVertex<Vertex>
{
    public Graph(){}

    public Graph(Graph<Vertex> that)
    {
        for (Vertex v : that.vertices())
            for (Vertex vertex : that.adjacentVerticesOf(v))
                if (!hasEdge(v, vertex)) addEdge(v, vertex);
    }

    /**
     * v and w may not be null
     * @return true if the graph hasEdge a edge between {@code v} and {@code w}, otherwise false.
     */
    public boolean hasEdge(Vertex v, Vertex w)
    {
        myAssert.assertNotNull(v, NULL_VERTEX);
        myAssert.assertNotNull(w, NULL_VERTEX);
        if (!contains(v) || !contains(w)) return false;

        myAssert.assertTrue(map.get(v).contains(w) == map.get(w).contains(w), "graph storage error");

        return map.get(v).contains(w);
    }

    /**
     * Add a new edge to the graph if the edge not existed, otherwise do nothing.
     * Edge will be added only once if {@code v} and {@code w} are same
     */
    public void addEdge(Vertex v, Vertex w)
    {
        if (hasEdge(v, w)) return;
        if (!map.contains(v)) { map.put(v, new Bag<>()); verticesNumber++; }
        if (!map.contains(w)) { map.put(w, new Bag<>()); verticesNumber++; }

        map.get(v).add(w);
        map.get(w).add(v);
        edgesNumber++;
    }

    /**
     * Delete all edges between {@code v} and {@code w}, v and w may be same or not
     */
    public void deleteAllEdgesBetween(Vertex v, Vertex w)
    {
        checkContains(v);
        checkContains(w);

        while (hasEdge(v, w))
        {
            map.get(v).delete(w);
            map.get(w).delete(v);
            --edgesNumber;
        }
    }

    public void deleteVertex(Vertex v)
    {
        checkContains(v);

        for (Vertex w : adjacentVerticesOf(v))
        {
            map.get(w).delete(v);
            --edgesNumber;
        }
        map.delete(v);
        --verticesNumber;
    }

    public int numberOfSelfLoops()
    {
        int count = 0;
        for (Vertex v : vertices())
            for (Vertex w : adjacentVerticesOf(v))
                if (v == w) ++count;
        return count / 2;   // every loop was counted twice
    }
}

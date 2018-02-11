package Graphs.DirectedGraphs;

import Graphs.UndirectedGraphs.Bag;
import Graphs.UndirectedGraphs.GraphBasedOnVertex;
import static Stuff.myAssert.*;
/**
 * Graph with directions
 * @param <Vertex> type of vertices in graph
 */
public class Digraph<Vertex> extends GraphBasedOnVertex<Vertex>
{
    /**
     * Add one new edge from {@code v} to {@code w}
     */
    public void addEdge(Vertex v, Vertex w)
    {
        assertNotNull(v, NULL_VERTEX);
        assertNotNull(w, NULL_VERTEX);

        if (!map.contains(v))
        {
            map.put(v, new Bag<>());
            ++verticesNumber;
        }

        if (!map.contains(w))
        {
            map.put(w, new Bag<>());
            ++verticesNumber;
        }

        Bag<Vertex> vertices = map.get(v);
        if (!vertices.contains(w))
        {
            vertices.add(w);
            ++edgesNumber;
        }
    }

    public Digraph<Vertex> reverse()
    {
        Digraph<Vertex> reverseGraph = new Digraph<>();
        for (Vertex v : vertices())
            for (Vertex w : adjacentVerticesOf(v))
                reverseGraph.addEdge(w, v);
        return reverseGraph;
    }
}

package Graphs.DirectedGraphs;

import Searching.HashTables.SeparateChainingHashST;
import Graphs.UndirectedGraphs.GraphAlgorithmBase;

import java.util.NoSuchElementException;

/**
 * using Kosaraju algorithm to count StronglyConnectedComponents
 */
public class KosarajuSCC<Vertex> extends GraphAlgorithmBase<Vertex>
{
    private SeparateChainingHashST<Vertex, Integer> id;
    private int numberOfComponents;
    private Digraph<Vertex> graph;

    public KosarajuSCC(Digraph<Vertex> graph)
    {
        super(graph);
        id = new SeparateChainingHashST<>();
        DepthFirstOrder<Vertex> order = new DepthFirstOrder<>(graph.reverse());
        for (Vertex v : order.reversePost())
            if (!isVisited(v))
            {
                dfs(graph, v);
                numberOfComponents++;
            }
    }

    private void dfs(Digraph<Vertex> graph, Vertex v)
    {
        visited.put(v, true);
        id.put(v, numberOfComponents);
        for (Vertex w : graph.adjacentVerticesOf(v))
            if (!isVisited(w))
                dfs(graph, w);
    }

    public boolean stronglyConnected(Vertex v, Vertex w)
    {
        return id.contains(v) && id.contains(w) && id.get(v).equals(id.get(w));
    }

    public int id(Vertex v)
    {
        if (id.contains(v))
        {
            return id.get(v);
        }
        throw new NoSuchElementException("Vertex " + v + " is not in the graph");
    }

    public int count()
    {
        return numberOfComponents;
    }
}

package Graphs.DirectedGraphs;

import Searching.HashTables.LinearProbingHashST;

public class TransitiveClosure<Vertex>
{
    LinearProbingHashST<Vertex, DirectedDFS<Vertex>> map;

    public TransitiveClosure(Digraph<Vertex> graph)
    {
        map = new LinearProbingHashST<>();

        for (Vertex v : graph.vertices())
            map.put(v, new DirectedDFS<Vertex>(graph, v));
    }

    public boolean reachable(Vertex v, Vertex w)
    {
        return map.get(v).reachableTo(w);
    }
}

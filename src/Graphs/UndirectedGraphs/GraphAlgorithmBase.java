package Graphs.UndirectedGraphs;

import Searching.HashTables.LinearProbingHashST;

abstract public class GraphAlgorithmBase<Vertex>
{
    protected LinearProbingHashST<Vertex, Boolean> visited;

    protected GraphAlgorithmBase(GraphBase<Vertex> G)
    {
        visited = new LinearProbingHashST<>(G.numberOfVertices());
    }

    protected boolean isVisited(Vertex v)
    {
        return visited.contains(v);
    }
}

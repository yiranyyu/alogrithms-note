package Graphs.UndirectedGraphs;

import Searching.HashTables.LinearProbingHashST;

public class TwoColor<Vertex>
    extends GraphAlgorithmBase<Vertex>
{
    private LinearProbingHashST<Vertex, Boolean> color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph<Vertex> G)
    {
        super(G);
        color = new LinearProbingHashST<>(G.numberOfVertices());
        if (G.numberOfVertices() > 0)
            color.put(G.peekVertex(), true);

        for (Vertex v : G.vertices())
            if (!isVisited(v))
                dfs(G, v);
    }

    private void dfs(Graph<Vertex> G, Vertex v)
    {
        visited.put(v, true);
        for (Vertex w : G.adjacentVerticesOf(v))
        {
            if (!isVisited(w))
            {
                color.put(w, !color.get(v));
                dfs(G, w);
            }
            else if (color.get(w) == color.get(v))
            {
                isTwoColorable = false;
                return;
            }
        }
    }

    public boolean isBipartite()
    {
        return isTwoColorable;
    }
}

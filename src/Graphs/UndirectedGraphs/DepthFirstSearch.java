package Graphs.UndirectedGraphs;

import Sortings.ElementarySorts.Quick;
import Stuff.Point;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

public class DepthFirstSearch<Vertex>
    extends GraphAlgorithmBase<Vertex>
{
    private int numberOfVertexInComponent;

    /**
     * Start dfs from {@code start}
     * @param G Graph to searchIn
     * @param start start vertex of graph
     */
    public DepthFirstSearch(Graph<Vertex> G, Vertex start)
    {
        super(G);
        numberOfVertexInComponent = 0;
        dfs(G, start);
    }

    private void dfs(Graph<Vertex> G, Vertex v)
    {
        visited.put(v, true);
        numberOfVertexInComponent++;

        for (Vertex w : G.adjacentVerticesOf(v))
            if (!isVisited(w)) dfs(G, w);
    }

    public int count()
    {
        return this.numberOfVertexInComponent;
    }
}

package Graphs.ShortestPaths;
// Created by yirany on 2018/1/30

import java.util.NoSuchElementException;

/**
 * @see DijkstraSPBase
 */
public class DijkstraSingleSP<Vertex>
    extends DijkstraSPBase<Vertex>
{

    private Vertex end;

    public DijkstraSingleSP(EdgeWeightedDigraph<Vertex, Double> digraph, Vertex source, Vertex to)
    {
        super(digraph, source);
        end = to;

        pq.insert(indexOf(source), 0.0);// start with source vertex
        while (!pq.isEmpty())
        {
            Vertex next = nextVertexInPQ();
            relax(next);
            if (next.equals(to)) break; // found path
        }
    }

    public boolean hasPath()
    {
        return distTo.get(end) < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge<Vertex, Double>> path()
    {
        if (!hasPath()) throw new NoSuchElementException("no path to " + end);

        return buildPathTo(end);
    }
}

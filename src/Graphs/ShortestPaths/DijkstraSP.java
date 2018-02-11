package Graphs.ShortestPaths;
// Created by yirany on 2018/1/29

import java.util.NoSuchElementException;

/**
 * Find the shortest paths from one source vertex to all other vertices in the graph,
 * using Dijkstra algorithm.
 * @see DijkstraSPBase
 */
public class DijkstraSP<Vertex>
    extends DijkstraSPBase<Vertex>
{
    public DijkstraSP(EdgeWeightedDigraph<Vertex, Double> digraph, Vertex source)
    {
        super(digraph, source);

        pq.insert(indexOf(source), 0.0);// start with source vertex
        while (!pq.isEmpty())
            relax(nextVertexInPQ());
    }

    public boolean hasPathTo(Vertex v)
    {
        return distTo.get(v) < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge<Vertex, Double>> pathTo(Vertex v)
    {
        if (!hasPathTo(v)) throw new NoSuchElementException("not have path to " + v + " in " + this.getClass());

        return buildPathTo(v);
    }

}

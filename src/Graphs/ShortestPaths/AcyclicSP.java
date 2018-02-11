package Graphs.ShortestPaths;
// Created by yirany on 2018/1/30

import Graphs.DirectedGraphs.Topological;

import java.util.NoSuchElementException;

/**
 * This class can handle graphs with positive and negative weights,
 * which contains no cycle
 * @see AcyclicLP
 */
public class AcyclicSP<Vertex>
    extends SPBase<Vertex>
{
    public AcyclicSP(EdgeWeightedDigraph<Vertex, Double> digraph, Vertex source)
    {
        super(digraph, source);
        Topological<Vertex> topological = new Topological<>(digraph);

        for (Vertex v : topological.order())
            relax(v);
    }

    private void relax(Vertex v)
    {
        for (DirectedEdge<Vertex, Double> e : graph.adjacentEdgesOf(v))
        {
            Vertex w = e.to();
            if (distTo.get(w) > distTo.get(v) + e.weight())
            {
                distTo.put(w, distTo.get(v) + e.weight());
                edgeTo.put(w, e);
            }
        }
    }

    public boolean hasPathTo(Vertex v)
    {
        return distTo(v) < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge<Vertex, Double>> pathTo(Vertex v)
    {
        if (!hasPathTo(v)) throw new NoSuchElementException("no such path in " + this.getClass());
        return buildPathTo(v);
    }
}

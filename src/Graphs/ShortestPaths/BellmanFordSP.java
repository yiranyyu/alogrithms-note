package Graphs.ShortestPaths;
// Created by yirany on 2018/1/30

import Fundamentals.Bags_Queues_Stacks.Queue;
import Searching.HashTables.SeparateChainingHashST;

import java.util.NoSuchElementException;

/**
 * For learning, you'd better use adopter idiom to use this class
 */
public class BellmanFordSP<Vertex>
    extends SPBase<Vertex>
{
    private SeparateChainingHashST<Vertex, Boolean> inQueue;
    private Queue<Vertex> toBeRelax;
    private int costOfRelaxes;
    private Iterable<DirectedEdge<Vertex, Double>> cycle;

    public BellmanFordSP(EdgeWeightedDigraph<Vertex, Double> digraph, Vertex source)
    {
        super(digraph, source);
        init(source);

        while (!toBeRelax.isEmpty() && !hasNegativeCycle())
        {
            Vertex v = toBeRelax.dequeue();
            inQueue.put(v, false);
            relax(v);
        }
    }

    private void init(Vertex source)
    {
        inQueue = new SeparateChainingHashST<>(graph.numberOfVertices());
        toBeRelax = new Queue<>();
        toBeRelax.enqueue(source);
        inQueue.put(source, true);
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

    private void relax(Vertex v)
    {
        for (DirectedEdge<Vertex, Double> e : graph.adjacentEdgesOf(v))
        {
            Vertex w = e.to();
            double newWeight = distTo.get(v) + e.weight();
            if (distTo.get(w) > newWeight)
            {
                distTo.put(w, newWeight); // decrease dist
                edgeTo.put(w, e);
                if (!inQueue.get(w))
                {
                    toBeRelax.enqueue(w);
                    inQueue.put(w, true);
                }
            }
            if (costOfRelaxes++ % graph.numberOfVertices() == 0)
                findNegativeCycle();
        }
    }

    public boolean hasNegativeCycle()
    {
        return cycle != null;
    }

    public Iterable<DirectedEdge<Vertex, Double>> negativeCycle()
    {
        if (!hasNegativeCycle()) throw new NoSuchElementException("no negative cycle in " + this.getClass());
        return cycle;
    }

    private void findNegativeCycle()
    {
        int length = edgeTo.size();
        EdgeWeightedDigraph<Vertex, Double> spt;// shortest spanning tree
        spt = new EdgeWeightedDigraph<>();
        for (Vertex v : graph.vertices())
            if (edgeTo.get(v) != null)
                spt.addEdge(edgeTo.get(v));

        EdgeWeightedCycleFinder<Vertex, Double> finder = new EdgeWeightedCycleFinder<>(spt);
        if (finder.hasCycle())
            cycle = finder.getCycle();
    }
}

package Graphs.ShortestPaths;
// Created by yirany on 2018/1/30

import Fundamentals.Bags_Queues_Stacks.Stack;
import Searching.HashTables.LinearProbingHashST;


abstract class SPBase<Vertex>
{
    LinearProbingHashST<Vertex, DirectedEdge<Vertex, Double>> edgeTo;
    LinearProbingHashST<Vertex, Double> distTo;
    EdgeWeightedDigraph<Vertex, Double> graph;

    SPBase(EdgeWeightedDigraph<Vertex, Double> digraph, Vertex source)
    {
        int length = digraph.numberOfVertices();
        edgeTo = new LinearProbingHashST<>(length);
        distTo = new LinearProbingHashST<>(length);
        initDist(digraph, source);
    }

    private void initDist(EdgeWeightedDigraph<Vertex, Double> digraph, Vertex source)
    {
        graph  = digraph;

        for (Vertex v : graph.vertices())
            distTo.put(v, Double.POSITIVE_INFINITY);
        distTo.put(source, 0.0);
    }

    public double distTo(Vertex v)
    {
        return distTo.get(v);
    }

    Iterable<DirectedEdge<Vertex, Double>> buildPathTo(Vertex v)
    {
        Stack<DirectedEdge<Vertex, Double>> path = new Stack<>();
        for (DirectedEdge<Vertex, Double> e = edgeTo.get(v); e != null; e = edgeTo.get(e.from()))
            path.push(e); // e == null means reach the start vertex, so end loop and return path
        return path;
    }
}

package Graphs.ShortestPaths;
// Created by yirany on 2018/1/30

import Searching.HashTables.LinearProbingHashST;

public class DijkstraAllPairsSP<Vertex>
{
    private LinearProbingHashST<Vertex, DijkstraSP<Vertex>> paths;
    public DijkstraAllPairsSP(EdgeWeightedDigraph<Vertex, Double> graph)
    {
        paths = new LinearProbingHashST<>(graph.numberOfVertices());
        for (Vertex source : graph.vertices())
            paths.put(source, new DijkstraSP<>(graph, source));
    }

    public Iterable<DirectedEdge<Vertex, Double>> path(Vertex from, Vertex to)
    {
        return paths.get(from).pathTo(to);
    }

    public double dist(Vertex from, Vertex to)
    {
        return paths.get(from).distTo(to);
    }
}

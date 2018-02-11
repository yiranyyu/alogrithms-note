package Graphs.ShortestPaths;
// Created by yirany on 2018/1/29

import Searching.HashTables.LinearProbingHashST;
import Graphs.UndirectedGraphs.Bag;
import Graphs.UndirectedGraphs.GraphBase;

import java.util.NoSuchElementException;

public class EdgeWeightedDigraph<Vertex, Weight extends Comparable<Weight>>
    extends GraphBase<Vertex>
{
    protected LinearProbingHashST<Vertex, Bag<DirectedEdge<Vertex, Weight>>> map;

    public EdgeWeightedDigraph()
    {
        map = new LinearProbingHashST<>();
    }

    public Iterable<DirectedEdge<Vertex, Weight>> adjacentEdgesOf(Vertex v)
    {
        return map.get(v);
    }

    @Override
    public Iterable<Vertex> adjacentVerticesOf(Vertex v)
    {
        return map.keys();
    }

    public Iterable<DirectedEdge<Vertex, Weight>> edges()
    {
        Bag<DirectedEdge<Vertex, Weight>> edgeList = new Bag<>();
        for (Vertex v : map.keys())
            for (DirectedEdge<Vertex, Weight> e : map.get(v))
                edgeList.add(e);
        return edgeList;
    }

    public Iterable<Vertex> vertices()
    {
        return map.keys();
    }

    public void addEdge(DirectedEdge<Vertex, Weight> e)
    {
        if (contains(e)) return;

        if (!contains(e.from()))
        {
            map.put(e.from(), new Bag<>());
            verticesNumber++;
        }

        map.get(e.from()).add(e);
        edgesNumber++;
    }

    public Vertex peek()
    {
        return map.peek();
    }

    public boolean contains(Vertex v)
    {
        return map.contains(v);
    }

    public boolean contains(DirectedEdge<Vertex, Weight> e)
    {
        Vertex from = e.from();
        return contains(from) && map.get(from).contains(e);
    }

    /**
     * Get weight of edge from {@code from} to {@code to}, if no parallel edges,
     * otherwise the behavior of this function is no defined;
     * @throws NoSuchElementException if the edge does not exist
     */
    public Weight weightOfNoParallelEdge(Vertex from, Vertex to)
    {
        for (DirectedEdge<Vertex, Weight> e : adjacentEdgesOf(from))
        {
            if (e.to().equals(to))
                return e.weight();
        }
        throw new NoSuchElementException("no such edge in " + this.getClass());
    }

}

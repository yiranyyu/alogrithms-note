package Graphs.ShortestPaths;
// Created by yirany on 2018/1/29

import Graphs.MST.EdgeBase;

public class DirectedEdge<Vertex, Weight extends Comparable<Weight>>
    extends EdgeBase<Vertex, Weight>
    implements Comparable<DirectedEdge<Vertex, Weight>>
{
    public DirectedEdge(Vertex from, Vertex to, Weight weight)
    {
        super(from, to, weight);
    }

    public Vertex from()
    {
        return v;
    }

    public Vertex to()
    {
        return w;
    }

    @Override
    public String toString()
    {
        return "DirectedEdge{" +
                v + " -> " + w +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(DirectedEdge<Vertex, Weight> that)
    {
        return super.compareTo(that);
    }
}

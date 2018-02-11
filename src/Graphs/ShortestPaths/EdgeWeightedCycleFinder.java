package Graphs.ShortestPaths;
// Created by yirany on 2018/1/30

import Fundamentals.Bags_Queues_Stacks.Stack;
import Graphs.UndirectedGraphs.Bag;
import Graphs.DirectedGraphs.DirectedCycles;

import java.util.NoSuchElementException;

public class EdgeWeightedCycleFinder<Vertex, Weight extends Comparable<Weight>>
{
    private Iterable<DirectedEdge<Vertex, Weight>> cycle;

    public EdgeWeightedCycleFinder(EdgeWeightedDigraph<Vertex, Weight> graph)
    {
        DirectedCycles<Vertex> finder = new DirectedCycles<>(graph);
        if (finder.hasCycle())
            buildCycle(finder, graph);
    }

    private void buildCycle(DirectedCycles<Vertex> finder, EdgeWeightedDigraph<Vertex, Weight> graph)
    {
        Stack<Vertex> vertices = finder.cycleAsStack();
        Bag<DirectedEdge<Vertex, Weight>> edges = new Bag<>();

        Vertex start = vertices.pop();
        Vertex from  = start;
        for (Vertex to : vertices)
        {
            Weight weight = graph.weightOfNoParallelEdge(from, to);
            edges.add(new DirectedEdge<>(from, to, weight));
            from = to;
        }
        Weight weight = graph.weightOfNoParallelEdge(from, start);
        edges.add(new DirectedEdge<>(from, start, weight));
        cycle = edges;
    }

    public boolean hasCycle()
    {
        return cycle != null;
    }

    public Iterable<DirectedEdge<Vertex, Weight>> getCycle()
    {
        if (!hasCycle()) throw new NoSuchElementException("no cycle in " + this.getClass());
        return cycle;
    }
}

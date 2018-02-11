package Context.Maxflow;
// Created by yirany on 2018/2/8

import Graphs.MST.Edge;
import Graphs.MST.EdgeWeightedGraph;
import Graphs.ShortestPaths.DirectedEdge;
import Graphs.ShortestPaths.EdgeWeightedDigraph;
import Graphs.UndirectedGraphs.Bag;

public class FlowNetWork extends EdgeWeightedGraph<Integer, Double>
{
    private final int source;
    private final int target;

    public FlowNetWork(int source, int target)
    {
        super();
        this.source = source;
        this.target = target;
    }

    public int source()
    {
        return source;
    }

    public int target()
    {
        return target;
    }

    public Iterable<FlowEdge> adjacentEdgesOf(int vertex)
    {
        Bag<FlowEdge> edges = new Bag<>();
        for (Edge<Integer, Double> edge : map.get(vertex))
                edges.add((FlowEdge)edge);
        return edges;
    }

    public Iterable<FlowEdge> edgesFrom(int from)
    {
        Bag<FlowEdge> edges = new Bag<>();
          for (Edge<Integer, Double> edge : map.get(from))
            if (((FlowEdge)edge).from() == from)
                edges.add((FlowEdge)edge);
        return edges;
    }

    public Iterable<FlowEdge> flowEdges()
    {
        Bag<FlowEdge> edges = new Bag<>();
        for (Integer vertex : vertices())
            for (FlowEdge edge : this.edgesFrom(vertex))
                edges.add(edge);
        return edges;
    }

    @Override
    public String toString()
    {
        return "FlowNetWork{" +
                "source=" + source +
                ", target=" + target +
                super.toString() +
                '}';
    }
}

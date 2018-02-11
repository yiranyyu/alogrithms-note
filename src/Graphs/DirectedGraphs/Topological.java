package Graphs.DirectedGraphs;

import Graphs.UndirectedGraphs.GraphBase;

public class Topological<Vertex>
{
    private Iterable<Vertex> order;
    public Topological(GraphBase<Vertex> graph)
    {
        DirectedCycles<Vertex> cycleFinder = new DirectedCycles<>(graph);
        if (!cycleFinder.hasCycle())
        {
            DepthFirstOrder<Vertex> dfs = new DepthFirstOrder<>(graph);
            order = dfs.reversePost();
        }
    }

    public Iterable<Vertex> order()
    {
        return order;
    }

    public boolean isDAG()
    {
        return order != null;
    }
}

package Context.Maxflow;
// Created by yirany on 2018/2/8

import Graphs.MST.Edge;

// Edges in the residual network
public class FlowEdge extends Edge<Integer, Double>
{
    private double flow;

    public FlowEdge(int from, int to, double capacity)
    {
        super(from, to, capacity);

        if (from == to) throw new IllegalArgumentException();
         this.flow = 0.0;
    }

    public int from()
    {
        return v;
    }

    public int to()
    {
        return w;
    }

    public double capacity()
    {
        return weight;
    }

    public double flow()
    {
        return flow;
    }

    // rest capacity of vertex vertex
    public double residualCapacity(int vertex)
    {
        if (vertex == v)
            return flow;             // 正向流量
        else if (vertex == w)
            return capacity() - flow;// 剩余流量
        else
            throw new InconsistentEdgeException();
    }

    public void addResidualFlowTo(int vertex, double delta)
    {
        if (vertex == v)
            flow -= delta;      // 增大剩余流量
        else if (vertex == w)
            flow += delta;      // 增大正向流量
        else
            throw new InconsistentEdgeException();
    }

    private static class InconsistentEdgeException extends RuntimeException
    {
        InconsistentEdgeException()
        {
            super("Inconsistent edge");
        }
    }

    public String toString()
    {
        return String.format("%d->%d %.2f %.2f", from(), to(), capacity(), flow);
    }
}

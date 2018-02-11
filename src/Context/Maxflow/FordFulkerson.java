package Context.Maxflow;
// Created by yirany on 2018/2/10

import Fundamentals.Bags_Queues_Stacks.Queue;
import Graphs.UndirectedGraphs.GraphAlgorithmBase;
import Searching.HashTables.LinearProbingHashST;

public class FordFulkerson extends GraphAlgorithmBase<Integer>
{
    private LinearProbingHashST<Integer, FlowEdge> edgeTo;
    private FlowNetWork netWork;
    private int target;
    private int source;
    private double value;

    public FordFulkerson(FlowNetWork netWork, int source, int target)
    {
        super(netWork);
        this.netWork = netWork;
        this.source = source;
        this.target = target;
        findMaxFlow();
    }

    private void findMaxFlow()
    {
        while (hasAugmentingPath())
        {
            // 利用存在的增广路径
            double bottole = Double.POSITIVE_INFINITY;
            for (int v = target; v != source; v = edgeTo(v).other(v))
                bottole = Math.min(bottole, edgeTo(v).residualCapacity(v));

            // 增大流量
            for (int v = target; v != source; v = edgeTo(v).other(v))
                edgeTo(v).addResidualFlowTo(v, bottole);

            value += bottole;
        }
    }

    public double value()
    {
        return value;
    }

    public boolean inCut(int v)
    {
        return isVisited(v);
    }

    private FlowEdge edgeTo(int vertex)
    {
        return this.edgeTo.get(vertex);
    }

    private boolean hasAugmentingPath()
    {
        Queue<Integer> queue = new Queue<>();
        visited = new LinearProbingHashST<>();
        edgeTo = new LinearProbingHashST<>();

        visited.put(source, true);
        queue.enqueue(source);
        while (!queue.isEmpty())
        {
            int v = queue.dequeue();
            for (FlowEdge e : netWork.adjacentEdgesOf(v))
            {
                int w = e.other(v);
                if (e.residualCapacity(w) > 0 && !isVisited(w))
                {
                    edgeTo.put(w, e);
                    visited.put(w, true);
                    queue.enqueue(w);
                }
            }
        }
        return isVisited(target);
    }

}

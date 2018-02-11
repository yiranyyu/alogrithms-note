package Graphs.MST;

import Fundamentals.Bags_Queues_Stacks.Queue;
import Sortings.PriorityQueues.MinPQ;
import Graphs.UndirectedGraphs.GraphAlgorithmBase;

/**
 * Get minimum spanning tree of {@code EdgeWeightedGraph}, using Prim algorithm.
 */
public class LazyPrimMST<Vertex, Weight extends Comparable<Weight>>
        extends GraphAlgorithmBase<Vertex>
{
    private Queue<Edge<Vertex, Weight>> mst;
    private MinPQ<Edge<Vertex, Weight>> pq;
    private EdgeWeightedGraph<Vertex, Weight> graph;

    public LazyPrimMST(EdgeWeightedGraph<Vertex, Weight> graph)
    {
        super(graph);
        this.graph = graph;
        pq  = new MinPQ<>();
        mst = new Queue<>();

        addVertexToMSTAndUpdatePq(graph.peek());
        while (!pq.isEmpty())
        {
            Edge<Vertex, Weight> e = pq.getMin();
            pq.delMin();

            Vertex v = e.either(), w = e.other(v);
            if(isVisited(v) && isVisited(w)) continue;

            mst.enqueue(e);
            if (!isVisited(v))
                addVertexToMSTAndUpdatePq(v);
            if (!isVisited(w))
                addVertexToMSTAndUpdatePq(w);
        }
    }

    private void addVertexToMSTAndUpdatePq(Vertex v)
    {
        visited.put(v, true);
        for (Edge<Vertex, Weight> e : graph.adjacentEdgesOf(v))
            if (!isVisited(e.other(v))) // if this edge cross mst vertices and other vertices
                pq.insert(e);
    }

    public Iterable<Edge<Vertex, Weight>> edges()
    {
        return mst;
    }
}

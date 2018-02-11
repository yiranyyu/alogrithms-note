package Graphs.MST;
// Created by yirany on 2018/1/29

import Fundamentals.Bags_Queues_Stacks.List;
import Fundamentals.UnionFind.UnionFind;
import Sortings.PriorityQueues.MinPQ;
import Searching.HashTables.LinearProbingHashST;
import Graphs.UndirectedGraphs.GraphAlgorithmBase;

/**
 * Get minimum spanning tree of {@code EdgeWeightedGraph} using Kruskal algorithm.
 */
public class KruskalMST<Vertex, Weight extends Comparable<Weight>>
    extends GraphAlgorithmBase<Vertex>
{
    private List<Edge<Vertex, Weight>> mst;
    private LinearProbingHashST<Vertex, Integer> indexOfVertices;

    private void init(EdgeWeightedGraph<Vertex, Weight> graph)
    {
        mst = new List<>();
        indexOfVertices = new LinearProbingHashST<>(graph.numberOfVertices());
        int index = 0;
        for (Vertex v : graph.vertices())
            indexOfVertices.put(v, index);
    }

    private int indexOf(Vertex v)
    {
        return indexOfVertices.get(v);
    }

    public KruskalMST(EdgeWeightedGraph<Vertex, Weight> graph)
    {
        super(graph);
        init(graph);

        MinPQ<Edge<Vertex, Weight>> pq = new MinPQ<>(graph.numberOfEdges());
        for (Edge<Vertex, Weight> e : graph.edges())
            pq.insert(e);
        UnionFind uf = new UnionFind(graph.numberOfVertices());

        while (!pq.isEmpty() && mst.size() < graph.numberOfVertices() - 1)
        {
            Edge<Vertex, Weight> e = pq.getAndDeleteMin();

            int vIndex = indexOf(e.either());
            int wIndex = indexOf(e.other());

            if (uf.connected(vIndex, wIndex))
                continue;// if both side of this edge in the mst, find next edge

            uf.union(vIndex, wIndex);
            mst.push(e);
        }
    }

    public Iterable<Edge<Vertex, Weight>> edges()
    {
        return mst;
    }
}

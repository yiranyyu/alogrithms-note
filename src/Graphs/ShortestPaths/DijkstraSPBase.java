package Graphs.ShortestPaths;
// Created by yirany on 2018/1/30

import Sortings.PriorityQueues.IndexMinPQ;
import Searching.HashTables.LinearProbingHashST;

import java.lang.reflect.Array;

/**
 * Can only handle graphs with non-negative weights. cycles in graphs
 * will affect the usability.
 */
abstract class DijkstraSPBase<Vertex> extends SPBase<Vertex>
{
    private LinearProbingHashST<Vertex, Integer> indexOfVertices;
    private Vertex[] vertices;
    IndexMinPQ<Double> pq;

    public DijkstraSPBase(EdgeWeightedDigraph<Vertex, Double> digraph, Vertex source)
    {
        super(digraph, source);
        init();
    }

    private void init()
    {
        int length = graph.numberOfVertices();
        pq = new IndexMinPQ<>(length);
        indexOfVertices = new LinearProbingHashST<>(length);
        vertices = (Vertex[]) Array.newInstance(graph.peek().getClass(), length);

        initIndices();
    }

    private void initIndices()
    {
        int index = 0;
        for (Vertex v : graph.vertices())
        {
            indexOfVertices.put(v, index);
            vertices[index++] = v;
        }
    }

    Vertex nextVertexInPQ()
    {
        return vertices[pq.getAndDeleteMin()];
    }

    int indexOf(Vertex v)
    {
        return indexOfVertices.get(v);
    }

    void relax(Vertex v)
    {
        for (DirectedEdge<Vertex, Double> e : graph.adjacentEdgesOf(v))
        {
            Vertex w = e.to();
            if (distTo.get(w) > distTo.get(v) + e.weight())
            {
                distTo.put(w, distTo.get(v) + e.weight());
                edgeTo.put(w, e);

                int index = indexOf(w);
                if (pq.contains(index))
                    pq.decreaseKey(index, distTo.get(w));
                else
                    pq.insert(index, distTo.get(w));
            }
        }
    }

}

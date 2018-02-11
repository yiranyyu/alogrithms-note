package Graphs.MST;

import Searching.HashTables.SeparateChainingHashST;
import Graphs.UndirectedGraphs.GraphAlgorithmBase;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.lang.reflect.Array;

/**
 * Get minimum spanning tree of {@code EdgeWeightedGraph}, using Prim algorithm.
 * Specialize the type of Weight to be Double, since I have no idea how to keep
 * the proper order of weights if they are generic types, even though I know how
 * to use Comparator.
 * 为了兼容使用{@code IndexMinPQ}而添加了{@code indexOfVertices}和{@code vertexOfIndices},
 * 所以效率可能相较Lazy实现无太多提高。
 * @see IndexMinPQ
 * @see LazyPrimMST use this version if you do not want the weight type to be Double
 */
public class PrimMST<Vertex>
        extends GraphAlgorithmBase<Vertex>
{
    private EdgeWeightedGraph<Vertex, Double> graph;
    private SeparateChainingHashST<Vertex, Edge<Vertex, Double>> edgeTo;
    private SeparateChainingHashST<Vertex, Double> distTo;
    private SeparateChainingHashST<Vertex, Integer> indexOfVertices;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph<Vertex, Double> graph)
    {
        super(graph);
        this.graph = graph;
        edgeTo = new SeparateChainingHashST<>();
        distTo = new SeparateChainingHashST<>();
        pq = new IndexMinPQ<>(graph.numberOfVertices());
        indexOfVertices = new SeparateChainingHashST<>();
        Vertex[] vertices = (Vertex[]) Array.newInstance(graph.peek().getClass(), graph.numberOfVertices());


        int index = 0;
        for (Vertex v : graph.vertices())
        {
            distTo.put(v, Double.POSITIVE_INFINITY);
            indexOfVertices.put(v, index);
            vertices[index] = v;
            index++;
        }

        Vertex peekVertex = graph.peek();
        pq.insert(indexOfVertices.get(peekVertex), 0.0);
        while (!pq.isEmpty())
            visit(vertices[pq.delMin()]);
    }

    private void visit(Vertex v)
    {
        visited.put(v, true);
        for (Edge<Vertex, Double> e : graph.adjacentEdgesOf(v))
        {
            Vertex w = e.other(v);

            if (isVisited(w)) continue;// both vertices of this edge is in the mst
            if (e.weight().compareTo(distTo.get(w)) < 0)
            {
                edgeTo.put(w, e);// update the shortest path

                distTo.put(w, e.weight());
                if (isInPq(v)) decreaseDist(v);
                else           AddDist(v);

            }
        }
    }

    private boolean isInPq(Vertex v)
    {
        return pq.contains(indexOfVertices.get(v));
    }

    private void decreaseDist(Vertex v)
    {
        pq.decreaseKey(indexOfVertices.get(v), distTo.get(v));
    }

    private void AddDist(Vertex v)
    {
        pq.insert(indexOfVertices.get(v), distTo.get(v));
    }

    public Iterable<Edge<Vertex, Double>> edges()
    {
        return edgeTo.values();
    }

    public double weight()
    {
        double amount = 0.0;
        for (double w : distTo.values())
            amount += w;
        return amount;
    }

}

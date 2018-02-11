package Graphs.MST;

public class Edge<Vertex, Weight extends Comparable<Weight>>
    extends EdgeBase<Vertex, Weight>
    implements Comparable<Edge<Vertex, Weight>>
{
    public Edge(Vertex v, Vertex w, Weight weight)
    {
        super(v, w, weight);
    }

    public Vertex either()
    {
        return v;
    }

    public Vertex other()
    {
        return w;
    }

    public Vertex other(Vertex vertex)
    {
        if (vertex.equals(v)) return w;
        if (vertex.equals(w)) return v;
        throw new RuntimeException("Inconsistent edge");
    }

    @Override
    public int compareTo(Edge<Vertex, Weight> that)
    {
        return super.compareTo(that);
    }
}

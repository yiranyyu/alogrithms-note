package Graphs.MST;
// Created by yirany on 2018/1/29

abstract public class EdgeBase<Vertex, Weight extends Comparable<Weight>>
{
    protected Vertex v, w;
    protected Weight weight;

    public EdgeBase(Vertex v, Vertex w, Weight weight)
    {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public Weight weight()
    {
        return weight;
    }


    @Override
    public String toString()
    {
        return "Edge{" +
                v + ", " + w +
                ", weight=" + weight +
                '}';
    }

    public int compareTo(EdgeBase<Vertex, Weight> that)
    {
        return this.weight.compareTo(that.weight);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge<?, ?> edge = (Edge<?, ?>) o;

        if (!v.equals(edge.v)) return false;
        if (!w.equals(edge.w)) return false;
        return weight.equals(edge.weight);
    }

    @Override
    public int hashCode()
    {
        int result = v.hashCode();
        result = 31 * result + w.hashCode();
        result = 31 * result + weight.hashCode();
        return result;
    }

}

package Graphs.UndirectedGraphs;

import Searching.HashTables.LinearProbingHashST;
import Stuff.TestGraph;

public class CountComponents<Vertex>
    extends GraphAlgorithmBase<Vertex>
{
    private LinearProbingHashST<Vertex, Integer> id;
    private int count;

    public CountComponents(Graph<Vertex> G)
    {
        super(G);
        count = 0;
        id = new LinearProbingHashST<>(G.numberOfVertices());
        for (Vertex v : G.vertices())
        {
            if (!isVisited(v))
            {
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(Graph<Vertex> G, Vertex v)
    {
        visited.put(v, true);
        id.put(v, count);
        for (Vertex w : G.adjacentVerticesOf(v))
            if (!isVisited(w))
                dfs(G, w);
    }

    public int id(Vertex v)
    {
        if (v == null) throw new IllegalArgumentException("Cannot find id of null pointer!");
        if (!id.contains(v)) throw new IllegalArgumentException("cannot get index of vertex which is not in graph");
        return id.get(v);
    }

    public boolean connected(Vertex v, Vertex w)
    {
        assert v != null && w != null : "call connected() with null vertex";

        if (v == null || w == null) return false;
        return id.get(w) == id.get(v);
    }

    public int count()
    {
        return this.count;
    }

    public static void main(String[] argv)
    {
        Graph<Integer> graph = new TestGraph();

        CountComponents<Integer> cc = new CountComponents<>(graph);
        Bag<Integer>[] components = (Bag<Integer>[] )new Bag[cc.count()];
        for (int i = 0; i < components.length; ++i) components[i] = new Bag<>();

        for (Integer i : graph.vertices())
            components[cc.id(i)].add(i);

        int index = 0;
        for (Bag<Integer> component : components)
        {
            System.out.print(index++ + ":");
            for (Integer ver : component)
                System.out.print(" " + ver);
            System.out.println();
        }
    }
}

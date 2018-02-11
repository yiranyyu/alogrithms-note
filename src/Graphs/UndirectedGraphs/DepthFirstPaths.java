package Graphs.UndirectedGraphs;

import Stuff.TestGraph;

public class DepthFirstPaths<Vertex>
    extends FindPathBase<Vertex>
{
    public DepthFirstPaths(Graph<Vertex> G, Vertex start)
    {
        super(G, start);
        dfs(G, start);
    }

    private void dfs(Graph<Vertex> G, Vertex v)
    {
        visited.put(v, true);
        for (Vertex w : G.adjacentVerticesOf(v))
            if (!isVisited(w))
            {
                edgeTo.put(w, v);
                dfs(G, w);
            }
    }

    public static void main(String[] argv)
    {
        Graph<Integer> graph = new TestGraph();
        DepthFirstPaths<Integer> paths = new DepthFirstPaths<>(graph, graph.peekVertex());
        for (Integer i : graph.vertices())
            System.out.println(paths.pathTo(i));
    }
}

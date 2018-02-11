package Graphs.UndirectedGraphs;

import Fundamentals.Bags_Queues_Stacks.Queue;
import Stuff.TestGraph;

public class BreadFirstPaths<Vertex>
    extends FindPathBase<Vertex>
{
    public BreadFirstPaths(Graph<Vertex> G, Vertex start)
    {
        super(G, start);
        bfs(G, start);
    }

    private void bfs(Graph<Vertex> G, Vertex source)
    {
        visited.put(source, true);
        Queue<Vertex> queue = new Queue<>();
        queue.enqueue(source);
        while (!queue.isEmpty())
        {
            Vertex cur = queue.dequeue();
            for (Vertex v : G.adjacentVerticesOf(cur))
            {
                if (!isVisited(v))
                {
                    edgeTo.put(v, cur);
                    visited.put(v, true);
                    queue.enqueue(v);
                }
            }
        }
    }

    public static void main(String[] argv)
    {
        Graph<Integer> graph = new TestGraph();
        BreadFirstPaths<Integer> bfs = new BreadFirstPaths<>(graph, 7);
        System.out.println("BFS");
        for (Integer i : graph.vertices())
            if (bfs.hasPathTo(i))
                System.out.println(i + ": " + bfs.pathTo(i));
            else
                System.out.println(i + ": ");
    }
}
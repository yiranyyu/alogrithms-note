package Stuff;

import Graphs.UndirectedGraphs.Graph;

public class TestGraph
    extends Graph<Integer>
{
    public TestGraph()
    {
        super();
        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(0, 5);
        addEdge(0, 6);
        addEdge(1, 2);
        addEdge(3, 2);
        addEdge(4, 6);
        addEdge(4, 2);
        addEdge(3, 4);
        addEdge(3, 5);

        addEdge(7, 8);

        addEdge(9, 10);
        addEdge(9, 11);
        addEdge(9, 12);
        addEdge(11, 12);


    }
}

package Graphs.DirectedGraphs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DegreesTest
{
    private Degrees<Integer> degrees;

    @Before
    public void setUp() throws Exception
    {
        Digraph<Integer> digraph = new Digraph<>();
        digraph.addEdge(0, 1);// 0 -> 1
        digraph.addEdge(0, 2);// 0 -> 2
        digraph.addEdge(1, 2);// 1 -> 2

        digraph.addVertex(3);

        degrees = new Degrees<>(digraph);
    }

    @Test
    public void inDegree() throws Exception
    {
        assertEquals(degrees.inDegree(0), 0);
        assertEquals(degrees.inDegree(1), 1);
        assertEquals(degrees.inDegree(2), 2);
        assertEquals(degrees.inDegree(3), 0);
    }

    @Test
    public void outDegree() throws Exception
    {
        assertEquals(degrees.outDegree(0), 2);
        assertEquals(degrees.outDegree(1), 1);
        assertEquals(degrees.outDegree(2), 0);
        assertEquals(degrees.outDegree(3), 0);
    }

    @Test
    public void startVertices() throws Exception
    {
        int count = 0;
        for (int vertex : degrees.startVertices())
        {
            if (degrees.inDegree(vertex) != 0)
                fail("start vertex with positive indegree");
            else
                ++count;
        }
        assertEquals(count, 2);
    }

    @Test
    public void sinkVertices() throws Exception
    {
        int count = 0;
        for (int vertex : degrees.sinkVertices())
        {
            if (degrees.outDegree(vertex) != 0)
                fail("sink vertex with positive outdegree");
            else
                ++count;
        }
        assertEquals(count, 2);
    }

    @Test
    public void isMap() throws Exception
    {
        assertFalse(degrees.isMap());

        Digraph<Integer> otherGraph = new Digraph<>();
        otherGraph.addEdge(1, 2);
        otherGraph.addEdge(2, 1);
        Degrees<Integer> other = new Degrees<>(otherGraph);
        assertTrue(other.isMap());
    }

}
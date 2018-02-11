package Graphs.UndirectedGraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

public class DegreeOfSeparation
{
    private static String start;
    private static String splitStr;
    private static Graph<String> graph;
    private static In input;
    private static String[] arguments;

    public static void main(String[] argv)
    {
        arguments = argv;
        init();
        readInput();
        if (containsStart())
            processUserRequest();
    }

    private static void init()
    {
        input = new In(arguments[0]);
        graph = new Graph<>();
        splitStr = arguments[1];
        start = arguments[0];
    }

    private static void readInput()
    {
        while (input.hasNextLine())
        {
            String[] words = input.readLine().split(splitStr);
            for (int i = 1; i < words.length; ++i)
                if (!graph.contains(words[i])) graph.addEdge(words[0], words[i]);
        }
    }

    private static boolean containsStart()
    {
        if (!graph.contains(start))
        {
            System.out.println(start  + "not in database");
            return false;
        }
        return true;
    }

    private static void processUserRequest()
    {
        BreadFirstPaths<String> bfs = new BreadFirstPaths<>(graph, start);
        while (!StdIn.isEmpty())
        {
            String sink = StdIn.readLine();
            if (graph.contains(sink))
            {
                if (bfs.hasPathTo(sink))
                {
                    for (String word : bfs.pathTo(sink))
                        System.out.println("    " + word);
                }
                else System.out.println("Not connected");
            }
            else System.out.println("Not in database.");
        }
    }
}

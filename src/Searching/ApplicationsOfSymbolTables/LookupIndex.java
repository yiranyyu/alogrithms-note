package Searching.ApplicationsOfSymbolTables;

import Searching.BalancedSearchTrees.RedBlackBST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

public class LookupIndex
{
    private static RedBlackBST<String, LinearSet<String>> searchTable;
    private static RedBlackBST<String, LinearSet<String>> reverseSearchTable;
    private static String[] arguments;
    private static In input;
    private static String splitStr;

    public static void main(String[] argv)
    {
        arguments = argv;
        init();
        readInput();
        processUserRequest();
    }

    private static void init()
    {
        searchTable = new RedBlackBST<>();
        reverseSearchTable = new RedBlackBST<>();
        input = new In(arguments[0]);
        splitStr = arguments[1];
    }

    private static void readInput()
    {
        while (input.hasNextLine())
        {
            String[] a= input.readLine().split(splitStr);
            String key = a[0];
            for (int i = 1; i < a.length; ++i)
            {
                String val = a[i];
                if (!searchTable.contains(key))         searchTable.put(key, new LinearSet<>());
                if (!reverseSearchTable.contains(val))  reverseSearchTable.put(val, new LinearSet<>());
                searchTable.get(key).add(val);
                reverseSearchTable.get(val).add(key);
            }
        }
    }

    private static void processUserRequest()
    {
        while (!StdIn.isEmpty())
        {
            String query = StdIn.readLine();
            if (searchTable.contains(query))
            {
                for (String s : searchTable.get(query))
                    System.out.println(" " + s);
            }
            if (reverseSearchTable.contains(query))
            {
                for (String s : reverseSearchTable.get(query))
                    System.out.println(" " + s);
            }
        }
    }
}

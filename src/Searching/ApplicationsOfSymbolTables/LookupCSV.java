package Searching.ApplicationsOfSymbolTables;

import Searching.BalancedSearchTrees.RedBlackBST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

public class LookupCSV
{
    public static void main(String[] argv)
    {
        In in = new In(argv[0]);
        int keyFiled = Integer.parseInt(argv[1]);
        int valField = Integer.parseInt(argv[2]);
        RedBlackBST<String, String> st = new RedBlackBST<>();
        while (in.hasNextLine())
        {
            String line = in.readLine();
            String[] token = line.split(",");
            String key = token[keyFiled];
            String val = token[valField];
            st.put(key, val);
        }
        while (!StdIn.isEmpty())
        {
            String query = StdIn.readString();
            if (st.contains(query))
            {
                System.out.println(st.get(query));
            }
        }
    }
}

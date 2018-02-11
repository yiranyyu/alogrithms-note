package Searching.ElementarySymbolTables;

import Searching.BinarySearchTrees.BinarySearchST;
import Searching.BalancedSearchTrees.BST;
import Searching.BalancedSearchTrees.RedBlackBST;
import Searching.HashTables.SeparateChainingHashST;
import Stuff.Stopwatch;
import edu.princeton.cs.algs4.StdIn;

public class FrequencyCounter
{
    private static double test(String alg, int minlen)
    {
        Stopwatch stopwatch = new Stopwatch();
        if (alg.equals("BST"))                      callBST(minlen);
        if (alg.equals("RedBlackBST"))              callRedBlackBST(minlen);
        if (alg.equals("BinarySearchST"))           callBinarySearchST(minlen);
        if (alg.equals("SequentialSearchST"))       callSequentialSearchST(minlen);
        if (alg.equals("SeparateChainingHashST"))   callSeparateChaningHashST(minlen);
        return stopwatch.elapsedTime();
    }
    private static void callSeparateChaningHashST(int minlen)
    {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
        doSearch(st, minlen);
    }
    private static void callBST(int minlen)
    {
        BST<String, Integer> st = new BST<>();
        doSearch(st, minlen);
    }

    private static void callRedBlackBST(int minlen)
    {
        RedBlackBST<String, Integer> st = new RedBlackBST<>();
        doSearch(st, minlen);
    }

    private static void callBinarySearchST(int minlen)
    {
        BinarySearchST<String, Integer> st = new BinarySearchST<>(1000);
        doSearch(st, minlen);
    }

    private static void callSequentialSearchST(int minlen)
    {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        doSearch(st, minlen);
    }

    private static void readInput(ST<String, Integer> st, int minLen)
    {
        while (!StdIn.isEmpty())
        {
            String word = StdIn.readString();
            if (word.length() < minLen) continue;
            if (!st.contains(word)) st.put(word, 1);
            else                    st.put(word, st.get(word) + 1);
        }
    }

    private static void doSearch(ST<String, Integer> st, int minLen)
    {
        System.out.println("start do searchIn");
        readInput(st, minLen);
        String max = getMax(st);
        System.out.println(max + " " + st.get(max));
    }

    private static String getMax(ST<String, Integer> st)
    {
        String max = " ";
        st.put(max, 0);
        for (String word : st.keys())
        {
            if (st.get(word) > st.get(max))
                max = word;
        }
        return max;
    }

    public static void main(String[] argv)
    {
        Stopwatch stopwatch = new Stopwatch();

        int minLen = Integer.parseInt(argv[0]);
        test(argv[1], minLen);

        System.out.println(stopwatch.elapsedTime()  + "ms");

    }
}

package Sortings.ElementarySorts;

import edu.princeton.cs.algs4.StdRandom;

public class Quick extends SortBase
{
    /**
     * Sort <code> a </code> using quick sortBySubstring
     * @param a array to be sorted
     */
    public static void sort(Comparable[] a)
    {
        // prevent inverted order, which will cause O(n^2) time consume
        StdRandom.shuffle(a);
        sort_iter(a, 0, a.length - 1);
    }

    private static void sort_iter(Comparable[] a, int lo, int hi)
    {
        if (lo >= hi) return;

        int v = partition(a, lo, hi);
        sort_iter(a, lo, v - 1);
        sort_iter(a, v + 1, hi);
    }

}

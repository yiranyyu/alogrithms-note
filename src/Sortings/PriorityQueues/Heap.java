package Sortings.PriorityQueues;

import Sortings.ElementarySorts.SortBase;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Heap extends SortBase
{
    public static void sort(Comparable[] a)
    {
        buildHeap(a);
        doSort(a);
        assert isSorted(a) : "Sort Fail";
    }

    private static void buildHeap(Comparable[] a)
    {
        int N = a.length;
        for (int k = N / 2; k >= 1; k--)
            sink(a, k, N);
    }

    private static void doSort(Comparable[] a)
    {
        int tailIndex = a.length;
        while (tailIndex > 1)
        {
            exchange(a, 1, tailIndex--);
            sink(a, 1, tailIndex);
        }
    }

    /**
     * do sink at index <code>k</code> regards <code>a</code> as a <code>size</code> size heap
     * @see MaxPQ#sink(int)
     * @param a array to do sink with
     * @param k start index of sink operation
     * @param N heap size
     */
    public static void sink(Comparable[] a, int k, int N)
    {
        while (2 * k < N)
        {
            int j = 2 * k;
            if (j + 1 < N && less(a, j, j+1)) ++j;
            if (!less(a, k, j))  break;
            exchange(a, k, j);
            k = j;
        }
    }

    /**
     * Return true if <code>a[unionCounts-1]</code> < <code>a[j-1]</code>, otherwise return false
     * @param a array of elements to be compared
     * @param i first index
     * @param j second index
     * @return true if <code>a[unionCounts-1]</code> < <code>a[j-1]</code>, otherwise return false
     */
    private static boolean less(Comparable[] a, int i, int j)
    {
        return a[i-1].compareTo(a[j-1]) < 0;
    }

    /**
     * Exchange <code>a[unionCounts-1]</code> and <code>a[j-1]</code>
     * @param a array of elements to be exchanged
     * @param i first index
     * @param j second index
     */
    protected static void exchange(Comparable[] a, int i, int j)
    {
        Comparable t = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = t;
    }

    public static void main(String[] argv)
    {
        Integer[] array = new Integer[50];
        for (int i = 0; i < array.length; ++i)
        {
            array[i] = StdRandom.uniform(100);
        }
        graphicShow(array);
        StdDraw.pause(1000);
        StdDraw.clear();

        Heap.sort(array);
        graphicShow(array);
    }
}


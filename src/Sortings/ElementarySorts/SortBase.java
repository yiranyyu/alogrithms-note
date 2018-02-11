package Sortings.ElementarySorts;

import edu.princeton.cs.algs4.StdDraw;

public abstract class SortBase
{
    protected static boolean less(Comparable a, Comparable b)
    {
        return a.compareTo(b) < 0;
    }

    protected static void exch(Comparable[] a, int i, int j)
    {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    static void show(Comparable[] a)
    {
        for (int i = 0; i < a.length; ++i)
        {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    protected static boolean isSorted(Comparable[] a)
    {
        for (int i = 0; i < a.length - 1; ++i)
        {
            if (less(a[i+1], a[i]))
                return false;
        }
        return true;
    }

    static public void graphicShow(Integer[] a)
    {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (Integer i : a)
        {
            if (max < i)
                max = i;
            if (min > i)
                min = i;
        }
        StdDraw.setYscale(min, max);
        StdDraw.setXscale(0, a.length);
        StdDraw.enableDoubleBuffering();
        for (int i = 0; i < a.length; ++i)
        {
            StdDraw.rectangle(i, a[i]/2.0, 0.3, a[i]/2.0);
        }
        StdDraw.show();
    }

    /**
     * Return index of the key and do partition. Every element before key is less or equals to
     * <code> a[key] </code> and every element after key is large or equals to <code> a[key] </code>
     * @param a array to sortBySubstring
     * @param lo start index of <code> a </code>
     * @param hi end index of <code> a </code>.inclusive
     * @return index of the key
     */
    protected static int partition(Comparable[] a, int lo, int hi)
    {
        int i = lo;
        int j = hi + 1;
        Comparable key = a[lo];
        while (true)
        {
            while (less(a[++i], key))
                if (i >= hi)
                    break;
            while (less(key, a[--j]))
                if (j == lo)
                    break;

            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

}

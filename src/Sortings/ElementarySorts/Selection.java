package Sortings.ElementarySorts;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Selection sort has the least multiply of change.
 */
public class Selection extends SortBase
{
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; ++i)
        {
            int min = i;
            for (int j = i + 1; j < N; ++j)
            {
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
        }
    }

    public static void main(String[] argv)
    {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; ++i)
        {
            array[i] = StdRandom.uniform(20);
        }
        graphicShow(array);
        StdDraw.pause(1000);
        StdDraw.clear();
        sort(array);
        graphicShow(array);
    }
}

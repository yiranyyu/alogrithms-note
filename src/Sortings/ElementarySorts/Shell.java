package Sortings.ElementarySorts;

import edu.princeton.cs.algs4.StdRandom;

public class Shell extends SortBase
{
    /**
     * Sort <code>a</code> using shell sort
     * @param a array to be sorted
     */
    public static void sort(Comparable[] a)
    {
        fasterSort(a);
    }

    /**
     * using <code>exchange</code> to sort
     * @see Shell#fasterSort(Comparable[])
     * @param a array to sort
     */
    public static void normalSort(Comparable[] a)
    {
        int N = a.length;
        int h = 1;
        while (h < N/3) h = 3 * h + 1;// 1, 4, 13, 40, 121, 364, 1093....
        while (h >= 1)
        {
            for (int i = h; i < N; ++i)
            {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                {
                    exch(a, j, j - h);
                }
            }
            h /= 3;
        }
    }

    /**
     * Sort <code>a</code> using shell sort
     * @param a array to be sorted
     */
    public static void fasterSort(Comparable[] a)
    {
        int N = a.length;
        int h = 1;
        while (h < N/3) h = 3 * h + 1;// 1, 4, 13, 40, 121, 364, 1093....
        while (h >= 1)
        {
            for (int i = h; i < N; ++i)
            {
                int j = i - h;
                Comparable tmp = a[i];
                for (; j >= 0 && less(tmp, a[j]); j -= h)
                {
                    a[j+h] = a[j];
                }
                a[j+h] = tmp;
            }
            h /= 3;
        }
    }

    public static void main(String[] argv)
    {
        Integer[] a = new Integer[100];
        for (int i = 0; i < a.length; ++i)
        {
            a[i] = StdRandom.uniform(100);
        }
        show(a);
        sort(a);
        show(a);
    }
}

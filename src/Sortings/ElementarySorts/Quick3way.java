package Sortings.ElementarySorts;

public class Quick3way extends SortBase
{
    /**
     * Partition as three parts
     * @param a array to be sorted
     */
    public static void sort(Comparable[] a)
    {
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (lo <= hi) return;
        int lt = lo;
        int i = lo + 1;
        int gt = hi;
        Comparable key = a[lo];
        while (i <= gt)
        {
            int cmp = a[i].compareTo(key);
            if      (cmp < 0)   exch(a, lt++, i++);
            else if (cmp > 0)   exch(a, i, gt--);
            else                i++;
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }
}

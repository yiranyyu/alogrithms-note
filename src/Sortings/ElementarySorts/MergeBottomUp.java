package Sortings.ElementarySorts;

import java.util.Arrays;
import static java.lang.Math.*;

public class MergeBottomUp extends SortBase
{
    /**
     * @see Merge#sort(Comparable[])
     * @param a array to be sorted
     */
    public static void sort(Comparable[] a)
    {
        Comparable[] aux = Arrays.copyOf(a, a.length);
        int N = a.length;
        boolean reverse = false;
        for (int sz = 1; sz < N; sz *= 2)
        {
            Comparable[] t = a;
            a = aux;
            aux = t;
            reverse = !reverse;

            for (int lo = 0; lo < N-sz; lo += sz+sz)
                Merge.fasterMerge(aux, lo, lo+sz-1, min(lo+sz+sz-1, N-1), a);
        }

        if (reverse)
            System.arraycopy(aux, 0, a, 0, N);
    }
}
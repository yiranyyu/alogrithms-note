package Sortings.ElementarySorts;

import java.util.Arrays;

public class Merge extends SortBase
{
    /**
     * Sort <code>a</code> using Merge sort
     * @param a array to be sorted
     */
    public static void sort(Comparable[] a)
    {
        Comparable[] aux = Arrays.copyOf(a, a.length);
        sort_iter(aux, 0, a.length-1, a);

        if (!isSorted(a))
            throw new RuntimeException("MergeSort Error, cannot sort the array");
    }

    /**
     * Do sortBySubstring by iterating through <code>a</code>
     * @param a array to be sorted
     * @param lo start index of array to be sorted
     * @param hi end(inclusive) index of array to be sorted
     * @param aux store sorted answer of <code>a</code>
     */
    private static void sort_iter(Comparable[] a, int lo, int hi, Comparable[] aux)
    {
        if (hi - lo <= 7)
        {
            Insertion.sort(aux, lo, hi + 1);
            return;
        }

        int mid = (hi - lo) / 2 + lo;
        sort_iter(aux, lo, mid, a);
        sort_iter(aux, mid + 1, hi, a);

        fasterMerge(a, lo, mid, hi, aux);
    }

    /**
     * Merge partly sorted array <code> a </code> into <code> aux </code>
     * @param a partly sorted array
     * @param lo start index of <code> a </code>
     * @param mid mid index of <code> a </code>
     * @param hi end index of <code> a </code>.(inclusive)
     * @param aux store the merged array
     */
    static void fasterMerge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux)
    {
        int i = lo;
        int j = mid + 1;
        int pos = lo;

        while (i <= mid && j <= hi)
        {
            if (less(a[i], a[j]))
                aux[pos++] = a[i++];
            else
                aux[pos++] = a[j++];
        }

        while (i <= mid)
            aux[pos++] = a[i++];
        while (j <= hi)
            aux[pos++] = a[j++];
    }
}

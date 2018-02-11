package Sortings.ElementarySorts;

import Stuff.Alphabet;
import Stuff.ArrayFactor;
import edu.princeton.cs.algs4.StdRandom;

public class Insertion extends SortBase
{
    /**
     * Sort <code>a</code> of range [start, end)
     * @param start first index of the range to be sorted
     * @param end after the last index of range to be sorted
     */
    public static void sort(Comparable[] a, int start, int end)
    {
        assertIndices(a, start, end);

        for (int i = start + 1; i < end; ++i)
        {
            int j = i-1;
            Comparable cur = a[i];
            for (; j >= start && less(cur, a[j]); --j) // 大于 cur 的往右挪一位
                a[j+1] = a[j];
            a[j+1] = cur; //把 cur放到空处
        }
    }

    public static void sort(Comparable[] a)
    {
        sort(a, 0, a.length);
    }
    public static void sort(Comparable[] a, int len)
    {
        sort(a, 0, len);
    }

    public static void sortBySubstring(String[] a, int start, int end, int startIndex)
    {
        assertIndices(a, start, end);

        for (int i = start + 1; i < end; ++i)
        {
            int j = i-1;
            String cur = a[i];
            for (; j >= start && less(cur, a[j], startIndex); --j)
                a[j+1] = a[j];
            a[j+1] = cur;
        }
    }

    private static boolean less(String a, String b, int startIndexOfSubstring)
    {
        String subA = a.substring(startIndexOfSubstring);
        String subB = b.substring(startIndexOfSubstring);
        return less(subA, subB);
    }

    private static void assertIndices(Comparable[] a, int start, int end)
    {
        if (start < 0 || end > a.length)
            throw new ArrayIndexOutOfBoundsException("index of array to be sorted out of range");
    }

    private static void testSortBySubstring()
    {
        int size = 20;
        int strLen = 10;

        for (int pos = 0; pos < strLen; ++pos)
        {
            String[] strings = ArrayFactor.randomStrings(size, strLen, Alphabet.LOWERCASE);
            String[] subStrs = new String[size];
            for (int i = 0; i < size; ++i)
                subStrs[i] = strings[i].substring(pos);
            sortBySubstring(strings, 0, size, pos);
            Insertion.sort(subStrs);
            for (int i = 0; i < size; ++i)
                if (!subStrs[i].equals(strings[i].substring(pos)))
                    throw new RuntimeException("Sort Result Error " + pos);
        }
        System.out.println("Sort Succeed");
    }

    public static void main(String[] argv)
    {
        testSortBySubstring();

        int size = 1000000;
        Integer[] a = new Integer[size];
        for (int i = 0; i < a.length; ++i)
            a[i] = StdRandom.uniform(0, size);
    }
}

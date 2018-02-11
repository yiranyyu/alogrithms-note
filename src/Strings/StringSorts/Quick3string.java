package Strings.StringSorts;
// Created by yirany on 2018/1/31

import Sortings.ElementarySorts.SortBase;
import Stuff.Alphabet;
import Stuff.Checker;
import edu.princeton.cs.algs4.StdRandom;

public class Quick3string extends SortBase
{
    private static int charAt(String s, int pos)
    {
        if (pos < s.length()) return s.charAt(pos);
        return -1;
    }

    public static void sort(String[] a)
    {
        sort(a, 0, a.length, 0);
    }

    private static void sort(String[] a, int first, int last, int pos)
    {
        if (last <= first + 1) return;

        exch(a, first, StdRandom.uniform(first+1, last));// pre shuffle

        int lt = first;
        int gt = last - 1;
        int value = charAt(a[first], pos);
        int i = first + 1;
        while (i <= gt)
        {
            int t = charAt(a[i], pos);
            if      (t < value) exch(a, lt++, i++);
            else if (t > value) exch(a, i, gt--);
            else                ++i;
        }

        // now a[lo..lt-1] < value = a[lt..gt] < a[gt+1..last)
        sort(a, first, lt, pos);
        // skip empty substrings
        if (value >= 0)
            sort(a, lt, gt+1, pos+1);
        sort(a, gt+1, last, pos);

    }

    public static void main(String[] argv)
    {
        String[] strings = Stuff.ArrayFactor.randomStrings(100, Alphabet.LOWERCASE);
        sort(strings);
        if (!Checker.isSorted(strings))
            throw new RuntimeException();
        System.out.println("su");
    }
}

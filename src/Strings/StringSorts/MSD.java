package Strings.StringSorts;
// Created by yirany on 2018/1/31

import Sortings.ElementarySorts.Insertion;
import Stuff.Alphabet;

/**
 * Most-Significant-Digit First sortBySubstring algorithm, which recursively sort
 * the substring by the first leading character in order.
 * @see LSD
 */
public class MSD extends KeyIndexedSortBase
{
    private static final int smallSize = 15;

    public static void sort(String[] a)
    {
        new MSD(a, Alphabet.EXTENDED_ASCII.radix()).sort(0, a.length, 0);
    }

    public MSD(String[] a, int radix)
    {
        super(a, radix);
    }

    /**
     * sortBySubstring range [first, last) of {@code a} by comparing character in {@code pos} index
     * @param first is inclusive
     * @param last is exclusive
     * @param pos index of character to compare
     */
    public void sort(int first, int last, int pos)
    {
        if (last <= first + smallSize)
        {
            Insertion.sortBySubstring(a, first, last, pos);
            return;
        }

        SortByOneChar sortCurPos = new SortByOneChar(first, last, pos);
        sortCurPos.sort();
        sortSubstrings(first, pos, sortCurPos.getCount());
    }

    private void sortSubstrings(int lo, int pos, int[] count)
    {
        for (int r = 0; r < radix; ++r)
            sort(lo + count[r], lo + count[r + 1], pos + 1);
    }

    private class SortByOneChar
    {
        int first;
        int last;
        int pos;
        int[] count;

        SortByOneChar(int first, int last, int pos)
        {
            count = new int[radix + 2];

            this.first = first;
            this.last = last;
            this.pos = pos;
        }

        public void sort()
        {
            countFrequency();
            initIndices();
            moveData();
            copyBack();
        }

        public int[] getCount()
        {
            return count;
        }

        private void countFrequency()
        {
            for (int i = first; i < last; ++i)
            {
                int index = charAtPos(a[i]);
                count[index + 2]++;
            }
        }

        private int charAtPos(String s)
        {
            if (pos < s.length())
                return s.charAt(pos);
            else
                return -1;// to sortBySubstring short strings
        }

        private void initIndices()
        {
            for (int r = 0; r < radix+1; ++r)
                count[r + 1] += count[r];
        }

        private void moveData()
        {
            for (int i = first; i < last; ++i)
            {
                int index = charAtPos(a[i]);
                aux[count[index + 1]++] = a[i];
            }
        }

        private void copyBack()
        {
            System.arraycopy(aux, 0, a, first, last - first);
        }

    }

    public static void main(String[] argv)
    {
        String[] strings = new String[20];
        for (int i = 0; i < 20; ++i) strings[i] = "abc";
        strings[19] = "abcd";
        sort(strings);
    }
}

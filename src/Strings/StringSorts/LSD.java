package Strings.StringSorts;
// Created by yirany on 2018/1/30

import Stuff.Alphabet;

/**
 * Least-Significant-Digit First sortBySubstring algorithm
 * @see MSD
 */
public class LSD extends KeyIndexedSortBase
{
    private int[] count;

    /**
     * @param lenToSort sortBySubstring the array by the first {@code lenToSort} characters of each string
     * @param radix e.g. 128 for ASCII, 256 for extended ASCII, 65536 for Unicode set
     * @see Alphabet#radix()  for convinient radix supply
     * @throws ArrayIndexOutOfBoundsException if {@code a} contains string with length less than {@code lenToSort}
     * or value of characters in string beyond the {@code radix}
     */
    public static void sort(String[] a, int lenToSort, int radix)
    {
        new LSD(a, radix).sort(lenToSort);
    }

    public static void sort(String[] a, int lenToSort)
    {
        sort(a, lenToSort, Alphabet.EXTENDED_ASCII.radix());
    }

    public LSD(String[] a, int radix)
    {
        super(a, radix);
        count = new int[radix + 1];
    }

    public void sort(int lenToSort)
    {
        for (int pos = lenToSort - 1; pos >= 0; --pos)
        {
            sortByOneChar(pos);
            swap();
        }

        // if lenToSort is odd, the sorted result is in the space aux points to,
        // so copy it back to the space the parameter of ctor(i.e. a) points to.
        if (lenToSort % 2 == 1)
            copyBack();
    }

    /**
     * sortBySubstring {@code a} by char at {@code pos} and store the result in {@code aux}.
     */
    private void sortByOneChar(int pos)
    {
        countFrequency(pos);
        initIndices();
        moveData(pos);
    }

    private void clearCount()
    {
        int size = count.length;
        for (int i = 0; i < size; ++i)
            count[i] = 0;
    }

    private void countFrequency(int pos)
    {
        clearCount();// init

        int index;
        for (String str : a)
        {
            index = str.charAt(pos);
            count[index]++;
        }
    }

    private void initIndices()
    {
        for (int r = 0; r < radix; ++r)
            count[r+1] += count[r];
    }

    private void moveData(int pos)
    {
        int index;
        for (String str : a)
        {
            index = str.charAt(pos);
            aux[count[index]++] = str;
        }
    }
}

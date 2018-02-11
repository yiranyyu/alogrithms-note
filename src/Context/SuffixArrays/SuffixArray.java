package Context.SuffixArrays;
// Created by yirany on 2018/2/6

import Strings.StringSorts.Quick3string;

public class SuffixArray
{
    private final String[] suffixes;
    private final int lenOfText;

    public SuffixArray(String text)
    {
        lenOfText = text.length();
        suffixes = new String[lenOfText];
        for (int i = 0; i < lenOfText; ++i)
            suffixes[i] = text.substring(i);
        Quick3string.sort(suffixes);
    }

    public int length()
    {
        return lenOfText;
    }

    public String select(int i)
    {
        return suffixes[i];
    }

    /**
     * Start index of substring in text
     * @param i index of substring in suffix array
     * @return start index of substring in original text
     */
    public int index(int i)
    {
        return lenOfText - suffixes[i].length();
    }

    private static int lcp(String s, String t)
    {
        int minLen = Math.min(s.length(), t.length());
        for (int i = 0; i < minLen; ++i)
            if (s.charAt(i) != t.charAt(i))
                return i;
        return minLen;
    }

    /**
     * @return length of longest common part between {@code i}th suffix and ({@code i - 1})th suffix
     */
    public int lcp(int i)
    {
        return lcp(suffixes[i], suffixes[i-1]);
    }

    /**
     * return number of strings strictly less than {@code key}
     * @param key the key
     */
    public int rank(String key)
    {
        int lo = 0, hi = lenOfText-1;
        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(suffixes[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else              return mid;
        }
        return lo;
    }
}

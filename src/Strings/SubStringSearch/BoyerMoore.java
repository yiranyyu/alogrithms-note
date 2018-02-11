package Strings.SubStringSearch;
// Created by yirany on 2018/2/2

import Stuff.Alphabet;

public class BoyerMoore
{
    private int[] indexOfMostRight;
    private String pattern;
    private Alphabet alphabet;
    private int radix = Alphabet.UNICODE16.radix();

    public static int search(String text, String pattern)
    {
        return new BoyerMoore(pattern).searchIn(text);
    }

    public static int search(String text, String pattern, Alphabet alphabet)
    {
        return new BoyerMoore(pattern, alphabet).searchWithAlphabetIn(text);
    }

    public BoyerMoore(String pattern)
    {
        this.pattern = pattern;
        buildIndicesTable();
    }

    public BoyerMoore(String pattern, Alphabet alphabet)
    {
        this.pattern = pattern;
        this.alphabet = alphabet;
        this.radix = alphabet.radix();
        buildIndicesTableWithAlphabet();
    }

    public int searchIn(String text)
    {
        int textLength = text.length();
        int patternLength = pattern.length();
        int skip = 0;
        for (int i = 0; i <= textLength - patternLength; i += skip)
        {
            skip = 0;

            // scan from right to left
            for (int j = patternLength-1; j >= 0; --j)
            {
                if (pattern.charAt(j) != text.charAt(i+j))
                {
                    skip = j - indexOfMostRight[text.charAt(i+j)];
                    if (skip < 1) skip = 1;
                    break;
                }
            }
            if (skip == 0) return i; // found
        }
        return textLength;
    }

    public int searchWithAlphabetIn(String text)
    {
        int textLength = text.length();
        int patternLength = pattern.length();
        int skip = 0;
        for (int i = 0; i <= textLength - patternLength; i += skip)
        {
            skip = 0;

            // scan from right to left
            for (int j = patternLength-1; j >= 0; --j)
            {
                if (pattern.charAt(j) != text.charAt(i+j))
                {
                    skip = j - indexOfMostRight[indexOfCharAt(text, i+j)];
                    if (skip < 1) skip = 1;
                    break;
                }
            }
            if (skip == 0) return i; // found
        }
        return textLength;
    }

    private void buildIndicesTable()
    {
        initIndices();
        int lengthOfPattern = pattern.length();
        for (int index = 0; index < lengthOfPattern; ++index)
            indexOfMostRight[pattern.charAt(index)] = index;
    }

    private void buildIndicesTableWithAlphabet()
    {
        initIndices();
        int lengthOfPattern = pattern.length();
        for (int index = 0; index < lengthOfPattern; ++index)
            indexOfMostRight[indexOfCharAt(pattern, index)] = index;
    }

    private void initIndices()
    {
        indexOfMostRight = new int[radix];
        for (int c = 0; c < radix; ++c)
            indexOfMostRight[c] = -1;
    }

    private int indexOfCharAt(String string, int index)
    {
        return alphabet.toIndex(string.charAt(index));
    }

    public static void main(String[] argv)
    {
        String text = "12345";
        String pattern = "34";
        int index = search(text, pattern, Alphabet.BASE64);
        System.out.println(text.substring(index, index+pattern.length()).equals(pattern));
    }
}

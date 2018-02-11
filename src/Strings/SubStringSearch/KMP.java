package Strings.SubStringSearch;
// Created by yirany on 2018/2/2

import Stuff.Alphabet;
import edu.princeton.cs.algs4.In;


/**
 * Useful for streams that can't be backup;
 * This class can be too SLOW if using big alphabet(default is the biggest : UNICODE16).
 * So, use small alphabet or switch to other class.
 * @see BoyerMoore usually faster searching if you can do backup operation in input stream
 */
public class KMP extends SubstringSearch
{
    private String pattern;
    private int[][] dfa;// definite finite state auto-machine
    private int radix;
    private Alphabet alphabet;

    public static int search(String txt, String pattern)
    {
        return new KMP(pattern).searchIn(txt);
    }

    public static int search(String txt, String pattern, Alphabet alphabet)
    {
        return new KMP(pattern, alphabet).searchWithAlphabetIn(txt);
    }

    public static int search(In in, String pattern)
    {
        return new KMP(pattern).searchIn(in);
    }

    public static int search(In in, String pattern, Alphabet alphabet)
    {
        return new KMP(pattern, alphabet).searchWithAlphabetIn(in);
    }

    public KMP(String pattern)
    {
        this.pattern = pattern;
        this.radix = Alphabet.UNICODE16.radix();
        buildDefiniteFiniteStateAutoMachine();
    }

    public KMP(String pattern, Alphabet alphabet)
    {
        this.pattern = pattern;
        this.alphabet = alphabet;
        this.radix = alphabet.radix();
        buildDefiniteFiniteStateAutoMachineWithAlphabet();
    }

    public int searchIn(String txt)
    {
        int indexInText = 0;
        int nextState = 0;
        int textLength = txt.length();
        int patternLength = pattern.length();

        for (; indexInText < textLength && nextState < patternLength; ++indexInText)
            nextState = dfa[txt.charAt(indexInText)][nextState];

        if (nextState == patternLength)
            return indexInText - patternLength;
        else
            return textLength;
    }

    public int searchIn(In in)
    {
        int indexInStream = 0;
        int nextState = 0;
        int patternLength = pattern.length();

        for (;in.hasNextChar() && nextState < patternLength; ++indexInStream)
            nextState = dfa[in.readChar()][nextState];

        if (nextState == patternLength)
            return indexInStream - patternLength;
        else
            return NOT_FOUND;
    }

    public int searchWithAlphabetIn(String txt)
    {
        int indexInText = 0;
        int nextState = 0;
        int textLength = txt.length();
        int patternLength = pattern.length();

        for (; indexInText < textLength && nextState < patternLength; ++indexInText)
            nextState = dfa[indexOfCharAt(txt, indexInText)][nextState];

        if (nextState == patternLength)
            return indexInText - patternLength;
        else
            return textLength;
    }

    public int searchWithAlphabetIn(In in)
    {
        int indexInStream = 0;
        int nextState = 0;
        int patternLength = pattern.length();

        for (; in.hasNextChar() && nextState < patternLength; ++indexInStream)
            nextState = dfa[alphabet.toIndex(in.readChar())][nextState];

        if (nextState == patternLength)
            return indexInStream - patternLength;
        else
            return NOT_FOUND;
    }


    private void buildDefiniteFiniteStateAutoMachine()
    {
        int patternLength = pattern.length();
        dfa = new int[radix][patternLength];

        dfa[pattern.charAt(0)][0] = 1;
        for (int restartState = 0, curState = 1; curState < patternLength; ++curState)
        {
            for (int c = 0; c < radix; ++c)
                dfa[c][curState] = dfa[c][restartState];
            dfa[pattern.charAt(curState)][curState] = curState+1;
            restartState = dfa[pattern.charAt(curState)][restartState];
        }
    }

    private void buildDefiniteFiniteStateAutoMachineWithAlphabet()
    {
        int patternLength = pattern.length();
        dfa = new int[radix][patternLength];

        dfa[indexOfCharAt(pattern, 0)][0] = 1;
        for (int restartState = 0, curState = 1; curState < patternLength; ++curState)
        {
            for (int c = 0; c < radix; ++c)
                dfa[c][curState] = dfa[c][restartState];

            dfa[indexOfCharAt(pattern, curState)][curState] = curState+1;
            restartState = dfa[indexOfCharAt(pattern, curState)][restartState];
        }
    }

    private int indexOfCharAt(String string, int index)
    {
        return alphabet.toIndex(string.charAt(index));
    }

}

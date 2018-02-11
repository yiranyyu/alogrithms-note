package Strings.SubStringSearch;
// Created by yirany on 2018/2/2

import Stuff.Alphabet;
import Stuff.PrimeGenerator;

import java.math.BigInteger;

import static Stuff.myMath.*;

/**
 * This class is far too slow than others in this package XD
 */
public class RabinKarp
{
    private static final long SMALL_NUMBER = 1_000_000_000_000_000L; // 10^15

    private String pattern;
    private long patternHash;
    private int patternLength;
    private Alphabet alphabet;
    private int radix;
    private final long prime = PrimeGenerator.nextLongPrimeGreaterThan(SMALL_NUMBER);
    private long factor; // radix^(patternLength-1) % prime

    public static int search(String text, String pattern)
    {
        return new RabinKarp(pattern).search(text);
    }

    public RabinKarp(String pattern)
    {
        this.pattern = pattern;
        patternLength = pattern.length();
        radix = Alphabet.UNICODE16.radix();
        factor = powMod(
                BigInteger.valueOf(radix), BigInteger.valueOf(patternLength-1), BigInteger.valueOf(prime)
        ).longValue();

        patternHash = hash(pattern, patternLength);

    }

    private long hash(String key, int length)
    {
        long h = 0;
        for (int j = 0; j < length; ++j)
            h = (radix * h + key.charAt(j)) % prime;
        return h;
    }

    private boolean check(String text, int start)
    {
        for (int i = 0; i < patternLength; ++i)
            if (text.charAt(start+i) != pattern.charAt(i))
                return false;
        return true;
    }

    private int search(String text)
    {
        int textLength = text.length();
        long textHash = hash(text, patternLength);
        if (patternHash == textHash && check(text, 0))
            return 0;

        for (int i = patternLength; i < textLength; ++i)
        {
            textHash = (textHash - mulMod(factor, text.charAt(i-patternLength), prime) + prime) % prime;
            textHash = (textHash*radix + text.charAt(i)) % prime;

            if (patternHash == textHash && check(text, i-patternLength+1))
                    return i - patternLength + 1;
        }

        return text.length();
    }
}

package Stuff;
// Created by yirany on 2018/2/2


import static Stuff.myMath.*;

import java.math.BigInteger;
import java.util.Random;


public class PrimeGenerator
{
    private static Random rand;

    private PrimeGenerator(){}

    public static long nextLongPrime()
    {
        long prime = rand.nextLong();
        while (!isPrime(prime))
            prime = rand.nextLong();
        return prime;
    }

    public static int nextIntPrime()
    {
        int prime = rand.nextInt();
        while(!isPrime(prime))
            prime = rand.nextInt();
        return prime;
    }

    public static long nextLongPrimeGreaterThan(long lowBound)
    {
        long prime = Rand.nextLongGreaterThan(lowBound);
        while (prime <= lowBound || !isPrime(prime))
            prime = Rand.nextLongGreaterThan(lowBound);
        return prime;

    }

    public static int nextIntPrimeGreaterThan(int lowBound)
    {
        int prime = Rand.nextIntGreaterThan(lowBound);
        while (prime <= lowBound || !isPrime(prime))
            prime = Rand.nextIntGreaterThan(lowBound);
        return prime;
    }

    public static boolean isPrime(long number)
    {
        if (number <= 1) return false;

        int times = getTestTimes(number);
        if (LongAddOverflow(number, number))
            return fermatTest(BigInteger.valueOf(number), times);
        return fermatTest(number, times);
    }

    private static int getTestTimes(long number)
    {
        if (number < 1000_000) return 16;
        if (number < 1000_000_000) return 21;
        if (number < 1000_000_000_000L) return 30;
        if (number < 1000_000_000_000_000L) return 38;
        return 40;
    }

    /**
     * @return true if pass the analysis, otherwise false
     */
    private static boolean fermatTest(BigInteger number, int times)
    {
        checkTestTimes(times);
        setRand();
        for (int i = 0; i < times; ++i)
        {
            long value = Math.abs(rand.nextLong());
            BigInteger base = BigInteger.valueOf(value);
            if (!modEquals(base, powMod(base, number, number), number))
                return false;
        }
        return true;
    }

    /**
     * @return true if pass the analysis, otherwise false
     */
    private static boolean fermatTest(long number, int times)
    {
        checkTestTimes(times);
        if (LongAddOverflow(number, number))
            throw new NumericOverflowException("too big too analysis, use BigInteger instead");

        setRand();
        for (int i = 0; i < times; ++i)
        {
            long base = Math.abs(rand.nextLong());
            if (!modEquals(base, powMod(base, number, number), number))
                return false;
        }
        return true;
    }

    private static void setRand()
    {
        rand = new Random(System.currentTimeMillis());
    }
    private static void checkTestTimes(int times)
    {
        if (times <= 0)
            throw new IllegalArgumentException("multiply of analysis must be positive integers");
    }

    public static void main(String[] argv)
    {
        for (int i = 0; i < 100; ++i)
            if (isPrime((long)i))
                System.out.println(i);
    }
}

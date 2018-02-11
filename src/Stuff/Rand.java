package Stuff;
// Created by yirany on 2018/1/31

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Rand
{
    private static Random rand = new Random(System.currentTimeMillis());
    private static final int DEFAULT_LIMIT = 1_000;
    private static int RANDOM_STRING_LENGTH_LIMIT = DEFAULT_LIMIT;

    private static final String BadBound = "bound must be positive";
    private static final String BadRange = "bound must be greater than origin";
    private static final String BadSize  = "size must be non-negative";


    public static void setRandomStringLengthLimit(int limit)
    {
        if (limit <= 0) throw new IllegalArgumentException("cannot set length limit <= 0");
        RANDOM_STRING_LENGTH_LIMIT = limit;
    }

    public static void setRandomStringLengthLimit()
    {
        RANDOM_STRING_LENGTH_LIMIT = DEFAULT_LIMIT;
    }

    public static long nextLong()
    {
        return ThreadLocalRandom.current().nextLong();
    }

    /**
     * @return a pseudorandom {@code long} value uniformly distributed in range [0, bound)
     */
    public static long nextLong(long bound)
    {
        if (bound <= 0)
            throw new IllegalArgumentException(BadBound);

        return ThreadLocalRandom.current().nextLong(bound);
    }

    /**
     * @return a pseudorandom {@code long} value uniformly distributed in range [origin, bound)
     **/
    public static long nextLong(long origin, long bound)
    {
        if (origin <= bound)
            throw new IllegalArgumentException(BadRange);

        return ThreadLocalRandom.current().nextLong(origin, bound);
    }


    public static long nextLongGreaterThan(long lowBound)
    {
        if (lowBound >= Long.MAX_VALUE-1)
            throw new myMath.NumericOverflowException("lowBound too large");

        return ThreadLocalRandom.current().nextLong(lowBound + 1, Long.MAX_VALUE);
    }

    /**
     * @return a pseudorandom {@code int} value
     */
    public static int nextInt()
    {
        return rand.nextInt();
    }

    /**
     * @return a pseudorandom uniformly distributed {@code int} value in range [0, bound),
     */
    public static int nextInt(int bound)
    {
        if (bound <= 0)
            throw new IllegalArgumentException(BadBound);

        return rand.nextInt(bound);
    }

    /**
     * @return a pseudo random uniformly distributed int value in range [origin, bound);
     */
    public static int nextInt(int origin, int bound)
    {
        if (origin >= bound)
            throw new IllegalArgumentException(BadRange);

        return origin + nextInt(bound - origin);
    }

    public static int nextIntGreaterThan(int origin)
    {
        if (origin >= Integer.MAX_VALUE-1)
            throw new myMath.NumericOverflowException("lowBound too large");

        return ThreadLocalRandom.current().nextInt(origin + 1, Integer.MAX_VALUE);
    }

    public static String randomString(Alphabet alphabet, int length)
    {
        StringBuilder builder = new StringBuilder();
        int radix = alphabet.radix();
        char next = 0;
        for (int i = 0; i < length; ++i)
        {
            next = alphabet.toChar(rand.nextInt(radix));
            builder.append(next);
        }
        return builder.toString();
    }

    public static String randomString(Alphabet alphabet)
    {
        int length = rand.nextInt(RANDOM_STRING_LENGTH_LIMIT);
        return randomString(alphabet, length);
    }

    public static int[] ints()
    {
        return ThreadLocalRandom.current().ints().toArray();
    }

    public static int[] ints(int length)
    {
        return ThreadLocalRandom.current().ints(length).toArray();
    }

    public static int[] ints(int origin, int bound)
    {
        return ThreadLocalRandom.current().ints(origin, bound).toArray();
    }

    public static int[] ints(int length, int origin, int bound)
    {
        return ThreadLocalRandom.current().ints(length, origin, bound).toArray();
    }

    public static void main(String[] argv)
    {
        String str = randomString(Alphabet.BASE64, 10);
        System.out.println(str);
    }
}

package Stuff;

import java.math.BigInteger;

/*
 * Created by yirany on 2017/10/15.
 */
public class myMath
{
    public final static long LONG_MAX = Long.MAX_VALUE;


    private final static BigInteger ZERO = BigInteger.ZERO;
    private final static BigInteger ONE = BigInteger.ONE;
    private final static BigInteger TWO = BigInteger.valueOf(2);
    private final static double LOG2 = Math.log(2);

    public static BigInteger mulMod(BigInteger a, BigInteger b, BigInteger mod)
    {
        BigInteger remain = BigInteger.valueOf(0);
        a = a.mod(mod);

        while (!b.equals(ZERO))
        {
            if (isOdd(b))
                remain = remain.add(a).mod(mod);
            a = a.multiply(TWO).mod(mod);
            b = b.divide(TWO);
        }
        return remain.mod(mod);
    }

    public static long mulMod(long a, long b, long mod)
    {
        if (LongAddOverflow(mod, mod)) throw new NumericOverflowException("too large mod to calculate");

        a %= mod;
        long remain = 0;
        while(b != 0)
        {
            if (b % 2 == 1)
                remain = (remain+a) % mod;
            a = (2*a) % mod;
            b /= 2;
        }
        return remain % mod;
    }

    public static int mulMod(int a, int b, int mod)
    {
        return (int)mulMod((long)a, (long)b, (long)mod);
    }

    public static BigInteger powMod(BigInteger base, BigInteger pow, BigInteger mod)
    {
        BigInteger remain = BigInteger.valueOf(1);
        base = base.mod(mod);
        while (!pow.equals(ZERO))
        {
            if (isOdd(pow))
                remain = mulMod(remain, base, mod);
            base = mulMod(base, base, mod);
            pow = pow.divide(TWO);
        }
        return remain;
    }

    /**
     * @return {@code base} power of {@code pow} mod {@code mod},
     * mod * mod must not overflow, otherwise the ans may be wrong
     */
    public static long powMod(long base, long pow, long mod)
    {
        if (base < 0 || pow < 0 || mod < 0) throw new NumberFormatException("negative input error");

        long remain = 1;
        base %= mod;
        while (pow > 0)
        {
            if (pow % 2 == 1)
                remain = mulMod(remain, base, mod);
            base = mulMod(base, base, mod);
            pow /= 2;
        }
        return remain;
    }

    public static int powMod(int base, int pow, int mod)
    {
        return (int)powMod((long)base, (long)pow, (long)mod);
    }

    /**
     * @return a % mod == b % mod
     */
    public static boolean modEquals(BigInteger a, BigInteger b, BigInteger mod)
    {
        return a.mod(mod).equals(b.mod(mod));
    }

    public static boolean modEquals(long a, long b, long mod)
    {
        return a % mod == b % mod;
    }

    public static boolean modEquals(int a, int b, int mod)
    {
        return modEquals((long)a, (long)b, (long)mod);
    }

    public static double log2(double x)
    {
        return Math.log(x) / LOG2;
    }

    private static boolean isOdd(BigInteger number)
    {
        return number.mod(TWO).equals(ONE);
    }

    /**
     * Return value of x!
     *
     * @param x must in [0, 20]
     * @throws IllegalArgumentException if x is negative
     * */
    public static long fact(int x)
    {
        assert x >= 0: "need fact of negative number";
        assert x < 21: "fact of too large x";
        if (x < 0 || x >= 21)
            throw new IllegalArgumentException("fact factor out of range");

        long rtn = 1;
        for (int i = 2; i <= x; i++)
        {
            rtn *= i;
        }
        return rtn;
    }

    /**
     * Return the combination number C(size, k)
     *
     * @param N must be positive
     * @param k must in [0, size]
     * @throws IllegalArgumentException if size is non-positive or k out of range [0, size]
     * */
    public static long combinationNumber(int N, int k)
    {
        assert k <= N : "k larger than size in myMath.combinationNumber";
        assert k >= 0 || N > 0 : "k < 0 or size <= 0 in myMath.combinationNumber";
        if (k > N || k < 0 || N <= 0)
            throw new IllegalArgumentException("wrong number of combination number arguments");

        if (k == 0)
            return 1;

        if (k > N / 2)
            k = N - k;

        long numerator = 1;
        long denominator = fact(k);
        while(k-- > 0)
        {
            numerator *= N--;
        }
        assert denominator > 0 : "denominator overflow in myMath.combinationNumber";
        assert numerator > 0 : "numerator overflow in myMath.combinationNumber";
        if (denominator <= 0 || numerator <= 0)
            throw new ArithmeticException("long number overflow");

        return numerator / denominator;
    }

    public static boolean LongAddOverflow(long left, long right)
    {
        if (left < 0 && right < 0)
            return left+right > 0;
        if (left > 0 && right > 0)
            return left+right < 0;
        return false;
    }

    public static boolean LongMinusOverflow(long left, long right)
    {
        if (left < 0 && right > 0)
            return left - right > 0;
        if (left > 0 && right < 0)
            return left - right < 0;
        return false;
    }

    public static boolean LongMulsOverflow(long left, long right)
    {
        if (left <= 1 && left >= -1 || right <= 1 && right >= -1)
            return false;
        if (left > 0 && right > 0 || left < 0 && right < 0)
            return Long.MAX_VALUE / left  < right;
        if (left > 0 && right < 0 || left < 0 && right > 0)
            return Long.MIN_VALUE / left > right;
        return false;
    }

    public static boolean DoubleAddOverflow(double left, double right)
    {
        return Double.MAX_VALUE - left < right ||
                Double.MIN_VALUE - left > right;
    }

    public static boolean DoubleMinusOverflow(double left, double right)
    {
        return DoubleAddOverflow(left, -right);
    }

    public static boolean DoubleMulsOverflow(double left, double right)
    {
        if (left <= 1 && left >= -1 || right <= 1 && right >= -1)
            return false;
        if (left > 0 && right > 0 || left < 0 && right < 0)
            return Double.MAX_VALUE / left  < right;
        if (left > 0 && right < 0 || left < 0 && right > 0)
            return Double.MIN_VALUE / left > right;
        return false;
    }

    public static long gcd(long a, long b)
    {
        return (b == 0 ? a : gcd(b, a%b));
    }

    public static BigInteger gcd(BigInteger a, BigInteger b)
    {
        return (b.equals(ZERO) ? a : gcd(b, a.mod(b)));
    }

    public static BigInteger oppositeOf(BigInteger value)
    {
        return ZERO.subtract(value);
    }

    public static void main(String[] args)
    {
        int N = 20;
        for (int k = 0; k <= N; k++)
        {
            System.out.println(combinationNumber(N, k) + "\n" + (fact(N) / (fact(N - k) * fact(k))));
        }
    }

    static class NumericOverflowException extends RuntimeException
    {
        public NumericOverflowException()
        {
            super();
        }

        public NumericOverflowException(String msg)
        {
            super(msg);
        }
    }
}

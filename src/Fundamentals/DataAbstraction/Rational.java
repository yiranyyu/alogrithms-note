package Fundamentals.DataAbstraction;

/*
 * Created by yirany on 2017/10/1.
 */

import Stuff.myMath;

import java.math.BigInteger;

import static Stuff.myMath.*;

public class Rational
{
    private static final String VALUE_OVERFLOW = "value of Rational overflow";
    private static final BigInteger ZERO = BigInteger.ZERO;

    private BigInteger numerator;
    private BigInteger denominator;

    public Rational(long numerator, long denominator)
    {
        long gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
        this.numerator = BigInteger.valueOf(numerator);
        this.denominator = BigInteger.valueOf(denominator);
        modifySymbol();
    }

    public Rational(BigInteger numerator, BigInteger denominator)
    {
        BigInteger gcd = gcd(numerator, denominator);
        this.numerator = numerator.divide(gcd);
        this.denominator = denominator.divide(gcd);
        modifySymbol();
    }

    public Rational add(Rational that)
    {
        return new Rational(
                numerator.multiply(that.denominator).add(that.numerator.multiply(denominator)),
                denominator.multiply(that.denominator));
    }

    public Rational subtract(Rational b)
    {
        return add(oppositeOf(b));
    }

    public Rational multiply(Rational b)
    {
        return new Rational(
                numerator.multiply(b.numerator),
                denominator.multiply(b.denominator));
    }

    public Rational divides(Rational b)
    {
        return multiply(b.reciprocal());
    }

    public Rational reciprocal()
    {
        return new Rational(this.denominator, this.numerator);
    }

    public static Rational oppositeOf(Rational value)
    {
        return new Rational(myMath.oppositeOf(value.numerator), value.denominator);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rational rational = (Rational) o;

        if (!numerator.equals(rational.numerator)) return false;
        return denominator.equals(rational.denominator);
    }

    @Override
    public int hashCode()
    {
        int result = numerator.hashCode();
        result = 31 * result + denominator.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return "Rational{" +
                numerator +
                " / " + denominator +
                '}';
    }

    private void modifySymbol()
    {
        if (isNegative(denominator))
        {
            numerator = ZERO.subtract(numerator);
            denominator = ZERO.subtract(denominator);
        }
    }

    private boolean isNegative(BigInteger value)
    {
        return value.compareTo(ZERO) < 0;
    }
}

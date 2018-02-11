package Fundamentals.DataAbstraction;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RationalTest
{
    private Rational half;
    private Rational one_third;

    @Before
    public void setUp() throws Exception
    {
        half = new Rational(1, 2);
        one_third = new Rational(1, 3);
    }

    @Test
    public void add() throws Exception
    {
        assertEquals(half.add(one_third), new Rational(5, 6));
    }

    @Test
    public void subtract() throws Exception
    {
        assertEquals(half.subtract(one_third), new Rational(1, 6));
    }

    @Test
    public void multiply() throws Exception
    {
        assertEquals(half.multiply(one_third), new Rational(1, 6));
    }

    @Test
    public void divides() throws Exception
    {
        assertEquals(half.divides(one_third), new Rational(3, 2));
    }

    @Test
    public void reciprocal() throws Exception
    {
        assertEquals(half.reciprocal(), new Rational(2, 1));
        assertEquals(one_third.reciprocal(), new Rational(3, 1));
    }

    @Test
    public void oppositeOf() throws Exception
    {
        assertEquals(Rational.oppositeOf(half), new Rational(-1, 2));
        assertEquals(Rational.oppositeOf(one_third), new Rational(-1, 3));
    }

    @Test
    public void equals() throws Exception
    {
        assertNotEquals(half, one_third);
    }

}
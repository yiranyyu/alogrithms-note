package Fundamentals.DataAbstraction;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest
{
    @Test
    public void isLeapYear() throws Exception
    {
        Date d1 = new Date(2000, 1, 1);
        Date d2 = new Date(1900, 2, 28);
        Date d3 = new Date(2016, 10, 6);

        assertTrue(d1.isLeapYear());
        assertFalse(d2.isLeapYear());
        assertTrue(d3.isLeapYear());
    }

    @Test
    public void testCheckDate()
    {
        try
        {
            Date d1 = new Date(2000, 13, 2);
            fail("permit month larger than 12 ");
        }catch (IllegalArgumentException e){}

        try
        {
            Date d2 = new Date(2000, 0, 2);
            fail("permit month less than 1");
        }catch (IllegalArgumentException e){}

        try
        {
            Date d2 = new Date(1900, 2, 29);
            fail("permit illegal day of month");
        }catch (IllegalArgumentException e){}

    }
}
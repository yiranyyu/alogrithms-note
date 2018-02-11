package Fundamentals.Bags_Queues_Stacks;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class FixedCapacityStackOfStringsTest
{
    private static final int DEFAULT_LENGTH = 10;
    private FixedCapacityStackOfStrings stack;

    private void initStack(int size)
    {
        stack = new FixedCapacityStackOfStrings(size);
    }

    @Before
    public void setUp() throws Exception
    {
        initStack(DEFAULT_LENGTH);
    }

    @Test
    public void iterator() throws Exception
    {
        initStack(DEFAULT_LENGTH);
        for (int i = 0 ; i < DEFAULT_LENGTH; ++i)
            stack.push("" + i);

        int i = DEFAULT_LENGTH;
        for (String str : stack)
            assertTrue(str.equals(--i + ""));
    }

    @Test
    public void isFull() throws Exception
    {
        initStack(2);
        stack.push("");
        stack.push("");
        assertTrue(stack.isFull());
    }

    @Test
    public void push() throws Exception
    {
        initStack(2);
        stack.push("1");
        stack.push("2");
        assertTrue(stack.pop().equals("2"));
    }

    @Test(expected = NoSuchElementException.class)
    public void pop() throws Exception
    {
        initStack(2);
        stack.push("1");
        stack.push("2");
        assertTrue(stack.pop().equals("2"));
        assertTrue(stack.pop().equals("1"));
        stack.pop();
    }

}
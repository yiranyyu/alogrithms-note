package Fundamentals.Bags_Queues_Stacks;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class StackTest
{
    private Stack<Integer> stack;

    private void init()
    {
        stack = new Stack<>();
    }

    @Before
    public void setUp() throws Exception
    {
        init();
    }

    @Test
    public void push() throws Exception
    {
        init();
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        int i = 4;
        while (!stack.isEmpty())
            assertTrue(i-- == stack.pop());
    }

    @Test(expected = NoSuchElementException.class)
    public void pop() throws Exception
    {
        stack.push(1);
        stack.push(2);
        assertTrue(stack.pop() == 2);

        while (!stack.isEmpty()) stack.pop();

        assertTrue(stack.size() == 0);
        stack.pop(); // should throw
    }

    @Test(expected = NoSuchElementException.class)
    public void top() throws Exception
    {
        stack.push(43);
        int size = stack.size();
        assertTrue(stack.top() == 43);
        assertTrue(stack.size() == size);

        init();
        stack.top();
    }

    @Test
    public void iterator() throws Exception
    {
        init();
        int size = 1000;
        for (int i = 0; i < size; ++i)
            stack.push(i);

        int i = size;
        for (int value : stack)
            assertTrue(value == --i);
        assertTrue(size == stack.size());
    }

}
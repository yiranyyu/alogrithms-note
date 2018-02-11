package Fundamentals.Bags_Queues_Stacks;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class QueueTest
{
    private Queue<Integer> queue;

    private void init()
    {
        queue = new Queue<>();
    }

    @Before
    public void setUp() throws Exception
    {
        init();
    }

    @Test
    public void enqueue() throws Exception
    {
        queue.enqueue(1);
        queue.enqueue(2);
        assertTrue(queue.dequeue() == 1);
    }

    @Test(expected = NoSuchElementException.class)
    public void dequeue() throws Exception
    {
        init();
        queue.enqueue(1);
        int prevSize = queue.size();
        assertTrue(queue.dequeue() == 1);
        assertTrue(queue.size()+1 == prevSize);
        queue.dequeue();
    }

    @Test
    public void size() throws Exception
    {
        init();
        assertTrue(queue.size() == 0);

        queue.enqueue(0);
        assertTrue(queue.size() == 1);

        queue.dequeue();
        assertTrue(queue.size() == 0);
    }

    @Test
    public void isEmpty() throws Exception
    {
        init();
        assertTrue(queue.isEmpty());

        queue.enqueue(0);
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void iterator() throws Exception
    {
        init();
        for (int i = 0; i < 10; ++i)
            queue.enqueue(i);

        int i = 0;
        for (int value : queue)
            assertTrue(value == i++);
    }

}
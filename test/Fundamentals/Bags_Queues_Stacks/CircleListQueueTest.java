package Fundamentals.Bags_Queues_Stacks;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class CircleListQueueTest
{
    private CircleListQueue<Integer> queue;

    private void initQueue()
    {
        queue = new CircleListQueue<>();
    }

    @Test
    public void isEmpty() throws Exception
    {
        initQueue();
        assertTrue(queue.isEmpty());

        queue.enqueue(1);
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void enqueue() throws Exception
    {
        initQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        assertTrue(queue.dequeue() == 2);
    }

    @Test
    public void size() throws Exception
    {
        initQueue();
        assertTrue(queue.size() == 0);

        queue.enqueue(1);
        queue.enqueue(1);
        assertTrue(queue.size() == 2);

        queue.dequeue();
        assertTrue(queue.size() == 1);
        queue.dequeue();
        assertTrue(queue.size() == 0);
        assertTrue(queue.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void dequeue() throws Exception
    {
        initQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        assertTrue(queue.dequeue() == 2);
        assertTrue(queue.dequeue() == 1);

        queue.dequeue();
    }

    @Test
    public void iterator() throws Exception
    {
        initQueue();
        for (int i = 0; i < 10; ++i)
            queue.enqueue(i);

        int i = 10;
        for (int val : queue)
            assertTrue(val == --i);
    }

}
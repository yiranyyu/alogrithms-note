package Fundamentals.Bags_Queues_Stacks;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ResizingArrayDequeTest
{

    private ResizingArrayDeque<Integer> deque;

    private void init()
    {
        deque = new ResizingArrayDeque<>();
    }

    @Before
    public void setUp() throws Exception
    {
        init();
    }

    @Test
    public void pushFront() throws Exception
    {
        init();
        deque.pushFront(0);
        deque.pushFront(1);
        deque.pushFront(2);
        deque.pushFront(3);

        int i = 3;
        while (!deque.isEmpty())
            assertTrue(deque.popFront() == i--);
    }

    @Test
    public void pushBack() throws Exception
    {
        init();
        deque.pushBack(0);
        deque.pushBack(1);
        deque.pushBack(2);
        deque.pushBack(3);

        int i = 3;
        while (!deque.isEmpty())
            assertTrue(deque.popBack() == i--);
    }

    @Test(expected = NoSuchElementException.class)
    public void popFront() throws Exception
    {
        init();
        deque.pushFront(1);
        int size = deque.size();

        assertTrue(deque.popFront() == 1);
        assertTrue(deque.size() + 1 == size);
        deque.popFront(); // should throw
    }

    @Test(expected = NoSuchElementException.class)
    public void popBack() throws Exception
    {
        init();
        deque.pushBack(1);
        int size = deque.size();

        assertTrue(deque.popBack() == 1);
        assertTrue(deque.size() + 1 == size);
        deque.popBack(); // should throw
    }

    @Test(expected = NoSuchElementException.class)
    public void back() throws Exception
    {
        deque.pushBack(43);
        assertTrue(deque.back() == 43);

        init();
        deque.back();// should throw
    }

    @Test(expected = NoSuchElementException.class)
    public void front() throws Exception
    {
        deque.pushFront(44);
        assertTrue(deque.front() == 44);

        init();
        deque.front();// should throw
    }

    @Test
    public void iterator() throws Exception
    {
        init();

        for (int i = 0; i < 10; ++i)
            deque.pushBack(i);

        int i = 0;
        for (int value : deque)
            assertTrue(value == i++);
    }

}
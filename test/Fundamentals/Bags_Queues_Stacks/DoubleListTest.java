package Fundamentals.Bags_Queues_Stacks;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoubleListTest
{

    private DoubleList<Integer> list;

    @Before
    public void setUp() throws Exception
    {
        initList();
    }

    private void initList()
    {
        list = new DoubleList<>();
    }

    @Test
    public void front() throws Exception
    {
        initList();
        list.pushBack(1);
        list.pushFront(2);
        list.pushFront(3);
        assertTrue(list.front() == 3);
    }

    @Test
    public void back() throws Exception
    {
        initList();
        list.pushFront(1);
        list.pushBack(2);
        list.pushBack(3);
        assertTrue(list.back() == 3);
    }

    @Test
    public void insertBefore() throws Exception
    {
        initList();
        list.pushFront(1);
        list.pushFront(2);
        list.insertBefore(2, 42);

        list.popBack();
        assertTrue(list.back() == 42);
        assertTrue(list.front() == 2);
    }

    @Test
    public void insertAfter() throws Exception
    {
        initList();

        list.pushBack(2);
        list.insertAfter(42, 1);
        assertTrue(list.back() == 42);
    }

    @Test
    public void pushFront() throws Exception
    {
        list.pushFront(43);
        assertTrue(list.front() == 43);
    }

    @Test
    public void pushBack() throws Exception
    {
        list.pushBack(44);
        assertTrue(list.back() == 44);
    }

    @Test
    public void popFront() throws Exception
    {
        int size = list.size();
        list.pushFront(45);
        assertTrue(list.back() == 45);

        list.popFront();
        assertTrue(list.size() == size);
    }

    @Test
    public void popBack() throws Exception
    {
        int size = list.size();
        list.pushBack(46);
        assertTrue(list.back() == 46);

        list.popBack();
        assertTrue(list.size() == size);
    }

    @Test
    public void iterator() throws Exception
    {
        initList();
        for (int i = 0; i < 10; ++i)
            list.pushFront(i);
        int i = 10;
        for (int value : list)
            assertTrue(value == --i);
    }
}
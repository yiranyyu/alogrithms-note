package Fundamentals.Bags_Queues_Stacks;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ListTest
{
    private List<Integer> list;

    private void initList()
    {
        list = new List<>();
    }

    @Before
    public void setUp() throws Exception
    {
        initList();
    }

    @Test(expected = NoSuchElementException.class)
    public void delete() throws Exception
    {
        initList();
        list.push(3);
        list.push(4);
        list.push(4);

        list.delete(4);
        assertTrue(list.size() == 1);
        assertTrue(list.front() == 3);

        list.delete(3);
        assertTrue(list.isEmpty());

        try
        {
            list.delete(null);
            fail("permit delete null");
        }catch (IllegalArgumentException e){}

        list.delete(0);
    }

    @Test
    public void push() throws Exception
    {
        initList();
        list.push(2);
        assertTrue(list.front() == 2);
        assertTrue(list.size() == 1);
    }

    @Test
    public void pushFront() throws Exception
    {
        list.pushFront(1);
        list.pushFront(2);
        assertTrue(list.front() == 2);
    }

    @Test
    public void pushBack() throws Exception
    {
        list.pushBack(1);
        list.pushBack(2);
        assertTrue(list.back() == 2);
    }

    @Test(expected = NoSuchElementException.class)
    public void popFront() throws Exception
    {
        list.pushFront(3);
        assertTrue(list.front() == 3);

        list.popFront();
        list.popFront();
    }

    @Test(expected = NoSuchElementException.class)
    public void popBack() throws Exception
    {
        initList();
        list.pushBack(3);
        assertTrue(list.back() == 3);

        list.popBack();
        list.popBack();
    }

    @Test(expected = NoSuchElementException.class)
    public void front() throws Exception
    {
        initList();
        list.pushFront(1);
        assertTrue(list.front() == 1);
        list.popFront();
        list.front();
    }

    @Test(expected = NoSuchElementException.class)
    public void back() throws Exception
    {
        initList();
        list.pushBack(1);
        assertTrue(list.back() == 1);
        list.popBack();
        list.back();
    }

    @Test
    public void iterator() throws Exception
    {
        initList();
        for (int i = 0; i < 10; ++i)
            list.pushBack(i);
        int i = 0;
        for (int value : list)
            assertTrue(value == i++);
    }

}
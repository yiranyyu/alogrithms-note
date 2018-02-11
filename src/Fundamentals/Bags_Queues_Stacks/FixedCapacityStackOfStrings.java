package Fundamentals.Bags_Queues_Stacks;

import Fundamentals.ContainerBase.LinearContainer;

import java.util.Iterator;

/*
 * Created by yirany on 2017/10/7.
 */
public class FixedCapacityStackOfStrings extends LinearContainer<String>
    implements Iterable<String>
{
    private String[] content;
    public FixedCapacityStackOfStrings(int capacity)
    {
        checkNotNegative(capacity);

        content = new String[capacity];
    }

    @Override
    public Iterator<String> iterator()
    {
        return new StackIterator();
    }

    public boolean isFull()
    {
        return size == content.length;
    }

    public void push(String str)
    {
        if (isFull())
            throw new UnsupportedOperationException("cannot push to full stack");
        content[size++] = str;
    }

    public String pop()
    {
        checkNotEmpty();

        String rtn = content[--size];
        content[size] = null;
        return rtn;
    }

    @Override
    public Iterable<String> values()
    {
        return this;
    }

    private class StackIterator
            implements Iterator<String>
    {
        private int i = size;

        @Override
        public boolean hasNext()
        {
            return i > 0;
        }

        @Override
        public String next()
        {
            return content[--i];
        }
    }

}

package Fundamentals.Bags_Queues_Stacks;

import Fundamentals.ContainerBase.ArrayBasedLinearContainer;

import java.util.Arrays;
import java.util.Iterator;

/*
 * Created by yirany on 2017/10/7.
 */

public class ResizingArrayStack<Value> extends ArrayBasedLinearContainer<Value>
    implements Iterable<Value>
{
    public ResizingArrayStack(int size)
    {
        super(size);
    }

    public Value pop()
    {
        checkNotEmpty();
        Value value = content[--size];
        content[size] = null; // 防止元素游离
        tryShrinkSize();
        return value;
    }

    public void push(Value value)
    {
        tryImproveCapacity();
        content[size++] = value;
    }

    @Override
    public Iterator<Value> iterator()
    {
        return new stackIterator();
    }

    protected void resize(int newSize)
    {
        checkNewSize(newSize);
        content = Arrays.copyOf(content, newSize);
    }

    @Override
    public Iterable<Value> values()
    {
        return this;
    }

    private class stackIterator implements Iterator<Value>
    {
        int top = size;
        public boolean hasNext()
        {
            return top > 0;
        }
        public Value next()
        {
            return content[--top];
        }
    }
}

package Fundamentals.Bags_Queues_Stacks;

import Fundamentals.ContainerBase.ArrayBasedLinearContainer;

import java.util.Iterator;

/*
 * Created by yirany on 2017/10/7.
 */
public class ResizingArrayDeque<Value> extends ArrayBasedLinearContainer<Value>
    implements Iterable<Value>
{
    // first represent the most old element
    private int first;

    public ResizingArrayDeque(int size)
    {
        super(size);
    }

    public ResizingArrayDeque(){}

    public void pushFront(Value value)
    {
        tryImproveCapacity();

        first = prevIndex();
        content[first] = value;
        ++size;
    }

    public void pushBack(Value value)
    {
        tryImproveCapacity();

        content[(lastIndex() + 1) % content.length] = value;
        ++size;
    }

    public Value popFront()
    {
        checkNotEmpty();

        Value value = content[first];
        content[first] = null;
        first = nextFirst();
        --size;

        tryShrinkSize();
        return value;
    }

    public Value popBack()
    {
        checkNotEmpty();

        Value value = content[lastIndex()];
        content[lastIndex()] = null;
        --size;

        tryShrinkSize();
        return value;
    }

    public Value back()
    {
        checkNotEmpty();
        return content[lastIndex()];
    }

    public Value front()
    {
        checkNotEmpty();
        return content[first];
    }

    @Override
    public Iterator<Value> iterator()
    {
        return new DequeIteratorFrontToBack();
    }

    private int nextFirst()
    {
        return (first + 1)% content.length;
    }

    private int prevIndex()
    {
        return (first - 1 + content.length) % content.length;
    }

    /**
     * @return the index of the last element
     * */
    private int lastIndex()
    {
        return (first + size - 1) % content.length;
    }

    protected void resize(int newSize)
    {
        checkNewSize(newSize);

        Value[] newArray = getNewArray(newSize);
        int oldStart = first;
        int newStart = first + (newSize - content.length);
        first = newStart;

        // if not cross the border of the array
        if (first + size - 1 < content.length)
            System.arraycopy(content, oldStart, newArray, newStart, size);
        else
        {
            // if cross the border
            int backPartLength = content.length - oldStart;
            System.arraycopy(content, oldStart, newArray, newStart, backPartLength);

            int prevPartLength = lastIndex() + 1;
            System.arraycopy(content, 0, newArray, 0, prevPartLength);
        }
        content = newArray;
    }

    @Override
    public Iterable<Value> values()
    {
        return this;
    }

    private class DequeIteratorFrontToBack
            implements Iterator<Value>
    {
        int visitedCount = 0;
        @Override
        public boolean hasNext()
        {
            return visitedCount < size;
        }

        @Override
        public Value next()
        {
            int index = (first + visitedCount) % content.length;
            ++visitedCount;
            return content[index];
        }
    }
}

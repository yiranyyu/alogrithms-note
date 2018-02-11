package Fundamentals.Bags_Queues_Stacks;

import Fundamentals.ContainerBase.ArrayBasedLinearContainer;
import Fundamentals.ContainerBase.LinearContainer;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;

/*
 * Created by yirany on 2017/10/8.
 */
public class RandomBag<Value> extends ArrayBasedLinearContainer<Value>
    implements Iterable<Value>
{
    public RandomBag(int size)
    {
        super(size);
    }

    public RandomBag(){}

    public void add(Value value)
    {
        tryImproveCapacity();

        content[size++] = value;
    }

    public Iterator<Value> iterator()
    {
        return new RandomBagIterator();
    }

    @Override
    public Iterable<Value> values()
    {
        return this;
    }

    protected void resize(int newSize)
    {
        checkNewSize(newSize);

        content = Arrays.copyOf(content, newSize);
    }

    private class RandomBagIterator
            implements Iterator<Value>
    {
        private int i = size;

        RandomBagIterator()
        {
            StdRandom.shuffle(content, 0, size);
        }

        @Override
        public boolean hasNext()
        {
            return i > 0;
        }

        @Override
        public Value next()
        {
            return content[--i];
        }
    }
}

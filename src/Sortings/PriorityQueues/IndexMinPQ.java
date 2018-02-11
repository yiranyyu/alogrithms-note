package Sortings.PriorityQueues;
// Created by yirany on 2018/1/30

import java.util.Iterator;

/**
 * Simple adopter to use edu.princeton.cs.algs4.IndexMinPQ
 * @see edu.princeton.cs.algs4.IndexMinPQ for detail
 */
public class IndexMinPQ<Key extends Comparable<Key>>
    implements Iterable<Integer>
{
    private edu.princeton.cs.algs4.IndexMinPQ<Key> impl;

    public IndexMinPQ(int size)
    {
        impl = new edu.princeton.cs.algs4.IndexMinPQ<>(size);
    }

    public boolean isEmpty()
    {
        return impl.isEmpty();
    }

    public int size()
    {
        return impl.size();
    }

    public void insert(int index, Key key)
    {
        impl.insert(index, key);
    }

    public int minIndex()
    {
        return impl.minIndex();
    }

    public Key minKey()
    {
        return impl.minKey();
    }

    public void deleteMin()
    {
        impl.delMin();
    }

    public int getAndDeleteMin()
    {
        return impl.delMin();
    }

    public Key keyOf(int index)
    {
        return impl.keyOf(index);
    }

    public void changeKey(int index, Key key)
    {
        impl.changeKey(index, key);
    }

    public void decreaseKey(int index, Key key)
    {
        impl.decreaseKey(index, key);
    }

    public void increaseKey(int index, Key key)
    {
        impl.increaseKey(index, key);
    }

    public void delete(int index)
    {
        impl.delete(index);
    }

    @Override
    public Iterator<Integer> iterator()
    {
        return impl.iterator();
    }

    public boolean contains(int index)
    {
        return impl.contains(index);
    }
}

package Sortings.PriorityQueues;

import java.util.NoSuchElementException;

abstract public class PriorityQueueBase<Key extends Comparable<Key>>
{
    // Store all the keysInOrder
    protected Key[] pq;
    protected int elementsNumber = 0;

    public PriorityQueueBase()
    {
        this(1);
    }
    public PriorityQueueBase(int size)
    {
        if (size < 0) throw new NegativeArraySizeException("negative container size in MaxPQ");
        pq = (Key[])new Comparable[size+1];
    }

    public int size()
    {
        return elementsNumber;
    }

    public boolean isEmpty()
    {
        return elementsNumber == 0;
    }

    protected boolean isFull()
    {
        return elementsNumber == pq.length - 1;
    }

    protected void resize(int newSize)
    {
        if (newSize <= elementsNumber)
            throw new IllegalArgumentException("trunking array");

        Key[] newPq = (Key[]) new Comparable[newSize + 1];
        System.arraycopy(pq, 1, newPq, 1, elementsNumber);
        pq = newPq;
    }

    /**
     * Return whether element unionCounts is less than element j
     * @param i index of first node to compare
     * @param j index of second node to compare
     * @return true if <code>pq[unionCounts]</code> less than <code>pq[j]</code>
     */
    protected boolean less(int i, int j)
    {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * exchange elements at index unionCounts and j
     * @param i index of first node
     * @param j index of second node
     */
    protected void exchange(int i, int j)
    {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    protected void shrinkSize()
    {
        if (pq.length > 4 && elementsNumber * 4 < pq.length)
            resize(elementsNumber * 2);
    }

    protected void assertNotEmpty()
    {
        if (isEmpty()) throw new NoSuchElementException("priority queue is empty");
    }



}

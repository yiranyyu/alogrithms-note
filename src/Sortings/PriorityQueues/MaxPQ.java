package Sortings.PriorityQueues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class MaxPQ<Key extends Comparable<Key>>
    extends PriorityQueueBase<Key>
{
    public MaxPQ(){}
    public MaxPQ(int size)
    {
        super(size);
    }
    public MaxPQ(Key[] array)
    {
        super(array.length);
        for (Key k : array)
            this.insert(k);
    }

    public Key getMax()
    {
        assertNotEmpty();
        return pq[1];
    }

    public void delMax()
    {
        assertNotEmpty();

        pq[1] = pq[elementsNumber--];
        keepOrderAndRemoveLast();
        shrinkSize();
    }

    public void insert(Key k)
    {
        if (isFull())
            resize(elementsNumber * 2);
        pq[++elementsNumber] = k;
        swim(elementsNumber);
    }

    private void keepOrderAndRemoveLast()
    {
        sink(1);
        pq[elementsNumber +1] = null;
    }

    /**
     * Adjust structure of <code>pq</code> from <code>k</code> and upward.
     * Until <code>k</code> node less than it's parent or k is root of <code>pq</code>
     * @param k start index of node to swim
     */
    private void swim(int k)
    {
        while (k > 1 && less(k/2, k))
        {
            exchange(k/2, k);
            k /= 2;
        }
    }

    /**
     * Adjust structure of <code>pq</code> from <code>k</code> and downward.
     * Until <code>k</code> is not less than it's children nodes or k has node child
     * @param k start index of node to sink
     */
    private void sink(int k)
    {
        while (2 * k <= elementsNumber)
        {
            int j = 2 * k;
            if (j < elementsNumber && less(j, j+1)) ++j;

            if (!less(k, j)) break; // return if current node is in order
            exchange(k, j);             // if current node less than child node, exchange nodes
            k = j;
        }
    }

    public static void main(String[] argv)
    {
        Integer[] integers = new Integer[10];
        for (int i = 0; i < integers.length; i++)
        {
            integers[i] = StdRandom.uniform(100);
        }
        System.out.println(Arrays.toString(integers));

        MaxPQ<Integer> maxPQ = new MaxPQ<>(integers);
        while (!maxPQ.isEmpty())
        {
            System.out.println(maxPQ.getMax());
            maxPQ.delMax();
        }
    }
}

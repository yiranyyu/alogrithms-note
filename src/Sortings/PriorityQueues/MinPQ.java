package Sortings.PriorityQueues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class MinPQ<Key extends Comparable<Key>>
    extends PriorityQueueBase<Key>
{
    public MinPQ(){}
    public MinPQ(int size)
    {
        super(size);
    }

    public MinPQ(Key[] array)
    {
        super(array.length);
        for (Key k : array)
            this.insert(k);
    }

    public Key getAndDeleteMin()
    {
        Key tmp = getMin();
        delMin();
        return tmp;
    }

    public Key getMin()
    {
        assertNotEmpty();
        return pq[1];
    }

    public void delMin()
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
        pq[elementsNumber + 1] = null;
    }

    private void swim(int k)
    {
        while (k > 1 && !less(k/2, k))
        {
            exchange(k/2, k);
            k /= 2;
        }
    }

    private void sink(int k)
    {
        while (2 * k <= elementsNumber)
        {
            int j = 2 * k;
            if (j < elementsNumber && !less(j, j+1)) ++j;

            if (less(k, j)) break;
            exchange(k, j);
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

        MinPQ<Integer> maxPQ = new MinPQ<>(integers);
        while (!maxPQ.isEmpty())
        {
            System.out.println(maxPQ.getMin());
            maxPQ.delMin();
        }

    }
}

package Searching.ApplicationsOfSymbolTables;

import Sortings.ElementarySorts.Insertion;

import java.util.Arrays;
import java.util.Iterator;


/**
 * Store single values in order
 * @param <Value> extends Comparable interface
 */
public class LinearSet<Value extends Comparable<Value>>
    implements Iterable<Value>
{
    private Value[] values;
    private int N = 0;

    public LinearSet(int size)
    {
        if (size < 0) throw new NegativeArraySizeException();
        values = (Value[]) new Comparable[size];
    }

    public LinearSet()
    {
        this(2);
    }

    public void add(Value val)
    {
        assertNotNull(val);
        if (contains(val)) return;
        tryImproveSize();

        values[N++] = val;
        Insertion.sort(values, N);
    }

    public void delete(Value val)
    {
        assertNotNull(val);

        int index = indexOf(val);
        if (index == -1) return;

        for (; index < N -1; ++index) values[index] = values[index+1];
        values[N - 1] = null;
        tryShrinkSize();
    }

    public boolean contains(Value val)
    {
        return indexOf(val) != -1;
    }

    private void tryImproveSize()
    {
        if (N == values.length) resize(N * 2);
    }

    private void tryShrinkSize()
    {
        if (values.length > 4 && N * 4 < values.length) resize(N * 2);
    }

    private void resize(int newSize)
    {
        assert (newSize <= N) : "try to narrow capacity below number of items!";
        values = Arrays.copyOf(values, newSize);
    }

    private void assertNotNull(Object o)
    {
        if (o == null)
            throw new IllegalArgumentException("calls with null value");
    }

    private int indexOf(Value val)
    {
        return Arrays.binarySearch(values, 0, N, val);
    }

    @Override
    public Iterator<Value> iterator()
    {
        class LinearSetIterator
            implements Iterator<Value>
        {
            private int cur = 0;
            @Override
            public boolean hasNext()
            {
                return cur < N;
            }

            @Override
            public Value next()
            {
                return values[cur++];
            }
        }
        return new LinearSetIterator();
    }
}

package Searching.HashTables;

import Fundamentals.Bags_Queues_Stacks.List;
import Searching.ElementarySymbolTables.ST;
import Searching.ElementarySymbolTables.SequentialSearchST;

public class SeparateChainingHashST<Key, Value>
    extends ST<Key, Value>
{
    private SequentialSearchST<Key, Value>[] map;
    private int size;  // number of pairs
    private int arrayLength;  // size of searchIn table

    public SeparateChainingHashST()
    {
        this(11);
    }

    public SeparateChainingHashST(int size)
    {
        arrayLength = nextPrime(size-1);
        map = new SequentialSearchST[arrayLength];
        this.size = 0;
        for (int i = 0; i < arrayLength; ++i)
            map[i] = new SequentialSearchST<>();
    }

    @Override
    public boolean contains(Key key)
    {
        return map[hash(key)].contains(key);
    }

    public boolean isEmpty()
    {
        return size == 0;
    }
    public int size()
    {
        return size;
    }

    /**
     * Return the index where {@code key} should be in the {@code map},
     * {@code key} must not be null
     * @return the index where {@code key} should be
     */
    private int hash(Key key)
    {
        return (key.hashCode() & 0x7fffffff) % arrayLength;
    }

    public Value get(Key key)
    {
        return map[hash(key)].get(key);
    }

    /**
     * Delete the key from the searchIn table
     * Key must not be null, otherwise this function throws a nullPointerException
     * @throws NullPointerException if {@code key} is null
     */
    public void delete(Key key)
    {
        int index = hash(key);
        size -= map[index].size();
        map[index].delete(key);
        size += map[index].size();

        if (arrayLength > 11 && 4*size < arrayLength)
            reduceCapacity();
    }

    public void put(Key key, Value value)
    {
        if (key == null)
            throw new IllegalArgumentException("call put with null key");
        if (value == null)
            throw new IllegalArgumentException("value to put can't be null");

        int index = hash(key);
        size -= map[index].size();
        map[index].put(key, value);
        size += map[index].size();
        if (size > 2*arrayLength) improveCapacity();
    }

    private void improveCapacity()
    {
        int capacity = nextPrime(arrayLength);
        changeCapacity(capacity);
    }

    private void reduceCapacity()
    {
        int capacity = prevPrime(arrayLength);
        changeCapacity(capacity);
    }

    private void changeCapacity(int newCapacity)
    {
        SequentialSearchST<Key, Value>[] newMap = new SequentialSearchST[newCapacity];

        for (int i = 0; i < newCapacity; ++i)
            newMap[i] = new SequentialSearchST<>();

        int index;
        for (SequentialSearchST<Key, Value> list : map)
        {
            if (list.isEmpty()) continue;

            for (Key key : list.keys())
            {
                index = (key.hashCode() & 0x7fffffff) % newCapacity;
                newMap[index].put(key, list.get(key));
            }
        }
        map = newMap;
        arrayLength = newCapacity;
    }

    public Iterable<Key> keys()
    {
        List<Key> keys = new List<>();
        for (SequentialSearchST<Key, Value> list : map)
            for (Key key : list)
                keys.push(key);
        return keys;
    }

    public Iterable<Value> values()
    {
        List<Value> values = new List<>();
        for (SequentialSearchST<Key, Value> list : map)
            for (Value val : list.values())
                values.push(val);
        return values;
    }

    public static void main(String[] argv)
    {
        int N = 10;
        int val = 0;
        int Limit = 10000;
        for (int i = 0; i < 6; ++i)
        {
            SeparateChainingHashST<Integer, Integer> st = new SeparateChainingHashST<>();
            val = 0;
            for (int j = 0; j < N; ++j)
            {
                val += 1;
                if (!st.contains(val))  st.put(val, 1);
                else                    st.put(val, st.get(val) + 1);
            }
            System.out.println("size : " + N + "\t size: " + st.size());
            N *= 10;
        }
    }
}

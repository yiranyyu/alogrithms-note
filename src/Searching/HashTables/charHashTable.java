package Searching.HashTables;
// Created by yirany on 2018/2/1

import Stuff.myAssert;

/**
 * faster hash table specialization for characters
 * @see LinearProbingHashST
 * @see SeparateChainingHashST
 */
public class charHashTable<Value>
{
    private static int NULL = Integer.MAX_VALUE;

    private int numberOfElements;
    private int Capacity;
    private int[] keys;
    private Value[] values;

    public charHashTable(int len)
    {
        if (len < 0)
            throw new ArrayIndexOutOfBoundsException("negative array size");

        Capacity = SeparateChainingHashST.nextPrime(len);
        keys = new int[Capacity];
        values = (Value[]) new Object[Capacity];
        for (int i = 0; i < Capacity; ++i)
            keys[i] = NULL;
    }

    public charHashTable()
    {
        this(11);
    }

    public Value get(char key)
    {
        if (isEmpty()) return null;

        for (int i = key%Capacity; keys[i] != NULL; i = (i+1) % Capacity)
            if (keys[i] == key)
                return values[i];
        return null;
    }

    public void put(char key, Value val)
    {
        myAssert.assertNotNull(val);
        if (numberOfElements >= Capacity / 2)
            resize(2 * Capacity);

        int i;
        for (i = key%Capacity; keys[i] != NULL; i = (i+1) % Capacity)
        {
            if (keys[i] == key)
            {
                values[i] = val;
                return;
            }
        }
        keys[i] = key;
        values[i] = val;
        numberOfElements++;
    }

    private void resize(int newSize)
    {
        if (newSize > Capacity)
            newSize = SeparateChainingHashST.nextPrime(Capacity);
        else if (newSize < Capacity)
            newSize = SeparateChainingHashST.prevPrime(Capacity);
        else
            return;

        charHashTable<Value> t = new charHashTable<>(newSize);
        for (int i = 0; i < Capacity; ++i)
            if (keys[i] != NULL)
                t.put((char)keys[i], values[i]);
        keys = t.keys;
        values = t.values;
        Capacity = t.Capacity;
    }

    public boolean isEmpty()
    {
        return numberOfElements == 0;
    }

    public int size()
    {
        return numberOfElements;
    }

    public char[] keys()
    {
        char[] chars = new char[size()];
        int index = 0;
        for (int c : keys)
            if (c != NULL)
                chars[index++] = (char)c;
        return chars;
    }

    public boolean contains(char key)
    {
        return keys[key%Capacity] != NULL;
    }
}

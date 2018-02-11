package Searching.BinarySearchTrees;

import Searching.ElementarySymbolTables.ST;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

public class BinarySearchST<Key extends Comparable<Key>, Value>
    extends ST<Key, Value>
{
    private Key[] keys;
    private Value[] vals;
    private int N = 0;

    public BinarySearchST(int capacity)
    {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size()
    {
        return N;
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    /**
     * Return the number of keysInOrder less than <code> key </code>
     *
     * @param key the key to find in ST
     * @return the number of keysInOrder less than <code> key </code> whether it exist or not
     */
    public int rank(Key key)
    {
        int lo = 0, hi = N - 1;
        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            int cmp = keys[mid].compareTo(key);
            if (cmp < 0) lo = mid + 1;
            else if (cmp > 0) hi = mid - 1;
            else return mid;
        }
        return lo;
    }

    public Value get(Key key)
    {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
            return vals[i];
        return null;
    }

    public void put(Key key, Value val)
    {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
        {
            vals[i] = val;
            return;
        }
        // add new key-value pair
        for (int j = N; j > i; j--)
        {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
        if (N == keys.length) fitCapacity(keys.length * 2);
    }

    private void fitCapacity(int newCapacity)
    {
        Key[] newKeys = (Key[]) new Comparable[newCapacity];
        Value[] newVals = (Value[]) new Object[newCapacity];
        System.arraycopy(keys, 0, newKeys, 0, N);
        System.arraycopy(vals, 0, newVals, 0, N);
        keys = newKeys;
        vals = newVals;
    }
    public void delete(Key key)
    {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException("delete from empty BinarySearchST.");
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
        {
            for (int j = i + 1; j < N; ++j)
            {
                keys[j - 1] = keys[j];
                vals[j - 1] = vals[j];
            }
            keys[N - 1] = null;
            vals[N - 1] = null;
            --N;
        }
    }

    private void checkEmpty()
    {
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException("get element from empty BinarySearchST.");
    }

    public Key min()
    {
        checkEmpty();
        return keys[0];
    }

    public Key max()
    {
        checkEmpty();
        return keys[N - 1];
    }

    public Key select(int k)
    {
        if (k >= N)
            throw new ArrayIndexOutOfBoundsException("index to select out of bound");
        return keys[k];
    }

    public Key ceiling(Key key)
    {
        if (key == null)    return null;
        return keys[rank(key)];
    }

    public Key floor(Key key)
    {
        if (key == null)    return null;
        int i = rank(key);
        if (i == 0)
            return key.compareTo(keys[0]) == 0 ? keys[0] : null;
        return key.compareTo(keys[i]) == 0 ? keys[i] : keys[i - 1];
    }

    public boolean contains(Key key)
    {
        int lo = 0, hi = N - 1;
        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            int cmp = keys[mid].compareTo(key);
            if (cmp < 0) lo = mid + 1;
            else if (cmp > 0) hi = mid - 1;
            else return true;
        }
        return false;
    }

    /**
     * Return sequence containing elements in range [lo, hi]
     * @param lo lower bound of sequence
     * @param hi upper bound of sequence
     * @return sequence containing elements in range [lo, hi]
     */
    public Iterable<Key> keys(Key lo, Key hi)
    {
        Queue<Key> q = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++)
            q.enqueue(keys[i]);
        if (contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }

    public Iterable<Key> keys()
    {
        Queue<Key> q = new Queue<>();
        for (int i = 0; i < N; i++)
            q.enqueue(keys[i]);
        return q;
    }

    public static void permutation(char[] str, int i) {
        if (i >= str.length)
            return;
        if (i == str.length - 1) {
            System.out.println(String.valueOf(str));
        } else {
            for (int j = i; j < str.length; j++) {
                char temp = str[j];
                str[j] = str[i];
                str[i] = temp;

                permutation(str, i + 1);

                temp = str[j];
                str[j] = str[i];
                str[i] = temp;
            }
        }
    }
    public static void main(String[] argv)
    {

        int N = 10;
        int Limit = 10000;
        for (int i = 0; i < 6; ++i)
        {
            BinarySearchST<Integer, Integer> st = new BinarySearchST(N);
            for (int j = 0; j < N; ++j)
            {
                int val = StdRandom.uniform(0, Limit);
                if (!st.contains(val))  st.put(val, 1);
                else                    st.put(val, st.get(val) + 1);
            }
            System.out.println("size : " + N + "\t size: " + st.size());
            N *= 10;
        }
    }

}

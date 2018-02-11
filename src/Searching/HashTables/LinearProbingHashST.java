package Searching.HashTables;

import Fundamentals.Bags_Queues_Stacks.Queue;
import Searching.ElementarySymbolTables.ST;

import java.util.Arrays;
import java.util.NoSuchElementException;


public class LinearProbingHashST<Key, Value>
    extends ST<Key, Value>
{
    private int numberOfElements;
    private int Capacity;
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST(int len)
    {
        if (len < 0) throw new ArrayIndexOutOfBoundsException("negative array size");

        Capacity = nextPrime(len);
        keys = (Key[]) new Object[Capacity];
        values = (Value[]) new Object[Capacity];
    }

    public LinearProbingHashST()
    {
        this(11);
    }

    public LinearProbingHashST(LinearProbingHashST<Key, Value> that)
    {
        this.Capacity = that.Capacity;
        this.keys = Arrays.copyOf(that.keys, Capacity);
        this.values = Arrays.copyOf(that.values, Capacity);
        this.numberOfElements = that.numberOfElements;
    }

    /**
     * peek one key from the {@code keys}, same to the first element of {@code keys()} returned
     * @throws NoSuchElementException if table is empty
     * @throws RuntimeException if unexpected Error occurred
     */
    public Key peek()
    {
        if (isEmpty()) throw new NoSuchElementException();
        for (Key key : keys)
            if (key != null) return key;
        throw new RuntimeException("In " + this.getClass());    // should never get here
    }

    private int hash(Key key)
    {
        return (key.hashCode() & 0x7fffffff) % Capacity;
    }

    @Override
    public boolean contains(Key key)
    {
        return !isEmpty() && get(key) != null;
    }

    public boolean isEmpty()
    {
        return numberOfElements == 0;
    }

    /**
     * @return value associated with {@code key} if exist, otherwise return null
     */
    @Override
    public Value get(Key key)
    {
        if (key == null) throw new IllegalArgumentException("call get with null key");
        if (isEmpty())   return null;

        for (int i = hash(key); keys[i] != null; i = (i + 1) % Capacity)
            if (keys[i].equals(key))
                return values[i];
        return null;
    }

    @Override
    public void put(Key key, Value val)
    {
        if (key == null) throw new IllegalArgumentException("call put with null key");
        if (val == null) {delete(key); return;}
        if (numberOfElements >= Capacity / 2) resize(2 * Capacity);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % Capacity)
        {
            if (keys[i].equals(key))
            {
                values[i] = val;
                return;
            }
        }
        keys[i] = key;
        values[i] = val;
        numberOfElements++;
    }

    public void delete(Key key){
        if (key == null) throw new IllegalArgumentException("call delete with null key");

        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i])) i = (i + 1) % Capacity;

        keys[i] = null; // remove key
        values[i] = null; // remove val
        i = (i + 1) % Capacity;

        while (keys[i] != null) // for keys with same hash code of deleted key
        {
            Key     keyToRedo = keys[i];
            Value   valToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            numberOfElements--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % Capacity;
        }
        numberOfElements--;
        if (numberOfElements > 0 && numberOfElements < Capacity / 8) resize(Capacity / 2);
    }

    @Override
    public Iterable<Key> keys()
    {
        Queue<Key> queue = new Queue<>();
        for (Key key : keys)
            if (key != null) queue.enqueue(key);
        return queue;
    }

    public Iterable<Value> vals()
    {
        Queue<Value> queue = new Queue<>();
        for (int i = 0; i < Capacity; ++i)
            if (keys[i] != null) queue.enqueue(values[i]);
        return queue;
    }

    private void resize(int newSize)
    {
        if (newSize > Capacity)
            newSize = nextPrime(Capacity);
        else if (newSize < Capacity)
            newSize = prevPrime(Capacity);
        else
            return;

        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(newSize);
        for (int i = 0; i < Capacity; ++i)
            if (keys[i] != null)
                t.put(keys[i], values[i]);
        keys = t.keys;
        values = t.values;
        Capacity = t.Capacity;
    }

    public int size()
    {
        return numberOfElements;
    }

}

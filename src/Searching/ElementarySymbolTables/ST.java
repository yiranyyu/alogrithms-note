package Searching.ElementarySymbolTables;

public abstract class ST<Key, Value>
{
    public abstract boolean contains(Key key);
    public abstract Value get(Key key);
    public abstract void put(Key key, Value val);
    public abstract Iterable<Key> keys();

    private static final int PRIME_LIST[] = {
            11, 23,
            53, 97, 193, 389, 769,
            1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433,
            1572869, 3145739, 6291469, 12582917, 25165843,
            50331653, 100663319, 201326611, 402653189, 805306457,
            1610612741,
    };

    /**
     * @return the least prime in PRIME_LIST larger than {@code prev}
     * @throws RuntimeException if prev has no next prime in the list
     */
    public static int nextPrime(int prev)
    {
        for (int prime : PRIME_LIST)
        {
            if (prime > prev)
                return prime;
        }
        throw new RuntimeException("array length too large!");
    }

    /**
     * @return largest prime in PRIME_LIST less than {@code prev}
     */
    public static int prevPrime(int prev)
    {
        for (int i = 1; i < PRIME_LIST.length; ++i)
        {
            if (PRIME_LIST[i] >= prev) return PRIME_LIST[i - 1];
        }
        return PRIME_LIST[PRIME_LIST.length - 1];
    }

}

package Strings.Tries;
// Created by yirany on 2018/1/31

import Fundamentals.Bags_Queues_Stacks.Queue;
import Searching.HashTables.charHashTable;
import Stuff.Alphabet;
import Stuff.ArrayFactor;
import Stuff.myAssert;
import Stuff.Stopwatch;

public class TrieST<Value> implements Tries<Value>
{
    private int size;
    private Node root;

    public TrieST()
    {
        // do nothing
    }

    public int size()
    {
        return size;
    }

    public Iterable<String> keys()
    {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix)
    {
        myAssert.assertNotNull(prefix, "prefix of string should never be null");

        Queue<String> strings = new Queue<>();
        Node rot = get(root, prefix, 0);
        collectAll(rot, prefix, strings);
        return strings;
    }

    /**
     * Collect all the keys int the tree of {@code rot}
     * @param rot root of the tree to find keys in
     * @param prefix of current character to make up the whole key
     * @param strings container to hold keys
     */
    private void collectAll(Node rot, String prefix, Queue<String> strings)
    {
        if (rot == null) return;
        if (rot.value != null) strings.enqueue(prefix);
        for (char c: rot.nextChars())
            collectAll(rot.linkTo(c), prefix + c, strings);
    }

    public Iterable<String> keysThatMatch(String pattern)
    {
        myAssert.assertNotNull(pattern, "pattern to match can't be null");

        Queue<String> strings = new Queue<>();
        collectWithPattern(root, "", pattern, strings);
        return strings;
    }

    private void collectWithPattern(Node rot, String prefix, String pattern, Queue<String> strings)
    {
        if (rot == null) return;

        int curLen = prefix.length();
        if (curLen == pattern.length())
        {
            if (rot.value != null)
                strings.enqueue(prefix);
            return;
        }

        char next = pattern.charAt(curLen);
        for (char c : rot.nextChars())
            if (next == '.' || next == c)
                collectWithPattern(rot.linkTo(c), prefix + c, pattern, strings);
    }

    public String longestPrefixOf(String s)
    {
        myAssert.assertNotNull(s, "can't get prefix of null String");

        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node rot, String s, int curLen, int currentMaxLen)
    {
        if (rot == null)    // reach the end of the searchIn tree
            return currentMaxLen;

        if (rot.value != null) // log length if find a new part
            currentMaxLen = curLen;

        if (curLen == s.length()) // found the string
            return currentMaxLen;

        char c = s.charAt(curLen);
        return search(rot.linkTo(c), s, curLen+1, currentMaxLen);
    }

    public void delete(String key)
    {
        myAssert.assertNotNull(key, "No null String in " + this.getClass());

        root = delete(root, key, 0);
    }

    /**
     * @return rot after delete operation, which will be used to update the tree
     */
    private Node delete(Node rot, String key, int curLen)
    {
        if (rot == null) return null;

        if (curLen != key.length()) // continue to find
        {
            char nextToFind = key.charAt(curLen);
            Node update = delete(rot.linkTo(nextToFind), key, curLen+1);
            rot.setLinkTo(nextToFind, update);
        }
        else if (rot.value != null)
        {
            --size;
            rot.value = null;
        }

        if (rot.value != null || rot.hasNext())
            return rot;
        return null;// return null means to delete cur rot
    }

    /**
     * @return  key associated with {@code key} if exist, otherwise return null
     */
    public Value get(String key)
    {
        myAssert.assertNotNull(key, "key can't be null in " + this.getClass());

        Node node = get(root, key, 0);
        if (node == null)
            return null;
        return (Value)node.value;
    }

    /**
     * try to get value of {@code key}, in the tree in which {@code x} is the root
     */
    private Node get(Node x, String key, int pos)
    {
        if (x == null) return null;

        // now, x is contains the value of last character in key
        if (pos == key.length())
            return x;

        char c = key.charAt(pos);
        return get(x.linkTo(c), key, pos+1);
    }

    public void put(String key, Value val)
    {
        myAssert.assertNotNull(val, "can't store null value to tries ");

        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value value, int pos)
    {
        if (x == null) x = new Node();
        if (pos == key.length())
        {
            if (x.value == null)
                ++size;
            x.value = value;
            return x;
        }

        char c = key.charAt(pos);
        x.setLinkTo(c, put(x.linkTo(c), key, value, pos+1));
        return x;
    }

    private static class Node
    {
        public static int count;

        private Object value;
        private charHashTable<Node> next;

        Node(Object value)
        {
            ++count;
            this.value = value;
            next = new charHashTable<>();
        }

        Node()
        {
            ++count;
            next = new charHashTable<>();
        }

        void setLinkTo(char c, Node node)
        {
            next.put(c, node);
        }

        Node linkTo(char c)
        {
            return next.get(c);
        }

        char[] nextChars()
        {
            return next.keys();
        }

        boolean hasNext()
        {
            return next.size() > 0;
        }
    }

    public static void main(String[] argv)
    {
        String[] strings = ArrayFactor.randomStrings(100_000, 7, Alphabet.UNICODE16);
        String[] tofinds = ArrayFactor.randomStrings(100_000, 7, Alphabet.UNICODE16);
        int T = 100;

        TrieST<Integer> st = new TrieST<>();
        for (String str : strings)
            st.put(str, 0);
        TST<Integer> tst = new TST<>();
        for (String str : strings)
            tst.put(str, 0);

        System.out.println("start");

        Stopwatch watch = new Stopwatch();
        for (int i = 0; i < T; ++i)
            for (String str : strings)
                st.get(str);
        System.out.println(watch.elapsedTime() / 1000.0 + " s");

        watch = new Stopwatch();
        for (int i = 0; i < T; ++i)
            for (String str : strings)
                tst.get(str);
        System.out.println(watch.elapsedTime() / 1000.0 + " s");

        System.out.println(TST.countOfNodes);
        System.out.println(Node.count);
    }
}

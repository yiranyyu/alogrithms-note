package Searching.ElementarySymbolTables;

import Fundamentals.Bags_Queues_Stacks.List;

import java.util.Iterator;

public class SequentialSearchST<Key, Value>
    extends ST<Key, Value>
    implements Iterable<Key>
{
    private class Node
    {
        Key key;
        Value value;
        Node next;
        Node(Key key, Value value, Node next)
        {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    private Node head;
    private int N = 0;

    public SequentialSearchST() {super();}

    public void put(Key key, Value value)
    {
        assertNotNull(key);
        assertNotNull(value);

        if (!tryFindKeyAndUpdate(key, value))
            addNode(key, value);
    }

    private void addNode(Key key, Value value)
    {
        head = new Node(key, value, head);
        ++N;
    }

    private boolean tryFindKeyAndUpdate(Key key, Value value)
    {
        Node node = head;
        while (node != null)
        {
            if (key.equals(node.key))
            {
                node.value = value;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public Key top()
    {
        assertNotEmpty();
        return head.key;
    }

    public Value get(Key key)
    {
        assertNotEmpty();
        assertNotNull(key);

        Node node = head;
        while (node != null)
        {
            if (key.equals(node.key))
                return node.value;
            node = node.next;
        }
        return null;
    }

    public void delete(Key key)
    {
        assertNotEmpty();
        assertNotNull(key);

        Node node = head;
        while (node.next != null)
        {
            if (key.equals(node.next.key))
            {
                node.next = node.next.next;
                --N;
                return;
            }
            node = node.next;
        }
        if (key.equals(node.key))
            deleteLast();
    }

    private void deleteLast()
    {
        assertNotEmpty();

        if (size() == 1)
        {
            head = null;
            --N;
            return;
        }
        Node node = head;
        while (node.next.next != null)
            node = node.next;
        node.next = null;
        --N;
    }

    public int size()
    {
        return N;
    }
    public boolean isEmpty()
    {
        return N == 0;
    }

    public boolean contains(Key key)
    {
        if (isEmpty())   return false;
        if (key == null) return false;

        for (Node node = head; node != null; node = node.next)
        {
            if (node.key.equals(key))
                return true;
        }
        return false;
    }

    private class SequentialSearchSTIterator
        implements Iterator<Key>
    {
        Node node = head;
        @Override
        public boolean hasNext()
        {
            return node != null;
        }

        @Override
        public Key next()
        {
            assert (hasNext()) : "do not have nextChars value in SequentialSearchSTIterator";
            Key rt = node.key;
            node = node.next;
            return rt;
        }
    }
    @Override
    public Iterator<Key> iterator()
    {
        return new SequentialSearchSTIterator();
    }

    public Iterable<Key> keys()
    {
        return this;
    }

    public Iterable<Value> values()
    {
        //Todo
        List<Value> vals = new List<>();
        Node node = head;
        while (node != null)
        {
            vals.push(node.value);
            node = node.next;
        }
        return vals;
    }

    private void assertNotEmpty()
    {
        if (isEmpty()) throw new UnsupportedOperationException("get from empty");
    }

    private void assertNotNull(Object o)
    {
        if (o == null) throw new NullPointerException("null obj to call with");
    }
}

package Fundamentals.Bags_Queues_Stacks;

/*
 * Created by yirany on 2017/10/7.
 */

import Fundamentals.ContainerBase.LinearContainer;

import java.util.Iterator;

public class CircleListQueue<Value> extends LinearContainer<Value>
    implements Iterable<Value>
{
    private Node<Value> last;

    public CircleListQueue()
    {
        last = new Node<>();
        last.next = last;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    public void enqueue(Value value)
    {
        last.value = value;
        Node<Value> node = new Node<>();
        node.next = last.next;
        last.next = node;
        last = node;
        ++size;
    }

    public int size()
    {
        return size;
    }

    /**
     * Cost linear time
     * @deprecated too slow, use Queue if you don't mind the memory cost
     * @see Queue
     * @return last value enqueued
     */
    public Value dequeue()
    {
        checkNotEmpty();

        Node<Value> node = last;
        while (node.next.next != last)
            node = node.next;

        // new node.next is the node to pop out
        Value value = node.next.value;
        node.next = last;
        --size;

        return value;
    }

    @Override
    public Iterable<Value> values()
    {
        return this;
    }

    @Override
    public Iterator<Value> iterator()
    {
        return new CircleListIterator();
    }

    private class CircleListIterator
            implements Iterator<Value>
    {
        Node<Value> head = null;

        CircleListIterator()
        {
            Node<Value> cur = last.next;
            while (cur != last)
            {
                Node<Value> node = new Node<>(cur.value);
                node.next = head;
                head = node;
                cur = cur.next;
            }
        }

        @Override
        public boolean hasNext()
        {
            return head != null;
        }

        @Override
        public Value next()
        {
            Value value = head.value;
            head = head.next;
            return value;
        }
    }
}

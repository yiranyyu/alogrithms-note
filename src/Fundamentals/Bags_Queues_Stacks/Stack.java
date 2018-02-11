package Fundamentals.Bags_Queues_Stacks;

import Fundamentals.ContainerBase.LinearContainer;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Created by yirany on 2017/10/7.
 */
public class Stack<Value> extends LinearContainer
    implements Iterable<Value>
{
    private Node<Value> head;

    public Stack()
    {
        head = new Node<>();
    }

    public void push(Value value)
    {
        Node<Value> first = new Node<>();
        first.value = value;
        first.next = head.next;
        head.next = first;
        ++size;
    }

    public Value pop()
    {
        checkNotEmpty();
        Value value = head.next.value;
        head.next = head.next.next;
        --size;
        return value;
    }

    @Override
    public Iterable values()
    {
        return this;
    }

    public Value top()
    {
        checkNotEmpty();
        return head.next.value;
    }

    public Iterator<Value> iterator()
    {
        return new StackIterator();
    }

    private class StackIterator
            implements Iterator<Value>
    {
        private Node<Value> cur = head.next;

        @Override
        public boolean hasNext()
        {
            return cur != null;
        }

        @Override
        public Value next()
        {
            if (!hasNext())
                throw new NoSuchElementException("no such element in Stack.Iterator");

            Value value = cur.value;
            cur = cur.next;
            return value;
        }
    }
}

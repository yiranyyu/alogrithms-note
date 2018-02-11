package Fundamentals.Bags_Queues_Stacks;

import Fundamentals.ContainerBase.LinearContainer;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Created by yirany on 2017/10/7.
 */

/**
 * Forward list container
 * */
public class List<Value> extends LinearContainer
    implements Iterable<Value>
{
    private Node<Value> head;// index is 0
    private Node<Value> last;// index is size

    public List()
    {
        super();
        head = new Node<>();
        head.next = last;
    }

    /**
     * Delete all Items in list that equals to {@code value}
     * @param value the value to delete
     */
    public void delete(Value value)
    {
        checkNotNull(value);
        checkNotEmpty();

        Node<Value> node = head;
        while (node.next != null)
        {
            if (value.equals(node.next.value))
            {
                node.next = node.next.next;
                --size;
            }
            else
                node = node.next;
        }
        if (value.equals(node.value))
            popBack();
    }

    public void push(Value value)
    {
        pushFront(value);
    }

    /**
     * Insert {@code value} at the head of the list
     * @param value is the element to be inserted (will be the first element of the new list)
     * */
    public void pushFront(Value value)
    {
        checkNotNull(value);

        Node<Value> newNode = new Node<>(value);
        newNode.next = head.next;
        head.next    = newNode;
        ++size;
    }

    /**
     * Insert {@code value} at the tail of the list
     * @param value is the element to be inserted (will be the last element)
     * */
    public void pushBack(Value value)
    {
        checkNotNull(value);

        Node<Value> newNode = new Node<>(value);
        if (last == null)
            head.next = newNode;
        else
            last.next    = newNode;
        last = newNode;
        ++size;
    }

    /**
     * @throws UnsupportedOperationException if list is empty
     * */
    public void popFront()
    {
        checkNotEmpty();

        head.next = head.next.next;
        --size;
    }

    /**
     * @throws UnsupportedOperationException if list is empty
     * */
    public void popBack()
    {
        checkNotEmpty();

        Node<Value> node = nodeAt(size -1);
        node.next = null;
        last = node;
        --size;
    }

    /**
     * @throws NoSuchElementException if the list empty
     * @return the first value of the list
     * */
    public Value front()
    {
        checkNotEmpty();

        return head.next.value;
    }

    /**
     * @throws NoSuchElementException if the list is empty
     * @return the last value of the list
     */
    public Value back()
    {
        checkNotEmpty();
        return last.value;
    }

    public boolean contains(Value value)
    {
        checkNotNull(value);
        if (isEmpty()) return false;
        Node node = head.next;
        while (node != null)
        {
            if (node.value.equals(value))
                return true;
            node = node.next;
        }

        return false;
    }

    @Override
    public Iterator<Value> iterator()
    {
        return new ListIterator();
    }

    /**
     * @param index should be in [0, size]
     * @return the first index RB_Node. if index is 0, return head
     * */
    private Node<Value> nodeAt(int index)
    {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_RANGE);

        Node<Value> cur = head;
        while (index-- > 0)
            cur = cur.next;
        return cur;
    }

    @Override
    public Iterable values()
    {
        return this;
    }

    private class ListIterator
            implements Iterator<Value>
    {
        Node<Value> cur = head.next;

        @Override
        public boolean hasNext()
        {
            return cur != null;
        }

        @Override
        public Value next()
        {
            assert (hasNext()) : "do not has nextChars element in List.iterator";

            Value rtn = cur.value;
            cur = cur.next;
            return rtn;
        }
    }
}

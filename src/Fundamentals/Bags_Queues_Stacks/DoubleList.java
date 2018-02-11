package Fundamentals.Bags_Queues_Stacks;

import Fundamentals.ContainerBase.LinearContainer;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Created by yirany on 2017/10/7.
 */
public class DoubleList<Value> extends LinearContainer<Value>
    implements Iterable<Value>
{
    private DoubleNode<Value> head;
    private DoubleNode<Value> tail;

    public DoubleList()
    {
        super();
        head = new DoubleNode<>();
        tail = new DoubleNode<>();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * @return the first element of the list
     * */
    public Value front()
    {
        checkNotEmpty();
        return head.next.value;
    }

    /**
     * @return the last element of the list
     * */
    public Value back()
    {
        checkNotEmpty();
        return tail.prev.value;
    }

    /**
     * Delete the first index element of the list
     * @param index is the pos of the element to delete, must in (0, size()]
     * @throws IndexOutOfBoundsException if the index <= 0 or index > size()
     * */
    private void delete(int index)
    {
        if (index <= 0 || index > size)
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_RANGE);

        DoubleNode<Value> node = nodeAt(index);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        --size;
    }

    /**
     * Insert {@code value} before the number {@code index} element

     * @deprecated easy to abuse, carefully read this document to assure you are not in the wrong way of calling
     * @param index must in (0, size()+1], represent the position {@code value} to push before
     * @throws IndexOutOfBoundsException if index out of range(0, size()+1]
     * */
    public void insertBefore(int index, Value value)
    {
        if (index <= 0 || index > size+1)
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_RANGE);

        DoubleNode<Value> node = nodeAt(index);
        DoubleNode<Value> newNode = new DoubleNode<>(value);

        node.prev.next = newNode;
        newNode.next = node;
        newNode.prev = node.prev;
        node.prev = newNode;
        ++size;
    }

    public void insertAfter(Value value, int index)
    {
        insertBefore(index+1, value);
    }

    public void pushFront(Value value)
    {
        insertBefore(1, value);
    }

    public void pushBack(Value value)
    {
        insertAfter(value, size);
    }

    public void popFront()
    {
        checkNotEmpty();
        delete(1);
    }

    public void popBack()
    {
        checkNotEmpty();
        delete(size);
    }

    /**
     * Return node in the list at index position
     *
     * @param index should be in range [0, size+1]
     * @return head if index is 0, tail if index is size+1,otherwise return the first index RB_Node
     * */
    private DoubleNode<Value> nodeAt(int index)
    {
        if  (index > size +1 || index < 0)
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_RANGE);

        DoubleNode<Value> cur = tail;
        if (index > size / 2)
        {
            int steps = size - index + 1;
            while (steps-- > 0)
                cur = cur.prev;
        }
        else
        {
            cur = head;
            while (index-- > 0)
                cur = cur.next;
        }
        return cur;
    }

    @Override
    public Iterable<Value> values()
    {
        return this;
    }

    @Override
    public Iterator<Value> iterator()
    {
        return new DoubleListIterator();
    }

    public Iterator<Value> reverseIterator()
    {
        return new DoubleListReverseIterator();
    }

    private class DoubleListIterator
            implements Iterator<Value>
    {
        DoubleNode<Value> cur = head.next;
        @Override
        public boolean hasNext()
        {
            return cur != tail;
        }

        @Override
        public Value next()
        {
            if  (!hasNext())
                throw new NoSuchElementException("do not has next element in " + this.getClass());

            Value value = cur.value;
            cur = cur.next;
            return value;
        }
    }

    private class DoubleListReverseIterator
            implements Iterator<Value>
    {
        DoubleNode<Value> cur = tail.prev;
        @Override
        public boolean hasNext()
        {
            return cur != head;
        }

        @Override
        public Value next()
        {
            if  (!hasNext())
                throw new NoSuchElementException("do not has next element in " + this.getClass());

            Value value = cur.value;
            cur = cur.prev;
            return value;
        }
    }
}

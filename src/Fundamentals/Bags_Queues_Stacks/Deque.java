package Fundamentals.Bags_Queues_Stacks;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Created by yirany on 2017/10/7.
 */
public class Deque<Value>
    implements Iterable<Value>
{
    private DoubleList<Value> content;

    public Deque()
    {
        content = new DoubleList<>();
    }

    public boolean isEmpty()
    {
        return content.isEmpty();
    }

    public int size()
    {
        return content.size();
    }

    public void pushFront(Value value)
    {
        content.pushFront(value);
    }

    public void pushBack(Value value)
    {
        content.pushBack(value);
    }

    /**
     * @throws NoSuchElementException if deque is empty
     * @return the most left element of the deque
     */
    public Value popFront()
    {
        Value value = content.front();
        content.popFront();
        return value;
    }

    /**
     * @throws NoSuchElementException if deque is empty
     * @return the most right element of the deque
     */
    public Value popBack()
    {
        Value value = content.back();
        content.popBack();
        return value;
    }

    @Override
    public Iterator<Value> iterator()
    {
        return content.iterator();
    }

    public String toString()
    {
        return content.toString();
    }
}

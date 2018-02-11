package Fundamentals.Bags_Queues_Stacks;

import java.util.Iterator;

/*
 * Created by yirany on 2017/10/7.
 */
public class Queue<Value>
    implements Iterable<Value>
{
    private DoubleList<Value> content;

    public Queue()
    {
        content = new DoubleList<>();
    }

    public void enqueue(Value value)
    {
        content.pushFront(value);
    }

    public Value dequeue()
    {
        Value value = content.back();
        content.popBack();
        return value;
    }

    public int size() {return content.size();}
    public boolean isEmpty(){return content.isEmpty();}

    @Override
    public Iterator<Value> iterator()
    {
        return content.reverseIterator();
    }

    @Override
    public String toString()
    {
        return content.toString();
    }

}

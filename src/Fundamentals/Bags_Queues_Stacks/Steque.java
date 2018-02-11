package Fundamentals.Bags_Queues_Stacks;

import java.util.Iterator;

/*
 * Created by yirany on 2017/10/7.
 */
public class Steque<Item>
    implements Iterable<Item>
{
    private List<Item> content;
    public Steque()
    {
        content = new List<>();
    }

    public int size()
    {
        return content.size();
    }

    public boolean isEmpty()
    {
        return content.isEmpty();
    }

    public void push(Item item)
    {
        content.pushFront(item);
    }

    public Item pop()
    {
        Item item = content.front();
        content.popFront();
        return item;
    }

    public void enqueue(Item item)
    {
        content.pushBack(item);
    }

    @Override
    public Iterator<Item> iterator()
    {
        return content.iterator();
    }

    @Override
    public String toString()
    {
        return content.toString();
    }
}

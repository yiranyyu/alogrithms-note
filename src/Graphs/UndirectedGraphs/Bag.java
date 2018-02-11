package Graphs.UndirectedGraphs;

import Fundamentals.Bags_Queues_Stacks.List;
import java.util.Iterator;
import static Stuff.myAssert.*;

public class Bag<Value>
    implements Iterable<Value>
{
    private List<Value> list;

    public Bag()
    {
        list = new List<>();
    }

    public void add(Value val)
    {
        assertNotNull(val);

        if (contains(val)) return;
        list.pushFront(val);
    }

    public void delete(Value val)
    {
        assertNotNull(val);

        list.delete(val);
    }


    public boolean contains(Value val)
    {
        assertNotNull(val);
        if (val == null) return false;

        for (Value v : list)
            if (val.equals(v))
                return true;
        return false;
    }

    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    public int size()
    {
        return list.size();
    }

    @Override
    public Iterator<Value> iterator()
    {
        return list.iterator();
    }
}

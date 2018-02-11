package Fundamentals.ContainerBase;

import java.util.NoSuchElementException;

/*
 * Created by yirany on 2017/10/8.
 */
abstract public class LinearContainer<Item>
{
    private static final String NULL_ITEM = "Can't add null to container";
    private static final String EMPTY = "Can't get element from empty Container";
    private static final String NEGATIVE_LENGTH = "Can't init with negative length";
    protected static final String INDEX_OUT_OF_RANGE = "index out of range";

    protected int size;
    public LinearContainer()
    {
        size = 0;
    }
    public boolean isEmpty()
    {
        return size == 0;
    }
    public int size()
    {
        return size;
    }

    abstract public Iterable<Item> values();

    @Override
    public String toString()
    {
        StringBuilder rtn = new StringBuilder("[");
        for (Item item : this.values())
        {
            rtn.append(item);
            rtn.append(", ");
        }
        if (!isEmpty())
        {
            int length = rtn.length();
            rtn.delete(length - 2, length);
        }
        rtn.append("]");
        return rtn.toString();
    }

    protected void checkNotEmpty()
    {
        if (isEmpty())
            throw new NoSuchElementException(EMPTY);
    }

    protected void checkNotNull(Object o)
    {
        if (o == null)
            throw new IllegalArgumentException(NULL_ITEM);
    }

    protected void checkNotNegative(int size)
    {
        if (size < 0)
            throw new NegativeArraySizeException(NEGATIVE_LENGTH);
    }
}

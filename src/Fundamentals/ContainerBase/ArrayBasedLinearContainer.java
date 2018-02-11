package Fundamentals.ContainerBase;
// Created by yirany on 2018/2/5

public abstract class ArrayBasedLinearContainer<Value> extends LinearContainer<Value>
{
    protected Value[] content;

    public ArrayBasedLinearContainer(int size)
    {
        content = getNewArray(size);
    }

    public ArrayBasedLinearContainer()
    {
        content = getNewArray(1);
    }

    protected Value[] getNewArray(int size)
    {
        checkNotNegative(size);
        return (Value[]) new Object[size];
    }

    protected void checkNewSize(int newSize)
    {
        checkNotNegative(newSize);
        if (newSize <= size)
            throw new IllegalArgumentException("try to trunk array in resize method");

    }

    protected void tryShrinkSize()
    {
        if (size > 1 && size < content.length/4)
            resize(content.length / 2);
    }

    protected void tryImproveCapacity()
    {
        if (isFull())
            resize(2*content.length);
    }

    private boolean isFull()
    {
        return size == content.length;
    }


    abstract protected void resize(int newSize);

}

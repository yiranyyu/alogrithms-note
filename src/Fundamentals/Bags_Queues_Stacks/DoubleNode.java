package Fundamentals.Bags_Queues_Stacks;

/*
 * Created by yirany on 2017/10/7.
 */
public class DoubleNode<Value>
{
    Value value;
    DoubleNode<Value> prev;
    DoubleNode<Value> next;

    DoubleNode(Value value)
    {
        this.value = value;
    }

    DoubleNode()
    {
        // do nothing
    }
}

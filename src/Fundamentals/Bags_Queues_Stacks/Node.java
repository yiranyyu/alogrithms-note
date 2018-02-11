package Fundamentals.Bags_Queues_Stacks;

/*
 * Created by yirany on 2017/10/7.
 */
public class Node<Value>
{
    public Node<Value> next;
    public Value value;

    Node(Value value)
    {
        this.value = value;
    }

    Node(){}
}

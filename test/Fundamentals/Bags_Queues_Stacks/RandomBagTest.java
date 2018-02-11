package Fundamentals.Bags_Queues_Stacks;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomBagTest
{
    private RandomBag<Integer> bag;

    @Test
    public void add() throws Exception
    {
        bag = new RandomBag<>();
        assertTrue(bag.isEmpty());
        assertTrue(bag.size() == 0);

        for (int i = 0; i < 10; ++i)
            bag.add(0);
        assertTrue(bag.size() == 10);
    }

    @Test
    public void iterator() throws Exception
    {
        bag = new RandomBag<>();
        for (int i = 0; i < 100; ++i)
            bag.add(i);
        for (int value : bag)
            System.out.printf("%4d", value);
    }

}
package Sortings.ElementarySorts;

import org.junit.Test;

public class QuickTest
{
    @Test
    public void sort() throws Exception
    {
        SortCompare.timeRandomInput("Quick", 1_000_000, 10);
    }

}
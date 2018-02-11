package Sortings.ElementarySorts;

import edu.princeton.cs.algs4.StdRandom;

public class TestTime
{
    public static void Test(String sortType, int length, int T)
    {
        double consume = 0;
        printInitMsg(length);
        for (int i = 0; i < T; ++i)
        {
            double newConsume = runSortAndReturnCost(sortType, length);
            printMsg(consume, newConsume, length);
            consume = newConsume;
            length *= 2;
        }
    }

    public static void Test(String sortType_1, String sortType_2, int length, int T)
    {
        double consume1 = 0;
        double consume2 = 0;
        printInitMsg(length);
        for (int i = 0; i < T; ++i)
        {
            assertArrayLength(length);

            double newConsume1 = runSortAndReturnCost(sortType_1, length);
            printMsg(consume1, newConsume1, length);
            consume1 = newConsume1;

            // Test sortType_2
            double newConsume2 = runSortAndReturnCost(sortType_2, length);
            printMsg(consume2, newConsume2, length);
            consume2 = newConsume2;

            System.out.printf(" %10.0f %10.0f\n", consume1, consume2);
            length *= 2;
        }
    }

    private static void assertArrayLength(int len)
    {
        if (len < 0)
        {
            System.out.println("length of array out of range");
            throw new NegativeArraySizeException();
        }
    }

    private static Double[] getRandomArray(int len)
    {
        Double[] a = new Double[len];
        for (int j = 0; j < len; ++j)
            a[j] = StdRandom.uniform();
        return a;
    }

    /**
     * @return time consume of analysis
     */
    private static double runSortAndReturnCost(String sortType, int length)
    {
        assertArrayLength(length);
        Double[] testData = getRandomArray(length);
        return  SortCompare.time(sortType, testData);
    }

    private static void printMsg(double oldConsume, double newConsume, int length)
    {
        if (oldConsume != 0 && newConsume != 0)
            System.out.printf("%10.5f, %10d\n",newConsume / oldConsume, length);
    }

    private static void printInitMsg(int length)
    {
        System.out.println("length is " + length);
    }

    public static void main(String[] argv)
    {
        String alg1 = "Quick";
        String alg2 = "Merge";
        Test(alg1, 32, 15);
//        Test(alg1, alg2, 32, 15);
//        StdDraw.pause(10000);
    }
}

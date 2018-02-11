package Sortings.ElementarySorts;

import Stuff.Stopwatch;
import edu.princeton.cs.algs4.StdRandom;

public class SortCompare
{
    /**
     * Return the time(mill seconds) it takes to sort <code>a</code>
     * @param alg type of sort to use
     * @param a array to be sorted
     * @return the time(mill seconds) it takes to sort<code>a</code> if <code>alg is correct</code>,
     *         otherwise return a small undefined time span
     */
    public static double time(String alg, Double[] a)
    {
        Stopwatch stopwatch = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Shell")) Shell.sort(a);
        if (alg.equals("Merge")) Merge.sort(a);
        if (alg.equals("MergeBottomUp")) MergeBottomUp.sort(a);
        if (alg.equals("Quick")) Quick.sort(a);
        return stopwatch.elapsedTime();
    }

    /**
     * Return the multiply it takes to sorted <code>size</code> size double array <code>T</code>
     * multiply using <code>alg</code> sort;
     * @param alg type of sort to be used
     * @param N length of array
     * @param T multiply of sort
     * @return total time consume of sorting size <code>size</code> double array <code>T</code>
     * multiply using <code>alg</code> sort;
     */
    public static double timeRandomInput(String alg, int N, int T)
    {
        double total = 0.0;
        Double[] a = new Double[N];
        while (T-- > 0)
        {
            for (int i = 0; i < N; ++i)
            {
                a[i] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args)
    {
        assert args.length == 4 : "four arguments required, but get " + args.length;

        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        System.out.printf("For %d random Doubles\n\t%s takes %f ms", N, alg1, t1);
        System.out.printf("\n\t%s takes %f ms. %f multiply of %s", alg2, t2, t2/t1, alg1);
    }
}

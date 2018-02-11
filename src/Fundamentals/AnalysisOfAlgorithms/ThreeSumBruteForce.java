package Fundamentals.AnalysisOfAlgorithms;

import Stuff.Rand;
import Stuff.Stopwatch;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/*
 * Created by yirany on 2017/10/15.
 */
public class ThreeSumBruteForce
{

    public static void analysis(int times)
    {
        int[] array = Rand.ints((int)(1.2 * 128), -128, 128);

        int x = 0;
        StdDraw.setXscale(0.0, 20.0);
        StdDraw.setYscale(0.0, 10000);
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(Color.RED);
        StdDraw.enableDoubleBuffering();

        while (true)
        {
            double total = 0;
            long cnt = 0;
            for (int i = 0; i < times; i++)
            {
                Stopwatch watch2 = new Stopwatch();
                cnt += count(array);
                total += watch2.elapsedTime();
            }
            total /= times;
            cnt /= times;

            System.out.printf("length : %-10d, found %-10d pairs, cost: %10.4f s\n", array.length, cnt, total/1000.0);
            StdDraw.point(x, total);
            StdDraw.show();

            x++;
            array = Rand.ints((int)(1.5 * array.length), -array.length, array.length);
        }

    }

    /**
     * Count the three-pair numbers that their sum is zero
     * @param array the array to find pairs in
     * */
    public static int count(int[] array)
    {
        return count(array, 0);
    }


    public static int count(int[] array, int sum)
    {
        int cnt = 0;
        for (int i = 0; i < array.length; i++)
        {
            long a = array[i];
            for (int j = i+1; j < array.length; j++)
            {
                long b = array[j];
                for (int k = j+1; k < array.length; k++)
                    if (a + b + array[k] == sum)
                        ++cnt;
            }
        }
        return cnt;
    }

    public static void main(String[] args)
    {
        analysis(2);
    }
}

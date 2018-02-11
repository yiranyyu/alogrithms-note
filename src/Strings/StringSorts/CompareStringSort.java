package Strings.StringSorts;
// Created by yirany on 2018/2/3

import Stuff.Alphabet;
import Stuff.ArrayFactor;
import Stuff.Stopwatch;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class CompareStringSort
{
    public static void main(String[] argv)
    {
        String[] strings = ArrayFactor.randomStrings(10000, Alphabet.BASE64);
        int T = 10000;
        System.out.println("start sort");

        Stopwatch watch = new Stopwatch();
        for (int i = 0; i < T; ++i)
        {
            watch.start();
            Arrays.sort(strings);
            watch.pause();

            StdRandom.shuffle(strings);
        }
        System.out.println("finish sort in " + watch.elapsedTime() / 1000.0 + " s");

        watch = new Stopwatch();
        for (int i = 0; i < T; ++i)
        {
            watch.start();
            Quick3string.sort(strings);
            watch.pause();

            StdRandom.shuffle(strings);
        }
        System.out.println("finish 3way sort in " + watch.elapsedTime() / 1000.0 + " s");
    }

}

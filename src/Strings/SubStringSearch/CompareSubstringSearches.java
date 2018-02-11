package Strings.SubStringSearch;
// Created by yirany on 2018/2/2

import Stuff.Alphabet;
import Stuff.Rand;
import Stuff.Stopwatch;

public class CompareSubstringSearches
{
    public static void main(String[] argv)
    {
        Alphabet alphabet = Alphabet.UNICODE16;
        String txt = Rand.randomString(alphabet, 1_00);
        String pattern = Rand.randomString(alphabet, 100);
        int T = 1;
        int times = 1;
        long t1 = 0;
        long t2 = 0;

        for (int j = 0; j < times; ++j)
        {
            Stopwatch watch = new Stopwatch();
            for (int i = 0; i < T; ++i)
                txt.indexOf(pattern);
            t1 += watch.elapsedNanoTime();

            System.out.println("start");
            watch = new Stopwatch();
            for (int i = 0; i < T; ++i)
                RabinKarp.search(txt, pattern);
            t2 += watch.elapsedNanoTime();

        }
        System.out.println(t1/1000_000_000.0 + " s");
        System.out.println(t2/1000_000_000.0 + " s");

    }
}

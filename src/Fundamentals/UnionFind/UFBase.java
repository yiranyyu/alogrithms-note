package Fundamentals.UnionFind;
// Created by yirany on 2018/1/29

import Stuff.Stopwatch;

import java.util.Scanner;

abstract public class UFBase
{
    // the root id of node p is id[p]
    protected int[] id;
    protected int numberOfComponents;

    abstract public int find(int p);
    abstract public void union(int p, int q);

    public int size()
    {
        return id.length;
    }

    public boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }

    public int getNumberOfComponents()
    {
        return numberOfComponents;
    }

    protected static void buildUFFromInputStream(Scanner in, UFBase uf)
    {
        int p = 0;
        int q = 0;

        while (in.hasNextInt())
        {
            p = in.nextInt();
            if (in.hasNextInt())
                q = in.nextInt();
            else
                break;

            if (!uf.connected(p, q))
                uf.union(p, q);
        }
    }

    public UFBase(int size)
    {
        if (size < 0)
            throw new NegativeArraySizeException("negative array length");

        numberOfComponents = size;
        id = new int[size];
        for (int i = 0; i < size; ++i)
            id[i] = i;
    }

    static int getLength(String[] args)
    {
        if (args.length > 0)
            return Integer.parseInt(args[0]);

        System.out.println("Enter a length");
        return new Scanner(System.in).nextInt();
    }

    private static void printTestResult(Stopwatch watch, UFBase uf)
    {
        System.out.println("use " + watch.elapsedTime() + "ms" + "\nFound " + uf.getNumberOfComponents() + " components" );
    }

    static void doTest(UFBase uf)
    {
        Scanner in = new Scanner(System.in);
        Stopwatch stopwatch = new Stopwatch();
        buildUFFromInputStream(in, uf);
        printTestResult(stopwatch, uf);
    }
}

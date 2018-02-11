package Graphs.ShortestPaths;
// Created by yirany on 2018/1/30

import java.util.Scanner;

public class Arbitrage
{
    public static void main(String[] argv)
    {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        EdgeWeightedDigraph<Integer, Double> system = new EdgeWeightedDigraph<>();
        String[] names = new String[size];
        for (int v = 0; v < size; ++v)
        {
            names[v] = in.next();
            for (int w = 0; w < size; ++w)
            {
                double rate = in.nextDouble();
                system.addEdge(new DirectedEdge<>(v, w, -Math.log(rate)));
            }
        }

        BellmanFordSP<Integer> spt = new BellmanFordSP<>(system, 0);
        if (spt.hasNegativeCycle())
        {
            double stake = 1000.0;
            for (DirectedEdge<Integer, Double> e : spt.negativeCycle())
            {
                System.out.printf("%10.5f %s", stake, names[e.from()]);

                stake *= Math.exp(-e.weight());
                System.out.printf("= %10.5f %s\n", stake, names[e.to()]);
            }
        }
        else
            System.out.println("No arbitrage opportunity.");
    }
}

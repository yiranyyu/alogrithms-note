package Fundamentals.UnionFind;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.Scanner;

/*
 * Created by yirany on 2017/10/28.
 */
public class TestConnection
{
    public static void main(String[] args)
    {
        int length = Integer.parseInt(args[0]);
        DynamicVisualImgOfQuickFind qf = new DynamicVisualImgOfQuickFind(length, Color.red, Color.black, true, false);
        DynamicVisualImgOfQuickUnion qu = new DynamicVisualImgOfQuickUnion(length, Color.green, Color.black, true, false);
        DynamicVisualImgOfWeightedQuickUnion wqu = new DynamicVisualImgOfWeightedQuickUnion(length, Color.red, Color.black, true, false);
        Scanner in = new Scanner(System.in);

        int p = 0;
        int q = 0;
        StdDraw.setXscale(0, length);
        StdDraw.setYscale(0, length);
        while (in.hasNextInt())
        {
            p = in.nextInt();
            if (in.hasNextInt())
                q = in.nextInt();
            else
                break;

            if (!wqu.connected(p, q))
                wqu.union(p, q);

            if (!qu.connected(p, q))
                qu.union(p, q);

            if (!qf.connected(p, q))
                qf.union(p, q);
        }

    }
}

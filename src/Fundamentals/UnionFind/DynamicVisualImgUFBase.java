package Fundamentals.UnionFind;
// Created by yirany on 2018/1/29

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

abstract public class DynamicVisualImgUFBase extends UFBase
{
    /**
     * {@code averageColor} is the color of the current average cost for union and other operation for one pair
     * {@code costColor} is the color of the costs of a specific union operation
     */
    private Color averageColor;
    private Color costColor;
    protected long totalAccess = 0;

    protected long unionCounts = 0;

    /**
     * {@code drawAverage} determines whether to draw the average cost for union
     * {@code drawCost} determines whether to drwa the cost for everty specific union operation
     */
    private boolean drawAverage = true;
    private boolean drawCost = true;

    public long getTotalAccess()
    {
        return totalAccess;
    }

    public DynamicVisualImgUFBase(
            int size, Color averageColor, Color costColor,
            boolean drawAverage, boolean drawCost)
    {
        super(size);

        this.averageColor = averageColor;
        this.costColor = costColor;
        this.drawAverage = drawAverage;
        this.drawCost = drawCost;
    }

    protected void draw(long oldAccess)
    {
        if (this.drawCost)
        {
            oldAccess = totalAccess - oldAccess;
            StdDraw.setPenColor(costColor);
            StdDraw.point(unionCounts, oldAccess);
        }
        if (this.drawAverage)
        {
            StdDraw.setPenColor(averageColor);
            StdDraw.point(unionCounts, (double)totalAccess / unionCounts);
        }
    }

    private static void initDrawingArguments(int length)
    {
        StdDraw.setPenRadius(0.02);
        StdDraw.setXscale(0, length);
        StdDraw.setYscale(0, length);
    }

    protected static void doTestAndDraw(String[] args, DynamicVisualImgUFBase uf)
    {
        initDrawingArguments(uf.size());
        doTest(uf);
        printAccess(uf);
    }

    protected void logUnion(long oldAccess)
    {
        --numberOfComponents;
        unionCounts++;
        draw(oldAccess);
    }

    private static void printAccess(DynamicVisualImgUFBase uf)
    {
        System.out.println("accessed " + uf.getTotalAccess() + " multiply");
    }
}

package Fundamentals.UnionFind;

import java.awt.*;

/*
 * Created by yirany on 2017/10/28.
 */
public class DynamicVisualImgOfWeightedQuickUnion
        extends DynamicVisualImgUFBase
{
    private int[] size;

    public DynamicVisualImgOfWeightedQuickUnion(int n)
    {
        this(n, Color.red, Color.black, true, true);
    }

    public DynamicVisualImgOfWeightedQuickUnion(int n, Color averageColor, Color costColor)
    {
        this(n, averageColor, costColor, true, true);
    }

    /**
     * @see DynamicVisualImgOfQuickFind#DynamicVisualImgOfQuickFind(int, Color, Color)
     * @param n the size of set
     * @param averageColor color of points representing the average costs for union and other operation
     * @param costColor color of points representing the costs of specific union operation
     * @param drawAverage determines whether draw the average costs or not
     * @param drawCost determines whether draw the costs of a specific operation or not
     */
    public DynamicVisualImgOfWeightedQuickUnion(
            int n, Color averageColor, Color costColor,
            boolean drawAverage, boolean drawCost)
    {
        super(n, averageColor, costColor, drawAverage, drawCost);

        this.size = new int[n];
        for (int i = 0;i < n; ++i)
            size[i] = 1;
    }

    public int find(int p)
    {
        while (p != id[p])
        {
            p = id[p];
            totalAccess++;
        }
        totalAccess++;
        return p;
    }

    public void union(int p, int q)
    {
        long cost = totalAccess;

        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;

        // add smaller component to another
        if (size[pRoot] < size[qRoot])
        {
            id[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        }
        else
        {
            id[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }

        totalAccess += 3;
        logUnion(cost);
    }

    public static void main(String[] args)
    {
        int length = getLength(args);
        doTestAndDraw(args, new DynamicVisualImgOfWeightedQuickUnion(length));
    }

}

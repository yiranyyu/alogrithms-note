package Fundamentals.UnionFind;

/*
 * Created by yirany on 2017/10/28.
 */

import java.awt.*;

/**
 * @see DynamicVisualImgOfQuickFind improve the speed of union operation. Logic are almost same
 */
public class DynamicVisualImgOfQuickUnion
        extends DynamicVisualImgUFBase
{
    public DynamicVisualImgOfQuickUnion(int n)
    {
        this(n, Color.red, Color.black, true, true);
    }

    public DynamicVisualImgOfQuickUnion(int n, Color averageColor, Color costColor)
    {
        this(n, averageColor, costColor, true, true);
    }

    /**
     * @param drawAverage determines whether draw the average costs or not
     * @param drawCost determines whether draw the costs of a specific operation or not
     */
    public DynamicVisualImgOfQuickUnion(
            int n, Color averageColor, Color costColor,
            boolean drawAverage, boolean drawCost)
    {
        super(n, averageColor, costColor, drawAverage, drawCost);
    }

    /**
     * @return the id of the root of p
     */
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
        if (pRoot == qRoot) return;

        // add the component of p to the component of q
        id[pRoot] = qRoot;

        totalAccess++;
        logUnion(cost);
    }

    public static void main(String[] args)
    {
        int length = getLength(args);
        doTestAndDraw(args, new DynamicVisualImgOfQuickUnion(length));
    }
}

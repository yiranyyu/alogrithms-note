package Fundamentals.UnionFind;

import java.awt.*;
/*
 * Created by yirany on 2017/10/27.
 */

/**
 * @apiNote this class is for testing usage
 * @see QuickFind if you have to use the function of this class
 */
public class DynamicVisualImgOfQuickFind
        extends DynamicVisualImgUFBase
{
    public DynamicVisualImgOfQuickFind(int n)
    {
        this(n, Color.red, Color.black, true, true);
    }

    public DynamicVisualImgOfQuickFind(int n, Color averageColor, Color costColor)
    {
        this(n, averageColor, costColor, true, true);
    }

    /**
     * @param averageColor color of points representing the average costs for union and other operation
     * @param costColor color of points representing the costs of specific union operation
     */
    public DynamicVisualImgOfQuickFind(
            int size, Color averageColor, Color costColor,
            boolean drawAverage, boolean drawCost)
    {
        super(size, averageColor, costColor, drawAverage, drawCost);
    }

    /**
     * @return the root id of node p
     */
    public int find(int p)
    {
        totalAccess++;
        return id[p];
    }

    /**
     * Connect {@code p} and {@code q} if they are in different components by add all the nodes
     * of component of p the the component of q
     * @param p one node
     * @param q another node
     */
    public void union(int p, int q)
    {
        long oldAccess = totalAccess;

        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;

        for (int i = 0; i < id.length; ++i)
        {
            if (id[i] == pRoot)
            {
                id[i] = qRoot;
                totalAccess += 2;
            }
        }

        totalAccess++;
        logUnion(oldAccess);
    }

    /**
     * Read a file of Integers to analysis DynamicVisualImgOfQuickFind
     * @param args args[0] should be the size of set to analysis
     */
    public static void main(String[] args)throws Exception
    {
        int length = getLength(args);
        doTestAndDraw(args, new DynamicVisualImgOfQuickFind(length));
    }
}

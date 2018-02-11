package Fundamentals.UnionFind;

/*
 * Created by yirany on 2017/10/29.
 */
public class WeightedQuickUnion extends UFBase
{
    private int[] sizes;

    public WeightedQuickUnion(int size)
    {
        super(size);
        sizes = new int[size];
        for (int i = 0; i < size; ++i)
            sizes[i] = 1;
    }

    public int find(int p)
    {
        while (p != id[p])
            p = id[p];
        return p;
    }

    public void union(int p, int q)
    {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;

        // add smaller component to another
        if (sizes[pRoot] < sizes[qRoot])
        {
            id[pRoot] = qRoot;
            sizes[qRoot] += sizes[pRoot];
        }
        else
        {
            id[qRoot] = pRoot;
            sizes[pRoot] += sizes[qRoot];
        }
        --numberOfComponents;
    }

    public static void main(String[] args)
    {
        int length = getLength(args);
        doTest(new WeightedQuickUnion(length));
    }

}

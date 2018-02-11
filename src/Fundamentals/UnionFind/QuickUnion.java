package Fundamentals.UnionFind;

/*
 * Created by yirany on 2017/10/28.
 */
public class QuickUnion extends UFBase
{
    public QuickUnion(int size)
    {
        super(size);
    }

    /**
     * @param p node to find root of
     * @return the id of the root of p
     */
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

        // add the component of p to the component of q
        id[pRoot] = qRoot;
        --numberOfComponents;
    }

    public static void main(String[] args)
    {
        int length = getLength(args);
        doTest(new QuickUnion(length));
    }
}

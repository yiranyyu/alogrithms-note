package Fundamentals.UnionFind;

/*
 * Created by yirany on 2017/10/27.
 */
public class QuickFind extends UFBase
{
    public QuickFind(int size)
    {
        super(size);
    }

    /**
     * @param p the node to find root of
     * @return the root id of node p
     */
    public int find(int p)
    {
        return id[p];
    }

    /**
     * @deprecated cost linear time
     * Connect {@code p} and {@code q} if they are in different components by add all the nodes
     * of component of p the the component of q
     */
    public void union(int p, int q)
    {
        if (connected(p, q)) return;

        int pRoot = find(p);
        int qRoot = find(q);

        for (int i = 0; i < id.length; ++i)
        {
            if (id[i] == pRoot)
            {
                id[i] = qRoot; // merge tree of pRoot to qRoot
            }
        }
        --numberOfComponents;
    }

    public static void main(String[] args)
    {
        int length = getLength(args);
        doTest(new QuickFind(length));
    }
}

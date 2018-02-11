package Fundamentals.UnionFind;
// Created by yirany on 2018/1/29

public class UnionFind
{
    WeightedQuickUnion uf;
    public UnionFind(int size)
    {
        uf = new WeightedQuickUnion(size);
    }

    public int size()
    {
        return uf.size();
    }

    public boolean connected(int p, int q)
    {
        return uf.connected(p, q);
    }

    public void union(int p, int q)
    {
        uf.union(p, q);
    }

    public int find(int p)
    {
        return uf.find(p);
    }
}

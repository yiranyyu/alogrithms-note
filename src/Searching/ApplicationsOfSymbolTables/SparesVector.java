package Searching.ApplicationsOfSymbolTables;

import Searching.HashTables.LinearProbingHashST;

public class SparesVector
{
    private LinearProbingHashST<Integer, Double> st;
    public SparesVector()
    {
        st = new LinearProbingHashST<>();
    }

    public int size()
    {
        return st.size();
    }

    public void put(int pos, double val)
    {
        st.put(pos, val);
    }

    public double get(int i)
    {
        if (!st.contains(i)) return 0.0;    // for values not recorded
        else                 return st.get(i);
    }

    /**
     * Calculate inner-product for this and {@code that}
     * @return inner-product
     */
    public double dot(double[] that)
    {
        double sum = 0.0;
        for (int i : st.keys()) sum += that[i] * this.get(i);
        return sum;
    }

    public double dot(SparesVector that)
    {
        double sum = 0.0;
        for (int i : st.keys())
        {
            sum += this.get(i) * that.get(i);
        }
        return sum;
    }
}

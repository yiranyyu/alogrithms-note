package Context.SuffixArrays;
// Created by yirany on 2018/2/6

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

/**
 * key word in context
 */
public class KWIC
{
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        int context = Integer.parseInt(args[1]);

        String text = in.readAll().replace("\\s+", " ");
        int N = text.length();
        SuffixArray suffixes = new SuffixArray(text);

        while (StdIn.hasNextLine())
        {
            String query = StdIn.readLine();
            for (int i = suffixes.rank(query); i < N && suffixes.select(i).startsWith(query); ++i)
            {
                int from = Math.max(0, suffixes.index(i) - context);
                int to = Math.min(N-1, from + query.length() + 2 * context);
                System.out.println(text.substring(from, to));
            }
            System.out.println();
        }
    }
}

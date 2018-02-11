package Stuff;

import edu.princeton.cs.algs4.In;

import java.io.File;
import java.io.OutputStream;

public class ParseAnswer
{
    public static boolean isNumber(char c)
    {
        return (c >= '0' && c <= '9');
    }
    public static boolean isABCD(char c)
    {
        return (c >= 'A' && c <= 'D');
    }
    public static void main(String[] argv)
    {
        File file = new File("G:\\Code\\Algorithms note\\Code\\src\\ans.csv");
        In in = new In(file);
        String[] Ans = new String[10000];
        int index = 0;
        while (!in.isEmpty())
        {
            String line = in.readLine();

            String[] words = line.split("[,]");

            for (String str : words)
            {
                if (str.length() > 0
                        && (isNumber(str.charAt(0))
                            || isABCD(str.charAt(0))))
                {
                    Ans[index++] = str;
                }
            }
        }

        int cnt = 0;
        for (int i = 380; i < 590 && Ans[i] != null; ++i, ++cnt)
        {
            if (cnt % 2 == 0 ) System.out.print("||");
            if (cnt % 10 == 0) System.out.println();
            if (cnt % 50 == 0) System.out.println();
            System.out.printf("%4s ", Ans[i]);
        }
    }
}

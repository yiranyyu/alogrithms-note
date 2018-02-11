package Context.SuffixArrays;
// Created by yirany on 2018/2/6

public class LongestRepeatedSubstring
{
    // Do not instantiate
    private LongestRepeatedSubstring(){}

    public static String lrs(String text)
    {
        int N = text.length();
        SuffixArray suffixes = new SuffixArray(text);
        String lrs = "";
        for (int i = 1; i < N; ++i)
        {
            int length = suffixes.lcp(i);
            if (length > lrs.length())
                lrs = suffixes.select(i).substring(0, length);
        }
        return lrs;
    }

    public static void main(String[] args)
    {
        System.out.println(lrs("aacaagtttacaagc"));
    }
}

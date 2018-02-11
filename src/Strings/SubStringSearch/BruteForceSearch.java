package Strings.SubStringSearch;
// Created by yirany on 2018/2/2

public class BruteForceSearch extends SubstringSearch
{
    public static int search(String txt, String pattern)
    {
        int txtLength = txt.length();
        int patternLength = pattern.length();
        for (int i = 0; i <= txtLength - patternLength; ++i)
        {
            int j = 0;
            for (; j < patternLength; ++j)
                if (txt.charAt(i+j) != pattern.charAt(j))
                    break;

            if (j == patternLength)
                return i;
        }
        return txtLength;
    }

    public static void main(String[] argv)
    {
        String txt = "123456";
        String pat = "234";
        int index = search(txt, pat);
        System.out.println(txt.substring(index, index + pat.length()).equals(pat));
    }
}

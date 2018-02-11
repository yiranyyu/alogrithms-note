package Strings.SubStringSearch;
// Created by yirany on 2018/2/2

public class BruteForceSearchWithExplicitBackup extends SubstringSearch
{
    public static int search(String txt, String pattern)
    {
        int indexInTxt = 0, indexInPattern = 0;
        int patternLength = pattern.length();
        int txtLength = txt.length();
        for (; indexInTxt < txtLength && indexInPattern < patternLength; ++indexInTxt)
        {
            if (txt.charAt(indexInTxt) == pattern.charAt(indexInPattern))
                ++indexInPattern;
            else
            {
                indexInTxt -= indexInPattern;
                indexInPattern = 0;
            }
        }
        if (indexInPattern == patternLength)
            return indexInTxt - patternLength;
        else
            return txtLength;
    }
}

package Stuff;
// Created by yirany on 2018/1/31

import Fundamentals.Bags_Queues_Stacks.List;

import java.io.*;

public class FileAnalysis
{
    private static String[] selectPatterns;
    private static String[] ignorePatterns;


    public static void main(String[] argv)
    {
        String DirectoryName = "G:\\Code\\Algorithms note\\Code";

        List<String> patterns = new List<>();
        patterns.push(".java");
        setSelectPatterns(patterns);

        List<String> ignorePatterns = new List<>();
        ignorePatterns.push("algs");
        ignorePatterns.push("Scheme");
        ignorePatterns.push("CMake");
        setIgnorePatterns(ignorePatterns);

        CounterData data = countAllFiles(new File(DirectoryName), 0);
        System.out.println("total : " + data);
    }

    private static void setSelectPatterns(List<String> patterns)
    {
        selectPatterns = new String[patterns.size()];

        int index = 0;
        for (String pattern : patterns)
            selectPatterns[index++] = pattern;
    }

    private static void setIgnorePatterns(List<String> patterns)
    {
        ignorePatterns = new String[patterns.size()];

        int index = 0;
        for (String pattern : patterns)
            ignorePatterns[index++] = pattern;
    }

    private static CounterData sumOf(CounterData d1, CounterData d2)
    {
        return new CounterData(d1.lines + d2.lines, d1.chars + d2.chars, d1.words + d2.words);
    }

    private static CounterData countAllFiles(File directory, int level)
    {
        File[] files = directory.listFiles();
        assert files != null : "path error";

        CounterData count = new CounterData();
        for (File file : files)
        {
            if (file.isDirectory())
                count = sumOf(count, countAllFiles(file, level+1));
            else if (needToCount(file))
                count = sumOf(count, countFile(file, level));
        }
        return count;
    }

    private static boolean needToCount(File file)
    {
        String path = file.getPath();
        for (String pattern : ignorePatterns)
        {
            if (path.contains(pattern))
                return false;
        }

        // no constraints
        if (selectPatterns.length == 0)
            return true;

        String fileName = file.getName();
        for (String pattern : selectPatterns)
        {
            if (!fileName.contains(pattern)) continue;

            String postfix = fileName.substring(fileName.indexOf(pattern));
            if (postfix.equals(pattern))
                return true;
        }
        return false;
    }

    private static CounterData countFile(File file, int level)
    {
        CounterData dta = new CounterData();
        try
        {
            Reader reader = new InputStreamReader(new FileInputStream(file));

            int input = 0;
            int prev = 0;
            while ((input = reader.read()) != -1)
            {
                ++dta.chars;
                if      (input == ' ' && prev != ' ')  ++dta.words;
                else if (input == '\n') ++dta.lines;
                prev = input;
            }
            printMsg(file, dta, level);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return dta;
    }

    private static void printMsg(File file, CounterData data, int level)
    {
        String fileName = file.getName();

        for (int i = 0; i < level; ++i)
            System.out.print("  ");
        System.out.printf("%-50s %s\n", fileName, "contains " + data);
    }

    static class CounterData
        implements Comparable<CounterData>
    {
        int lines;
        int chars;
        int words;

        CounterData(int lines, int chars, int words)
        {
            this.lines = lines;
            this.chars = chars;
            this.words = words;
        }

        CounterData() {}

        @Override
        public String toString()
        {
            return "{ "
                    + lines + " lines, " +
                    chars + " chars, " +
                    words + " words" +
                    "}";
        }

        @Override
        public int compareTo(CounterData that)
        {
            if (this.lines > that.lines) return 1;
            if (this.lines < that.lines) return -1;

            if (this.words > that.words) return 1;
            if (this.words < that.words) return -1;

            if (this.chars > that.chars) return 1;
            if (this.chars < that.chars) return -1;

            return 0;
        }
    }
}

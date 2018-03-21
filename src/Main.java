import Fundamentals.Bags_Queues_Stacks.List;
import Searching.HashTables.LinearProbingHashST;
import Stuff.Alphabet;
import Strings.RegularExpression.NFA;
import Stuff.ArrayFactor;
import Stuff.FileAnalysis;
import edu.princeton.cs.algs4.In;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    private static String[] selectPatterns;
    private static String[] ignorePatterns;

    public static void main(String[] argv)
    {
        String DirectoryName = "C:\\Users\\微星\\.vscode\\extensions\\James-Yu.latex-workshop-3.14.0";
        countAllFiles(new File(DirectoryName));
    }

    private static void countAllFiles(File directory)
    {
        File[] files = directory.listFiles();
        if (files == null)
            throw new RuntimeException("path error");

        for (File file : files)
        {
            if (file.isDirectory())
                countAllFiles(file);
            else
                countFile(file);
        }
    }
    private static void countFile(File file)
    {
        try
        {
            Reader reader = new InputStreamReader(new FileInputStream(file));
            Scanner in = new Scanner(reader);

            while (in.hasNextLine())
            {
                String line = in.nextLine();
                if (line.contains("child_process") && !line.contains("require('child_process'") && !line.contains("require(\"child_process\")"))
                   printMsg(file);

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void printMsg(File file)
    {
        System.out.println(file.getAbsolutePath());
    }
}


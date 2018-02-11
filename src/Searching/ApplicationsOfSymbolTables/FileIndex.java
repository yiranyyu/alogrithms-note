package Searching.ApplicationsOfSymbolTables;

import Searching.BalancedSearchTrees.RedBlackBST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import java.io.File;

public class FileIndex
{
    private static RedBlackBST<String, LinearSet<File>> st;
    private static RedBlackBST<String, File> fileSt;
    private static String[] fileNames;

    public static void main(String[] argv)
    {
        fileNames = argv;
        init();
        printGuideMsg();
        readAndProcessRequest();
    }

    private static void init()
    {
        st      = new RedBlackBST<>();
        fileSt  = new RedBlackBST<>();
        for (String filename : fileNames)
            readFile(new File(filename));
    }

    private static void printGuideMsg()
    {
        System.out.println("Total: \n File  : " + fileSt.size() + "\n Words : " + st.size());
        System.out.println("Continue:");
    }

    private static void readAndProcessRequest()
    {
        while (!StdIn.isEmpty())
        {
            String query = StdIn.readString();
            if (st.contains(query))
            {
                for (File file : st.get(query))
                    System.out.println(" " + file.getName());
            }
            else
            {
                if (fileSt.contains(query))
                    System.out.println(" Found file " + fileSt.get(query).getName());
                else
                    System.out.println(" -NOT FOUND");
            }
        }
    }

    private static void readFile(File file)
    {
        if (file.isDirectory())
            readDirectory(file);
        else
            doReadImp(file);
    }

    private static void readDirectory(File dir)
    {
        System.out.println("Processing Directory " + dir.getAbsolutePath());
        File[] files = dir.listFiles();
        try
        {
            for (File file : files)
                readFile(file);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Read content of one file(not directory), store pairs of [word, file] to {@code st} and [filename, file]
     * to {@code fileSt}
     * @param file to read content from
     */
    private static void doReadImp(File file)
    {
        In in = new In(file);
        if (!fileSt.contains(file.getName())) fileSt.put(file.getName(), file);
        while (!in.isEmpty())
        {
            String[] words = in.readString().split("[(){}\\[\\]<>,!&^%$*.~@'\";:+\\-=/#]");

            for (String word : words)
            {
                if (!st.contains(word)) st.put(word, new LinearSet<>());
                LinearSet<File> set = st.get(word);
                set.add(file);
            }
        }
    }
}

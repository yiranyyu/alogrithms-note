package Searching.ApplicationsOfSymbolTables;

import Searching.BalancedSearchTrees.RedBlackBST;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;

public class FileIndex
{
    private static RedBlackBST<String, Bag<File>> st;
    private static RedBlackBST<String, File> fileSt;
    private static String[] fileNames;

    public static void main(String[] argv)
    {
        fileNames = new String[1];
        fileNames[0] = "C:\\mingw64\\lib\\gcc\\x86_64-w64-mingw32\\7.1.0\\include\\c++";
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
        if (files == null)
            throw new DirectoryIteratorException(new IOException("cannot list file of " + dir));
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
        if (!fileSt.contains(file.getName()))
            fileSt.put(file.getName(), file);
        while (!in.isEmpty())
        {
            String[] words = in.readString().split("[(){} \\[\\]<>,!&^%$*.~@'\";:+\\-=/#]");

            for (String word : words)
            {
                if (!st.contains(word))
                    st.put(word, new Bag<>());
                st.get(word).add(file);
            }
        }
    }
}

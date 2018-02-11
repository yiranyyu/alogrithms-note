package Strings.DateCompression;
// Created by yirany on 2018/2/4

import edu.princeton.cs.algs4.StdOut;

import java.io.FileInputStream;
import java.io.IOException;

public class HexDump
{
    // Do not instantiate
    private HexDump(){}

    public static void main(String[] args)
    {
        FileInputStream in;
        try
        {
            in = new FileInputStream("G:\\Code\\Algorithms note\\Code\\src\\Strings\\DateCompression\\tinyCompress");
            System.setIn(in);
        }catch (IOException e){e.printStackTrace();}

        int bytesPerLine = 16;
        if (args.length == 1)
            bytesPerLine = Integer.parseInt(args[0]);

        int i;
        for (i = 0; !BinaryStdIn.isEmpty(); ++i)
        {
            if (bytesPerLine == 0)
            {
                BinaryStdIn.readChar();
                continue;
            }

            if (i == 0)
                System.out.print("");
            if (i % bytesPerLine == 0)
                System.out.println();
            else
                System.out.print(" ");

            char input = BinaryStdIn.readByte();
            System.out.printf("%02X", input & 0xff);
        }
        if (bytesPerLine != 0)
            System.out.println();
        System.out.println((8*i) + " bits");
    }
}

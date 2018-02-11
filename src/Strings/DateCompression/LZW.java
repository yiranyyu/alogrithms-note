package Strings.DateCompression;
// Created by yirany on 2018/2/5

import Strings.Tries.TST;

public class LZW extends DataCompressionBase
{
    private static final int RADIX = 256;
    private static final int EOF = RADIX;
    private static final int WIDTH = 12;// width of one output character in bits
    private static final int SIZE = (1 << WIDTH);  // 2^12

    // Do not instantiate out of package
    LZW(){}

    public static void compress()
    {
        String input = BinaryStdIn.readString();
        TST<Integer> tries = new TST<>();

        for (int i = 0; i < RADIX; ++i)
            tries.put("" + (char)i, i);

        int nextCode = RADIX + 1;
        while (input.length() > 0)
        {
            String prefix = tries.longestPrefixOf(input); // find longest prefix
            BinaryStdOut.write(tries.get(prefix), WIDTH);

            int t = prefix.length();
            if (t < input.length() && nextCode < SIZE)
                tries.put(input.substring(0, t+1), nextCode++); // update code table

            input = input.substring(t); // read prefix from input stream
        }

        BinaryStdOut.write(EOF, WIDTH);
        BinaryStdOut.close();
        BinaryStdIn.close();
    }

    public static void expand()
    {
        String[] st = new String[SIZE];

        int nextCode;
        for (nextCode = 0; nextCode < RADIX; ++nextCode)
            st[nextCode] = "" + (char)nextCode;
        st[nextCode++] = " "; // EOF

        int codeword = BinaryStdIn.readInt(WIDTH);
        String value = st[codeword];
        while (true)
        {
            BinaryStdOut.write(value);
            codeword = BinaryStdIn.readInt(WIDTH);// 读取包含前瞻字符的下一个codeword
            if (codeword == EOF)
                break;
            String s = st[codeword];
            if (codeword == nextCode)
                s = value + value.charAt(0);// if the code to compile is not in the table, build this entry

            if (nextCode < SIZE)
                st[nextCode++] = value + s.charAt(0);// add new entry to code table
            value = s;
        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    @Override
    public void doCompress()
    {
        compress();
    }

    @Override
    public void doExpand()
    {
        expand();
    }

    public static void main(String[] args)
    {
        new LZW().test(
                "G:\\Code\\Algorithms note\\Code\\src\\Strings\\DateCompression\\img.png",
                "G:\\Code\\Algorithms note\\Code\\src\\Strings\\DateCompression\\expand.png");
    }
}

package Strings.DateCompression;
// Created by yirany on 2018/2/4

public class BinaryDump
{
    // Do not instantiate
    private BinaryDump(){}

    public static void main(String[] argv)
    {
        int width = Integer.parseInt(argv[0]);
        int cnt;
        for (cnt = 0; !BinaryStdIn.isEmpty(); ++cnt)
        {
            if (width == 0) continue;

            if (cnt != 0 && cnt % width == 0)
                System.out.println();
            if (BinaryStdIn.readBit())
                System.out.print("1");
            else
                System.out.print("0");
        }
        System.out.printf("\n%d %s", cnt, "bits");
    }
}

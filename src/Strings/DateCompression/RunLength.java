package Strings.DateCompression;
// Created by yirany on 2018/2/4

public class RunLength extends DataCompressionBase
{
    private static final boolean ZERO = false;
    private static final boolean ONE = true;

    // do not instantiate out of package
    RunLength(){}

    public static void expand()
    {
        boolean bit = ZERO;
        while (!BinaryStdIn.isEmpty())
        {
            char cnt = BinaryStdIn.readByte();// read 8-bits char(value in range[0, 255]
            for (char i = 0; i < cnt; ++i)
                BinaryStdOut.writeBit(bit);
            bit = !bit;
        }
        BinaryStdOut.close();
        BinaryStdIn.close();
    }

    public static void compress()
    {
        char cnt = 0;
        boolean bit;
        boolean oldBit = ZERO;
        while (!BinaryStdIn.isEmpty())
        {
            bit = BinaryStdIn.readBit();
            if (bit != oldBit)
            {
                BinaryStdOut.writeByte(cnt);
                cnt = 0;
                oldBit = !oldBit;
            }
            else
            {
                if (cnt == 255)
                {
                    BinaryStdOut.writeByte(cnt);
                    cnt = 0;
                    BinaryStdOut.writeByte(cnt);
                }
            }
            ++cnt;
        }
        BinaryStdOut.write(cnt);
        BinaryStdOut.close();
        BinaryStdIn.close();
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
    }
}

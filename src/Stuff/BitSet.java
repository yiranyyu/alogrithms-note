package Stuff;
// Created by yirany on 2018/2/4

import oracle.jrockit.jfr.events.Bits;

import java.util.Arrays;

public class BitSet
{
    //index :0 1 2 3 4 5 6 7 8 9
    //       0 0 0 0 1 1 1 0 1 1
    //     MSB                 LSB
    private boolean[] bits;

    public BitSet(byte value)
    {
        bits = new boolean[8];
        fillBits(value);
    }

    public BitSet(char value)
    {
        bits = new boolean[16];
        fillBits(value);
    }

    public BitSet(short value)
    {
        bits = new boolean[16];
        fillBits(value);
    }

    public BitSet(int value)
    {
        bits = new boolean[32];
        fillBits(value);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < bits.length; ++i)
        {
            if (i % 8 == 0 && i != 0)
                builder.append(' ');
            if (bits[i]) builder.append('1');
            else         builder.append('0');
        }
        return builder.toString();
    }

    private void fillBits(long value)
    {
        int length = bits.length;
        for (int i = 0; i < length; ++i)
            if (((value >>> (length - i - 1)) & 1) == 1)
                bits[i] = true;
    }

    public static void main(String[] args)
    {
        for (int i = Byte.MIN_VALUE; i < Byte.MAX_VALUE; ++i)
        {
            byte b = (byte)i;
            char c = (char)b;
            if (!new BitSet(b).toString().equals(new BitSet(c).toString().substring(9)))
            {
                System.out.println(new BitSet(b));
                System.out.println(new BitSet(c));
                throw new RuntimeException();
            }
        }
    }
}

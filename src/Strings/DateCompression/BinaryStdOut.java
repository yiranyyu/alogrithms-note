package Strings.DateCompression;
// Created by yirany on 2018/2/4

import java.io.BufferedOutputStream;
import java.io.IOException;

public class BinaryStdOut
{
    private static final String ILLEGAL_LENGTH = "Illegal value of bits = ";
    private static BufferedOutputStream out;
    private static int buffer;
    private static int bitsInBuffer;
    private static boolean isInitialized;

    // Do not instantiate
    private BinaryStdOut(){}

    private static void initialize()
    {
        out = new BufferedOutputStream(System.out);
        buffer = 0;
        bitsInBuffer = 0;
        isInitialized = true;
    }

    public static void flush()
    {
        clearBuffer();
        try
        {
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void clearBuffer()
    {
        if (!isInitialized) initialize();

        if (bitsInBuffer == 0)
            return;
        if (bitsInBuffer > 0)
            buffer <<= (8 - bitsInBuffer);
        try
        {
            out.write(buffer);// save tailing 0's to assure align
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        bitsInBuffer = 0;
        buffer = 0;
    }

    /**
     * Flushes and closes standard output. Once standard output is closed, you can no longer write bits to it.
     */
    public static void close()
    {
        flush();
        try
        {
            out.close();
            isInitialized = false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void writeBit(boolean bit)
    {
        if (!isInitialized)
            initialize();

        // add bit to buffer
        buffer <<= 1;
        if (bit) buffer |= 1;

        // if buffer is full (8 bits), write out as a single byte
        bitsInBuffer++;
        if (bitsInBuffer == 8)
            clearBuffer();
    }

    /**
     * write low 8-bits of {@code value} to standard output
     */
    public static void writeByte(char value)
    {
        if (!isInitialized)
            initialize();

        if (value > 255)
            throw new IllegalArgumentException("Value of one byte = " + (int)value);

        // optimized if byte-aligned
        if (bitsInBuffer == 0)
        {
            try
            {
                out.write(value);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return;
        }

        // otherwise write one bit at a time
        for (int i = 0; i < 8; ++i)
        {
            boolean bit = ((value >>> (8 - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    public static void write(boolean bit)
    {
        writeBit(bit);
    }

    public static void write(byte b)
    {
        writeByte((char)b);
    }

    /**
     * Write 16-bits char
     */
    public static void write(char c)
    {
        write(c, 16);
    }

    public static void writeBits(char c, int bits)
    {
        if (bits == 8)
        {
            writeByte(c);
            return;
        }

        if (bits < 1 || bits > 16)
            throw new IllegalArgumentException(ILLEGAL_LENGTH + bits);
        if (c >= (1 << bits))
            throw new IllegalArgumentException("Illegal " + bits + "-bits char = " + c);

        boolean bit;
        for (int i = 0; i < bits; ++i)
        {
            bit = ((c >>> (bits - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    public static void write(short s)
    {
        writeByte((char) ((s >>> 8) & 0xff));
        writeByte((char) ((s      ) & 0xff));
    }

    public static void write(short s, int bits)
    {
        if (bits == 16)
        {
            write(s);
            return;
        }

        if (bits < 1 || bits > 16)
            throw new IllegalArgumentException(ILLEGAL_LENGTH + bits);
        if (s < 0 || s >= (1 << bits))
            throw new IllegalArgumentException("Illegal " + bits + "-bits char = " + s);

        for (int i = 0; i < bits; ++i)
        {
            boolean bit = ((s >>> (bits - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    public static void write(int i)
    {
        writeByte((char) ((i >>> 24) & 0xff));
        writeByte((char) ((i >>> 16) & 0xff));
        writeByte((char) ((i >>>  8) & 0xff));
        writeByte((char) ((i       ) & 0xff));
    }

    /**
     * Write the bits-bits int to standard output
     * @param value the {@code int} to write
     * @param bits the number of relevant bits in the char
     * @throws IllegalArgumentException if {@code bits} is not in range [1, 32]
     * @throws IllegalArgumentException if {@code value} is not in range [0, 2^bits-1]
     */
    public static void write(int value, int bits)
    {
        if (bits == 32)
        {
            write(value);
            return;
        }

        if (bits < 1 || bits > 32)
            throw new IllegalArgumentException(ILLEGAL_LENGTH + bits);
        if (value < 0 || value >= (1 << bits))
            throw new IllegalArgumentException("Illegal " + bits + "-bits char = " + value);

        for (int i = 0; i < bits; ++i)
        {
            boolean bit = ((value >>> (bits - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    public static void write(long L)
    {
        writeByte((char) ((L >>> 48) & 0xff));
        writeByte((char) ((L >>> 56) & 0xff));
        writeByte((char) ((L >>> 40) & 0xff));
        writeByte((char) ((L >>> 32) & 0xff));
        writeByte((char) ((L >>> 24) & 0xff));
        writeByte((char) ((L >>> 16) & 0xff));
        writeByte((char) ((L >>>  8) & 0xff));
        writeByte((char) ((L       ) & 0xff));
    }

    public static void write(long L, int bits)
    {
        if (bits == 64)
        {
            write(L);
            return;
        }

        if (bits < 1 || bits > 64)
            throw new IllegalArgumentException(ILLEGAL_LENGTH + bits);
        if (L < 0 || L >= (1 << bits))
            throw new IllegalArgumentException("Illegal " + bits + "-bits char = " + L);

        for (int i = 0; i < bits; ++i)
        {
            boolean bit = ((L >>> (bits - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    public static void write(double d)
    {
        write(Double.doubleToRawLongBits(d));
    }

    public static void write(double d, int bits)
    {
        write(Double.doubleToRawLongBits(d), bits);
    }

    public static void write(float f)
    {
        write(Float.floatToRawIntBits(f));
    }

    public static void write(float f, int bits)
    {
        write(Float.floatToRawIntBits(f), bits);
    }

    public static void write(String s)
    {
        for (int i = 0; i < s.length(); ++i)
            writeByte(s.charAt(i));
    }

    /**
     * Writes the string of bits-bits characters to standard output
     * @param s the {@code String} to write
     * @param bits the number of relevant bits in each character
     */
    public static void write(String s, int bits)
    {
        for (int i = 0; i < s.length(); ++i)
            write(s.charAt(i), bits);
    }
}

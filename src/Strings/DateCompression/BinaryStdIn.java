package Strings.DateCompression;
// Created by yirany on 2018/2/4

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

public class BinaryStdIn
{
    private static final int EOF = -1;
    private static final String ILLEGAL_LENGTH = "Illegal value of bits = ";

    private static BufferedInputStream in;
    private static int buffer;
    private static int bitsInBuffer;
    private static boolean isInitialized; //has BinaryStdIn been called for first time?

    // Do not instantiate
    private BinaryStdIn(){}

    // fill the buffer
    private static void initialize()
    {
        in = new BufferedInputStream(System.in);
        buffer = 0;
        bitsInBuffer = 0;
        fillBuffer();
        isInitialized = true;
    }

    private static void fillBuffer()
    {
        try
        {
            // read next byte to buffer
            buffer = in.read();
            bitsInBuffer = 8;
        }
        catch (IOException e)
        {
            System.out.println("EOF");
            buffer = EOF;
            bitsInBuffer = -1;
        }
    }

    public static boolean isEmpty()
    {
        if (!isInitialized)
            initialize();
        return buffer == EOF;
    }

    /**
     * Close this input stream and release any associated system resource
     */
    public static void close()
    {
        if (!isInitialized)
            initialize();

        try
        {
            in.close();
            isInitialized = false;
        }
        catch (IOException e)
        {
            throw new IllegalStateException("Could not close BinaryStdIn", e);
        }
    }

    /**
     * @return true iff standard input is empty
     */
    public static boolean readBit()
    {
        checkStreamNotEmpty();
        bitsInBuffer--;
        boolean bit = ((buffer >> bitsInBuffer) & 1) == 1;
        if (bitsInBuffer == 0) fillBuffer();
        return bit;
    }

    /**
     * Read one byte from the standard input stream and return the value as 8-bits char
     * value in range [0, 255]
     */
    public static char readByte()
    {
        checkStreamNotEmpty();

        // special case when aligned byte
        if (bitsInBuffer == 8)
        {
            int x = buffer;
            fillBuffer();
            return (char)(x & 0xff);
        }

        // combine last n bits of current buffer with first 8-n bits
        // of new buffer, n is bits in current buffer
        int x = buffer;
        int oldN = bitsInBuffer;
        x <<= (8 - oldN); // set high n bits

        fillBuffer();
        checkStreamNotEmpty();
        x |= (buffer >>> oldN);// set low 8-n bits
        bitsInBuffer = oldN;
        return (char)(x & 0xff);
        // the above code doesn't quite work for the last char in stream, if bitsInBuffer == 8,
        // because the new buffer will be -1, so there is a special case for aligned byte
    }

    /**
     * Read 16-bits char value
     */
    public static char readChar()
    {
        return readChar(16);
    }

    /**
     * Read the next {@code bits} bits from standard input and return as an
     * character.
     * @throws IllegalArgumentException unless {@code 1 <= r <= 16}
     * @throws NoSuchElementException if there are fewer than {@code bits} left in the stream
     */
    public static char readChar(int bits)
    {
        if (bits < 1 || bits > 16)
            throw new IllegalArgumentException(ILLEGAL_LENGTH + bits);

        // optimize bits = 8 case
        if (bits == 8) return (char)readByte();

        return (char)readLong(bits);
    }

    public static short readShort()
    {
        short x = 0;
        for (int i = 0; i < 2; ++i)
        {
            char b = readByte();
            x <<= 8;
            x |= b;
        }
        return x;
    }

    public static short readShort(int bits)
    {
        return (short)readChar(bits);
    }

    public static int readInt()
    {
        int x = 0;
        for (int i = 0; i < 4; ++i)
        {
            char b = readByte();
            x <<= 8;
            x |= b;
        }
        return x;
    }

    public static int readInt(int bits)
    {
        if (bits < 1 || bits > 32)
            throw new IllegalArgumentException(ILLEGAL_LENGTH + bits);

        // optimize bits == 32 case
        if (bits == 32) return readInt();

        return (int)readLong(bits);
    }

    public static long readLong()
    {
        long x = 0;
        for (int i = 0; i < 8; ++i)
        {
            char b = readByte();
            x <<= 8;
            x |= b;
        }
        return x;
    }

    public static long readLong(int bits)
    {
        if (bits < 1 || bits > 64)
            throw new IllegalArgumentException(ILLEGAL_LENGTH + bits);

        if (bits == 64) return readLong();

        long x = 0;
        for (int i = 0; i < bits; ++i)
        {
            x <<= 1;
            boolean bit = readBit();
            if (bit) x |= 1;
        }
        return x;
    }

    public static double readDouble()
    {
        return Double.longBitsToDouble(readLong());
    }

    public static double readDouble(int bits)
    {
        return Double.longBitsToDouble(readLong(bits));
    }

    public static float readFloat()
    {
        return Float.intBitsToFloat(readInt());
    }

    public static float readFloat(int bits)
    {
        return Float.intBitsToFloat(readInt(bits));
    }

    /**
     * Read the remaining bytes of data from standard input and return as a string
     * of 8-bits characters
     */
    public static String readString()
    {
        checkStreamNotEmpty();

        StringBuilder builder = new StringBuilder();
        while (!isEmpty())
        {
            char c = readByte();
            builder.append(c);
        }

        return builder.toString();
    }


    public static String readWord()
    {
        checkStreamNotEmpty();

        char input = (char)readByte();
        while (!isEmpty() && input == ' ')
            input = (char)readByte();

        StringBuilder builder = new StringBuilder();
        if (input != ' ') builder.append(input);

        while (!isEmpty() && input != ' ')
        {
            input = (char)readByte();
            builder.append(input);
        }
        return builder.toString();
    }

    public static String readLine()
    {
        checkStreamNotEmpty();

        StringBuilder builder = new StringBuilder();
        char input = (char)readByte();
        while (!isEmpty() && input != '\n')
        {
            builder.append(input);
            input = (char)readByte();
        }
        if (input != '\n')
            builder.append(input);
        return builder.toString();
    }


    private static void checkStreamNotEmpty()
    {
        if (isEmpty())
            throw new NoSuchElementException("Reading from empty input stream");
    }
}

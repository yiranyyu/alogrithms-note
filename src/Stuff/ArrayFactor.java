package Stuff;

import static Stuff.Rand.*;

/*
 * Created by yirany on 2017/10/8.
 */
public class ArrayFactor
{
    /**
     * @param size must be 0 or positive
     * @return a int array of sequential elements from 0 to size-1
     * */
    public static int[] ints(int size)
    {
        checkSize(size);
        int[] ints = new int[size];
        while (size-- > 0)
            ints[size] = size;
        return ints;
    }

    /**
     * @param size must be 0 or positive
     * @return a Integer array of sequential elements from 0 to size-1
     * */
    public static Integer[] Integers(int size)
    {
        checkSize(size);
        Integer[] integers = new Integer[size];
        fillArray(integers);
        return integers;
    }

    /**
     * @param size must be 0 or positive
     * @return a double array of sequential elements from 0.0 to size-1
     * */
    public static double[] doubles(int size)
    {
        checkSize(size);
        double[] doubles = new double[size];
        while (size-- > 0)
            doubles[size] = size;
        return doubles;
    }

    public static Double[] Doubles(int size)
    {
        checkSize(size);
        Double[] doubles = new Double[size];
        fillArray(doubles);
        return doubles;
    }


    public static float[] floats(int size)
    {
        checkSize(size);
        float[] floats = new float[size];
        while (size-- > 0)
            floats[size] = size;
        return floats;
    }

    public static Float[] Floats(int size)
    {
        checkSize(size);
        Float[] floats = new Float[size];
        fillArray(floats);
        return floats;
    }

    /**
     * @param alphabet takes to build string from
     * @return random strings with same length
     */
    public static String[] randomStrings(int size, int strLength, Alphabet alphabet)
    {
        checkSize(size);
        String[] strings = new String[size];
        for (int i = 0; i < size; ++i)
            strings[i] = randomString(alphabet, strLength);
        return strings;
    }

    /**
     * @see #randomStrings(int, int, Alphabet)
     * @return random strings with random length
     */
    public static String[] randomStrings(int size, Alphabet alphabet)
    {
        checkSize(size);
        String[] strings = new String[size];
        for (int i = 0; i < size; ++i)
            strings[i] = randomString(alphabet);
        return strings;
    }

    private static void checkSize(int size)
    {
        if (size < 0)
            throw new NegativeArraySizeException("size < 0");
    }

    private static void fillArray(Number[] array)
    {
        int size = array.length;
        while (size-- > 0)
            array[size] = size;
    }
}

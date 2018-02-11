package Stuff;

/*
 * Created by yirany on 2017/10/1.
 */
public class Checker
{
    /**
     * Check if the <code>array</code> is sorted in a non-increasing case
     * @param array the array to check, won't change it's content
     * @return true if <code>array</code> is sorted, otherwise return false
     */
    public static boolean isSorted(int[] array)
    {
        for (int i = 0; i < array.length - 1; i++)
        {
            if (array[i] > array[i + 1])
                return false;
        }
        return true;
    }

    public static boolean isSorted(Comparable[] array)
    {
        for (int i = 0; i < array.length-1; ++i)
            if (array[i].compareTo(array[i+1]) > 0)
                return false;
        return true;
    }
}

package Fundamentals.AnalysisOfAlgorithms;

import java.util.Arrays;

import static Stuff.Checker.isSorted;

/*
 * Created by yirany on 2017/10/15.
 */
public class TwoSumFaster
{

    /**
     * @param array the array to find elements in
     * @param sum value of pairs to find
     * @param start startIndex of the array to find elements in
     * @param end endIndex(exclusive) of the array to find elements in
     * @return number of pairs whose sum is {@code sum}
     * */
    public static int count(int[] array, int start, int end, int sum)
    {
        if (start < 0 || end > array.length)
            throw new ArrayIndexOutOfBoundsException("negative index to find two sum");
        if (start > end)
            throw new NegativeArraySizeException("start index of array less than end index");

        // assure the array is sorted(to use binary searchIn)
        if (!isSorted(array))
            Arrays.sort(array);

        int cnt = 0;
        int N = end - 1;
        for (int i = start; i < N; i++)
        {
            // in case there are more than one corresponding numbers
            // loop to find all pairs
            int startIndex = i + 1;
            startIndex = Arrays.binarySearch(array, startIndex, end, sum-array[i] );
            while (startIndex != -1)
            {
                cnt++;
                startIndex = Arrays.binarySearch(array, startIndex+1, end, sum-array[i] );
            }

        }
        return cnt;
    }

    public static void main(String[] args)
    {
        int[] a = {1, 2, 3, -1, -1, -2, -1};
        System.out.println(count(a, 0, a.length, 0));
    }
}

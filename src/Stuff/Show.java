package Stuff;
// Created by yirany on 2018/1/31

public class Show
{
    public static void showAlphabet(Alphabet alphabet, int colsOfRow, String split)
    {
        int radix = alphabet.radix();
        for (int i = 0; i < radix; ++i)
        {
            System.out.print(alphabet.toChar(i) + split);
            if (i % colsOfRow == 0 && i != 0)
                System.out.println();
        }
    }

    public static void showAlphabet(Alphabet alphabet, int colOfRow)
    {
        showAlphabet(alphabet, colOfRow, " ");
    }

    public static void showAlphabet(Alphabet alphabet)
    {
        showAlphabet(alphabet, 32, " ");
    }

    public static void main(String[] argv)
    {
        showAlphabet(Alphabet.UNICODE16);
    }
}

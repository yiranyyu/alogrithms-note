package Stuff;
// Created by yirany on 2018/1/30


import Searching.HashTables.charHashTable;

public class Alphabet
{
    public static final Alphabet BINARY = new Alphabet("01");
    public static final Alphabet OCTAL = new Alphabet("01234567");
    public static final Alphabet DECIMAL = new Alphabet("0123456789");
    public static final Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");
    public static final Alphabet DNA = new Alphabet("ACGT");
    public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");
    public static final Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    public static final Alphabet ALPHABET = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz");
    public static final Alphabet PROTEIN = new Alphabet("ACDEFGHIKLMNPQRSTVWY");
    public static final Alphabet BASE64 = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
    public static final Alphabet ASCII = new Alphabet(128);
    public static final Alphabet EXTENDED_ASCII = new Alphabet(256);
    public static final Alphabet UNICODE16 = new Alphabet(65536);

    private char[] values;
    private charHashTable<Integer> indices;

    public Alphabet(int radix)
    {
        if (radix <= 0)
            throw new IllegalArgumentException("radix must be positive");

        values = new char[radix];
        indices = new charHashTable<>(radix);
        for (int i = 0; i < radix; ++i)
        {
            values[i] = (char)i;
            indices.put((char)i, i);
        }
    }

    public Alphabet(String s)
    {
        assertLegal(s);
        indices = new charHashTable<>(s.length());
        values = new char[s.length()];
        for (int i = 0; i < values.length; ++i)
        {
            values[i] = s.charAt(i);
            indices.put(s.charAt(i), i);
        }
    }

    private void assertLegal(String s)
    {
        if (s.isEmpty()) throw new IllegalArgumentException("cannot build alphabet from empty char set");
        boolean[] unicode = new boolean['\uffff'];
        for (char c : s.toCharArray())
        {
            if (unicode[c])
                throw new IllegalArgumentException("same character '" + c + "' in " + this.getClass());
            unicode[c] = true;
        }
    }

    public char toChar(int index)
    {
        return values[index];
    }

    public int toIndex(char c)
    {
        return indices.get(c);
    }

    public boolean contains(char c)
    {
        return indices.contains(c);
    }

    public int radix()
    {
        return values.length;
    }

    /**
     * for speed's reason, this method do not handle the case {@code radix()} returns 1,
     * after all this case itself is weired.
     */
    public int lgR()
    {
        int rt = 0;
        for (int t = radix() - 1; t >= 1; t /= 2)
            rt += 1;
        return rt;
    }

    public int[] toIndices(String s)
    {
        int index = 0;
        int[] rt = new int[s.length()];

        for (char c : s.toCharArray())
            rt[index++] = toIndex(c);
        return rt;
    }

    public String toChars(int[] indices)
    {
        StringBuilder builder = new StringBuilder();
        for (int index : indices)
            builder.append(toChar(index));
        return builder.toString();
    }
}

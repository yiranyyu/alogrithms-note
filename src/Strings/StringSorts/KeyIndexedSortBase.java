package Strings.StringSorts;
// Created by yirany on 2018/1/31

abstract class KeyIndexedSortBase
{
    String[] a;
    String[] aux;
    int radix;

    KeyIndexedSortBase(String[] a, int radix)
    {
        this.a = a;
        this.radix = radix;
        aux = new String[a.length];
    }

    void swap()
    {
        String[] t = a;
        a = aux;
        aux = t;
    }

    void copyBack() {
        System.arraycopy(aux, 0, a, 0, a.length);
    }
}

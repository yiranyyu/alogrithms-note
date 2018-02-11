package Strings.Tries;
// Created by yirany on 2018/2/2

import Fundamentals.Bags_Queues_Stacks.List;
import Fundamentals.Bags_Queues_Stacks.Queue;

public class TST<Value> implements Tries<Value>
{
    static int countOfNodes;
    private Node root;

    public Value get(String key)
    {
        Node node = get(root, key, 0);
        if (node == null) return null;
        return node.value;
    }

    private Node get(Node rot, String key, int posInString)
    {
        if (rot == null) return null;

        char c = key.charAt(posInString);
        if      (c < rot.c)
            return get(rot.left, key, posInString);
        else if (c > rot.c)
            return get(rot.right, key, posInString);
        else if (posInString < key.length() - 1)
            return get(rot.mid, key, posInString+1);
        else
            return rot;// found
    }

    public void put(String key, Value value)
    {
        root = put(root, key, value, 0);
    }

    private Node put(Node rot, String key, Value value, int posInString)
    {
        char c = key.charAt(posInString);
        if (rot == null)
        {
            rot =new Node();
            rot.c = c;

            ++countOfNodes;
        }

        if (c < rot.c)
            rot.left = put(rot.left, key, value, posInString);
        else if (c > rot.c)
            rot.right = put(rot.right, key, value, posInString);
        else if (posInString < key.length() - 1)
            rot.mid = put(rot.mid, key, value, posInString+1);
        else
            rot.value = value;
        return rot;
    }

    @Override
    public String longestPrefixOf(String query)
    {
        if (query == null)
            throw new IllegalArgumentException("calls longestPrefixOf() with null");

        Node rot = root;
        int length = query.length();
        int prefixLen = 0;
        for (int i = 0; i < length && rot != null; /*no increment*/ )
        {
            char c = query.charAt(i);
            if      (c < rot.c) rot = rot.left;
            else if (c > rot.c) rot = rot.right;
            else
            {
                ++i; // increment if found same character
                if (rot.value != null)
                    prefixLen = i;// assign if found value
                rot = rot.mid;
            }
        }
        return query.substring(0, prefixLen);
    }

    @Override
    public Iterable<String> keys()
    {
        return collect(root);
    }

    private Iterable<String> collect(Node rot)
    {
        List<String> strings = new List<>();
        collect(rot, new StringBuilder(), strings);
        return strings;
    }

    private void collect(Node rot, StringBuilder prefix, List<String> strings)
    {
        if (rot == null) return;

        collect(rot.left, prefix, strings);

        if (rot.value != null)
            strings.push(prefix.toString() + rot.c);

        collect(rot.mid, prefix.append(rot.c), strings);

        prefix.deleteCharAt(prefix.length()-1);
        collect(rot.right, prefix, strings);

    }

    @Override
    public Iterable<String> keysWithPrefix(String prefix)
    {
        if (prefix == null)
            throw new IllegalArgumentException("calls keysWithPrefix() with null prefix");

        List<String> strings = new List<>();
        Node rot = get(root, prefix, 0);
        if (rot == null) return strings;

        if (rot.value != null)
            strings.push(prefix);
        collect(rot.mid, new StringBuilder(prefix), strings);
        return strings;
    }

    private void collect(Node rot, StringBuilder prefix, int indexInPattern, String pattern ,List<String> strings)
    {
        if (rot == null) return;

        char c = pattern.charAt(indexInPattern);
        if (c == '.' || c < rot.c)
            collect(rot.left, prefix, indexInPattern, pattern, strings);
        if (c == '.' || c == rot.c)
        {
            if (indexInPattern == pattern.length() - 1 && rot.value != null)
                strings.push(prefix.toString() + rot.c);
            if (indexInPattern < pattern.length()-1)
            {
                collect(rot.mid, prefix.append(rot.c), indexInPattern+1, pattern, strings);
                prefix.deleteCharAt(prefix.length()-1);
            }
        }

        if (c == '.' || c > rot.c)
            collect(rot.right, prefix, indexInPattern, pattern, strings);
    }

    @Override
    public Iterable<String> keysThatMatch(String pattern)
    {
        List<String> strings = new List<>();
        collect(root, new StringBuilder(), 0, pattern, strings);
        return strings;
    }

    private class Node
    {
        char c;
        Value value;
        Node left, mid, right;
    }
}

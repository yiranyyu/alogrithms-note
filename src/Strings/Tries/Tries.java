package Strings.Tries;
// Created by yirany on 2018/2/5

public interface Tries<Value>
{
    Iterable<String> keys();
    Iterable<String> keysWithPrefix(String prefix);

    Iterable<String> keysThatMatch(String pattern);

    String longestPrefixOf(String s);

    Value get(String key);
    void put(String key, Value val);
}

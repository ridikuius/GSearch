package com.gsearch.dictionary;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Trie树节点
 * Created by wangyana on 2016/9/9.
 */
public class TrieNode2 {

    public TrieNode2[] childNodes;

    /**
     * 词频统计
     */
    public int freq;

    /**
     * 节点字符
     */
    public char nodeChar;

    /**
     * 插入记录时的节点编码
     */
    public HashSet<Integer> hashSet = new HashSet<Integer>();

    public TrieNode2() {
        childNodes = new TrieNode2[26];
        freq = 0;
    }

    @Override
    public String toString() {
        return "TrieNode2{" +
               "childNodes=" + Arrays.toString(childNodes) +
               ", freq=" + freq +
               ", nodeChar=" + nodeChar +
               ", hashSet=" + hashSet +
               '}';
    }
}
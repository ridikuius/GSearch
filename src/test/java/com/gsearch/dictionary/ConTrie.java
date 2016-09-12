package com.gsearch.dictionary;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * Trie树操作
 * Created by wangyanan on 2016/9/9.
 *
 */

public class ConTrie {

    /**
     * 插入操作
     * @param root
     * @param word
     * @param id
     */
    private static TrieNode2 AddTrieNode(TrieNode2 root, String word, Integer id)
    {
        if (word.length() == 0)
            return root;
        //求字符地址，方便将该字符放入到26叉树中的哪一叉中
        int k = word.charAt(0) - 'a';
        //如果该叉树为空，则初始化
        if (root.childNodes[k] == null)
        {
            root.childNodes[k] = new TrieNode2();
            //记录下字符
            root.childNodes[k].nodeChar = word.charAt(0);
        }

        //该id途径的节点
        root.childNodes[k].hashSet.add(id);
        String nextWord = word.substring(1);
        //说明是最后一个字符，统计该词出现的次数
        if (nextWord.length() == 0)
            root.childNodes[k].freq++;
        AddTrieNode(root.childNodes[k], nextWord, id);
        return root;
    }


    public static void main(String[] args) {
        TrieNode2 trieNode2 = new TrieNode2();
        String s = "bear";
        Integer i = 0;
        ArrayList<String> list = Lists.newArrayList("bear","bell","bid","bull", "buy", "sell","stock","stop");
        System.out.println(AddTrieNode(trieNode2,s,i));
    }

}

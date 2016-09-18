package com.gsearch.index;

import static com.gsearch.Analyzer.DictionaryAnalyzer.INDEXES;
import static com.gsearch.Analyzer.DictionaryAnalyzer.makeDic;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 组建索引
 * 两遍文档遍历法
 * 1. 第一遍遍历文档统计信息：文档个数N，文档集合内所包含的不同单词个数M,每个单词在多少个文档出现过的信息DF
 * 2. 第二遍遍历文档建立倒排列表。
 * Created by wangyanan on 2016/9/6.
 */
public class DoubleTraverseIndex {


    /**
     * 文档集合内所包含的不同单词个数，Map<文档ID,Map<单词,在该文档中出现的次数>>
     */
    private static final Map<Integer, Map<String, Integer>> WORD_NUM = Maps.newHashMap();

    /**
     * 每个单词在多少个文档出现过的信息DF，Map<单词,List<存在文档ID>>
     */
    private static final Map<String, Set<Integer>> DF = Maps.newHashMap();

    static {
        makeDic();
    }

    /**
     * 获得文档集合内所包含的不同单词个数
     */
    public static void getWordNums() {
        for (Integer id : INDEXES.keySet()) {
            for (String word : INDEXES.get(id)) {
                if (!WORD_NUM.containsKey(id)) {
                    WORD_NUM.put(id, getNum(word, INDEXES.get(id)));
                } else {
                    WORD_NUM.get(id).putAll(getNum(word, INDEXES.get(id)));
                }
            }
        }
        System.out.println(WORD_NUM);
    }

    /**
     * 构造单词和词频map
     *
     * @param word  单词
     * @param words 文档分割而成的单词集
     */
    private static Map<String, Integer> getNum(String word, List<String> words) {
        int num = 0;
        Map<String, Integer> map = Maps.newHashMap();
        for (String str : words) {
            if (word.equals(str)) {
                num++;
            }
        }
        map.put(word, num);
        return map;
    }


    public static void getDF() {
        for (Integer id : INDEXES.keySet()) {
            for (String word : INDEXES.get(id)) {
                if (DF.containsKey(word)) {
                    DF.get(word).add(id);
                } else {
                    DF.put(word, Sets.newHashSet(id));
                }
            }
        }
        System.out.println(DF);
    }

    public static void main(String[] args) {
        getWordNums();
        getDF();
    }

}


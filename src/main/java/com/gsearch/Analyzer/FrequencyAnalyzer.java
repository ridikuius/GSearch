package com.gsearch.Analyzer;

import static com.gsearch.util.ConstructionUtil.CONTENT;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 词频统计分词器
 * 存在问题：
 * 1.分词算法
 * 2.词频统计
 * 3.搜索
 * Created by wangyanan on 2016/9/6.
 */
public class FrequencyAnalyzer {

    private static final HashMap<Character, ArrayList<Integer>> DICTIONARY = Maps.newHashMap();
    private static final ArrayList<Integer> INDEXES = Lists.newArrayList();
    private static final Map<Integer, Integer> WORD_FREQUENCY = Maps.newHashMap();
    private static StringBuilder sb = new StringBuilder();
    private static Integer len;

    /**
     * 字典
     */
    private void _index() {
        for (Integer index : CONTENT.keySet()) {
            String doc = CONTENT.get(index).replaceAll("[\\p{Punct}\\s]+", "");
            _analyzer(index, doc, DICTIONARY);
        }
    }

    /**
     * 分词器
     */
    private void _analyzer(Integer index, String doc, Map<Character, ArrayList<Integer>> map) {
        if (!Strings.isNullOrEmpty(doc)) {
            for (int i = 0; i < doc.length(); i++) {
                if (map.containsKey(doc.charAt(i))) {
                    map.get(doc.charAt(i)).add(index);
                } else {
                    map.put(doc.charAt(i), Lists.newArrayList(index));
                }
            }
        }
    }


    /**
     * 创建搜索索引
     *
     * @param search 搜索key
     */
    public void makeIndex(String search) {
        for (int i = 0; i < search.length(); i++) {
            if (DICTIONARY.containsKey(search.charAt(i))) {
                INDEXES.addAll(DICTIONARY.get(search.charAt(i)));
            }
        }
        len = search.length() / 2;
    }

    public void _statistics() {
        for (Integer index : INDEXES) {
            if (WORD_FREQUENCY.containsKey(index)) {
                WORD_FREQUENCY.put(index, WORD_FREQUENCY.get(index) + 1);
            } else {
                WORD_FREQUENCY.put(index, 0);
            }
        }

    }

    public void _search() {
        WORD_FREQUENCY.keySet().stream().filter(index -> WORD_FREQUENCY.get(index) >= len)
                .forEach(index -> {
                    sb.append(CONTENT.get(index));
                    sb.append("\n");
                });
        System.out.println(sb.toString());
    }


    public static void main(String[] args) throws IOException {
        FrequencyAnalyzer analyzer = new FrequencyAnalyzer();
        analyzer._index();
        analyzer.makeIndex("词频");
        analyzer._statistics();
        analyzer._search();
    }
}

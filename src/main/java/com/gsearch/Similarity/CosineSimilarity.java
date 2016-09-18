package com.gsearch.Similarity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.gsearch.Analyzer.DictionaryAnalyzer;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 根据余弦定理计算文本的相似度
 * 判定方式：余弦相似度，通过计算两个向量的夹角余弦值来评估他们的相似度
 * 余弦夹角原理：
 * 1.找出连篇文章的关键词。
 * 2.每篇文章各取出若干关键词。
 * 3.生成两篇文章各自的词频向量。
 * 4.计算两个向量的余弦相似度，值越大表示越相似。
 * 向量a=(x1,y1),向量b=(x2,y2)
 * similarity=∑a.b/∑|a|*∑|b|
 * a.b=x1x2+y1y2
 * |a|=根号[(x1)^2+(y1)^2],|b|=根号[(x2)^2+(y2)^2]
 * Created by wangyanan on 2016/9/9.
 */
public class CosineSimilarity {

    private static Set<String> KEYWORDS = Sets.newLinkedHashSet();
    private static List<String> KEY1 = Lists.newLinkedList();
    private static List<String> KEY2 = Lists.newLinkedList();
    private static List<Integer> VECTOR1 = Lists.newLinkedList();
    private static List<Integer> VECTOR2 = Lists.newLinkedList();


    /**
     * 用分词器提取关键词
     */
    public static void getKeyWords(String s1, String s2) {
        KEY1 = DictionaryAnalyzer.seg(s1);
        KEY2 = DictionaryAnalyzer.seg(s2);
        KEYWORDS.addAll(KEY1);
        KEYWORDS.addAll(KEY2);
    }

    public static void wfVector() {
        Map<String, Integer> keyMap1 = Maps.newHashMap();
        Map<String, Integer> keyMap2 = Maps.newHashMap();
        conMap(keyMap1, KEY1);
        conMap(keyMap2, KEY2);
        conMap(keyMap1, KEYWORDS);
        conMap(keyMap2, KEYWORDS);
        VECTOR1.addAll(keyMap1.keySet().stream().map(keyMap1::get).collect(Collectors.toList()));
        VECTOR2.addAll(keyMap2.keySet().stream().map(keyMap2::get).collect(Collectors.toList()));
    }

    public static double getCosine() {
        double squares1 = getPow(VECTOR1);
        double squares2 = getPow(VECTOR2);
        double sum = 0;
        for (int i = 0; i < VECTOR1.size(); i++) {
            sum = sum + VECTOR1.get(i) * VECTOR2.get(i);
        }
        BigDecimal
                der =
                BigDecimal.valueOf(Math.sqrt(squares1))
                        .multiply(BigDecimal.valueOf(Math.sqrt(squares2)));
        return BigDecimal.valueOf(sum).divide(der, 9, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private static double getPow(List<Integer> vector) {
        double squares = 0;
        for (Integer sum : vector) {
            squares = squares + Math.pow(sum, 2);
        }
        return squares;
    }

    private static void conMap(Map<String, Integer> keyMap, Collection<String> key) {
        for (String str : key) {
            if (!keyMap.containsKey(str)) {
                keyMap.put(str, 0);
            } else {
                keyMap.put(str, keyMap.get(str) + 1);
            }
        }
    }

    public static void main(String[] args) {
        String s1 = "这只皮靴号码大了那只号码合适";
        String s2 = "这只皮靴号码不小那只更合适";
        getKeyWords(s1, s2);
        wfVector();
        getCosine();
    }
}

package com.gsearch.Analyzer;

import static com.gsearch.util.ConstructionUtil.CONTENT;
import static com.gsearch.util.ConstructionUtil.DIC;
import static com.gsearch.util.ConstructionUtil.MAXNUM;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 字典查询分词器
 * Created by RI01796 on 2016/9/6.
 */
public class DictionaryAnalyzer {

    /**
     * 内容和字典
     */
    public static HashMap<Integer, ArrayList<String>> INDEXES = Maps.newHashMap();

    /**
     * 将内容分割成字典
     */
    public static void makeDic() {
        for (Integer index : CONTENT.keySet()) {
            INDEXES.put(index, (ArrayList<String>) seg(CONTENT.get(index)));
        }
    }

    public static List<String> seg(String text) {
        List<String> result = new ArrayList<>();
        while (text.length() > 0) {
            int len = MAXNUM;
            if (text.length() < len) {
                len = text.length();
            }
            String tryWord = text.substring(0, len);
            while (!DIC.contains(tryWord)) {
                if (tryWord.length() == 1) {
                    break;
                }
                tryWord = tryWord.substring(0, tryWord.length() - 1);
            }
            result.add(tryWord);
            text = text.substring(tryWord.length());
        }
        return result;
    }


    public static void _search(String s) {
        INDEXES.keySet().stream().filter(index -> INDEXES.get(index).contains(s))
                .forEach(index -> {
                    System.out.println(CONTENT.get(index));
                });
    }


    public static void main(String[] args) throws IOException {
        makeDic();
        _search("搜索");
    }


}

package com.gsearch.index;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 字典查询分词器
 * Created by RI01796 on 2016/9/6.
 */
public class DictionaryAnalyzer {

    /**
     * 字典
     */
    private static final ArrayList<String> DIC = Lists.newArrayList();

    /**
     * 分词最大长度
     */
    private static Integer MAXNUM = 0;


    /**
     * 内容表
     */
    private static HashMap<Integer, String> CONTENT = Maps.newHashMap();

    /**
     * 内容和字典
     */
    private static HashMap<Integer, ArrayList<String>> INDEXES = Maps.newHashMap();

    /**
     * 读字典,构建字典树
     */
    static {
        URL url = Resources.getResource("test.txt");
        try {
            BufferedReader reader =
                    Resources.asByteSource(url).asCharSource(Charsets.UTF_8).openBufferedStream();
            String word;
            while ((word = reader.readLine()) != null) {
                DIC.add(word);
                if (word.length() > MAXNUM) {
                    MAXNUM = word.length();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 读单个文件
     *
     * @param fileName 文件名
     */
    public static void readDocument(String fileName) throws IOException {
        URL url = Resources.getResource(fileName);
        BufferedReader reader =
                Resources.asByteSource(url).asCharSource(Charsets.UTF_8).openBufferedStream();
        Integer index = 1;
        String word;
        while ((word = reader.readLine()) != null) {
            CONTENT.put(index, word);
            index++;
        }
        reader.close();
    }

    /**
     * 将内容分割成字典
     */
    public static  void makeDic() {
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
        readDocument("document.txt");
        makeDic();
        _search("搜索");
        "dsd".equals("sss");
    }


}

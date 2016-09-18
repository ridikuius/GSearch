package com.gsearch.util;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 构造字典和文档表
 * Created by wangyanan on 2016/9/18.
 */
public class ConstructionUtil {

    /**
     * 字典
     */
    public static final ArrayList<String> DIC = Lists.newArrayList();
    /**
     * 内容表
     */
    public static HashMap<Integer, String> CONTENT = Maps.newHashMap();


    /**
     * 分词最大长度
     */
    public static Integer MAXNUM = 0;

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

    static {
        URL url = Resources.getResource("document.txt");
        BufferedReader reader = null;
        Integer index = 1;
        String word;
        try {
            reader = Resources.asByteSource(url).asCharSource(Charsets.UTF_8).openBufferedStream();
            while ((word = reader.readLine()) != null) {
                CONTENT.put(index, word);
                index++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

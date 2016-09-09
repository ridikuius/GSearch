package com.gsearch.dictionary;

import java.util.List;

/**
 * 字典接口
 *
 * Created by wangyanan on 2016/9/8.
 */
public interface IDictionary {

    /**
     * 词典中的词的最大长度，用作设置最大划分长度
     *
     * @return 长度
     */
    int getMaxLength();

    /**
     * 判断指定的文本是不是一个词
     *
     * @param item   文本
     * @param start  指定的文本从哪个下标索引开始
     * @param length 指定的文本的长度
     * @return 是否
     */
    boolean contains(String item, int start, int length);

    /**
     * 判断文本是不是一个词
     *
     * @param item 文本
     * @return 是否
     */
    boolean contains(String item);

    /**
     * 批量将词加入词典
     *
     * @param items 集合中的每一个元素是一个词
     */
    void addAll(List<String> items);

    /**
     * 将单个词加入词典
     *
     * @param item 词
     */
    void add(String item);

    /**
     * 批量将词从词典中删除
     *
     * @param items 集合中的每一个元素是一个词
     */
    void removeAll(List<String> items);

    /**
     * 将单个词从词典中删除
     *
     * @param item 词
     */
    void remove(String item);

    /**
     * 清空词典中的所有的词
     */
    void clear();

}

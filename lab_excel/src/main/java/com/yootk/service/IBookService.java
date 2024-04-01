package com.yootk.service;
public interface IBookService {
    public static final String DIRECTORY = "data"; // 存储目录
    /**
     * 添加图书数据，该数据组成结构为：
     *      图书名称,图书作者,图书印刷量,图书销售量,图书破损量,图书库存量
     * 当数据合法时，允许将该数据保存在Excel文件之中，不合法时不允许保存
     * @param data 图书数据
     * @return 已有的数据量
     */
    public int add(String data);
}
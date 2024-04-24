package com.yootk.dao;

import com.yootk.vo.Book;

import java.util.List;

/**
 * @ClassName:IBookDAO
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/4/18 21:29
 */

public interface IBookDAO {
    public boolean doCreateBatch(List<Book> books); // 批量增加图书数据
}

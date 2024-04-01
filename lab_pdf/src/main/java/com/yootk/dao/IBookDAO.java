package com.yootk.dao;
import com.yootk.vo.Book;
import java.util.List;
public interface IBookDAO {
    // 根据年份统计当年的图书信息
    List<Book.BookSummary> getStatByYear(int year);
}
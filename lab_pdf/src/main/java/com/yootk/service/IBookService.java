package com.yootk.service;
import com.yootk.vo.Book;
import java.util.List;
public interface IBookService {
    List<Book.BookSummary> stat(int year);
}
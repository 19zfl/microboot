package com.yootk.service.impl;
import com.yootk.dao.IBookDAO;
import com.yootk.service.IBookService;
import com.yootk.vo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    private IBookDAO bookDAO;
    @Override
    public List<Book.BookSummary> stat(int year) {
        if (year > 0) {
            return this.bookDAO.getStatByYear(year);
        }
        return List.of();
    }
}
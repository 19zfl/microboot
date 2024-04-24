package com.yootk.service.impl;

import com.yootk.dao.IBookDAO;
import com.yootk.service.IBookService;
import com.yootk.vo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName:BookServiceImpl
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/4/18 21:33
 */

@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    private IBookDAO bookDAO;
    @Override
    public int add(List<Book> books) {
        if (books.size() > 0) {
            if (this.bookDAO.doCreateBatch(books)) {
                return books.size();
            }
        }
        return 0;
    }
}

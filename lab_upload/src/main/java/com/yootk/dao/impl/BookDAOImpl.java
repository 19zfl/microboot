package com.yootk.dao.impl;

import com.yootk.dao.IBookDAO;
import com.yootk.vo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName:BookDAOImpl
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/4/18 21:30
 */

@Repository
public class BookDAOImpl implements IBookDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean doCreateBatch(List<Book> books) {
        String sql = "INSERT INTO book(name, print, sale, broke, inventory) " +
                " VALUES (?, ?, ?, ?, ?)";
        return this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, books.get(i).getName());
                ps.setInt(2, books.get(i).getPrint());
                ps.setInt(3, books.get(i).getSale());
                ps.setInt(4, books.get(i).getBroke());
                ps.setInt(5, books.get(i).getInventory());
            }

            @Override
            public int getBatchSize() {
                return books.size();
            }
        }).length == books.size();
    }
}
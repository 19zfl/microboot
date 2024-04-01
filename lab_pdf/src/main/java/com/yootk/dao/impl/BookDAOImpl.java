package com.yootk.dao.impl;
import com.yootk.dao.IBookDAO;
import com.yootk.vo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class BookDAOImpl implements IBookDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Book.BookSummary> getStatByYear(int year) {
        String sql = "SELECT name, SUM(print) as print_sum, SUM(sale) as sale_sum, SUM(broke) as broke_sum " +
                "FROM book " +
                "WHERE YEAR(indate) = ? " +
                "GROUP BY name";
        return this.jdbcTemplate.query(sql, new Object[]{year}, new RowMapper<Book.BookSummary>() {
            @Override
            public Book.BookSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book.BookSummary bookSummary = new Book.BookSummary();
                bookSummary.setName(rs.getString(1));
                bookSummary.setPrintSum(rs.getInt(2));
                bookSummary.setSaleSum(rs.getInt(3));
                bookSummary.setBrokeSum(rs.getInt(4));
                return bookSummary;
            }
        });
    }
}
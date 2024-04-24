package com.yootk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @ClassName:SpringJDBCConfig
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/4/18 21:28
 */

@Configuration
public class SpringJDBCConfig {
    @Bean("jdbcTemplate") // Bean 注册
    public JdbcTemplate jdbcTemplate(DataSource dataSource) { // 注 入DataSource
        JdbcTemplate jdbcTemplate = new JdbcTemplate(); // 实例化 JDBC 模版类
        jdbcTemplate.setDataSource(dataSource); // 注入数据源实例
        return jdbcTemplate; // 返回 Bean 对象
    }
}

package com.yootk.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
@Configuration                        // 配置类
public class SpringJDBCConfig {                // SpringJDBC配置类
    @Bean("jdbcTemplate")                    // Bean注册
    public JdbcTemplate jdbcTemplate(DataSource dataSource) { // 注入DataSource
        JdbcTemplate jdbcTemplate = new JdbcTemplate();    // 实例化JDBC模版类
        jdbcTemplate.setDataSource(dataSource);        // 注入数据源实例
        return jdbcTemplate;                // 返回Bean对象
    }
}
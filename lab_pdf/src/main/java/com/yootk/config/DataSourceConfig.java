package com.yootk.config;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import javax.sql.DataSource;
@Configuration // 配置类
@PropertySource("classpath:config/database.properties")    // 配置加载
public class DataSourceConfig { // 数据库配置Bean
    @Value("${yootk.database.dirverClassName}") // 资源文件读取配置项
    private String driverClassName; // 数据库驱动程序
    @Value("${yootk.database.jdbcUrl}") // 资源文件读取配置项
    private String jdbcUrl; // 数据库连接地址
    @Value("${yootk.database.username}")  // 资源文件读取配置项
    private String username;  // 用户名
    @Value("${yootk.database.password}")  // 资源文件读取配置项
    private String password;  // 密码
    @Value("${yootk.database.connectionTimeOut}")  // 资源文件读取配置项
    private long connectionTimeout;  // 连接超时
    @Value("${yootk.database.readOnly}")  // 资源文件读取配置项
    private boolean readOnly;  // 只读配置
    @Value("${yootk.database.pool.idleTimeOut}") // 资源文件读取配置项
    private long idleTimeout;  // 连接最小维持时长
    @Value("${yootk.database.pool.maxLifetime}")  // 资源文件读取配置项
    private long maxLifetime;  // 连接最大存活时长
    @Value("${yootk.database.pool.maximumPoolSize}")  // 资源文件读取配置项
    private int maximumPoolSize;  // 连接池最大维持数量
    @Value("${yootk.database.pool.minimumIdle}") // 资源文件读取配置项
    private int minimumIdle; // 连接池最小维持数量
    @Bean("dataSource") // Bean注册
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource(); // DataSource子类实例化
        dataSource.setDriverClassName(this.driverClassName); // 驱动程序
        dataSource.setJdbcUrl(this.jdbcUrl); // JDBC连接地址
        dataSource.setUsername(this.username); // 用户名
        dataSource.setPassword(this.password); // 密码
        dataSource.setConnectionTimeout(this.connectionTimeout); // 连接超时
        dataSource.setReadOnly(this.readOnly); // 是否为只读数据库
        dataSource.setIdleTimeout(this.idleTimeout); // 最小维持时间
        dataSource.setMaxLifetime(this.maxLifetime); // 连接的最大时长
        dataSource.setMaximumPoolSize(this.maximumPoolSize); // 连接池最大容量
        dataSource.setMinimumIdle(this.minimumIdle); // 最小维持连接量
        return dataSource; // 返回Bean实例
    }
}
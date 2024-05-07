package com.example.blockfilesextension.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.hikari.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.hikari.jdbc-url}")
    private String url;

    @Value("${spring.datasource.hikari.username}")
    private String username;

    @Value("${spring.datasource.hikari.password}")
    private String password;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maximumPoolSize;

    @Value("${spring.datasource.hikari.max-lifetime}")
    private int maxLifetime;

    @Value("${spring.datasource.hikari.idle-timeout}")
    private int idleTimeout;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private int connectionTimeout;

    @Value("${spring.datasource.hikari.pool-name}")
    private String poolName;

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaximumPoolSize(maximumPoolSize);
        dataSource.setMaxLifetime(maxLifetime);
        dataSource.setIdleTimeout(idleTimeout);
        dataSource.setConnectionTimeout(connectionTimeout);
        dataSource.setPoolName(poolName);
        return dataSource;
    }
}


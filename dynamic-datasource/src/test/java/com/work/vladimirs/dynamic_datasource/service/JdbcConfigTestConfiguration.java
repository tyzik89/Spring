package com.work.vladimirs.dynamic_datasource.service;

import com.work.vladimirs.dynamic_datasource.sharding.ShardDataSourceRouter;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Конфигурация JDBC для БД
 */
@TestConfiguration
public class JdbcConfigTestConfiguration {

    private static final String DEFAULT_DATASOURCE_URL = "jdbc:h2:mem:dbShard1;MODE=PostgreSQL;DB_CLOSE_DELAY=-1";

    @Bean
    HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(DEFAULT_DATASOURCE_URL);
        hikariConfig.setUsername("sa");
        hikariConfig.setPassword("password");
        hikariConfig.setDriverClassName("org.h2.Driver");
        hikariConfig.setPoolName("test_pool");
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setConnectionTimeout(30000);
        return hikariConfig;
    }

    @Bean
    DataSourceProperties dsProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUrl(DEFAULT_DATASOURCE_URL);
        dataSourceProperties.setDriverClassName("org.h2.Driver");
        dataSourceProperties.setUsername("sa");
        dataSourceProperties.setPassword("password");
        return dataSourceProperties;
    }

    @Bean
    public DataSource defaultDataSource(HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public ShardDataSourceRouter shardDataSource(DataSource defaultDataSource) {
        ShardDataSourceRouter shardDataSourceRouter = new ShardDataSourceRouter();
        shardDataSourceRouter.setDefaultTargetDataSource(defaultDataSource); // Установите источник по умолчанию
        shardDataSourceRouter.addDataSource(DEFAULT_DATASOURCE_URL, defaultDataSource);
        return shardDataSourceRouter;
    }

    @Bean
    public JdbcTemplate defaultJdbcTemplate(ShardDataSourceRouter shardDataSource) {
        return new JdbcTemplate(shardDataSource);
    }

    @Bean
    public DataSource secondDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:dbShard2;MODE=PostgreSQL;DB_CLOSE_DELAY=-1")
                .driverClassName("org.h2.Driver")
                .username("sa")
                .password("password")
                .build();
    }

    @Bean
    public JdbcTemplate secondJdbcTemplate(DataSource secondDataSource) {
        return new JdbcTemplate(secondDataSource);
    }
}

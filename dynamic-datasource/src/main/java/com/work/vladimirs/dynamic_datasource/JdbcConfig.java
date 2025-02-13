package com.work.vladimirs.dynamic_datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация JDBC для БД rkk.
 */
@Configuration
public class JdbcConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dsProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource defaultDataSource(@Qualifier("hikariConfig") HikariConfig hikariConfig,
                                        @Qualifier("dsProperties") DataSourceProperties dsProperties) {
        hikariConfig.setUsername(dsProperties.getUsername());
        hikariConfig.setPassword(dsProperties.getPassword());
        hikariConfig.setJdbcUrl(dsProperties.getUrl());
        hikariConfig.setDriverClassName(dsProperties.getDriverClassName());
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public ShardDataSourceRouter shardDataSource(@Qualifier("dsProperties") DataSourceProperties dsProperties,
                                                 @Qualifier("defaultDataSource") DataSource defaultDataSource) {

        ShardDataSourceRouter dataSourceRouter = new ShardDataSourceRouter();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(dsProperties.getUrl(), defaultDataSource);

        dataSourceRouter.setTargetDataSources(targetDataSources);
        dataSourceRouter.setDefaultTargetDataSource(defaultDataSource); // Установите источник по умолчанию
        return dataSourceRouter;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("shardDataSource") ShardDataSourceRouter shardDataSource) {
        return new JdbcTemplate(shardDataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcNamedTemplate(@Qualifier("jdbcTemplate") JdbcTemplate jdbcTemplate) {
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }
}

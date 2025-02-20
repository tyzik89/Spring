package com.work.vladimirs.dynamic_datasource.config;

import com.work.vladimirs.dynamic_datasource.sharding.ShardDataSourceRouter;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * Конфигурация JDBC для БД
 */
@Configuration
public class JdbcConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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

        dataSourceRouter.setDefaultTargetDataSource(defaultDataSource); // Установите источник по умолчанию
        dataSourceRouter.addDataSource(dsProperties.getUrl().replaceAll("/", ""), defaultDataSource);
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

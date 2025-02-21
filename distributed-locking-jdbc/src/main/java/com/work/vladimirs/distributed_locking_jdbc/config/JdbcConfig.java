package com.work.vladimirs.distributed_locking_jdbc.config;

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
    public DataSource dataSource(@Qualifier("hikariConfig") HikariConfig hikariConfig,
                                        @Qualifier("dsProperties") DataSourceProperties dsProperties) {
        hikariConfig.setUsername(dsProperties.getUsername());
        hikariConfig.setPassword(dsProperties.getPassword());
        hikariConfig.setJdbcUrl(dsProperties.getUrl());
        hikariConfig.setDriverClassName(dsProperties.getDriverClassName());
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcNamedTemplate(@Qualifier("jdbcTemplate") JdbcTemplate jdbcTemplate) {
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }
}

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
import org.springframework.integration.jdbc.lock.DefaultLockRepository;
import org.springframework.integration.jdbc.lock.JdbcLockRegistry;
import org.springframework.integration.jdbc.lock.LockRepository;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

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


/*    Суть - заменить TransactionManager у LockRepository.
    По умолчанию, он бы использовал JPATransactionManager, и при невозможности получить блокировку, откатывалась
    бы основная бизнес-транзакция. При этом сам LockRegistry написан так, что невозможность получить блокировку - нормальное явление,
    он просто попробует сделать это позже. Но ваша бизнес-транзакция все равно откатится.*/

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public LockRepository lockRepository(PlatformTransactionManager transactionManager,
                                         DataSource dataSource) {
        DefaultLockRepository lockRepository = new DefaultLockRepository(dataSource, "client_1");

        lockRepository.setPrefix("my_");
        lockRepository.setTimeToLive(60_000);
        lockRepository.setTransactionManager(transactionManager);
        return lockRepository;
    }

    @Bean
    public LockRegistry lockRegistry(LockRepository lockRepository) {
        return new JdbcLockRegistry(lockRepository);
    }
}

package com.work.vladimirs.dynamic_datasource.service;

import com.work.vladimirs.dynamic_datasource.sharding.ShardDataSourceContextHolder;
import com.work.vladimirs.dynamic_datasource.sharding.ShardDataSourceRouter;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Сервис для получения пула соединений с нужным шардом (JdbcUrl)
 */
@ConditionalOnProperty(value = "myapp.database.sharding.enabled", havingValue = "true")
@Service
@Slf4j
public class ShardSwitcherService {

    private final HikariConfig hikariConfig;

    private final DataSourceProperties dsProperties;

    private final ShardDataSourceRouter shardDataSourceRouter;

    public ShardSwitcherService(@Qualifier("hikariConfig") HikariConfig hikariConfig,
                                @Qualifier("dsProperties") DataSourceProperties dsProperties,
                                @Qualifier("shardDataSource") ShardDataSourceRouter shardDataSourceRouter) {
        this.hikariConfig = hikariConfig;
        this.dsProperties = dsProperties;
        this.shardDataSourceRouter = shardDataSourceRouter;
    }

    private final AtomicInteger poolNameCounter = new AtomicInteger(1);

    public void switchDataSourceContext(String dataSourceKey) {
        String replacedShardKey = dataSourceKey.replaceAll("/", "");
        if (!shardDataSourceRouter.isDataSourcePresent(replacedShardKey)) {
            shardDataSourceRouter.addDataSource(replacedShardKey, createHikariDataSource(dataSourceKey));
        }
        ShardDataSourceContextHolder.setDataSourceShardKey(replacedShardKey);
    }

    private DataSource createHikariDataSource(String dataSourceKey) {
        HikariConfig config = new HikariConfig(hikariConfig.getDataSourceProperties());

        config.setJdbcUrl(dataSourceKey);
        config.setPoolName(hikariConfig.getPoolName() + poolNameCounter.getAndIncrement());

        config.setUsername(dsProperties.getUsername());
        config.setPassword(dsProperties.getPassword());
        config.setDriverClassName(dsProperties.getDriverClassName());
        return new HikariDataSource(config);
    }

    public void clearCurrentContext() {
        ShardDataSourceContextHolder.clear();
    }
}

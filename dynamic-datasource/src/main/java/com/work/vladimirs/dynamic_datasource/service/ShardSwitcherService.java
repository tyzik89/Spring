package com.work.vladimirs.dynamic_datasource.service;

import com.work.vladimirs.dynamic_datasource.sharding.ShardDataSourceContextHolder;
import com.work.vladimirs.dynamic_datasource.sharding.ShardDataSourceRouter;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Lookup;
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

    private final DataSourceProperties dsProperties;

    private final ShardDataSourceRouter shardDataSourceRouter;

    public ShardSwitcherService(@Qualifier("dsProperties") DataSourceProperties dsProperties,
                                @Qualifier("shardDataSource") ShardDataSourceRouter shardDataSourceRouter) {
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

    @Lookup("hikariConfig")
    protected HikariConfig getHikariConfig() {
        return null;
    }

    private DataSource createHikariDataSource(String dataSourceKey) {
        HikariConfig config = getHikariConfig();

        config.setJdbcUrl(dataSourceKey);
        config.setPoolName(config.getPoolName() + "_" + poolNameCounter.getAndIncrement());

        config.setUsername(dsProperties.getUsername());
        config.setPassword(dsProperties.getPassword());
        config.setDriverClassName(dsProperties.getDriverClassName());
        System.out.println("Создание нового datasource с параметрами: " + config.getSchema() + " " + config.getJdbcUrl() + " " +     config.getPoolName());
        return new HikariDataSource(config);
    }

    public void clearCurrentContext() {
        ShardDataSourceContextHolder.clear();
    }
}

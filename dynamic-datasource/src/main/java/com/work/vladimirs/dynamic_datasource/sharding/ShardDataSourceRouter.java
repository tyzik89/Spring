package com.work.vladimirs.dynamic_datasource.sharding;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Маршрутизатор для DataSources
 */
public class ShardDataSourceRouter extends AbstractRoutingDataSource {

    private final Map<Object, Object> targetDataSources = new ConcurrentHashMap<>();

    /**
     * Определение текущего ключа источника данных
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return ShardDataSourceContextHolder.getDataSourceShardKey();
    }

    public void addDataSource(String shardKey, DataSource dataSource) {
        targetDataSources.put(shardKey, dataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet(); // Обновляем настройки
    }

    public boolean isDataSourcePresent(String shardKey) {
        return targetDataSources.containsKey(shardKey);
    }

    public void showTargetDs () {
        System.out.println(targetDataSources.size());
        for (Map.Entry<Object, Object> objectObjectEntry : targetDataSources.entrySet()) {
            System.out.println(objectObjectEntry.getKey() + " : " + objectObjectEntry.getValue());
        }
    }
}

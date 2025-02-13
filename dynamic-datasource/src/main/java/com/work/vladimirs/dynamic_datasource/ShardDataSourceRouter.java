package com.work.vladimirs.dynamic_datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ShardDataSourceRouter extends AbstractRoutingDataSource {

    private final Map<Object, Object> targetDataSources = new ConcurrentHashMap<>();

    @Override
    protected Object determineCurrentLookupKey() {
        return ShardSourceContextHolder.getDataSourceKey();
    }

    public void addDataSource(String key, DataSource dataSource) {
        targetDataSources.put(key, dataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet(); // Обновляем настройки
    }

    public void showTargetDs () {
        System.out.println(targetDataSources.size());
        for (Map.Entry<Object, Object> objectObjectEntry : targetDataSources.entrySet()) {
            System.out.println(objectObjectEntry.getKey() + " : " + objectObjectEntry.getValue());
        }
    }
}

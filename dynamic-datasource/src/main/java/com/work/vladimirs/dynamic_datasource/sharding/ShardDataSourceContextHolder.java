package com.work.vladimirs.dynamic_datasource.sharding;

/**
 * Держатель контекста (текущего ключа DataSource для шарда)
 */
public final class ShardDataSourceContextHolder {
    /**
     * Контекст (фактический DataSource) привязан к выполняющемуся потоку, поэтому ThreadLocal
     */
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setDataSourceShardKey(String key) {
        CONTEXT.set(key);
    }

    public static String getDataSourceShardKey() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}

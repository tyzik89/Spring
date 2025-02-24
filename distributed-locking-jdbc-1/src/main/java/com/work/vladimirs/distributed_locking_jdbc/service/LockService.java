package com.work.vladimirs.distributed_locking_jdbc.service;

import org.springframework.stereotype.Service;
import org.springframework.integration.jdbc.lock.*;

//@Service
public class LockService {

    private final JdbcLockRegistry lockRegistry;

    public LockService(JdbcLockRegistry lockRegistry) {
        this.lockRegistry = lockRegistry;
    }

//    public void acquireLock(String lockName, String podName) {
//        if (lockRegistry.obtain(lockName)) {
//            // Здесь можно добавить логику для сохранения имени пода в таблицу
//            // Например, использовать JDBC для выполнения SQL-запроса
//            String sql = "UPDATE locks SET pod_name = ? WHERE lock_name = ?";
//            // Выполнить запрос с использованием JdbcTemplate или другого механизма
//            // jdbcTemplate.update(sql, podName, lockName);
//        } else {
//            // Обработка случая, когда блокировка не может быть захвачена
//            System.out.println("Не удалось захватить блокировку: " + lockName);
//        }
//    }
//
//    public void releaseLock(String lockName) {
//        lockRegistry.release(lockName);
//        // Здесь можно добавить логику для удаления имени пода, если это необходимо
//        String sql = "UPDATE locks SET pod_name = NULL WHERE lock_name = ?";
//        // Выполнить запрос с использованием JdbcTemplate или другого механизма
//        // jdbcTemplate.update(sql, lockName);
//    }
}

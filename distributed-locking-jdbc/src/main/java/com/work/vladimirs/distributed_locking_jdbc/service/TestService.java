package com.work.vladimirs.distributed_locking_jdbc.service;

import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Service
@EnableScheduling
public class TestService {

    private final LockRegistry lockRegistry;

    public TestService(LockRegistry lockRegistry) {
        this.lockRegistry = lockRegistry;
    }

    @Scheduled(fixedDelay = 10000, timeUnit = TimeUnit.MILLISECONDS)
    public void isolateProcess() throws InterruptedException {
        Lock lock = lockRegistry.obtain("the-lock");
        System.out.println("Trying to get lock");
        if (lock.tryLock()) {
            System.out.println("Lock has been acquired");
            try {
                Thread.sleep(5000);
            } finally {
                lock.unlock();
                System.out.println("Lock has been released");
            }
        } else {
            System.out.println("Could not acquire lock, try again later");
        }
    }
}

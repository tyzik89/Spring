package com.work.vladimirs.distributed_locking_jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("ru.work.vladimirs")
public class DistributedLockingJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedLockingJdbcApplication.class, args);
	}

}

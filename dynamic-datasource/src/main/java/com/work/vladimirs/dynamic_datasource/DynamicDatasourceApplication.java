package com.work.vladimirs.dynamic_datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ConfigurationPropertiesScan("ru.work.vladimirs")
public class DynamicDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicDatasourceApplication.class, args);
	}

}

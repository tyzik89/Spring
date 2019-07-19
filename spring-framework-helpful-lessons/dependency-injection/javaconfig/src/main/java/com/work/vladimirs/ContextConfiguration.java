package com.work.vladimirs;

import org.springframework.context.annotation.Bean;

public class ContextConfiguration {

    @Bean
    public BaseRepository baseRepository() {
        return new BaseRepository();
    }

    @Bean
    public BaseService baseService() {
        return new BaseService();
    }

    @Bean
    public DependentService dependent() {
        return new DependentService(baseRepository(), baseService());
    }
}

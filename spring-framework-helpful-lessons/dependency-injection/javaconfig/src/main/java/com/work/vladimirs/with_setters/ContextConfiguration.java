package com.work.vladimirs.with_setters;

import com.work.vladimirs.BaseRepository;
import com.work.vladimirs.BaseService;
import com.work.vladimirs.DependentService;
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
    public DependentServiceWithSetter dependent() {
        DependentServiceWithSetter dependentServiceWithSettere = new DependentServiceWithSetter();

        dependentServiceWithSettere.setBaseRepository(baseRepository());
        dependentServiceWithSettere.setBaseService(baseService());

        return dependentServiceWithSettere;
    }
}

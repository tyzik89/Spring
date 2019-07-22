package com.work.vladimirs.with_setters;

import com.work.vladimirs.BaseRepository;
import com.work.vladimirs.BaseService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class DependentServiceWithSetter {

    private BaseRepository baseRepository;
    private BaseService baseService;

    @Inject
    public void setBaseRepository(BaseRepository repository) {
        this.baseRepository = repository;
    }

    @Inject
    public void setBaseService(BaseService base) {
        this.baseService = base;
    }

    public String process() {
        return baseService.isMany(baseRepository.count()) ? "Too big number" : "Everything is fine";
    }
}

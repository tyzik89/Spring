package com.work.vladimirs.with_setters;

import com.work.vladimirs.BaseRepository;
import com.work.vladimirs.BaseService;

public class DependentServiceWithSetter {

    private BaseRepository baseRepository;
    private BaseService baseService;

    public void setBaseRepository(BaseRepository repository) {
        this.baseRepository = repository;
    }

    public void setBaseService(BaseService base) {
        this.baseService = base;
    }

    public String process() {
        return baseService.isMany(baseRepository.count()) ? "Too big number" : "Everything is fine";
    }
}

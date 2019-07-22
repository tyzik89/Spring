package com.work.vladimirs.with_setters;

import com.work.vladimirs.BaseRepository;
import com.work.vladimirs.BaseService;

public class DependentServiceWithSetter {

    private BaseRepository baseRepository;
    private BaseService baseService;

    public void setBaseRepository(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    public void setBaseService(BaseService baseService) {
        this.baseService = baseService;
    }

    public String process() {
        return baseService.isMany(baseRepository.count()) ? "Too big number" : "Everything is fine";
    }
}

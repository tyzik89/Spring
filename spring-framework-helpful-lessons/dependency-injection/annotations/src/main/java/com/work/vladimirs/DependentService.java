package com.work.vladimirs;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class DependentService {

    private final BaseRepository baseRepository;
    private final BaseService baseService;

    @Inject
    public DependentService(final BaseRepository repository, final BaseService base) {
        this.baseRepository = repository;
        this.baseService = base;
    }

    public String process() {
        return baseService.isMany(baseRepository.count()) ? "Too big number" : "Everything is fine";
    }
}

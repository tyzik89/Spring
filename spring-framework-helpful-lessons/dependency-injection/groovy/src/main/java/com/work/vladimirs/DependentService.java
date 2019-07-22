package com.work.vladimirs;

public class DependentService {

    private final BaseRepository baseRepository;
    private final BaseService baseService;

    public DependentService(final BaseRepository repository, final BaseService base) {
        this.baseRepository = repository;
        this.baseService = base;
    }

    public String process() {
        return baseService.isMany(baseRepository.count()) ? "Too big number" : "Everything is fine";
    }
}

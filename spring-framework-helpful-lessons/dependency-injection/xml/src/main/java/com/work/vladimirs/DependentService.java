package com.work.vladimirs;

public class DependentService {

    private final BaseRepository baseRepository;
    private final BaseService baseService;

    public DependentService(final BaseRepository baseRepository, final BaseService baseService) {
        this.baseRepository = baseRepository;
        this.baseService = baseService;
    }

    public String process() {
        return baseService.isMany(baseRepository.count()) ? "Too big number" : "Everything is fine";
    }
}

package com.work.vladimirs.redis_caching.service;

import com.work.vladimirs.redis_caching.model.Magazine;
import com.work.vladimirs.redis_caching.repository.MagazineRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// @CacheConfig аннотация конфигурирует все кэш-операции данного класса.
@CacheConfig(cacheNames = "MagazineCache", cacheManager = "alternateCacheManager")
@RequiredArgsConstructor
@Slf4j
public class MagazineService {

    private final MagazineRepository magazineRepository;

    // @Cacheable — говорит, что результат работы метода попадает в кэш
    // и при последующем вызове берётся оттуда (по ключу, указанному в параметре).
    @Cacheable
    public List<Magazine> findAll() {
        return magazineRepository.findAll();
    }

    @Cacheable(key = "#id")
    public Magazine findById(Long id) {
        log.info("Find magazine by id: {}", id);
        return magazineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found by id " + id));
    }

    // @CachePut — позволяет обновить запись в кэше.
    @CachePut(key = "#Magazine.id")
    public Magazine save(Magazine magazine) {
        return magazineRepository.save(magazine);
    }

    // @CacheEvict — удаляет запись из кэша.
    @CacheEvict(key = "#id")
    public void deleteById(Long id) {
        magazineRepository.deleteById(id);
    }
}

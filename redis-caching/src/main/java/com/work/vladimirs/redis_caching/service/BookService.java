package com.work.vladimirs.redis_caching.service;

import com.work.vladimirs.redis_caching.model.Book;
import com.work.vladimirs.redis_caching.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// @CacheConfig аннотация конфигурирует все кэш-операции данного класса.
@CacheConfig(cacheNames = "BookCache")
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    // @Cacheable — говорит, что результат работы метода попадает в кэш
    // и при последующем вызове берётся оттуда (по ключу, указанному в параметре).
    @Cacheable
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Cacheable(key = "#id")
    public Book findById(Long id) {
        log.info("Find book by id: {}", id);
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found by id " + id));
    }

    // @CachePut — позволяет обновить запись в кэше.
    @CachePut(key = "#book.id")
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @CachePut(condition = "#result.size > 0")
    public List<Book> saveAll(List<Book> books) {
        return bookRepository.saveAll(books);
    }

    // @CacheEvict — удаляет запись из кэша.
    @CacheEvict(key = "#id")
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}

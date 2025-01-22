package com.work.vladimirs.redis_caching.service;

import com.work.vladimirs.redis_caching.model.Book;
import com.work.vladimirs.redis_caching.repository.BookRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "BookCache")
public class BookService {

    private BookRepository bookRepository;

    @Cacheable
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}

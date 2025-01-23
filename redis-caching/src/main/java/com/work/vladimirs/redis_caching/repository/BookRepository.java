package com.work.vladimirs.redis_caching.repository;

import com.work.vladimirs.redis_caching.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}

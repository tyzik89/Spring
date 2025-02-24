package com.work.vladimirs.redis_caching.controller;

import com.work.vladimirs.redis_caching.model.Book;
import com.work.vladimirs.redis_caching.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        long startTime = System.currentTimeMillis();
        List<Book> books = bookService.findAll();
        long duration = System.currentTimeMillis() - startTime;

        log.info("Duration: {}", duration);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.save(book));
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<Book>> createAll(@RequestBody List<Book> books) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.saveAll(books));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(bookService.save(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }
}

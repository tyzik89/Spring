package com.work.vladimirs.redis_caching.controller;

import com.work.vladimirs.redis_caching.model.Magazine;
import com.work.vladimirs.redis_caching.service.MagazineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/magazines")
@RequiredArgsConstructor
@Slf4j
public class MagazineController {

    private final MagazineService magazineService;

    @GetMapping
    public ResponseEntity<List<Magazine>> findAll() {
        long startTime = System.currentTimeMillis();
        List<Magazine> magazines = magazineService.findAll();
        long duration = System.currentTimeMillis() - startTime;

        log.info("Duration: {}", duration);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(magazines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Magazine> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(magazineService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Magazine> create(@RequestBody Magazine magazine) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(magazineService.save(magazine));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Magazine> update(@PathVariable Long id, @RequestBody Magazine magazine) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(magazineService.save(magazine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        magazineService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }
}

package com.work.vladimirs.redis_caching.repository;

import com.work.vladimirs.redis_caching.model.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {

}

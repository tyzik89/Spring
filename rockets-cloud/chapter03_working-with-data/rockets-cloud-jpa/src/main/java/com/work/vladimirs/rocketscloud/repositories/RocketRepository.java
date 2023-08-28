package com.work.vladimirs.rocketscloud.repositories;

import com.work.vladimirs.rocketscloud.inventory.Rocket;
import org.springframework.data.repository.CrudRepository;

public interface RocketRepository extends CrudRepository<Rocket, Long> {
}

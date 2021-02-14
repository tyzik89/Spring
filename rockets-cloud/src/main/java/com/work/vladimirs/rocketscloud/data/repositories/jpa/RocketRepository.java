package com.work.vladimirs.rocketscloud.data.repositories.jpa;

import com.work.vladimirs.rocketscloud.models.inventory.Rocket;
import org.springframework.data.repository.CrudRepository;

public interface RocketRepository extends CrudRepository<Rocket, Long> {
}

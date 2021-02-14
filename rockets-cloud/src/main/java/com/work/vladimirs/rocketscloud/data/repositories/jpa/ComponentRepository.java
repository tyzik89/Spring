package com.work.vladimirs.rocketscloud.data.repositories.jpa;

import com.work.vladimirs.rocketscloud.models.inventory.Component;
import org.springframework.data.repository.CrudRepository;

public interface ComponentRepository extends CrudRepository<Component, String> {
}

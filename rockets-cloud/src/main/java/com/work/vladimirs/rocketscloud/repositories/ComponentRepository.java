package com.work.vladimirs.rocketscloud.repositories;

import com.work.vladimirs.rocketscloud.inventory.Component;

import java.util.Optional;

public interface ComponentRepository {

    Iterable<Component> findAll();

    Optional<Component> findById(String id);

    Component save(Component component);
}

package com.work.vladimirs.rocketscloud.data.repositories;

import com.work.vladimirs.rocketscloud.models.inventory.Component;

public interface ComponentRepository {

    Iterable<Component> findAll();

    Component findById(String id);

    Component save(Component component);
}

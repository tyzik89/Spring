package com.work.vladimirs.rocketscloud.data.repositories.jdbc;

import com.work.vladimirs.rocketscloud.models.inventory.Component;

@Deprecated
public interface ComponentRepository {

    Iterable<Component> findAll();

    Component findById(String id);

    Component save(Component component);
}

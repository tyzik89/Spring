package com.work.vladimirs.rocketscloud.data.repositories;

import com.work.vladimirs.rocketscloud.models.inventory.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComponentRepositoryJdbcImpl implements ComponentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<Component> findAll() {
        return null;
    }

    @Override
    public Component findOne(String id) {
        return null;
    }

    @Override
    public Component save(Component component) {
        return null;
    }
}

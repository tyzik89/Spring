package com.work.vladimirs.rocketscloud.data.repositories;

import com.work.vladimirs.rocketscloud.models.inventory.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ComponentRepositoryJdbcImpl implements ComponentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<Component> findAll() {
        String query = "select id, name, type from Components";
        return jdbcTemplate.query(
                query,
                this::mapRowToComponent
        );
    }

    @Override
    public Component findById(String id) {
        String query = "select id, name, type from Components where id = ?";
        return jdbcTemplate.queryForObject(
                query,
                this::mapRowToComponent,
                id
        );
    }

    @Override
    public Component save(Component component) {
        String query = "insert into Components (id, name, type) values (?, ?, ?)";
        int row = jdbcTemplate.update(
                query,
                component.getId(),
                component.getName(),
                component.getType().toString()
        );
        return component;
    }

    private Component mapRowToComponent(ResultSet resultSet, int rowNum) throws SQLException {
        return new Component(
                resultSet.getString("id"),
                resultSet.getString("name"),
                Component.Type.valueOf(resultSet.getString("type"))
        );
    }
}

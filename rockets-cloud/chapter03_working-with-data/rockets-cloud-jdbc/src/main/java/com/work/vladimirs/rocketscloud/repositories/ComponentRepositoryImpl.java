package com.work.vladimirs.rocketscloud.repositories;

import com.work.vladimirs.rocketscloud.inventory.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// Т.к. Spring data jdbc автоматом создаёт реализации, надобность в этом репозитории отпадает
@Repository
public class ComponentRepositoryImpl implements ComponentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ComponentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Component> findAll() {
        return jdbcTemplate.query(
                "select id, name, type from Component",
                this::mapRowToComponent);
    }

    private Component mapRowToComponent(ResultSet resultSet, int rowNum) throws SQLException {
        return new Component(
                resultSet.getString("id"),
                resultSet.getString("name"),
                Component.Type.valueOf(resultSet.getString("type")));
    }

//    @Override
//    public Optional<Component> findById(String id) {
//        List<Component> results = jdbcTemplate.query(
//                "select id, name, type from Component where id = ?",
//                this::mapRowToComponent,
//                id);
//        return results.size() == 0?
//                Optional.empty() :
//                Optional.of(results.get(0));
//    }

    /**
     * Метод с явной реализацией RowMapper
     */
    @Override
    public Optional<Component> findById(String id) {
        List<Component> results = jdbcTemplate.query(
                "select id, name, type from Component where id = ?",
                new RowMapper<Component>() {
                    @Override
                    public Component mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        return new Component(
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                Component.Type.valueOf(resultSet.getString("type")));
                    }
                },
                id);
        return results.size() == 0?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public Component save(Component component) {
        jdbcTemplate.update(
                "insert into Component(id, name, type) values (?, ?, ?)",
                component.getId(),
                component.getName(),
                component.getType().toString());
        return component;
    }
}

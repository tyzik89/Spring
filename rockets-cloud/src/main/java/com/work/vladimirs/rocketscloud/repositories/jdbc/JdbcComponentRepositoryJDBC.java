package com.work.vladimirs.rocketscloud.repositories.jdbc;

import com.work.vladimirs.rocketscloud.inventory.jdbc.ComponentJdbc;
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
@Deprecated
public class JdbcComponentRepositoryJDBC implements ComponentRepositoryJDBC {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcComponentRepositoryJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<ComponentJdbc> findAll() {
        return jdbcTemplate.query(
                "select id, name, type from Component",
                this::mapRowToComponent);
    }

    private ComponentJdbc mapRowToComponent(ResultSet resultSet, int rowNum) throws SQLException {
        return new ComponentJdbc(
                resultSet.getString("id"),
                resultSet.getString("name"),
                ComponentJdbc.Type.valueOf(resultSet.getString("type")));
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
    public Optional<ComponentJdbc> findById(String id) {
        List<ComponentJdbc> results = jdbcTemplate.query(
                "select id, name, type from Component where id = ?",
                new RowMapper<ComponentJdbc>() {
                    @Override
                    public ComponentJdbc mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        return new ComponentJdbc(
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                ComponentJdbc.Type.valueOf(resultSet.getString("type")));
                    }
                },
                id);
        return results.size() == 0?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public ComponentJdbc save(ComponentJdbc component) {
        jdbcTemplate.update(
                "insert into Component(id, name, type) values (?, ?, ?)",
                component.getId(),
                component.getName(),
                component.getType().toString());
        return component;
    }
}

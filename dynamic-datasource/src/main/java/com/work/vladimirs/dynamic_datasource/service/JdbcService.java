package com.work.vladimirs.dynamic_datasource.service;

import com.work.vladimirs.dynamic_datasource.model.TestEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class JdbcService {

    private static final String SQL_GET_ALL = "SELECT * FROM entity";
    private static final String SQL_GET_BY_ID = "SELECT * FROM entity WHERE id = :id";
    private static final String SQL_INSERT = "INSERT INTO entity (id, field) VALUES (:id, :field)";

    private final NamedParameterJdbcTemplate jdbcNamedTemplate;

    public JdbcService( @Qualifier("jdbcNamedTemplate") NamedParameterJdbcTemplate jdbcNamedTemplate) {
        this.jdbcNamedTemplate = jdbcNamedTemplate;
    }


    public List<TestEntity> getAll(){
        return jdbcNamedTemplate.queryForList(SQL_GET_ALL, Collections.EMPTY_MAP);
    }

    public TestEntity getById(Long id){
        SqlParameterSource parameterSource = new MapSqlParameterSource( "id", id);
        return jdbcNamedTemplate.queryForObject(SQL_GET_BY_ID, parameterSource, (rs, rowNum) -> {
            TestEntity testEntity = new TestEntity();
            testEntity.setId(rs.getLong("id"));
            testEntity.setField(rs.getString("field"));
            return testEntity;
        });
    }

    public int save(Long id, String field){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        namedParameters.addValue("field", field);
        return jdbcNamedTemplate.update(SQL_INSERT, namedParameters);
    }
}

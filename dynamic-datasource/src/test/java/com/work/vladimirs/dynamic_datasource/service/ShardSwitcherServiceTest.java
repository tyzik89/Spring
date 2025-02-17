package com.work.vladimirs.dynamic_datasource.service;

import com.work.vladimirs.dynamic_datasource.sharding.ShardDataSourceContextHolder;
import com.work.vladimirs.dynamic_datasource.sharding.ShardDataSourceRouter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest(classes = {ShardSwitcherService.class})
@Import({JdbcConfigTestConfiguration.class})
class ShardSwitcherServiceTest {

    @Autowired
    ShardSwitcherService shardSwitcherService;

    @Autowired
    ShardDataSourceRouter shardDataSource;

    @BeforeAll
    static void init(@Autowired JdbcTemplate defaultJdbcTemplate,
                     @Autowired JdbcTemplate secondJdbcTemplate) {
        defaultJdbcTemplate.execute("CREATE TABLE test (id BIGINT, name VARCHAR(50))");

        secondJdbcTemplate.execute("CREATE TABLE test (id BIGINT, name VARCHAR(50))");
        secondJdbcTemplate.update("INSERT INTO test (id, name) VALUES (?, ?)", 1, "test1");
        secondJdbcTemplate.update("INSERT INTO test (id, name) VALUES (?, ?)", 2, "test2");
    }

    @Test
    @DisplayName("switchDataSourceContext должен проверять новый ключ шарда на отсутствие в контексте, добавлять его в контекст и переключаться на нужный DataSource")
    void switchDataSourceContext_shouldCheckShardKey_addNewShardDataSourceIntoContext_switchDataSourceContext() throws SQLException {
        //--given
        try (Connection connection = shardDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM test")) {

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long aLong = resultSet.getLong(1);
                assertThat(aLong).isEqualTo(0);
            }
        }

        String newDataSourceKey = "jdbc:h2:mem:dbShard2;MODE=PostgreSQL;DB_CLOSE_DELAY=-1";

        //--when
        shardSwitcherService.switchDataSourceContext(newDataSourceKey);

        //--then
        assertThat(shardDataSource.isDataSourcePresent(newDataSourceKey)).isTrue();
        assertThat(shardDataSource.getResolvedDataSources()).containsKey(newDataSourceKey);
        assertThat(ShardDataSourceContextHolder.getDataSourceShardKey()).isEqualTo(newDataSourceKey);

        try (Connection connection = shardDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM test")) {

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                long aLong = resultSet.getLong(1);
                assertThat(aLong).isEqualTo(2);
            }
        }
    }

    @Test
    void clearCurrentContext() {
    }

    @AfterEach
    void tearDown() {
        ShardDataSourceContextHolder.clear();
    }
}
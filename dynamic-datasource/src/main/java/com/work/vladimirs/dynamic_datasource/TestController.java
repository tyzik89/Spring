package com.work.vladimirs.dynamic_datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping("/api/v1/test")
@Slf4j
public class TestController {

    private final JdbcService jdbcService;

    private final ShardDataSourceRouter shardDataSource;

    private final DataSourceProperties dsProperties;

    public TestController(JdbcService jdbcService, ShardDataSourceRouter shardDataSource, @Qualifier("dsProperties") DataSourceProperties dsProperties) {
        this.jdbcService = jdbcService;
        this.shardDataSource = shardDataSource;
        this.dsProperties = dsProperties;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TestEntity>> findAll(@RequestParam String url) {
        ShardSourceContextHolder.setDataSourceKey(url);
        List<TestEntity> all = jdbcService.getAll();
        ShardSourceContextHolder.clear();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(all);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TestEntity> findById(@PathVariable Long id, @RequestParam String url) {
        ShardSourceContextHolder.setDataSourceKey(url);
        TestEntity byId = jdbcService.getById(id);
        ShardSourceContextHolder.clear();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(byId);
    }

    @GetMapping("/save")
    public ResponseEntity<Integer> save(@RequestParam Long id, @RequestParam String field, @RequestParam String url) {
        ShardSourceContextHolder.setDataSourceKey(url);
        int save = jdbcService.save(id, field);
        ShardSourceContextHolder.clear();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(save);
    }

/*====================================================================================================================*/

    @GetMapping("/addDs")
    public ResponseEntity<String> addDs(@RequestParam String url) {
        shardDataSource.addDataSource(url, createDataSource(url, dsProperties));
        return ResponseEntity
                .status(HttpStatus.OK).body("OK");
    }

    private DataSource createDataSource(String url, DataSourceProperties properties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(properties.getUsername());
        config.setPassword(properties.getPassword());
        return new HikariDataSource(config);
    }

    @GetMapping("/showDs")
    public void showDs() {
        shardDataSource.showTargetDs();
    }
}

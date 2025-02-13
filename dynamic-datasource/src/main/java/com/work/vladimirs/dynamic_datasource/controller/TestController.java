package com.work.vladimirs.dynamic_datasource.controller;

import com.work.vladimirs.dynamic_datasource.model.TestEntity;
import com.work.vladimirs.dynamic_datasource.service.JdbcService;
import com.work.vladimirs.dynamic_datasource.service.ShardSwitcherService;
import com.work.vladimirs.dynamic_datasource.sharding.ShardDataSourceContextHolder;
import com.work.vladimirs.dynamic_datasource.sharding.ShardDataSourceRouter;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
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

    private final ShardSwitcherService shardSwitcherService;

    private final ShardDataSourceRouter shardDataSource;

    public TestController(JdbcService jdbcService, ShardSwitcherService shardSwitcherService, ShardDataSourceRouter shardDataSource) {
        this.jdbcService = jdbcService;
        this.shardSwitcherService = shardSwitcherService;
        this.shardDataSource = shardDataSource;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TestEntity>> findAll(@RequestParam String url) {
        shardSwitcherService.switchDataSourceContext(url);
        List<TestEntity> all = jdbcService.getAll();
        shardSwitcherService.clearCurrentContext();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(all);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TestEntity> findById(@PathVariable Long id, @RequestParam String url) {
        shardSwitcherService.switchDataSourceContext(url);
        TestEntity byId = jdbcService.getById(id);
        shardSwitcherService.clearCurrentContext();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(byId);
    }

    @GetMapping("/save")
    public ResponseEntity<Integer> save(@RequestParam Long id, @RequestParam String field, @RequestParam String url) {
        shardSwitcherService.switchDataSourceContext(url);
        int save = jdbcService.save(id, field);
        shardSwitcherService.clearCurrentContext();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(save);
    }

/*====================================================================================================================*/

//    @GetMapping("/addDs")
//    public ResponseEntity<String> addDs(@RequestParam String url) {
//        shardDataSource.addDataSource(url, createDataSource(url, dsProperties));
//        return ResponseEntity
//                .status(HttpStatus.OK).body("OK");
//    }

    private DataSource createDataSource(String url, DataSourceProperties properties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setPoolName("my-new-pool");
        config.setUsername(properties.getUsername());
        config.setPassword(properties.getPassword());
        return new HikariDataSource(config);
    }

    @GetMapping("/showDs")
    public void showDs() {
        shardDataSource.showTargetDs();
    }
}

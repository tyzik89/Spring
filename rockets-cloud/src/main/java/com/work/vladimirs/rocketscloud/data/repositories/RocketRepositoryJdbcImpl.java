package com.work.vladimirs.rocketscloud.data.repositories;

import com.work.vladimirs.rocketscloud.controllers.DesignRocketController;
import com.work.vladimirs.rocketscloud.models.inventory.Component;
import com.work.vladimirs.rocketscloud.models.inventory.Rocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

public class RocketRepositoryJdbcImpl implements RocketRepository {

    private static final Logger LOG = LoggerFactory.getLogger(RocketRepositoryJdbcImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Rocket save(Rocket rocket) {
        long rocketId = saveRocketInfo(rocket);
        LOG.debug("rocketId: {}", rocketId);
        rocket.setId(rocketId);
        for (Component component : rocket.getComponents()) {
            saveComponentToRocket(component, rocketId);
        }
        return rocket;
    }

    private void saveComponentToRocket(Component component, long rocketId) {
        String query = "insert into Rocket_Components (rocket, component) values (?, ?)";
        jdbcTemplate.update(
                query,
                rocketId,
                component.getId()
        );
    }

    private long saveRocketInfo(Rocket rocket) {
        String query = "insert into Rocket (name, createdAt) values (?, ?)";
        rocket.setCreateAt(new Date());

        PreparedStatementCreator creator = new PreparedStatementCreatorFactory(
                query,
                Types.VARCHAR,
                Types.TIMESTAMP
        ).newPreparedStatementCreator(
                Arrays.asList(
                        rocket.getName(),
                        new Timestamp(rocket.getCreateAt().getTime()))
        );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(creator, keyHolder);

        return keyHolder.getKey().longValue();
    }
}

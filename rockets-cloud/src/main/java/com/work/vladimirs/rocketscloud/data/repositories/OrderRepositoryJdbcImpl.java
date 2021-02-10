package com.work.vladimirs.rocketscloud.data.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.vladimirs.rocketscloud.models.inventory.Rocket;
import com.work.vladimirs.rocketscloud.models.services.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepositoryJdbcImpl implements OrderRepository {

    private static final Logger LOG = LoggerFactory.getLogger(OrderRepositoryJdbcImpl.class);

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderRocketInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public OrderRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.orderInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Rocket_Order")
                .usingGeneratedKeyColumns("id");

        this.orderRocketInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Rocket_Order_Rockets");

        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);
        List<Rocket> rockets = order.getRockets();
        for (Rocket rocket : rockets) {
            saveRocketToOrder(rocket, orderId);
        }
        return order;
    }

    private void saveRocketToOrder(Rocket rocket, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("rocketOrder", orderId);
        values.put("rocket", rocket.getId());
        orderRocketInserter.execute(values);
    }

    private long saveOrderDetails(Order order) {
        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());
        long orderId = orderInserter
                .executeAndReturnKey(values)
                .longValue();
        LOG.info("orderId: {}", orderId);
        return orderId;
    }
}

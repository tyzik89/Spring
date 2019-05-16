package com.work.vladimirs.shawermacloud.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.vladimirs.shawermacloud.entity.Order;
import com.work.vladimirs.shawermacloud.entity.Shawerma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderShawermaInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.orderInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Shawerma_Order")
                .usingGeneratedKeyColumns("id");
        this.orderShawermaInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Shawerma_Order_Shawermas");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order newOrder) {
        newOrder.setPlacedAt(new Date());
        long orderId = saveOrderDetails(newOrder);
        newOrder.setId(orderId);

        List<Shawerma> shawermas = newOrder.getShawermas();
        for (Shawerma shawerma : shawermas) {
            saveShawermaToOrder(shawerma, orderId);
        }

        return newOrder;
    }

    private long saveOrderDetails(Order order) {
        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());

        //Всттавка данных ордера и возврат сгенерированного id
        long orderId = orderInserter
                .executeAndReturnKey(values)
                .longValue();
        return orderId;
    }

    private void saveShawermaToOrder(Shawerma shawerma, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("shawermaOrder", orderId);
        values.put("shawerma", shawerma.getId());
        orderShawermaInserter.execute(values);
    }
}

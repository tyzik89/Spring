package com.work.vladimirs.rocketscloud.repositories.jdbc;

import com.work.vladimirs.rocketscloud.inventory.Component;
import com.work.vladimirs.rocketscloud.inventory.Rocket;
import com.work.vladimirs.rocketscloud.inventory.RocketOrder;
import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

// Т.к. Spring data jdbc автоматом создаёт реализации, надобность в этом репозитории отпадает
//@Repository
@Deprecated
public class JdbcOrderRepositoryJDBC implements OrderRepositoryJDBC {

    private final JdbcOperations jdbcOperations;

    @Autowired
    public JdbcOrderRepositoryJDBC(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public RocketOrder save(RocketOrder rocketOrder) {
        PreparedStatementCreatorFactory factory =
                new PreparedStatementCreatorFactory(
                        "insert into Rocket_Order"
                        + "(delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, "
                        + "cc_number, cc_expiration, cc_cvv, placed_at) "
                        + "values (?,?,?,?,?,?,?,?,?) ",
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP);

        factory.setReturnGeneratedKeys(true);
        rocketOrder.setPlaceAt(new Date());

        PreparedStatementCreator statementCreator =
                factory.newPreparedStatementCreator(
                        Arrays.asList(
                                rocketOrder.getDeliveryName(),
                                rocketOrder.getDeliveryStreet(),
                                rocketOrder.getDeliveryCity(),
                                rocketOrder.getDeliveryState(),
                                rocketOrder.getDeliveryZip(),
                                rocketOrder.getCcNumber(),
                                rocketOrder.getCcExpiration(),
                                rocketOrder.getCcCVV(),
                                rocketOrder.getPlaceAt()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(statementCreator, keyHolder);

        long orderId = keyHolder.getKey().longValue();
        rocketOrder.setId(orderId);

        List<Rocket> rockets = rocketOrder.getRockets();
        int orderKey = 0;
        for (Rocket rocket : rockets) {
            saveRocket(orderId, orderKey++, rocket);
        }
        return rocketOrder;
    }

    private long saveRocket(long orderId, int orderKey, Rocket rocket) {
        rocket.setCreatedAt(new Date());
        PreparedStatementCreatorFactory creatorFactory =
                new PreparedStatementCreatorFactory(
                        "insert into Rocket "
                        + "(name, created_at, rocket_order, rocket_order_key) "
                        + "values (?, ?, ?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG);
        
        creatorFactory.setReturnGeneratedKeys(true);
        
        PreparedStatementCreator statementCreator = 
                creatorFactory.newPreparedStatementCreator(
                        Arrays.asList(
                                rocket.getName(),
                                rocket.getCreatedAt(),
                                orderId,
                                orderKey));
        
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(statementCreator, keyHolder);
        long rocketId = keyHolder.getKey().longValue();
        rocket.setId(rocketId);
        
        saveComponentRefs(rocketId, rocket.getComponents());
        return rocketId;
    }

    private void saveComponentRefs(long rocketId, List<Component> components) {
        int key = 0;
        for (Component component : components) {
            jdbcOperations.update(
                    "insert into Component_Ref (component, rocket, rocket_key) "
                    + "values (?, ?, ?)",
                    component.getId(),
                    rocketId,
                    key++);
        }
    }
}

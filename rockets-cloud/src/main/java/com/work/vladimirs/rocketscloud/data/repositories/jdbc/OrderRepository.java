package com.work.vladimirs.rocketscloud.data.repositories.jdbc;

import com.work.vladimirs.rocketscloud.models.services.Order;

public interface OrderRepository {

    Order save(Order order);
}

package com.work.vladimirs.rocketscloud.data.repositories.jdbc;

import com.work.vladimirs.rocketscloud.models.services.Order;

@Deprecated
public interface OrderRepository {

    Order save(Order order);
}

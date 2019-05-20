package com.work.vladimirs.shawermacloud.repositories.JDBCTemplate;

import com.work.vladimirs.shawermacloud.entity.Order;

public interface OrderRepository {

    Order save(Order newOrder);
}

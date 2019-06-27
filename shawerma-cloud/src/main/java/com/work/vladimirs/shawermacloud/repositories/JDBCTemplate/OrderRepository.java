package com.work.vladimirs.shawermacloud.repositories.JDBCTemplate;

import com.work.vladimirs.shawermacloud.entity.Order;
import com.work.vladimirs.shawermacloud.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepository {

    Order save(Order newOrder);

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}

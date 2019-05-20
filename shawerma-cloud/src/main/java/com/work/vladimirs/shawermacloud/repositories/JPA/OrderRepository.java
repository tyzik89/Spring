package com.work.vladimirs.shawermacloud.repositories.JPA;

import com.work.vladimirs.shawermacloud.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}

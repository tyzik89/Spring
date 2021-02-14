package com.work.vladimirs.rocketscloud.data.repositories.jpa;

import com.work.vladimirs.rocketscloud.models.services.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}

package com.work.vladimirs.rocketscloud.data.repositories.jpa;

import com.work.vladimirs.rocketscloud.models.entities.User;
import com.work.vladimirs.rocketscloud.models.services.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}

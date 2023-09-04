package com.work.vladimirs.rocketscloud.services;

import com.work.vladimirs.rocketscloud.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class OrderAdminService {

    @Autowired
    private OrderRepository orderRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}

package com.work.vladimirs.model.service;

import com.work.vladimirs.model.entity.Order;

import java.util.List;

public interface OrderService {

    void save(Order order);

    void delete(Order order);

    List<Order> getAll();

    Order getById(Integer id);
}

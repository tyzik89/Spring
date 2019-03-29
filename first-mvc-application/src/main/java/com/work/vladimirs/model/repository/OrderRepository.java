package com.work.vladimirs.model.repository;

import com.work.vladimirs.model.entity.Order;

import java.util.List;

public interface OrderRepository {

    void save(Order order);

    void delete(Order order);

    List<Order> getAll();

    Order getById(Integer id);
}

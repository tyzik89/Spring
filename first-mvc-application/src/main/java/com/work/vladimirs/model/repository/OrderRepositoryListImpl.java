package com.work.vladimirs.model.repository;

import com.work.vladimirs.model.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryListImpl implements OrderRepository {

    private List<Order> orders = new ArrayList<Order>();

    public void save(Order order) {
        orders.add(order);
    }

    public void delete(Order order) {
        orders.remove(order);
    }

    public List<Order> getAll() {
        return orders;
    }

    public Order getById(Integer id) {
        return orders.get(id);
    }
}

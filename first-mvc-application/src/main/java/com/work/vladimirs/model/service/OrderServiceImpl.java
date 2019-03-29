package com.work.vladimirs.model.service;

import com.work.vladimirs.model.entity.Order;
import com.work.vladimirs.model.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void save(Order order) {
        if (order != null) {
            orderRepository.save(order);
        }
    }

    public void delete(Order order) {
        if(order!=null) {
            orderRepository.delete(order);
        }
    }

    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    public Order getById(Integer id) {
        return orderRepository.getById(id);
    }
}

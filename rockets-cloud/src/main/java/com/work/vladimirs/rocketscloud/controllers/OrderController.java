package com.work.vladimirs.rocketscloud.controllers;

import com.work.vladimirs.rocketscloud.inventory.RocketOrder;
import com.work.vladimirs.rocketscloud.repositories.jdbc.OrderRepositoryJDBC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("rocketOrder")
public class OrderController {

    private final OrderRepositoryJDBC orderRepositoryJDBC;

    public OrderController(OrderRepositoryJDBC orderRepositoryJDBC) {
        this.orderRepositoryJDBC = orderRepositoryJDBC;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid RocketOrder order,
                               Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        orderRepositoryJDBC.save(order);
        log.info("Order saved: {}", order);
        sessionStatus.setComplete(); // Очистка сеанса и объекта RocketOrder после подтверждения заказа
        return "redirect:/";
    }
}

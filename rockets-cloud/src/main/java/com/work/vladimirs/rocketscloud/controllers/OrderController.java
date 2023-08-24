package com.work.vladimirs.rocketscloud.controllers;

import com.work.vladimirs.rocketscloud.inventory.jdbc.RocketOrderJdbc;
import com.work.vladimirs.rocketscloud.repositories.jdbc.OrderRepositoryJDBC;
import com.work.vladimirs.rocketscloud.repositories.jpa.OrderRepositoryJPA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("rocketOrder")
public class OrderController {

    private final OrderRepositoryJDBC orderRepositoryJDBC;

    public OrderController(OrderRepositoryJDBC orderRepositoryJDBC,
                           OrderRepositoryJPA orderRepositoryJPA) {
        this.orderRepositoryJDBC = orderRepositoryJDBC;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute(name = "rocketOrder") RocketOrderJdbc rocketOrder,
                               Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        orderRepositoryJDBC.save(rocketOrder);
        log.info("Order saved: {}", rocketOrder);
        sessionStatus.setComplete(); // Очистка сеанса и объекта RocketOrder после подтверждения заказа
        return "redirect:/";
    }
}

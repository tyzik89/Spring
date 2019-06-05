package com.work.vladimirs.shawermacloud.controller;

import com.work.vladimirs.shawermacloud.entity.Order;
import com.work.vladimirs.shawermacloud.entity.User;
import com.work.vladimirs.shawermacloud.repositories.JDBCTemplate.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /*@GetMapping("/current")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }*/

    @GetMapping("/current")
    public String showOrderForm() {
        return "orderForm";
    }


    /*
        There are several ways to determine who the user is. These are a few of the most
        common ways:
         Inject a Principal object into the controller method
         Inject an Authentication object into the controller method
         Use SecurityContextHolder to get at the security context
         Use an @AuthenticationPrincipal annotated method
    */

    @PostMapping
    public String processOrder(@Valid Order order,
                               Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        //Set current user, who create order
        order.setUser(user);
        orderRepository.save(order);
        //Clear session after save order
        sessionStatus.setComplete();
        return "redirect:/";
    }
}

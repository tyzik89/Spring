package com.work.vladimirs.rocketscloud.controllers;

import com.work.vladimirs.rocketscloud.models.services.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("order") Order order, Errors errors) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        LOG.info("Order submitted: {}", order);
        return "redirect:/";
    }
}

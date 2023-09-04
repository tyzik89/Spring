package com.work.vladimirs.rocketscloud.controllers;

import com.work.vladimirs.rocketscloud.services.OrderAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OrderAdminService orderAdminService;
    
    @GetMapping("/deleteOrders")
    public String deleteAllOrders() {
        orderAdminService.deleteAllOrders();
        return "redirect:/";
    }
}
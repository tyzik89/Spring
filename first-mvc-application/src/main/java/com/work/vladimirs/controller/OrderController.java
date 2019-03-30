package com.work.vladimirs.controller;

import com.work.vladimirs.model.entity.Order;
import com.work.vladimirs.model.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getOrderedPage(Model model) {
        List<Order> orderList = orderService.getAll();
        model.addAttribute("orderList", orderList);
        return "order";
    }

    @RequestMapping(value = "/add-new-order", method = RequestMethod.GET)
    public String addNewOrderPage() {
        return "addNewOrder";
    }

    @RequestMapping(value="/add-new-order", method=RequestMethod.POST)
    public String addNewOrder(@RequestParam(value="title") String title, @RequestParam(value="price") Double price) {
        Order order = new Order();
        order.setTitle(title);
        order.setPrice(price);
        orderService.save(order);
        return "redirect:/";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteOrder(@PathVariable Integer id) {
        Order order = orderService.getById(id);
        orderService.delete(order);
        return "redirect:/";
    }

}

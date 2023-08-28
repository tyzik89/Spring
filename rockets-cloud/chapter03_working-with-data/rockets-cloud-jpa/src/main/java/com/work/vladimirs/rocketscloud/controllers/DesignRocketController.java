package com.work.vladimirs.rocketscloud.controllers;

import com.work.vladimirs.rocketscloud.inventory.Component;
import com.work.vladimirs.rocketscloud.inventory.Rocket;
import com.work.vladimirs.rocketscloud.inventory.RocketOrder;
import com.work.vladimirs.rocketscloud.repositories.ComponentRepository;
import com.work.vladimirs.rocketscloud.repositories.RocketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("rocketOrder")
public class DesignRocketController {

    @Autowired
    private ComponentRepository componentRepository;
    @Autowired
    private RocketRepository rocketRepository;


    @ModelAttribute(name = "rocketOrder")
    public RocketOrder order() {
        return new RocketOrder();
    }

    @ModelAttribute(name = "rocket")
    public Rocket rocket() {
        return new Rocket();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Component> components = new ArrayList<>();
        componentRepository.findAll().forEach(i -> components.add(i));

        Component.Type[] types = Component.Type.values();
        for (Component.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(components, type));
        }
        return "design";
    }

    private Iterable<Component> filterByType(List<Component> components, Component.Type type) {
        return components
                .stream()
                .filter(c -> type.equals(c.getType()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processRocket(@Valid Rocket rocket,
                                Errors errors,
                                @ModelAttribute(name = "rocketOrder") RocketOrder rocketOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        rocketOrder.addRocket(rocket);
        log.info("Processing rocket {}", rocket);
        return "redirect:/orders/current"; // redirect означает, что браузер должен отправить get с указанныем путём и открыть другую страницу
    }
}

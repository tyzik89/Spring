package com.work.vladimirs.rocketscloud.web;

import com.work.vladimirs.rocketscloud.data.repositories.jpa.RocketRepository;
import com.work.vladimirs.rocketscloud.data.repositories.jpa.ComponentRepository;
import com.work.vladimirs.rocketscloud.data.repositories.jpa.UserRepository;
import com.work.vladimirs.rocketscloud.models.entities.User;
import com.work.vladimirs.rocketscloud.models.inventory.Rocket;
import com.work.vladimirs.rocketscloud.models.inventory.Component;
import com.work.vladimirs.rocketscloud.models.services.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignRocketController {
    private static final Logger LOG = LoggerFactory.getLogger(DesignRocketController.class);

    private final RocketRepository rocketRepository;
    private final ComponentRepository componentRepository;
    private UserRepository userRepository;

    @Autowired
    public DesignRocketController(ComponentRepository componentRepository, RocketRepository rocketRepository, UserRepository userRepository) {
        this.componentRepository = componentRepository;
        this.rocketRepository = rocketRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }


    @ModelAttribute(name = "rocket")
    public Rocket rocket() {
        return new Rocket();
    }

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {
        /*List<Component> components = Arrays.asList(
                new Component("PMK1C", "PODS MK1 Cockpit", Component.Type.PODS),
                new Component("PMK2C", "PODS MK2 Cockpit", Component.Type.PODS),
                new Component("PMK1CP", "PODS MK1 Command Pod", Component.Type.PODS),
                new Component("PMK2CP", "PODS MK2 Command Pod", Component.Type.PODS),

                new Component("RWI", "RW Inline", Component.Type.REACTION_WHEELS),
                new Component("RWA", "RW Advanced", Component.Type.REACTION_WHEELS),

                new Component("ELVT30", "LV-T30", Component.Type.ENGINES),
                new Component("ELVT45", "LVT-45", Component.Type.ENGINES),
                new Component("EREL10", "RE-L10", Component.Type.ENGINES),
                new Component("EREI5", "RE-I5", Component.Type.ENGINES),

                new Component("FFLT100", "FL-T100", Component.Type.FUEL_TANKS),
                new Component("FFLT200", "FL-T200", Component.Type.FUEL_TANKS),
                new Component("FFLT800", "FL-T400", Component.Type.FUEL_TANKS),
                new Component("FR12", "R-12", Component.Type.FUEL_TANKS)
        );*/

        List<Component> components = new ArrayList<>();
        componentRepository.findAll().forEach(i -> components.add(i));
        Component.Type[] types = Component.Type.values();

        for (Component.Type type : types) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(components, type));
        }

        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);

        return "designForm";
    }

    private List<Component> filterByType(List<Component> components, Component.Type type) {
        return components
                .stream()
                .filter(c -> type.equals(c.getType()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(@Valid Rocket rocket, Errors errors, @ModelAttribute Order order, Model model, Principal principal) {
        if (errors.hasErrors()) {
            return showDesignForm(model, principal);
        }

        LOG.info("Process design for rocket: {}", rocket);
        Rocket savedRocket = rocketRepository.save(rocket);
        order.addRocket(savedRocket);

        LOG.info("Saved rocket: {}", savedRocket);
        return "redirect:/orders/current";
    }
}

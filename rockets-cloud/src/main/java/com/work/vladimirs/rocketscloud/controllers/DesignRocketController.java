package com.work.vladimirs.rocketscloud.controllers;

import com.work.vladimirs.rocketscloud.inventory.Component;
import com.work.vladimirs.rocketscloud.inventory.Rocket;
import com.work.vladimirs.rocketscloud.inventory.RocketOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("rocketOrder")
public class DesignRocketController {

    @ModelAttribute
    public void addComponentsToModel(Model model) {
        List<Component> components = Arrays.asList(
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
        );
        Component.Type[] types = Component.Type.values();
        for (Component.Type type : types) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(components, type)
            );
        }
    }

    @ModelAttribute(name = "rocketOrder")
    public RocketOrder order() {
        return new RocketOrder();
    }

    @ModelAttribute(name = "rocket")
    public Rocket rocket() {
        return new Rocket();
    }

    @GetMapping
    public String showDesignForm() {
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
                                @ModelAttribute RocketOrder rocketOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        rocketOrder.addRocket(rocket);
        log.info("Processing rocket {}", rocket);
        return "redirect:/orders/current"; // redirect означает, что браузер должен отправить get с указанныем путём и открыть другую страницу
    }
}

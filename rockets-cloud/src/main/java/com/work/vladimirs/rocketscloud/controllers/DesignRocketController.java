package com.work.vladimirs.rocketscloud.controllers;

import com.work.vladimirs.rocketscloud.models.inventory.Rocket;
import com.work.vladimirs.rocketscloud.models.inventory.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.Errors;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
public class DesignRocketController {

    private static final Logger LOG = LoggerFactory.getLogger(DesignRocketController.class);

    @GetMapping
    public String showDesignForm(Model model) {
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
                    filterByType(components, type));
        }

        model.addAttribute("rocket", new Rocket());

        return "designForm";
    }

    private List<Component> filterByType(List<Component> components, Component.Type type) {
        return components
                .stream()
                .filter(c -> type.equals(c.getType()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("rocket") Rocket rocket, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "designForm";
        }

        LOG.info("Processing rocket: {}", rocket);
        return "redirect:/orders/current";
    }
}
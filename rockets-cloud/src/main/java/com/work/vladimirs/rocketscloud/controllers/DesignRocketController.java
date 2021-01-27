package com.work.vladimirs.rocketscloud.controllers;

import com.work.vladimirs.rocketscloud.inventory.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/design")
public class DesignRocketController {

    private static final Logger LOG = LoggerFactory.getLogger(DesignRocketController.class);

    @GetMapping
    public String showDesignForm(Model model) {
        List<Part> parts = Arrays.asList(
                new Part("PMK1C", "PODS MK1 Cockpit", Part.Type.PODS),
                new Part("PMK2C", "PODS MK2 Cockpit", Part.Type.PODS),
                new Part("PMK1CP", "PODS MK1 Command Pod", Part.Type.PODS),
                new Part("PMK2CP", "PODS MK2 Command Pod", Part.Type.PODS),

                new Part("RWI", "RW Inline", Part.Type.REACTION_WHEELS),
                new Part("RWA", "RW Advanced", Part.Type.REACTION_WHEELS),

                new Part("ELVT30", "LV-T30", Part.Type.ENGINES),
                new Part("ELVT45", "LVT-45", Part.Type.ENGINES),
                new Part("EREL10", "RE-L10", Part.Type.ENGINES),
                new Part("EREI5", "RE-I5", Part.Type.ENGINES),

                new Part("FFLT100", "FL-T100", Part.Type.FUEL_TANKS),
                new Part("FFLT200", "FL-T200", Part.Type.FUEL_TANKS),
                new Part("FFLT800", "FL-T400", Part.Type.FUEL_TANKS),
                new Part("FR12", "R-12", Part.Type.FUEL_TANKS)
        );

        Part.Type[] types = Part.Type.values();

        for (Part.Type type : types) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(parts, type));
        }

        model.addAttribute("design", new Rocket());

        return "design";
    }

    private Object filterByType(List<Part> parts, Part.Type type) {

    }
}

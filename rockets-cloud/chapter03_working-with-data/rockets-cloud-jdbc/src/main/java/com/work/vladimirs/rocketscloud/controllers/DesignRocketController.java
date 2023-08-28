package com.work.vladimirs.rocketscloud.controllers;

import com.work.vladimirs.rocketscloud.inventory.Component;
import com.work.vladimirs.rocketscloud.inventory.Rocket;
import com.work.vladimirs.rocketscloud.inventory.RocketOrder;
import com.work.vladimirs.rocketscloud.repositories.ComponentRepository;
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

    private final ComponentRepository componentRepository;

    @Autowired
    public DesignRocketController(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

//    /**
//     * Добавление компонентов вручную
//     */
//    @ModelAttribute
//    public void addComponentsToModel(Model model) {
//        List<ComponentJdbc> components = Arrays.asList(
//            new ComponentJdbc("PMK1C", "PODS MK1 Cockpit", ComponentJdbc.Type.PODS),
//            new ComponentJdbc("PMK2C", "PODS MK2 Cockpit", ComponentJdbc.Type.PODS),
//            new ComponentJdbc("PMK1CP", "PODS MK1 Command Pod", ComponentJdbc.Type.PODS),
//            new ComponentJdbc("PMK2CP", "PODS MK2 Command Pod", ComponentJdbc.Type.PODS),
//
//            new ComponentJdbc("RWI", "RW Inline", ComponentJdbc.Type.REACTION_WHEELS),
//            new ComponentJdbc("RWA", "RW Advanced", ComponentJdbc.Type.REACTION_WHEELS),
//
//            new ComponentJdbc("ELVT30", "LV-T30", ComponentJdbc.Type.ENGINES),
//            new ComponentJdbc("ELVT45", "LVT-45", ComponentJdbc.Type.ENGINES),
//            new ComponentJdbc("EREL10", "RE-L10", ComponentJdbc.Type.ENGINES),
//            new ComponentJdbc("EREI5", "RE-I5", ComponentJdbc.Type.ENGINES),
//
//            new ComponentJdbc("FFLT100", "FL-T100", ComponentJdbc.Type.FUEL_TANKS),
//            new ComponentJdbc("FFLT200", "FL-T200", ComponentJdbc.Type.FUEL_TANKS),
//            new ComponentJdbc("FFLT800", "FL-T400", ComponentJdbc.Type.FUEL_TANKS),
//            new ComponentJdbc("FR12", "R-12", ComponentJdbc.Type.FUEL_TANKS)
//        );
//        ComponentJdbc.Type[] types = ComponentJdbc.Type.values();
//        for (ComponentJdbc.Type type : types) {
//            model.addAttribute(
//                    type.toString().toLowerCase(),
//                    filterByType(components, type)
//            );
//        }
//    }

    /**
     * Добавление компонентов через БД
     */
    @ModelAttribute
    public void addComponentsToModel(Model model) {
        List<Component> components = new ArrayList<>();
        componentRepository.findAll().forEach(components::add);
        Component.Type[] types = Component.Type.values();
        for (Component.Type type : types) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(components, type));
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
                                @ModelAttribute(name = "rocketOrder") RocketOrder rocketOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        rocketOrder.addRocket(rocket);
        log.info("Processing rocket {}", rocket);
        return "redirect:/orders/current"; // redirect означает, что браузер должен отправить get с указанныем путём и открыть другую страницу
    }
}

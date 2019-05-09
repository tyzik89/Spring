package com.work.vladimirs.shawermacloud.controller;

import com.work.vladimirs.shawermacloud.entity.Ingredient;
import com.work.vladimirs.shawermacloud.entity.Ingredient.Type;
import com.work.vladimirs.shawermacloud.entity.Shawerma;
import com.work.vladimirs.shawermacloud.repositories.IngredientRepository;
import com.work.vladimirs.shawermacloud.repositories.ShawemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignShawermaControllerJDBC {

    @Autowired
    private IngredientRepository repository;

    @Autowired
    private ShawemaRepository shawemaRepository;

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        repository.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(type, ingredients));
        }
        model.addAttribute("shawerma", new Shawerma());
        return "designForm";
    }

    private List<Ingredient> filterByType(Type type, List<? extends Ingredient> ingredients) {
        List<Ingredient> result = new ArrayList<Ingredient>();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getType().equals(type))
                result.add(ingredient);
        }
        return result;
    }

    @PostMapping
    public String processDesign(@Valid Shawerma shawerma, Errors errors) {
        if (errors.hasErrors()) {
            return "designForm";
        }
        return "redirect:/orders/current";
    }

}

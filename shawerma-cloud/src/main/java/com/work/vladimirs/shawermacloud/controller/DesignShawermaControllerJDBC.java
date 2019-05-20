package com.work.vladimirs.shawermacloud.controller;

import com.work.vladimirs.shawermacloud.entity.Ingredient;
import com.work.vladimirs.shawermacloud.entity.Ingredient.Type;
import com.work.vladimirs.shawermacloud.entity.Order;
import com.work.vladimirs.shawermacloud.entity.Shawerma;
import com.work.vladimirs.shawermacloud.repositories.JDBCTemplate.IngredientRepository;
import com.work.vladimirs.shawermacloud.repositories.JDBCTemplate.ShawemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignShawermaControllerJDBC {

    @Autowired
    @Qualifier(value = "JdbcIngredientRepository")
    private IngredientRepository ingredientRepository;

    @Autowired
    @Qualifier(value = "JdbcShawermaRepository")
    private ShawemaRepository shawemaRepository;

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

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

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "shawerma")
    public Shawerma taco() {
        return new Shawerma();
    }

    @PostMapping
    public String processDesign(@Valid Shawerma shawerma, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "designForm";
        }

        Shawerma saved = shawemaRepository.save(shawerma);
        order.addShawerma(saved);

        return "redirect:/orders/current";
    }

}

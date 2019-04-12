package com.work.vladimirs.shawermacloud.controller;

import com.work.vladimirs.shawermacloud.entity.Ingredient;
import com.work.vladimirs.shawermacloud.entity.Ingredient.Type;
import com.work.vladimirs.shawermacloud.entity.Shawerma;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/design")
public class DesignShawermaController {

    List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("WCOM", "Обычный лаваш", Type.WRAP),
            new Ingredient("WCHE", "Сырная простыня", Type.WRAP),
            new Ingredient("PCHI", "Курица", Type.PROTEIN),
            new Ingredient("PBEE", "Говядина", Type.PROTEIN),
            new Ingredient("VSAL", "Салат", Type.VEGGIES),
            new Ingredient("VHAL", "Халапенью", Type.VEGGIES),
            new Ingredient("CMOZ", "Моцарелла", Type.CHEESE),
            new Ingredient("CMAA", "Маасдам", Type.CHEESE),
            new Ingredient("SSAL", "Сальса", Type.SAUCE),
            new Ingredient("SMAY", "Майонез", Type.SAUCE)
    );

    @GetMapping
    public String showDesignForm(Model model) {
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(type));
        }
        model.addAttribute("design", new Shawerma());
        return "design";
    }

    private List<Ingredient> filterByType(Type type) {
        List<Ingredient> result = new ArrayList<Ingredient>();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getType().equals(type))
                result.add(ingredient);
        }
        return result;
    }

    @PostMapping
    public String processDesign() {
        return "redirect:/orders/current";
    }
}

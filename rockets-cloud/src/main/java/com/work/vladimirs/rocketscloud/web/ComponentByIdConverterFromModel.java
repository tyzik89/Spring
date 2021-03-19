package com.work.vladimirs.rocketscloud.web;

import com.work.vladimirs.rocketscloud.data.repositories.jpa.ComponentRepository;
import com.work.vladimirs.rocketscloud.models.inventory.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

@org.springframework.stereotype.Component
public class ComponentByIdConverterFromModel implements Converter<String, Optional<Component>> {

    private ComponentRepository componentRepository;

    @Autowired
    public ComponentByIdConverterFromModel(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @Override
    public Optional<Component> convert(String id) {
        return componentRepository.findById(id);
    }

    /*// Конвертация для JPA
    @Override
    public Ingredient convert(String id) {
        Optional<Ingredient> optionalIngredient = ingredientRepo.findById(id);
        return optionalIngredient.isPresent() ?
                optionalIngredient.get() : null;
    }*/
}

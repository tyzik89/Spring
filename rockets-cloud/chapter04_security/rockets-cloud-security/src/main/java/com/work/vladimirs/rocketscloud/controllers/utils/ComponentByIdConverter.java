package com.work.vladimirs.rocketscloud.controllers.utils;

import com.work.vladimirs.rocketscloud.inventory.Component;
import com.work.vladimirs.rocketscloud.repositories.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Конвертер полей с UI (*{components} = ["PMK1C", "FFLT200"] и т.д.) в поле List<Component> components, объекта Rocket
 */
@org.springframework.stereotype.Component
class ComponentByIdConverter implements Converter<String, Component> {

    private final ComponentRepository componentRepository;

    @Autowired
    public ComponentByIdConverter(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @Override
    public Component convert(String id) {
        return componentRepository.findById(id).orElse(null);
    }

}

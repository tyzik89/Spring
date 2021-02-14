package com.work.vladimirs.rocketscloud.controllers.utils;

import com.work.vladimirs.rocketscloud.data.repositories.jdbc.ComponentRepository;
import com.work.vladimirs.rocketscloud.models.inventory.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

@org.springframework.stereotype.Component
public class ComponentByIdConverterFromModel implements Converter<String, Component> {

    private ComponentRepository componentRepository;

    @Autowired
    public ComponentByIdConverterFromModel(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @Override
    public Component convert(String id) {
        return componentRepository.findById(id);
    }
}

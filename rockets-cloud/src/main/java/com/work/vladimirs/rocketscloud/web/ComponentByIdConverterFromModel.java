package com.work.vladimirs.rocketscloud.web;

import com.work.vladimirs.rocketscloud.data.repositories.jpa.ComponentRepository;
import com.work.vladimirs.rocketscloud.models.inventory.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

@org.springframework.stereotype.Component
public class ComponentByIdConverterFromModel implements Converter<String, Component> {
    private static final Logger LOG = LoggerFactory.getLogger(ComponentByIdConverterFromModel.class);

    private ComponentRepository componentRepository;

    @Autowired
    public ComponentByIdConverterFromModel(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

/*
    // For JDBC
    @Override
    public Optional<Component> convert(String id) {
        return componentRepository.findById(id);
    }*/

    // For JPA
    @Override
    public Component convert(String id) {
        LOG.debug("Convert. id = {}", id);
        Optional<Component> optionalIngredient = componentRepository.findById(id);
        return optionalIngredient.isPresent() ?
                optionalIngredient.get() : null;
    }
}

package com.work.vladimirs.rocketscloud.controllers.utils;

import com.work.vladimirs.rocketscloud.inventory.Component;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Component
class ComponentByIdConverter implements Converter<String, Component> {
    
    private Map<String, Component> componentMap = new HashMap<>();

    public ComponentByIdConverter() {
       componentMap.put("PMK1C", new Component("PMK1C", "PODS MK1 Cockpit", Component.Type.PODS));
       componentMap.put("PMK2C", new Component("PMK2C", "PODS MK2 Cockpit", Component.Type.PODS));
       componentMap.put("PMK1CP", new Component("PMK1CP", "PODS MK1 Command Pod", Component.Type.PODS));
       componentMap.put("PMK2CP", new Component("PMK2CP", "PODS MK2 Command Pod", Component.Type.PODS));

       componentMap.put("RWI", new Component("RWI", "RW Inline", Component.Type.REACTION_WHEELS));
       componentMap.put("RWA", new Component("RWA", "RW Advanced", Component.Type.REACTION_WHEELS));

       componentMap.put("ELVT30", new Component("ELVT30", "LV-T30", Component.Type.ENGINES));
       componentMap.put("ELVT45", new Component("ELVT45", "LVT-45", Component.Type.ENGINES));
       componentMap.put("EREL10", new Component("EREL10", "RE-L10", Component.Type.ENGINES));
       componentMap.put("EREI5", new Component("EREI5", "RE-I5", Component.Type.ENGINES));

       componentMap.put("FFLT100", new Component("FFLT100", "FL-T100", Component.Type.FUEL_TANKS));
       componentMap.put("FFLT200", new Component("FFLT200", "FL-T200", Component.Type.FUEL_TANKS));
       componentMap.put("FFLT800", new Component("FFLT800", "FL-T400", Component.Type.FUEL_TANKS));
       componentMap.put("FR12", new Component("FR12", "FR-12", Component.Type.FUEL_TANKS));
    }

    @Override
    public Component convert(String id) {
        return componentMap.get(id);
    }
}

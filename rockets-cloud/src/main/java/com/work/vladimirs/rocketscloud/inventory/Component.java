package com.work.vladimirs.rocketscloud.inventory;

import lombok.Data;

@Data
public class Component {

    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        PODS, REACTION_WHEELS, ENGINES, FUEL_TANKS
    }

}

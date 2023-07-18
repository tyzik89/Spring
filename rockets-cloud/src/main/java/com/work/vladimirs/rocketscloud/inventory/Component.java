package com.work.vladimirs.rocketscloud.inventory;

import lombok.Data;

import java.io.Serializable;

@Data
public class Component implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        PODS, REACTION_WHEELS, ENGINES, FUEL_TANKS
    }

}

package com.work.vladimirs.rocketscloud.inventory;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@Table("Component") // нужно для Spring Data
@RequiredArgsConstructor
public class Component implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // нужно для Spring Data
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        PODS, REACTION_WHEELS, ENGINES, FUEL_TANKS
    }

}

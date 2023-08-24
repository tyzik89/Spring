package com.work.vladimirs.rocketscloud.inventory.jpa;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Связь между Rocket и Component
 */
@Data
@Table // нужно для Spring Data
public class ComponentRefJpa {

    private final String component;
}

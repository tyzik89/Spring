package com.work.vladimirs.rocketscloud.inventory.jdbc;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Связь между Rocket и Component
 */
@Data
@Table("Component_Ref") // нужно для Spring Data
public class ComponentRefJdbc {

    private final String component;
}

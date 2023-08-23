package com.work.vladimirs.rocketscloud.inventory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
//@Table // нужно для Spring Data
@javax.persistence.Table // нужно для JPA
@Entity // нужно для JPA
@AllArgsConstructor // нужно для JPA
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true) // force = true - сделать все св-ва финальными и присвоить им дефолтные зн-я
public class Component implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id // нужно для Spring Data
    @javax.persistence.Id // // нужно для JPA
    private final String id;
    private final String name;
    @Enumerated(EnumType.STRING)
    private final Type type;

    public static enum Type {
        PODS, REACTION_WHEELS, ENGINES, FUEL_TANKS
    }

}

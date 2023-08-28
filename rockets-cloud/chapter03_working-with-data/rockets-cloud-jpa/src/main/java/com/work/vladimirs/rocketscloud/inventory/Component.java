package com.work.vladimirs.rocketscloud.inventory;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "Component") // нужно для JPA
@Entity // нужно для JPA
@RequiredArgsConstructor // нужно для JPA
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true) // force = true - сделать все св-ва финальными и присвоить им дефолтные зн-я
public class Component {

    @Id // // нужно для JPA
    private final String id;
    private final String name;
    @Enumerated(EnumType.STRING)
    private final Type type;

    public static enum Type {
        PODS, REACTION_WHEELS, ENGINES, FUEL_TANKS
    }

}

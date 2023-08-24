package com.work.vladimirs.rocketscloud.inventory.jpa;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
//@Table // нужно для Spring Data
@Entity // нужно для JPA
public class RocketJpa {

//    @Id // нужно для Spring Data
    @Id // нужно для JPA
    @GeneratedValue(strategy = GenerationType.AUTO) // нужно для JPA
    private Long id;
    private Date createdAt = new Date();

    @NotNull
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 component")
    @ManyToMany // нужно для JPA
    private List<ComponentJpa> components;
}

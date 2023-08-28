package com.work.vladimirs.rocketscloud.inventory;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity // нужно для JPA
@Table(name = "Rocket")
public class Rocket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // нужно для JPA
    @GeneratedValue(strategy = GenerationType.AUTO) // нужно для JPA
    private Long id;
    private Date createdAt = new Date();

    @NotNull
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 component")
    @ManyToMany(targetEntity = Component.class) // нужно для JPA
    private List<Component> components;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}

package com.work.vladimirs.rocketscloud.inventory;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Table("Rocket") // нужно для Spring Data
public class Rocket {

    @Id // нужно для Spring Data
    private Long id;
    private Date createdAt = new Date();

    @NotNull
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 component")
    private List<Component> components;
}

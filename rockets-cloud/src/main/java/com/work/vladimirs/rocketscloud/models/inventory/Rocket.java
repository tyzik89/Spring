package com.work.vladimirs.rocketscloud.models.inventory;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;

public class Rocket {

    private Long id;

    private Date createAt;

    @NotNull
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotNull(message = "You must choose at least 1 component")
    @Size(min = 1, message = "You must choose at least 1 component")
    private List<String> components;

    public Rocket() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rocket rocket = (Rocket) o;

        if (!id.equals(rocket.id)) return false;
        if (!createAt.equals(rocket.createAt)) return false;
        if (!name.equals(rocket.name)) return false;
        return components.equals(rocket.components);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + createAt.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + components.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "id=" + id +
                ", createAt=" + createAt +
                ", name='" + name + '\'' +
                ", components=" + components +
                '}';
    }
}

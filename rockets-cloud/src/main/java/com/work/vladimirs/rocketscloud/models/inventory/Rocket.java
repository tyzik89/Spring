package com.work.vladimirs.rocketscloud.models.inventory;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

public class Rocket {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rocket rocket = (Rocket) o;

        if (!name.equals(rocket.name)) return false;
        return components.equals(rocket.components);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + components.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "name='" + name + '\'' +
                ", components=" + components +
                '}';
    }
}

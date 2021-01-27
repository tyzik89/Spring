package com.work.vladimirs.rocketscloud.models.inventory;

import java.util.List;

public class Rocket {

    private String name;
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

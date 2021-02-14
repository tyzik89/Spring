package com.work.vladimirs.rocketscloud.models.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity   //Using for JPA
@Table(name = "Components")   //Using for JPA
public class Component {

    public static enum Type {
        PODS, REACTION_WHEELS, ENGINES, FUEL_TANKS
    }

    @Id   //Using for JPA
    private final String id;
    private final String name;
    private final Type type;

    //Конструктор по умолчанию, необходим для JPA
    private Component() {
        this.id = null;
        this.name = null;
        this.type = null;
    }

    public Component(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Component component = (Component) o;

        if (!id.equals(component.id)) return false;
        if (!name.equals(component.name)) return false;
        return type == component.type;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Component{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}

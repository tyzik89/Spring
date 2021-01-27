package com.work.vladimirs.rocketscloud.inventory;

public class Part {

    public static enum Type {
        PODS, REACTION_WHEELS, ENGINES, FUEL_TANKS
    }

    private final String id;
    private final String name;
    private final Type type;

    public Part(String id, String name, Type type) {
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

        Part part = (Part) o;

        if (!id.equals(part.id)) return false;
        if (!name.equals(part.name)) return false;
        return type == part.type;
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
        return "Parts{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}

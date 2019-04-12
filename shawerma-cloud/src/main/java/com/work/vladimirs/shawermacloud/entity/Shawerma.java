package com.work.vladimirs.shawermacloud.entity;

import java.util.List;

public class Shawerma {

    private String name;
    private List<String> ingredients;

    public Shawerma(String name, List<String> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public Shawerma() {
    }

    @Override
    public String toString() {
        return "Shawerma{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shawerma shawerma = (Shawerma) o;

        if (!name.equals(shawerma.name)) return false;
        return ingredients != null ? ingredients.equals(shawerma.ingredients) : shawerma.ingredients == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}

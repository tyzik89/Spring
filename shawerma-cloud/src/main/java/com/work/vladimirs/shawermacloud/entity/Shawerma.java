package com.work.vladimirs.shawermacloud.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class Shawerma {

    private Long id;
    private Date createAt;

    @NotNull
    @Size(min = 2, message = "Имя должно быть как минимум 2 символа")
    private String name;

    @Size(min = 1, message = "Необходимо выбрать хотя бы 1 ингредиент")
    private List<String> ingredients;





    public Shawerma() {
    }

    public Shawerma(Long id, Date createAt, String name, List<String> ingredients) {
        this.id = id;
        this.createAt = createAt;
        this.name = name;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Shawerma{" +
                "id=" + id +
                ", createAt=" + createAt +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shawerma shawerma = (Shawerma) o;

        if (!id.equals(shawerma.id)) return false;
        if (!createAt.equals(shawerma.createAt)) return false;
        if (!name.equals(shawerma.name)) return false;
        return ingredients.equals(shawerma.ingredients);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + createAt.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + ingredients.hashCode();
        return result;
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

package com.work.vladimirs.shawermacloud.repositories;

import com.work.vladimirs.shawermacloud.entity.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}

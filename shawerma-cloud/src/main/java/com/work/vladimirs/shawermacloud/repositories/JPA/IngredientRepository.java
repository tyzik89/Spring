package com.work.vladimirs.shawermacloud.repositories.JPA;

import com.work.vladimirs.shawermacloud.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}

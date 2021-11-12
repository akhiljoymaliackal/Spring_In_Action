package tacos.repository;

import tacos.domain.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findByID(String id);
    Ingredient save(Ingredient ingredient);
}

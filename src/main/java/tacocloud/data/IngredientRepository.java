package tacocloud.data;

import tacocloud.Ingredients;

import java.util.Optional;

public interface IngredientRepository {
    Iterable<Ingredients> findAll();

    Optional<Ingredients> findBiId(String id);

    Ingredients save(Ingredients ingredient);
}

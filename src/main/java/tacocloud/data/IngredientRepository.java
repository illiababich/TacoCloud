package tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import tacocloud.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}

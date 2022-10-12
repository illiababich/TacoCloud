package tacocloud.data;

import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacocloud.Ingredient;
import tacocloud.IngredientUDT;

import java.util.Optional;

@Component
public class StringToIngredientConverter implements Converter<String, IngredientUDT> {

  private final IngredientRepository ingredientRepository;

  public StringToIngredientConverter(IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }
  
  @Override
  public IngredientUDT convert(@NonNull String id) {
    Optional<Ingredient> ingredient = ingredientRepository.findById(id);
    if (ingredient.isEmpty()) {
      return null;
    }
    
    return ingredient.map(i -> {
      return new IngredientUDT(i.getName(), i.getType());
    }).get();
  }
}

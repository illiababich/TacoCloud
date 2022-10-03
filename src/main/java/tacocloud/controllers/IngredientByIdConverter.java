package tacocloud.controllers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacocloud.Ingredients;
import tacocloud.Type;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredients> {
    private final Map<String, Ingredients> ingredientsMap = new HashMap<>();

    public IngredientByIdConverter() {
        ingredientsMap.put("FLTO", new Ingredients("FLTO", "Flour Tortilla", Type.WRAP));
        ingredientsMap.put("COTO", new Ingredients("COTO", "Corn Tortilla", Type.WRAP));
        ingredientsMap.put("GRBF", new Ingredients("GRBF", "Ground Beef", Type.MEAT));
        ingredientsMap.put("CARN", new Ingredients("CARN", "Carnitas", Type.MEAT));
        ingredientsMap.put("TMTO", new Ingredients("TMTO", "Diced Tomatoes", Type.VEGGIES));
        ingredientsMap.put("LETC", new Ingredients("LETC", "Lettuce", Type.VEGGIES));
        ingredientsMap.put("CHED", new Ingredients("CHED", "Cheddar", Type.CHEESE));
        ingredientsMap.put("JACK", new Ingredients("JACK", "Monterrey Jack", Type.CHEESE));
        ingredientsMap.put("SLSA", new Ingredients("SLSA", "Salsa", Type.SAUCE));
        ingredientsMap.put("SRCR", new Ingredients("SRCR", "Sour Cream", Type.SAUCE));
    }

    @Override
    public Ingredients convert(String id) {
        return ingredientsMap.get(id);
    }
}

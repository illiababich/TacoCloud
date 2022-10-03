package tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacocloud.Ingredients;
import tacocloud.Taco;
import tacocloud.TacoOrder;
import tacocloud.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.validation.Errors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes
public class DesignTacoController {

    @ModelAttribute
    public void addIngredientsToModel (Model model) {
        List<Ingredients> ingredients = Arrays.asList(
                new Ingredients("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredients("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredients("GRBF", "Ground Beef", Type.MEAT),
                new Ingredients("CARN", "Carnitas", Type.MEAT),
                new Ingredients("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredients("LETC", "Lettuce", Type.VEGGIES),
                new Ingredients("CHED", "Cheddar", Type.CHEESE),
                new Ingredients("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredients("SLSA", "Salsa", Type.SAUCE),
                new Ingredients("SRCR", "Sour Cream", Type.SAUCE)
        );

        Type[] types = Type.values();

        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    /*
    @PostMapping
    public String processTaco(Taco taco, @ModelAttribute TacoOrder tacoOrder) {
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }
     */

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {

        if (errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredients> filterByType(List<Ingredients> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}

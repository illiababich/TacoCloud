package tacocloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacocloud.*;
import tacocloud.data.IngredientRepository;
import tacocloud.data.TacoRepository;
import tacocloud.data.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    private final TacoRepository tacoRepository;

    private final UserRepository userRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo, UserRepository userRepo) {
        this.ingredientRepository = ingredientRepo;
        this.tacoRepository = tacoRepo;
        this.userRepository = userRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder", value = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "user")
    public User user(Principal principal) {
        String username = principal.getName();
        return userRepository.findByUsername(username);
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        //log.info("   --- Saving taco");

        if (errors.hasErrors()) {
            return "design";
        }

        Taco saved = tacoRepository.save(taco);
        tacoOrder.addTaco(saved);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
        return StreamSupport
                .stream(ingredients.spliterator(), false)
                .filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }
}

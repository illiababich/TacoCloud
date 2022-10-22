package tacocloud.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import tacocloud.Ingredient;
import tacocloud.Type;
import tacocloud.TacoOrder;
import tacocloud.Taco;
import tacocloud.User;
import tacocloud.data.IngredientRepository;
import tacocloud.data.TacoRepository;
import tacocloud.data.UserRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
@Slf4j
@Data
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    private final TacoRepository tacoRepository;

    private final UserRepository userRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo, UserRepository userRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepository = tacoRepo;
        this.userRepository = userRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);

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
    public String processTaco(@Valid Taco taco, Errors errors, @SessionAttribute TacoOrder tacoOrder) {

        if (errors.hasErrors()) {
            return "design";
        }

        Taco saved = tacoRepository.save(taco);
        tacoOrder.addTaco(saved);
        System.out.println(tacoOrder.getTacos());

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}

package tacocloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tacocloud.data.IngredientRepository;
import tacocloud.data.UserRepository;

@SpringBootApplication
public class TacoCloudApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    //TODO: login credentials are incorrect show an error while logging in
    //TODO: registration fields validation
    //TODO: write tests for all the classes
    //TODO: http://localhost:8080/login?error while logging out from the home page
    //TODO: no tacos are shown while processing the order
    //TODO: password confirmation on the register page
    //TODO: check if user already exists while registering

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository ingredientRepo, UserRepository userRepo) {
        return args -> {
            ingredientRepo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
            ingredientRepo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
            ingredientRepo.save(new Ingredient("GRBF", "Ground Beef", Type.MEAT));
            ingredientRepo.save(new Ingredient("CARN", "Carnitas", Type.MEAT));
            ingredientRepo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
            ingredientRepo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
            ingredientRepo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
            ingredientRepo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
            ingredientRepo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
            ingredientRepo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

            userRepo.save(new User("gus", new BCryptPasswordEncoder().encode("frit"), "Gustavo Frit", "42 Morris Road", "Southampton", "Hampshire", "SO15 2BR", "+(44)7495-233853"));
        };
    }
}

package tacocloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TacoCloudApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    //TODO: login credentials are incorrect show an error while logging in
    //TODO: registration fields validation (unique username, confirm password, full name and phone not null)
    //TODO: write tests for all the classes
    // illiababich
}

package tacocloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") //if an HTTP GET request is received for the root path /, then this method should handle that request
    public String home() {
        return "home";
    }
}

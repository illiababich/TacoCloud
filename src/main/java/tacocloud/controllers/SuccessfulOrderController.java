package tacocloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders/current")
public class SuccessfulOrderController {
    @GetMapping("/success")
    public String successfulOrderForm() {
        return "successfulOrder";
    }
}

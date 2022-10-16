package tacocloud.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacocloud.data.UserRepository;
import tacocloud.security.RegistrationForm;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registrationForm";
    }

//    @PostMapping
//    public String processRegistration(@Valid RegistrationForm registrationForm, Errors errors, SessionStatus sessionStatus) {
//
//        if (errors.hasErrors()) {
//            return "registrationForm";
//        }
//
//        userRepository.save(registrationForm.toUser(passwordEncoder));
//        sessionStatus.setComplete();
//
//        return "redirect:/login";
//    }
    @PostMapping
    public String processRegistration(RegistrationForm form) {
        userRepository.save(form.toUser(passwordEncoder));

        System.out.println(form.toString());

        return "redirect:/login";
    }
}

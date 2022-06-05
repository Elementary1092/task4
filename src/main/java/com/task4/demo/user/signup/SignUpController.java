package com.task4.demo.user.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {
    private SignUpService service;

    @Autowired
    public SignUpController(SignUpService service) {
        this.service = service;
    }

    @GetMapping
    public String signUpPage(Model model) {
        model.addAttribute("title", "Sign up")
                .addAttribute("proxy", new StandardSignUpEntity());

        service.setSignUpStrategy(new StandardSignUp());

        return "signup";
    }

    @PostMapping
    public void signUp(StandardSignUpEntity entity) {
        service.registerUser(entity);
    }
}

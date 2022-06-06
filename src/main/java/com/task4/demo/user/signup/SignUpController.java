package com.task4.demo.user.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class SignUpController {
    private SignUpService service;

    @Autowired
    public SignUpController(SignUpService service) {
        this.service = service;
    }

    @GetMapping("/signup")
    public String signUpPage(Model model) {
        model.addAttribute("title", "Sign up")
                .addAttribute("proxy", new StandardSignUpEntity());

        service.setSignUpStrategy(new StandardSignUp());

        return "signup";
    }

    @PostMapping("/signup")
    public void signUp(StandardSignUpEntity entity,
                       final HttpServletResponse response) throws IOException {
        service.registerUser(entity);
        response.sendRedirect("/login");
    }
}

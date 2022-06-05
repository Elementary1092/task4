package com.task4.demo.user.login;

import com.task4.demo.user.PoolOfSessionsAndUsers;
import com.task4.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping
public class LoginController {
    private LoginService service;

    private PoolOfSessionsAndUsers usersAndSessions;

    @Autowired
    public LoginController(PoolOfSessionsAndUsers usersAndSessions,
                           LoginService service) {
        this.usersAndSessions = usersAndSessions;
        this.service = service;
    }

    @GetMapping(path = "/login")
    public String login(Model model) {
        model.addAttribute("title", "Log in")
                .addAttribute("user", new User());

        service.setLoginStrategy(new StandardLogin());

        return "login";
    }

    @PostMapping(path = "/login")
    public void login(User user, HttpSession session) {
        User userDetails = (User) service.login(user);

        session.setAttribute("user_id", userDetails.getId());

        usersAndSessions.add(userDetails.getId(), session.getId());
    }
}

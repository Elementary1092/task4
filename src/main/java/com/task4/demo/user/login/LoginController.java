package com.task4.demo.user.login;

import com.task4.demo.user.PoolOfSessionsAndUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.UUID;

@Controller
@RequestMapping
public class LoginController {
    private LoginStrategy loginStrategy;

    private PoolOfSessionsAndUsers usersAndSessions;

    @Autowired
    public LoginController(PoolOfSessionsAndUsers usersAndSessions) {
        this.usersAndSessions = usersAndSessions;
    }

    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(path = "/login")
    public void login(@RequestBody HashMap<String, String> parameters,
                      HttpSession session) {
        usersAndSessions.add(UUID.randomUUID(), session.getId());
    }

    public LoginStrategy getLoginStrategy() {
        return loginStrategy;
    }

    public void setLoginStrategy(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }
}

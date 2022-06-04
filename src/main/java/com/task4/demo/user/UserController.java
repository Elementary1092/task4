package com.task4.demo.user;

import com.task4.demo.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public void getAllUsers() {

    }

    @PostMapping
    public void removeCheckedUsers(SessionRegistry sessionRegistry) {
        List<Object> principals = sessionRegistry.getAllPrincipals();
        List<Object> sessions = new ArrayList<>();

        for (Object principal : principals) {
            sessions.addAll(sessionRegistry.getAllSessions(principal, false));
        }
    }

    @GetMapping(path = "/{id}")
    public void getUser(@PathVariable(name="id") UUID userId) {

    }

    @PatchMapping(path = "{id}")
    public void updateUser(@PathVariable(name="id") UUID userId) {

    }
}

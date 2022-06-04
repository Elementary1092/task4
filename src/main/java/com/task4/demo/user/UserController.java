package com.task4.demo.user;

import com.task4.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public void removeCheckedUsers() {

    }

    @GetMapping(path = "/{id}")
    public void getUser(@PathVariable(name="id") UUID userId) {

    }

    @PatchMapping(path = "{id}")
    public void updateUser(@PathVariable(name="id") UUID userId) {

    }
}

package com.task4.demo.user;

import com.task4.demo.common.DefaultFooterConfigurer;
import com.task4.demo.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {
    private UserService service;

    private PoolOfSessionsAndUsers usersAndSessions;

    @Autowired
    public UserController(UserService service, PoolOfSessionsAndUsers usersAndSessions) {
        this.service = service;
        this.usersAndSessions = usersAndSessions;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("title", "Users")
                .addAttribute("users", setUsersOnlineParameter(service.getAllUsers()));
        new DefaultFooterConfigurer().populateFooter(model);

        return "users";
    }

    @PostMapping("/users")
    public void removeCheckedUsers() {

    }

    @GetMapping(path = "users/{id}")
    public String getUserPage(@PathVariable(name="id") UUID userId, Model model) {
        model.addAttribute("title", "User");
        model.addAttribute("user", service.findUserById(userId));

        return "user";
    }

    @PatchMapping(path = "users/{id}")
    public void updateUser(@PathVariable(name="id") UUID userId, User user) {
        service.updateUser(user);
    }

    @DeleteMapping(path = "/logout")
    public void logout(final HttpSession session) {
        usersAndSessions.expireUserSession((UUID) session.getAttribute("user_id"), session);
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    public void handleExistingUserException() {}

    private List<User> setUsersOnlineParameter(List<User> users) {
        for (User user : users) {
            if (usersAndSessions.existsUserSession(user.getId())) {
                user.setIsOnline(true);
            }
        }

        return users;
    }
}

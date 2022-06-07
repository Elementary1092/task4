package com.task4.demo.user;

import com.task4.demo.common.DefaultFooterConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public String getAllUsers(Model model,
                              final HttpServletRequest request,
                              final HttpServletResponse response) throws IOException {
        if (!service.userExists(request)) {
            response.sendRedirect("/login");
        }

        model.addAttribute("title", "Users")
                .addAttribute("users", service.getAllUsers())
                .addAttribute("checked", new ArrayList<UUID>());
        new DefaultFooterConfigurer().populateFooter(model);

        return "users";
    }

    @PostMapping()
    public void changeUsersList(final HttpServletResponse response) throws IOException {
        response.sendRedirect("/users");
    }

    @PostMapping("/block/{id}")
    public void blockUser(@PathVariable("id") UUID id,
                          final HttpServletResponse response) throws IOException {
        service.blockById(id);
        response.sendRedirect("/users");
    }

    @PostMapping("/unblock/{id}")
    public void unblockUser(@PathVariable("id") UUID id,
                            final HttpServletResponse response) throws IOException {
        service.unblockById(id);
        response.sendRedirect("/users");
    }

    @PostMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") UUID id,
                           final HttpServletResponse response) throws IOException {
        service.deleteById(id);
        response.sendRedirect("/users");
    }

    @RequestMapping(path = "/logout")
    public void logout(final HttpServletRequest request,
                       final HttpServletResponse response) throws IOException {
        service.logout(request, response);
        response.sendRedirect("/login");
    }
}

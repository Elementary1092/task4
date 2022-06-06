package com.task4.demo.user;

import com.task4.demo.common.DefaultFooterConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("title", "Users")
                .addAttribute("users", service.getAllUsers())
                .addAttribute("checkedUser", new ArrayList<User>());
        new DefaultFooterConfigurer().populateFooter(model);

        return "users";
    }

    @PostMapping("/users")
    public void changeUsersList(
            @RequestParam(value = "users") List<User> users,
            final HttpServletRequest request,
            final SessionRegistry sessions
    ) throws IOException {
        String field = request.getParameter("button");

        if (field.equals("block")) {
            blockCheckedUsers(users, sessions);
        } else if (field.equals("unblock")) {
            unblockCheckedUsers(users);
        } else if (field.equals("delete")) {
            deleteUsers(users, sessions);
        } else {
            new IOException("Not found");
        }
    }

    public void blockCheckedUsers(List<User> users,
                                  final SessionRegistry sessions) {
        service.blockUsers(users, sessions);
    }

    public void unblockCheckedUsers(List<User> users) {
        service.unblockUsers(users);
    }

    public void deleteUsers(List<User> users,
                            final SessionRegistry sessions) throws IOException {
        service.deleteUsers(users, sessions);
    }

    @RequestMapping(path = "/logout")
    public void logout(final HttpSession session) {
        service.logout((UUID) session.getAttribute("user_id"), session);
    }
}

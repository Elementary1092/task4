package com.task4.demo.user.login;

import com.task4.demo.user.PoolOfSessionsAndUsers;
import com.task4.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

@Controller
@RequestMapping
public class LoginController {
    private LoginService service;

    private PoolOfSessionsAndUsers usersAndSessions;

    private RedirectStrategy redirectStrategy;

    @Autowired
    public LoginController(PoolOfSessionsAndUsers usersAndSessions,
                           LoginService service) {
        this.usersAndSessions = usersAndSessions;
        this.service = service;
        this.redirectStrategy = new DefaultRedirectStrategy();
    }

    @RequestMapping(path = "/")
    public void homePage(final HttpServletRequest request,
                         final HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request, response, "/login");
    }

    @GetMapping(path = "/login")
    public Model login(Model model) {
        model.addAttribute("title", "Log in")
                .addAttribute("user", new UserLoginDetails());
        service.setLoginStrategy(new StandardLogin());

        return model;
    }

    @PostMapping(path = "/login")
    public void login(@ModelAttribute UserLoginDetails user,
                      final HttpSession session,
                      final HttpServletRequest request,
                      final HttpServletResponse response) throws IOException {
        try {
            service.login(user, session);
            setSessionInformation(request, response);
        }
        catch (RuntimeException e) {
            redirectStrategy.sendRedirect(request, response, "/login");
            return;
        }
    }

    private void setSessionInformation(final HttpServletRequest request,
                                       final HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        redirectStrategy.sendRedirect(request, response, "/users");
    }
}

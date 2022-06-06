package com.task4.demo.user.login;

import com.task4.demo.user.PoolOfSessionsAndUsers;
import com.task4.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public void login(final HttpServletResponse response) throws IOException {
        response.sendRedirect("/login");
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
            User userDetails = service.login(user);
            user.setAuthorized();
            setSessionInformation(userDetails, session, request, response);
        }
        catch (RuntimeException e) {
            System.out.println("error");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            redirectStrategy.sendRedirect(request, response, "/login");
            return;
        }
    }

    private void setSessionInformation(final User user,
                                       final HttpSession session,
                                       final HttpServletRequest request,
                                       final HttpServletResponse response) throws IOException {
        session.setAttribute("user_id", user.getId());
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        usersAndSessions.add(user.getId(), session.getId());
        response.sendRedirect("/users");
    }
}

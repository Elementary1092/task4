package com.task4.demo.user.login;

import com.task4.demo.repositories.UserRepository;
import com.task4.demo.user.PoolOfUsers;
import com.task4.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@DependsOnDatabaseInitialization
public class LoginService {
    private UserRepository repository;

    private LoginStrategy loginStrategy;

    private PoolOfUsers users;

    @Autowired
    public LoginService(UserRepository repository, PoolOfUsers users) {
        this.repository = repository;
        this.users = users;
    }

    public void setLoginStrategy(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }

    public User login(UserLoginDetails user,
                      final HttpServletRequest request) throws RuntimeException {
        User userDetails = loginStrategy.setLoginEntity(user).login(repository);
        if (!userDetails.isBlocked()) {
            request.getSession().setAttribute("user_id", userDetails.getId());
            users.add(userDetails.getId(), request.getSession());
            return userDetails;
        } else {
            throw new RuntimeException("user is blocked");
        }
    }
}

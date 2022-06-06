package com.task4.demo.user.login;

import com.task4.demo.repositories.UserRepository;
import com.task4.demo.user.PoolOfSessionsAndUsers;
import com.task4.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;

@Service
@DependsOnDatabaseInitialization
public class LoginService {
    private UserRepository repository;

    private LoginStrategy loginStrategy;

    private PoolOfSessionsAndUsers usersAndSessions;

    @Autowired
    public LoginService(UserRepository repository, PoolOfSessionsAndUsers usersAndSessions) {
        this.repository = repository;
        this.usersAndSessions = usersAndSessions;
    }

    public void setLoginStrategy(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }

    public User login(UserLoginDetails user, HttpSession session) throws RuntimeException {
        User userDetails = loginStrategy.setLoginEntity(user).login(repository);
        userDetails.setIsOnline(true);
        if (!userDetails.isBlocked()) {
            user.setAuthorized();
            usersAndSessions.add(userDetails.getId(), session.getId());
            session.setAttribute("user_id", userDetails.getId());
            return userDetails;
        } else {
            throw new RuntimeException("user is blocked");
        }
    }
}

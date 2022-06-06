package com.task4.demo.user;

import com.task4.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
@DependsOnDatabaseInitialization
public class UserService {
    private final UserRepository repository;

    private PoolOfSessionsAndUsers usersAndSessions;

    @Autowired
    public UserService(UserRepository repository,
                       PoolOfSessionsAndUsers usersAndSessions) {
        this.repository = repository;
        this.usersAndSessions = usersAndSessions;
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public void deleteUsers(List<User> users, final SessionRegistry sessions) {
        users.stream().filter(user -> user.isChecked()).toList();
        for (User user : users) {
            deleteById(user.getId());
            usersAndSessions.expireUserSession(user.getId(), sessions);
        }
    }

    public void blockById(User user) {
        user.setBlocked(true);
        repository.save(user);
    }

    public void blockUsers(List<User> users, SessionRegistry sessions) {
        users.stream().filter(user -> user.isChecked()).toList();
        for (User user : users) {
            blockById(user);
            usersAndSessions.expireUserSession(user.getId(), sessions);
        }
    }

    public void unblock(User user) {
        user.setBlocked(false);
        repository.save(user);
    }

    public void unblockUsers(List<User> users) {
        users.stream().filter(user -> user.isChecked()).toList();
        for (User user : users) {
            unblock(user);
        }
    }

    public List<User> getAllUsers() {
        return setUsersOnlineParameter(repository.findAll());
    }

    public void logout(UUID userId, HttpSession session) {
        usersAndSessions.expireUserSession(userId, session);
    }

    private List<User> setUsersOnlineParameter(List<User> users) {
        for (User user : users) {
            if (usersAndSessions.existsUserSession(user.getId())) {
                user.setIsOnline(true);
            }
        }

        return users;
    }
}

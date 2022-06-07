package com.task4.demo.user;

import com.task4.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@DependsOnDatabaseInitialization
public class UserService {
    private final UserRepository repository;

    private PoolOfUsers users;

    @Autowired
    public UserService(UserRepository repository,
                       PoolOfUsers users) {
        this.repository = repository;
        this.users = users;
    }

    public boolean userExists(final HttpServletRequest request) {
        UUID userId = (UUID) request.getSession().getAttribute("user_id");

        return users.existsUserSession(userId);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
        users.expireUserSession(id);
    }

    public void deleteUsers(List<UUID> userIds) {
        for (UUID userId : userIds) {
            deleteById(userId);
        }
    }

    public void blockById(UUID id) {
        Optional<User> mayBeUser = repository.findById(id);
        if (mayBeUser.isPresent()) {
            User user = mayBeUser.get().setBlocked(true);
            repository.save(user);
            users.expireUserSession(user.getId());
        }
    }

    public void blockUsers(List<UUID> userIds) {
        for (UUID userId : userIds) {
            blockById(userId);
        }
    }

    public void unblockById(UUID id) {
        Optional<User> mayBeUser = repository.findById(id);
        if (mayBeUser.isPresent()) {
            User user = mayBeUser.get().setBlocked(false);
            repository.save(user);
        }
    }

    public void unblockUsers(List<UUID> userIds) {
        for (UUID userId : userIds) {
            unblockById(userId);
        }
    }

    public List<User> getAllUsers() {
        return setUsersOnlineParameter(repository.findAll());
    }

    public void logout(final HttpServletRequest request,
                       final HttpServletResponse response) {
        UUID userId = (UUID) request.getSession().getAttribute("user_id");
        users.expireUserSession(userId);
        request.getSession().invalidate();
    }

    private List<User> setUsersOnlineParameter(List<User> users) {
        for (User user : users) {
            if (this.users.existsUserSession(user.getId())) {
                user.setIsOnline(true);
            }
        }

        return users;
    }
}

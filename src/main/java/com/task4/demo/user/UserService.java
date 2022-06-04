package com.task4.demo.user;

import com.task4.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> findUserById(UUID id) {
        return repository.findById(id);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public void blockById(UUID id) {

    }

    public User findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User registerUser(User user) {
        if (repository.existsByEmail(user.getEmail())) {

        }

        user.setId(UUID.randomUUID());
        user.setRegDate(Date.valueOf(LocalDate.now()));
        user.setLastSeen(Date.valueOf(LocalDate.now()));
        user.setOnline(true);

        return repository.save(user);
    }

    public User updateUser(User user) {
        repository.deleteById(user.getId());

        return repository.save(user);
    }
}

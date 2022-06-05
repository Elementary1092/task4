package com.task4.demo.user;

import com.task4.demo.exceptions.UserAlreadyExistsException;
import com.task4.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
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

    public User updateUser(User user) {
        repository.deleteById(user.getId());

        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println();
        return repository.findByEmail(email);
    }
}

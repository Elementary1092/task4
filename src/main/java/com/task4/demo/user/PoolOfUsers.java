package com.task4.demo.user;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PoolOfUsers {
    private final Map<UUID, Boolean> usersAndContexts;

    public PoolOfUsers() {
        this.usersAndContexts = new HashMap<>();
    }

    synchronized public void add(UUID id) {
        usersAndContexts.put(id, true);
    }

    public boolean existsUserSession(UUID id) {
        return usersAndContexts.containsKey(id);
    }

    synchronized public void expireUser(UUID id) {
        usersAndContexts.remove(id);
    }

    synchronized public void expireUser(UUID id, HttpSession session) {
        session.invalidate();
        usersAndContexts.remove(id);
    }
}

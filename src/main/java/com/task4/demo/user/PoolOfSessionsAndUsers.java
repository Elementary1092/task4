package com.task4.demo.user;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PoolOfSessionsAndUsers {
    private final Map<UUID, String> usersAndSessionIds;

    public PoolOfSessionsAndUsers() {
        this.usersAndSessionIds = new HashMap<>();
    }

    synchronized public void add(UUID id, String sessionId) {
        usersAndSessionIds.put(id, sessionId);
    }

    public boolean existsUserSession(UUID id) {
        return usersAndSessionIds.containsKey(id);
    }

    synchronized public void expireUserSession(UUID id, HttpSession session) {
        session.invalidate();
        usersAndSessionIds.remove(id);
    }
}

package com.task4.demo.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, updatable = false)
    private Date regDate;

    @Column(nullable = false)
    private Date lastSeen;

    @Transient
    private boolean isOnline;

    public User() {}

    public User(String email) {
        this.email = email;
    }

    public User(@JsonProperty("id")UUID id,
                @JsonProperty("email")String email,
                @JsonProperty("registered_date")Date regDate,
                @JsonProperty("last_seen")Date lastSeen) {
        this.id = id;
        this.email = email;
        this.regDate = regDate;
        this.lastSeen = lastSeen;
        this.isOnline = false;
    }

    public User(UUID id, String email, String password,
                Date regDate, Date lastSeen, boolean isOnline) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.regDate = regDate;
        this.lastSeen = lastSeen;
        this.isOnline = isOnline;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public void fillIfRegDateIsNotSet() {
        if (regDate != null) {
            this.setId(UUID.randomUUID());
            this.setRegDate(Date.valueOf(LocalDate.now()));
            this.setLastSeen(Date.valueOf(LocalDate.now()));
            this.setOnline(true);
        }
    }
}

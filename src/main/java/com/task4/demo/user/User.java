package com.task4.demo.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity(name = "user")
@Table(name = "users")
public class User implements UserDetails {
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

    @Transient
    private boolean checked;

    @Column(nullable = false)
    private boolean blocked;

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

    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isOnline;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isOnline;
    }

    @Override
    public boolean isEnabled() {
        return isOnline;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getRegDate() {
        return regDate;
    }

    public User setRegDate(Date regDate) {
        this.regDate = regDate;
        return this;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public User setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
        return this;
    }

    public boolean getIsOnline() {
        return isOnline;
    }

    public User setIsOnline(boolean online) {
        isOnline = online;
        return this;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public User setBlocked(boolean blocked) {
        this.blocked = blocked;
        return this;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @PrePersist
    public void fillIfIdIsNotSet() {
        if (id == null) {
            this.setId(UUID.randomUUID())
                    .setRegDate(Date.valueOf(LocalDate.now()))
                    .setLastSeen(Date.valueOf(LocalDate.now()))
                    .setIsOnline(true);
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", regDate=").append(regDate);
        sb.append(", lastSeen=").append(lastSeen);
        sb.append(", isOnline=").append(isOnline);
        sb.append('}');
        return sb.toString();
    }
}

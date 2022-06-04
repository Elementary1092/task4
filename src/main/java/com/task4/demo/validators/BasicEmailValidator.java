package com.task4.demo.validators;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class BasicEmailValidator implements EmailValidator {
    private final Pattern pattern = Pattern.compile(
            "^[a-zA-Z0-9_-]+(?:\\\\.[a-zA-Z0-9_!-]+)*@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)*$"
    );

    private String email;

    public BasicEmailValidator() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public void validate() {
        if (!pattern.matcher(email).matches()) {

        }
    }
}

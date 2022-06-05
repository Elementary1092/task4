package com.task4.demo.user.signup;

public interface SignUpEntity {
    String getUsername();
    String getPassword();
    String getConfirmationPassword();

    void setUsername(String username);

    void setPassword(String password);

    void setConfirmationPassword(String confirmationPassword);
}

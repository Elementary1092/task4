package com.task4.demo.user.signup;

public class StandardSignUpEntity implements SignUpEntity {
    private String username;

    private String password;

    private String confirmationPassword;

    public StandardSignUpEntity() {}

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getConfirmationPassword() {
        return confirmationPassword;
    }
}

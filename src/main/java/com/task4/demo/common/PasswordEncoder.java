package com.task4.demo.common;

import com.task4.demo.validators.BasicPasswordValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder implements Encoder {
    //It is not possible to annotate this class as Bean because it is NOT thread-safe
    private final BCryptPasswordEncoder encoder;

    public PasswordEncoder() {
        this.encoder = new BCryptPasswordEncoder();
    }

    /*
    This method is used to encrypt passed parameter using bcrypt algorithm.
    Returns encrypted version of a passed string.
     */
    @Override
    public String encode(String toBeEncrypted) {
        return encoder.encode(toBeEncrypted);
    }

    /*
    This method checks if encrypted version of a candidate string (first parameter)
    is the same as a desired encrypted string.
     */
    @Override
    public boolean matches(String candidate, String realEncoded) {
        return encoder.matches(candidate, realEncoded);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return this.encode(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return this.matches(rawPassword.toString(), encodedPassword);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return Encoder.super.upgradeEncoding(encodedPassword);
    }
}

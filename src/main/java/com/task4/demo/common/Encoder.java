package com.task4.demo.common;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface Encoder extends PasswordEncoder {
    String encode(String toBeEncrypted);
    boolean matches(String candidate, String realEncrypted);
}

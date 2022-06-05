package com.task4.demo.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import java.util.ArrayList;
import java.util.List;

public class EmailValidatorTests {
    private EmailValidator validator;

    public EmailValidatorTests() {
        this.validator = new BasicEmailValidator();
    }

    @Test
    void contextLoads() {
        List<String> emails = new ArrayList<>(10);

        emails.add("sardorjuraboev1092@gmail.com");
        emails.add("s.j@gmail.com");

        for (String email : emails) {
            try {
                validator.validate(email);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

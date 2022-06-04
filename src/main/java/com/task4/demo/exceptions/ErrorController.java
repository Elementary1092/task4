package com.task4.demo.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping(path = "/error")
public class ErrorController {
    @GetMapping
    public void displayError() {

    }
}

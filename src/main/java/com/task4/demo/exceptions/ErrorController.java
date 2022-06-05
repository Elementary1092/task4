package com.task4.demo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequestMapping(path = "/error")
public class ErrorController {
    @GetMapping
    public ModelAndView displayError(HttpStatus status, WebRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("error_code", (status==null)?500:status.value());
        model.put("request", request.getContextPath());

        return new ModelAndView("error", model);
    }
}

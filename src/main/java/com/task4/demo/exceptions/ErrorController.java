package com.task4.demo.exceptions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/error")
public class ErrorController {
    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

        ModelAndView errorPage = new ModelAndView("error");
        StringBuffer errorMsg = new StringBuffer().append("HTTP Error Code: ");
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMsg.append("400. Bad Request");
                break;
            }
            case 401: {
                errorMsg.append("401. Unauthorized");
                break;
            }
            case 404: {
                errorMsg.append("404. Resource not found");
                break;
            }
            case 500: {
                errorMsg.append("500. Internal Server Error");
                break;
            }
            default: {
                errorMsg.append(httpErrorCode + ". Unknown error");
            }
        }

        errorPage.addObject("error", errorMsg.toString());

        return errorPage;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}

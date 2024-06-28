package gr.aueb.cf.employee.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(value = { HttpClientErrorException.NotFound.class })
    public String handleBadRequest(Model model, HttpClientErrorException.NotFound ex) {
        model.addAttribute("err", ex);
        return "404";
    }

    @ExceptionHandler(value = { HttpClientErrorException.Forbidden.class })
    public String handleException(Model model, HttpClientErrorException.Forbidden e) {
        model.addAttribute("err", e);
        return "403";
    }
}

package com.max.springmvc.exception;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SpringMVCTestHandlerException {

    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView HandleArithmeticException(Exception ex){

        System.out.println("----->Exception:" + ex);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception",ex);
        return modelAndView;
    }
}

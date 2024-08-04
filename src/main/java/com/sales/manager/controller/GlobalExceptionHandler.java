package com.sales.manager.controller;

import com.sales.manager.exception.ErrorNode;
import com.sales.manager.exception.ProductNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public final ErrorNode handleProductNotFoundException(Exception ex, WebRequest request) {
        return new ErrorNode(NOT_FOUND.toString(), ex.getMessage());
    }

//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(UNAUTHORIZED)
//    @ResponseBody
//    public final ErrorNode handleAccessDeniedException(Exception ex, WebRequest request) {
//        return new ErrorNode(UNAUTHORIZED.toString(), ex.getMessage());
//    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public final ErrorNode handleUnknownException(Exception ex) {
        System.out.println(ex);
        return new ErrorNode(INTERNAL_SERVER_ERROR.toString(), ex.getMessage());
    }
}

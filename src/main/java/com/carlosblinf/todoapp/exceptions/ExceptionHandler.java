package com.carlosblinf.todoapp.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {TodoExceptions.class})
    protected ResponseEntity<Object> handleConflict(TodoExceptions ex, WebRequest request) {
        Map<String, Object> bodyOfResponse = new HashMap<>();
        bodyOfResponse.put("message", ex.getMessage());

        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), ex.getHttpStatus(), request);
    }
}

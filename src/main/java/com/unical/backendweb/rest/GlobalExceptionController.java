package com.unical.backendweb.rest;

import com.unical.backendweb.exceptions.Errors;
import com.unical.backendweb.exceptions.user.UserNotAuthorizedException;
import com.unical.backendweb.exceptions.Error;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@ControllerAdvice
public class GlobalExceptionController {


    @ExceptionHandler(UserNotAuthorizedException.class)
    public ResponseEntity<Object> unauthorizedUser(UserNotAuthorizedException e) {
        return new ResponseEntity<>("Utente non autenticato", HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleGenericError(HttpClientErrorException e) {
        String message = null;

        HttpStatus status = HttpStatus.valueOf(e.getStatusCode().value());
        switch (status){
            case UNAUTHORIZED -> message = "Utente non autenticato";
            case FORBIDDEN -> message = "Utente non autenticato";
            case NOT_FOUND -> message = "Risorsa non trovata";
            default -> message = "bo status code non gestito mi disp";
        }

        return ResponseEntity
                .status(status)
                .cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS))
                .body(new Error(message));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleGenericError(ResponseStatusException e) {
        String message = null;

        HttpStatus status = HttpStatus.valueOf(e.getStatusCode().value());
        switch (status){
            case UNAUTHORIZED -> message = "Utente non autenticato";
            case FORBIDDEN -> message = "Utente non autenticato";
            case NOT_FOUND -> message = "Risorsa non trovata";
            default -> message = "bo status code non gestito mi disp";
        }

        return ResponseEntity
                .status(status)
                .cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS))
                .body(new Error(message));
    }

    // was RestClientException.class, HttpMessageNotReadableException.class
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericError(Exception e) {
        return new ResponseEntity<>(new Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ArrayList<String> errors = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName;
            try {
                fieldName = ((FieldError) error).getField();

            } catch (ClassCastException ex) {
                fieldName = error.getObjectName();
            }

            String message = error.getDefaultMessage();
            errors.add(fieldName + " " + message);
        });

        return new ResponseEntity<>(new Errors(errors), HttpStatus.BAD_REQUEST);
    }
}

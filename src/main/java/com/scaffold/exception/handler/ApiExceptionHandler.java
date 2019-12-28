package com.scaffold.exception.handler;

import com.scaffold.exception.MissingRequiredFieldException;
import com.scaffold.model.ErrorResponse;
import com.scaffold.model.Error;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Log4j2
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        return handleBadRequest(ex, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        ErrorResponse errors = new ErrorResponse();
        errors.addAll(ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new Error(BAD_REQUEST.value(), fieldError.getDefaultMessage()
                        + " : " + fieldError.getField()))
                .collect(Collectors.toList()));

        return handleExceptionInternal(ex, errors,
                new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler({MissingRequiredFieldException.class, MissingRequestHeaderException.class})
    public ResponseEntity<Object> handleBadRequestException(Exception ex, WebRequest request) {
        return handleBadRequest(ex, request);
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

        ErrorResponse errors = new ErrorResponse();
        errors.add(new Error(INTERNAL_SERVER_ERROR.value(), "Something went wrong while processing your request."));

        log.error("Something went wrong while processing your request :", ex);

        HttpHeaders headers = new HttpHeaders();
        return handleExceptionInternal(ex, errors,
                headers, INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request) {
        ErrorResponse errors = new ErrorResponse();
        errors.add(new Error(BAD_REQUEST.value(), ex.getLocalizedMessage()));


        return handleExceptionInternal(ex, errors,
                new HttpHeaders(), BAD_REQUEST, request);
    }
}

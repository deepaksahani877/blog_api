package com.app.blog_api.exception;

import com.app.blog_api.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidResourceIdException.class)
    ResponseEntity<ErrorResponse> invalidPostIdExceptionHandler(InvalidResourceIdException exception, WebRequest webRequest) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(), webRequest.getDescription(false), String.valueOf(HttpStatus.BAD_REQUEST), new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ErrorResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException resourceNotFoundException, WebRequest webRequest) {
        ErrorResponse response = new ErrorResponse(resourceNotFoundException.getMessage(), webRequest.getDescription(false), String.valueOf(HttpStatus.NOT_FOUND), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUsernameOrPasswordException.class)
    ResponseEntity<ErrorResponse> invalidUsernameOrPasswordExceptionHandler(InvalidUsernameOrPasswordException invalidUsernameOrPasswordException, WebRequest webRequest) {
        ErrorResponse response = new ErrorResponse(invalidUsernameOrPasswordException.getMessage(), webRequest.getDescription(false), String.valueOf(HttpStatus.UNAUTHORIZED), new Date());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseBody
    public ResponseEntity<HashMap<String, String>> handleAuthenticationException(Exception ex) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Invalid username or password");
        map.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
    }

}
